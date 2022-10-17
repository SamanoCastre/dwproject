package com.dw.dwproject.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.dw.dwproject.api.Representation;
import com.dw.dwproject.api.pojo.UserPojo;
import com.dw.dwproject.core.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.eclipse.jetty.http.HttpStatus;
import org.jooq.impl.DefaultDSLContext;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private final UserService userService;

	/**
	 * @param userService
	 */
	public UserResource(DefaultDSLContext config) {
		this.userService = new UserService(config);
	}
	
	@GET
	@Timed
	@Path("users")
	public Representation<List<UserPojo>> getUsers() throws JsonProcessingException {	
	  return new Representation<List<UserPojo>>(HttpStatus.OK_200, userService.getUsers());
	}
	
	@GET
	@Path("user/{id}")
	public Representation<UserPojo> getUser(@PathParam("id") final int id) {
	  return new Representation<UserPojo>(HttpStatus.OK_200, userService.getUser(id));
	}
	
	@POST
	@Path("user")
	public Representation<Integer> createUser(@NotNull @Valid final UserPojo user) {
	  return new Representation<Integer>(HttpStatus.OK_200, userService.createUser(user));
	}
	
	@PUT
	@Path("user/{id}")
	public Representation<Integer> editUser(@NotNull @Valid final UserPojo user,
	  @PathParam("id") final int id) {
		user.setId(id);
	  return new Representation<Integer>(HttpStatus.OK_200, userService.editUser(user));
	}
	
	@DELETE
	@Path("user/{id}")
	public Representation<UserPojo> deleteUser(@PathParam("id") final int id) {
	  return new Representation<UserPojo>(HttpStatus.OK_200, userService.deleteUser(id));
	}
}
