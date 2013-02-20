package com.igraliste.api;

import javax.ejb.Local;

@Local
public interface HomeBean {
	
	void sendMessageToConsumer();
	
}
