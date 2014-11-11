package org.ospokemon.server.rest.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ospokemon.JPokemonException;
import org.ospokemon.StatusCondition;

@Path("statuscondition")
public class StatusConditionService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StatusCondition> getAll() {
		return StatusCondition.manager.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public StatusCondition getStatusCondition(@PathParam("id") String id) {
		StatusCondition statusCondition = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			statusCondition = StatusCondition.manager.get(decodedId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusCondition;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStatusCondition(StatusCondition statusCondition) {
		try {
			StatusCondition.manager.register(statusCondition);
			String encodedId = URLEncoder.encode(statusCondition.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStatusCondition(StatusCondition statusCondition) {
		try {
			StatusCondition.manager.update(statusCondition);
			return Response.ok().build();
		} catch (JPokemonException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteStatusCondition(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			StatusCondition.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
