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

import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.naming.Reference;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

/**
 * IgralisteConnectionFactoryImpl
 *
 * @version $Revision: $
 */
public class IgralisteConnectionFactoryImpl implements IgralisteConnectionFactory
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1057055706148113318L;

/** The logger */
   private static Logger log = Logger.getLogger("IgralisteConnectionFactoryImpl");

   /** Reference */
   private Reference reference;

   /** ManagedConnectionFactory */
   private IgralisteManagedConnectionFactory mcf;

   /** ConnectionManager */
   private ConnectionManager connectionManager;

   /**
    * Default constructor
    */
   public IgralisteConnectionFactoryImpl()
   {

   }

   /**
    * Default constructor
    * @param mcf ManagedConnectionFactory
    * @param cxManager ConnectionManager
    */
   public IgralisteConnectionFactoryImpl(IgralisteManagedConnectionFactory mcf, ConnectionManager cxManager)
   {
      this.mcf = mcf;
      this.connectionManager = cxManager;
   }

   /** 
    * Get connection from factory
    *
    * @return IgralisteConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   @Override
   public IgralisteConnection getConnection() throws ResourceException
   {
      log.finest("getConnection()");
      return (IgralisteConnection)connectionManager.allocateConnection(mcf, null);
   }

   /**
    * Get the Reference instance.
    *
    * @return Reference instance
    * @exception NamingException Thrown if a reference can't be obtained
    */
   @Override
   public Reference getReference() throws NamingException
   {
      log.finest("getReference()");
      return reference;
   }

   /**
    * Set the Reference instance.
    *
    * @param reference A Reference instance
    */
   @Override
   public void setReference(Reference reference)
   {
      log.finest("setReference()");
      this.reference = reference;
   }


}
