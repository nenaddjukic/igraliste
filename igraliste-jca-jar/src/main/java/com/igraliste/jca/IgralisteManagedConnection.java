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

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;

import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igraliste.jca.camel.CamelEngine;

/**
 * IgralisteManagedConnection
 *
 * @version $Revision: $
 */
public class IgralisteManagedConnection implements ManagedConnection
{

   /** The logger */
   private static Logger log = LoggerFactory.getLogger(IgralisteManagedConnection.class);

   /** The logwriter */
   private PrintWriter logwriter;

   /** ManagedConnectionFactory */
   private IgralisteManagedConnectionFactory mcf;

   /** Listeners */
   private List<ConnectionEventListener> listeners;

   /** Connection */
   private IgralisteConnectionImpl connection;
   
   /** Camel */
   private CamelEngine engine;

   /**
    * Default constructor
    * @param mcf mcf
    */
   public IgralisteManagedConnection(IgralisteManagedConnectionFactory mcf)
   {
      this.mcf = mcf;
      this.logwriter = null;
      this.listeners = Collections.synchronizedList(new ArrayList<ConnectionEventListener>(1));
      this.connection = null;
      this.engine = new CamelEngine();
   }

   /**
    * Creates a new connection handle for the underlying physical connection 
    * represented by the ManagedConnection instance. 
    *
    * @param subject Security context as JAAS subject
    * @param cxRequestInfo ConnectionRequestInfo instance
    * @return generic Object instance representing the connection handle. 
    * @throws ResourceException generic exception if operation fails
    */
   public Object getConnection(Subject subject,
      ConnectionRequestInfo cxRequestInfo) throws ResourceException
   {
      log.debug("getConnection()");
      connection = new IgralisteConnectionImpl(this, mcf);
      return connection;
   }

   /**
    * Used by the container to change the association of an 
    * application-level connection handle with a ManagedConneciton instance.
    *
    * @param connection Application-level connection handle
    * @throws ResourceException generic exception if operation fails
    */
   public void associateConnection(Object connection) throws ResourceException
   {
      log.debug("associateConnection()");

      if (connection == null)
         throw new ResourceException("Null connection handle");

      if (!(connection instanceof IgralisteConnectionImpl))
         throw new ResourceException("Wrong connection handle");

      this.connection = (IgralisteConnectionImpl)connection;
   }

   /**
    * Application server calls this method to force any cleanup on the ManagedConnection instance.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void cleanup() throws ResourceException
   {
      log.debug("cleanup()");
   }

   /**
    * Destroys the physical connection to the underlying resource manager.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void destroy() throws ResourceException
   {
      log.debug("destroy()");
   }

   /**
    * Adds a connection event listener to the ManagedConnection instance.
    *
    * @param listener A new ConnectionEventListener to be registered
    */
   public void addConnectionEventListener(ConnectionEventListener listener)
   {
      log.debug("addConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.add(listener);
   }

   /**
    * Removes an already registered connection event listener from the ManagedConnection instance.
    *
    * @param listener already registered connection event listener to be removed
    */
   public void removeConnectionEventListener(ConnectionEventListener listener)
   {
      log.debug("removeConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.remove(listener);
   }

   /**
    * Close handle
    *
    * @param handle The handle
    */
   void closeHandle(IgralisteConnection handle)
   {
      ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
      event.setConnectionHandle(handle);
      for (ConnectionEventListener cel : listeners)
      {
         cel.connectionClosed(event);
      }

   }

   /**
    * Gets the log writer for this ManagedConnection instance.
    *
    * @return Character ourput stream associated with this Managed-Connection instance
    * @throws ResourceException generic exception if operation fails
    */
   public PrintWriter getLogWriter() throws ResourceException
   {
      log.debug("getLogWriter()");
      return logwriter;
   }

   /**
    * Sets the log writer for this ManagedConnection instance.
    *
    * @param out Character Output stream to be associated
    * @throws ResourceException  generic exception if operation fails
    */
   public void setLogWriter(PrintWriter out) throws ResourceException
   {
      log.debug("setLogWriter()");
      logwriter = out;
   }

   /**
    * Returns an <code>javax.resource.spi.LocalTransaction</code> instance.
    *
    * @return LocalTransaction instance
    * @throws ResourceException generic exception if operation fails
    */
   public LocalTransaction getLocalTransaction() throws ResourceException
   {
      throw new NotSupportedException("LocalTransaction not supported");
   }

   /**
    * Returns an <code>javax.transaction.xa.XAresource</code> instance. 
    *
    * @return XAResource instance
    * @throws ResourceException generic exception if operation fails
    */
   public XAResource getXAResource() throws ResourceException
   {
      throw new NotSupportedException("GetXAResource not supported not supported");
   }

   /**
    * Gets the metadata information for this connection's underlying EIS resource manager instance. 
    *
    * @return ManagedConnectionMetaData instance
    * @throws ResourceException generic exception if operation fails
    */
   public ManagedConnectionMetaData getMetaData() throws ResourceException
   {
      log.debug("getMetaData()");
      return new IgralisteManagedConnectionMetaData();
   }

   /**
    * Call me
    */
   void callMe()
   {
      log.debug("callMe() Managed Connection");
   }
   
   void transferFile(String fileName){
	   log.debug("About to transfer file with name {}",fileName);
	   engine.startFileTransfering(fileName);
   }

}
