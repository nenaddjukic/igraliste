package com.igraliste.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;
import com.igraliste.api.HomeBean;
import com.igraliste.ejb.util.cache.CacheCreator;

@Singleton
@Startup
@Local(HomeBean.class)
public class HomeBeanImpl implements HomeBean {
	Logger log = LoggerFactory.getLogger(HomeBeanImpl.class);

	@Inject
	private CacheCreator creator;

	@PostConstruct
	public void initializeHomeBean() {
		log.debug("Home is initialized");
		try {
			CacheEntity entity = new CacheEntity("1", "vrednost");
			creator.put("1", entity);
		} catch (Exception e) {
			log.error("Error ocured: {}",e);
		}
	}

	@PreDestroy
	public void predestroyHomeBean() {
		log.debug("Home is in pre destroy");
		CacheEntity entity = creator.get("1");
		log.debug("Value is: " + entity.getValue());
	}

	@Override
	public void sendMessageToConsumer() {
		// TODO Auto-generated method stub

	}
}
