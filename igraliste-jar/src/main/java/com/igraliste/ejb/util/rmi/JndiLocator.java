package com.igraliste.ejb.util.rmi;

import java.io.Serializable;
import java.util.Hashtable;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class JndiLocator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7499242431521687914L;
	Logger log = LoggerFactory.getLogger(JndiLocator.class);

	public <T> T lookup(Class<T> clazz, String jndiName) {
		Object bean = lookup(jndiName);
		return clazz.cast(PortableRemoteObject.narrow(bean, clazz));
	}

	public Object lookup(String jndiName) {
		Context context = null;
		log.debug("Lookup for jndi: {}",jndiName);
		try {
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        context = new InitialContext(jndiProperties);
			return context.lookup(jndiName);
		} catch (NamingException ex) {
			throw new IllegalStateException("NamingException in JNDI locator");
		} finally {
			try {
				context.close();
			} catch (NamingException ex) {
				throw new IllegalStateException("NamingException in JNDI locator finally");
			}
		}
	}
}
