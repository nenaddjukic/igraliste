package com.igraliste.jca.camel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelEngine {

	Logger log = LoggerFactory.getLogger(CamelEngine.class);

	public CamelEngine() {

	}

	public void startFileTransfering(final String fileName) {
		log.debug("Received work to transfer: {}", fileName);
		final CamelContext context = new DefaultCamelContext();
		final ExecutorService executor = Executors.newFixedThreadPool(20);
		try {
			InitialContext ic = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) ic
					.lookup("/ConnectionFactory");
			context.addComponent("jms",
					JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("ftp://127.0.0.1/?username=nenad&password=nenad&noop=true&fileName="
									+ fileName).process(new MyCamelProcessor())
							.choice()
							.when(header("CamelFileName").endsWith(".xml"))
							.to("jms:XmlMessages").otherwise().multicast()
							.parallelProcessing().executorService(executor)
							.to("jms:IncomingMessages");
				}
			});
			context.start();
			Thread.sleep(2000);
		} catch (NamingException e) {
			log.error("NamingException in creating camel rout: {}", e);
		}catch (Exception e) {
			log.error("Exception in creating camel rout: {}", e);
		}finally {
			try {
				context.stop();
			} catch (Exception e) {
				log.error("ERROR IN stopping camel rout: {}", e);
			}
		}
	}

}
