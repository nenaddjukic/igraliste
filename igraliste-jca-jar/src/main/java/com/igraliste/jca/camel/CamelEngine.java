package com.igraliste.jca.camel;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelEngine {

	Logger log = LoggerFactory.getLogger(CamelEngine.class);

	public CamelEngine() {

	}

	public void startFileTransfering(String fileName) {
		log.debug("Received work to transfer: {}", fileName);
		try {
			CamelContext context = new DefaultCamelContext();
			InitialContext ic = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) ic
					.lookup("/ConnectionFactory");
			context.addComponent("jms",
					JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from(
							"ftp://byethost22.com/ftp"
									+ "?username=b22_12664855&password=sheckey7").to(
							"jms:IncomingMessages");
				}
			});
			context.start();
		} catch (Exception e) {
			log.error("ERROR IN creating camel rout: {}", e);
		}
	}

}