package com.igraliste.ejb.util.rmi;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

@ApplicationScoped
public class JndiLocator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7499242431521687914L;

	public <T> T lookup(Class<T> clazz, String jndiName) {
		Object bean = lookup(jndiName);
		return clazz.cast(PortableRemoteObject.narrow(bean, clazz));
	}

	public Object lookup(String jndiName) {
		Context context = null;
		try {
			context = new InitialContext();
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
