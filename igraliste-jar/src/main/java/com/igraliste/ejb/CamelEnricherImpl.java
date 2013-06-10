package com.igraliste.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CamelEnricher;

@Stateless
@Remote(CamelEnricher.class)
public class CamelEnricherImpl implements CamelEnricher{

	Logger log = LoggerFactory.getLogger(CamelEnricherImpl.class);
	@Override
	public String storeCamelMessage(String message) {
		log.debug("Storing Camel message to cache: {}",message);
		return message;		
	}

}
