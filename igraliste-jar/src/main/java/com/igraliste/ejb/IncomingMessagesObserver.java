package com.igraliste.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.hornetq.jms.client.HornetQTextMessage;
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
		HornetQTextMessage tm = (HornetQTextMessage) message;
		//String content = processByteMessage(tm);
		try {
			log.debug("Received message " + tm.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("There was an error in JMS handler: {}",e);
		}
	}
}
