package com.igraliste.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;
import com.igraliste.api.HomeBean;

@Singleton
@Startup
@Local(HomeBean.class)
public class HomeBeanImpl implements HomeBean{
	Logger log = LoggerFactory.getLogger(HomeBeanImpl.class);

	private Cache<String, CacheEntity> cache;

	@PostConstruct
	public void initializeHomeBean(){
		log.debug("Home is initialized");
		DefaultCacheManager m = new DefaultCacheManager();
		cache =  m.getCache();
		CacheEntity entity = new CacheEntity("1", "vrednost");
		cache.put("1", entity);
	}
	
	@PreDestroy
	public void predestroyHomeBean(){
		log.debug("Home is in pre destroy");
		CacheEntity entity = cache.get("1");
		log.debug("Value is: " + entity.getValue());
	}

	@Override
	public void sendMessageToConsumer() {
		// TODO Auto-generated method stub
		
	}
}
