/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.igraliste.jca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IgralisteConnectionImpl
 * 
 * @version $Revision: $
 */
public class IgralisteConnectionImpl implements IgralisteConnection {
	/** The logger */
	private Logger log = LoggerFactory
			.getLogger(IgralisteConnectionImpl.class);

	/** ManagedConnection */
	private IgralisteManagedConnection mc;

	/** ManagedConnectionFactory */
	private IgralisteManagedConnectionFactory mcf;

	/**
	 * Default constructor
	 * 
	 * @param mc
	 *            IgralisteManagedConnection
	 * @param mcf
	 *            IgralisteManagedConnectionFactory
	 */
	public IgralisteConnectionImpl(IgralisteManagedConnection mc,
			IgralisteManagedConnectionFactory mcf) {
		this.mc = mc;
		this.mcf = mcf;
	}

	/**
	 * Call me
	 */
	public void callMe() {
		log.debug("Call me has been reached");
		mc.callMe();
	}

	/**
	 * Close
	 */
	public void close() {
		log.debug("Closing connection Impl");
		mc.closeHandle(this);
	}

	@Override
	public void sendMessage(String message) {
		log.debug("Message received: "+message);
		mc.transferFile(message);
	}

}
