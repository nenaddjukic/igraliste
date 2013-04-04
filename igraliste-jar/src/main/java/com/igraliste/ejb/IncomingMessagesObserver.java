package com.igraliste.ejb;

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
		log.debug("I HAVE RECEIVED MESSAGE");
		HornetQBytesMessage tm = (HornetQBytesMessage) message;
		log.debug("Received message " + tm.toString());
	}

}
