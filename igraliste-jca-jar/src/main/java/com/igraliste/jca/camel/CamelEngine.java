package com.igraliste.jca.camel;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
		try {		
			InitialContext ic = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) ic
					.lookup("/ConnectionFactory");
			context.addComponent("jms",
					JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("ftp://127.0.0.1/?username=nenad&password=nenad&noop=true&fileName="+fileName)
							.process(new Processor() {
								public void process(Exchange exchange)
										throws Exception {
									log.debug("We just downloaded: "
											+ exchange.getIn().getHeader(
													"CamelFileName"));
								}
							}).to("jms:IncomingMessages");
				}
			});
			context.start();
			Thread.sleep(2000);
		} catch (Exception e) {
			log.error("ERROR IN creating camel rout: {}", e);
		} finally{
			try {
				context.stop();
			} catch (Exception e) {
				log.error("ERROR IN stopping camel rout: {}", e);
			}
		}
	}

}
