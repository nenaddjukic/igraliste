package com.igraliste.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;
import com.igraliste.api.CamelEnricher;
import com.igraliste.ejb.util.cache.CacheCreator;

@Stateless
@Remote(CamelEnricher.class)
public class CamelEnricherImpl implements CamelEnricher{

	Logger log = LoggerFactory.getLogger(CamelEnricherImpl.class);
	
	@Inject
	private CacheCreator creator;
	
	@Override
	public String storeCamelMessage(String message) {
		log.debug("Storing Camel message to cache: {}",message);
		CacheEntity entry = new CacheEntity("2", message);
		creator.put("2", entry);
		return "2";		
	}

}
