package com.igraliste.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.HomeBean;

@Singleton
@Startup
@Local(HomeBean.class)
public class HomeBeanImpl implements HomeBean{
	Logger log = LoggerFactory.getLogger(HomeBeanImpl.class);

	@PostConstruct
	public void initializeHomeBean(){
		log.debug("Home is initialized");
	}
	
	@PreDestroy
	public void predestroyHomeBean(){
		log.debug("Home is in pre destroy");
	}

	@Override
	public void sendMessageToConsumer() {
		// TODO Auto-generated method stub
		
	}
}
