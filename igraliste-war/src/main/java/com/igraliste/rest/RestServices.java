package com.igraliste.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/igraliste")
@RequestScoped
public class RestServices {
	
	Logger log = LoggerFactory.getLogger(RestServices.class);
	//http://localhost:8080/igraliste/rest/igraliste/sendid?id=5
	//will call getStatus
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/sendid")
	public Response getStatus(@QueryParam("id") final String serviceId)
	{
		String result = null;
		log.debug("Received a rest call with service id: {}",serviceId);
		try {
			result=serviceId;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.debug("Rest has finished, result:{}", result);
		final ResponseBuilder builder = Response.ok(result);
		return builder.build();
	}
	//http://localhost:8080/igraliste/rest/igraliste
	//will call initialize
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/")
	public Response initialize()
	{
		log.debug("Rest is initialized");
		final ResponseBuilder builder = Response.ok("OK");
		return builder.build();
	}
}
