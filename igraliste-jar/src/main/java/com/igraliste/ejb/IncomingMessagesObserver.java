package com.igraliste.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.hornetq.jms.client.HornetQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;
import com.igraliste.ejb.util.cache.CacheCreator;

@MessageDriven(name = "MessageMDBSample", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/IncomingMessages"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class IncomingMessagesObserver implements MessageListener {

	private Logger log = LoggerFactory
			.getLogger(IncomingMessagesObserver.class);
	@Inject
	private CacheCreator creator;

	public void onMessage(final Message message) {
		log.debug("IncomingMessagesObserver has received the message");		
		try {
			HornetQTextMessage tm = (HornetQTextMessage) message;
			final String result = tm.getText();
			log.debug("Received message " + result);
			final CacheEntity res = creator.get(result);
			log.debug("Result is: {}",res.getValue());
		} catch (JMSException e) {
			log.error("There was an error in JMS handler: {}",e);
		}
	}
}
