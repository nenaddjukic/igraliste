///*
// * JBoss, Home of Professional Open Source.
// * Copyright 2012, Red Hat Middleware LLC, and individual contributors
// * as indicated by the @author tags. See the copyright.txt file in the
// * distribution for a full listing of individual contributors.
// *
// * This is free software; you can redistribute it and/or modify it
// * under the terms of the GNU Lesser General Public License as
// * published by the Free Software Foundation; either version 2.1 of
// * the License, or (at your option) any later version.
// *
// * This software is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// * Lesser General Public License for more details.
// *
// * You should have received a copy of the GNU Lesser General Public
// * License along with this software; if not, write to the Free
// * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
// * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
// */
//package com.igraliste.rar;
//
//import com.igraliste.rar.IgralisteConnection;
//import com.igraliste.rar.IgralisteConnectionFactory;
//import com.igraliste.rar.IgralisteConnectionFactoryImpl;
//import com.igraliste.rar.IgralisteConnectionImpl;
//import com.igraliste.rar.IgralisteManagedConnection;
//import com.igraliste.rar.IgralisteManagedConnectionFactory;
//import com.igraliste.rar.IgralisteRA;
//import java.util.UUID;
//import java.util.logging.Logger;
//
//import javax.annotation.Resource;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import static org.junit.Assert.*;
//
///**
// * ConnectorTestCase
// * 
// * @version $Revision: $
// */
//@RunWith(Arquillian.class)
//public class ConnectorTestCase {
//	private static Logger log = Logger.getLogger("ConnectorTestCase");
//
//	private static String deploymentName = "ConnectorTestCase";
//
//	/**
//	 * Define the deployment
//	 * 
//	 * @return The deployment archive
//	 */
//	@Deployment
//	public static ResourceAdapterArchive createDeployment() {
//		log.fine("Start of the test");
//		System.out.println("LAL");
//		ResourceAdapterArchive raa = ShrinkWrap.create(
//				ResourceAdapterArchive.class, deploymentName + ".rar");
//		JavaArchive ja = ShrinkWrap.create(JavaArchive.class, UUID.randomUUID()
//				.toString() + ".jar");
//		ja.addClasses(IgralisteRA.class,
//				IgralisteManagedConnectionFactory.class,
//				IgralisteManagedConnection.class,
//				IgralisteConnectionFactory.class,
//				IgralisteConnectionFactoryImpl.class,
//				IgralisteConnection.class, IgralisteConnectionImpl.class);
//		raa.addAsLibrary(ja);
//		raa.addAsManifestResource("META-INF/ironjacamar.xml", "ironjacamar.xml");
//		System.out.println("LAL3");
//		return raa;
//	}
//
//	/** Resource */
//	@Resource(mappedName = "java:/eis/IgralisteConnectionFactory")
//	private IgralisteConnectionFactory connectionFactory1;
//
//	/**
//	 * Test getConnection
//	 * 
//	 * @exception Throwable
//	 *                Thrown if case of an error
//	 */
//	@Test
//	public void testGetConnection1() throws Throwable {
//		System.out.println("LAL4");
//		assertNotNull(connectionFactory1);
//		System.out.println("LAL5");
//		IgralisteConnection connection1 = connectionFactory1.getConnection();
//		System.out.println("LAL7");
//		assertNotNull(connection1);
//		connection1.close();
//	}
//
//}
