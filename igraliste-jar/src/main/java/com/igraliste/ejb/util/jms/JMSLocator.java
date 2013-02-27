package com.igraliste.ejb.util.jms;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.ejb.util.rmi.JndiLocator;

@Stateless
public class JMSLocator implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6547360544314970492L;
	private Logger log = LoggerFactory.getLogger(JMSLocator.class);
	@Inject
	private JndiLocator jndi;
	
	public void send(final String queueName,final Object objMessage){
		try{
		    ConnectionFactory cf = (ConnectionFactory) jndi.lookup("/ConnectionFactory");
		    Queue queue = (Queue) jndi.lookup("queue/"+queueName);
		    Connection con = cf.createConnection("guest", "guestp");
		    Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		    MessageProducer producer = session.createProducer(queue);
		    ObjectMessage message = session.createObjectMessage((Serializable) objMessage);
		    producer.send(message);
		    log.debug("Sent TextMessage to the Queue: " + message.getJMSMessageID());
		    session.close();
		}catch(JMSException e){
			log.error("JMSException in send message to queue: {}",e);
		}catch (Exception e) {
			log.error("Exception in send message to queue: {}",e);
		}
	}
}
