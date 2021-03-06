package com.igraliste.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.api.CacheEntity;
import com.igraliste.api.HomeBean;
import com.igraliste.ejb.util.cache.CacheCreator;
import com.igraliste.ejb.util.jms.JMSLocator;
import com.igraliste.ejb.util.rmi.JndiLocator;
import com.igraliste.jca.IgralisteConnection;
import com.igraliste.jca.IgralisteConnectionFactory;

@Singleton
@Startup
@Local(HomeBean.class)
public class HomeBeanImpl implements HomeBean {
	Logger log = LoggerFactory.getLogger(HomeBeanImpl.class);

	@Inject
	private CacheCreator creator;
	@Inject
	private JndiLocator jndi;
	@Inject
	private JMSLocator jms;

	private IgralisteConnectionFactory connectionFactory1;

	@PostConstruct
	public void initializeHomeBean() {
		log.debug("Home is initialized");
		try {
			CacheEntity entity = new CacheEntity("1", "vrednost");
			creator.put("1", entity);
			jms.send("test", entity);
			connectionFactory1 = (IgralisteConnectionFactory) jndi.lookup("java:/eis/IgralisteConnectionFactory");
			IgralisteConnection connection = connectionFactory1.getConnection();
			connection.sendMessage("Test.txt");
		} catch (Exception e) {
			log.error("Error ocured: {}",e);
		}
	}
	@Override
	public String storeCamelMessage(String message){
		log.debug("Storing Camel message to cache: {}",message);
		return message;
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
