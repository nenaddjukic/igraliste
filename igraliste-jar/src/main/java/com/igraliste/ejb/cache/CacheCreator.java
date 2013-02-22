package com.igraliste.ejb.cache;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.infinispan.Cache;
import org.infinispan.config.Configuration;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;

@SuppressWarnings("deprecation")
@ApplicationScoped
public class CacheCreator {
	
	private Logger log = LoggerFactory.getLogger(CacheCreator.class);
	private DefaultCacheManager m;
	private Cache<String, CacheEntity> cache;
	@PostConstruct
	public void generateCache(){
		m = new DefaultCacheManager();
		Configuration c = new Configuration();
		m.defineConfiguration("defaultCache", c);
		cache =  m.getCache("defaultCache");
	}
	public void put(String id, CacheEntity entity) {
		log.debug("Storing CacheEntry to cache: {}; with value: {}",id,entity);
		cache.put(id, entity);
	}
	public CacheEntity get(String id) {
		log.debug("Get value from cache for id:",id);
		return cache.get(id);
	}
}
