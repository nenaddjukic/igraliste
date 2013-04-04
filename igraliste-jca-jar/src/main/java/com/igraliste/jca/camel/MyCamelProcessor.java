package com.igraliste.jca.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCamelProcessor implements Processor{
	Logger log = LoggerFactory.getLogger(MyCamelProcessor.class);

	@Override
	public void process(final Exchange exchange) throws Exception {
		log.debug("We just downloaded: {}",exchange.getIn().getHeader("CamelFileName"));	
	}

}
