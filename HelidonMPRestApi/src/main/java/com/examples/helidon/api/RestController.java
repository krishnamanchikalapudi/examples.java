package com.examples.helidon.api;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.examples.helidon.model.Person;

/**
 * @author krishna manchikalapudi
 */

@Path("/v1")
@RequestScoped
public class RestController {

	public RestController() {
		// TODO Auto-generated constructor stub
	}

	@Path("/")
	@GET
	public String getDefaultMessage() {
		return "Welcome to Helidon MicroProfile example";
	}

	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMessage(@PathParam("name") String name) {
		Person p = new Person("123", "Krishna");
		JsonObject jsonObj = (JsonObject) p;
		return Response.status(Response.Status.OK).entity(jsonObj).build();
	}

}