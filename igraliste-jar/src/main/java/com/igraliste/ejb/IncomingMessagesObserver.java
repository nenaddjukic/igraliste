package com.igraliste.ejb;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;

import javax.ejb.ActivationConfigProperty;

import org.hornetq.jms.client.HornetQBytesMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "MessageMDBSample", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/IncomingMessages"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class IncomingMessagesObserver implements MessageListener {

	private Logger log = LoggerFactory
			.getLogger(IncomingMessagesObserver.class);

	public void onMessage(Message message) {
		log.debug("IncomingMessagesObserver has received the message");
		HornetQBytesMessage tm = (HornetQBytesMessage) message;
		String content = processByteMessage(tm);
		log.debug("Received message " + content);
	}

	private String processByteMessage(HornetQBytesMessage tm) {
		BytesMessage byteMessage = (BytesMessage) tm;
		String response = "";
		StringBuffer buffer = new StringBuffer();
		try {
			for (int i = 0; i < (int) byteMessage.getBodyLength(); i++) {
				buffer.append((char) byteMessage.readByte());
			}
			response = buffer.toString().trim();
			log.debug("Message content is: " + response);
		} catch (JMSException e) {
			log.error("Error has accoured in processing byte message: {}",e);
		}
		return response;
	}
}
