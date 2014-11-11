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

import org.ospokemon.ContestCategory;
import org.ospokemon.JPokemonException;

@Path("contestcategory")
public class ContestCategoryService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContestCategory> getAll() {
		return ContestCategory.manager.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContestCategory getContestCategory(@PathParam("id") String id) {
		ContestCategory contestCategory = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			contestCategory = ContestCategory.manager.get(decodedId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contestCategory;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createContestCategory(ContestCategory contestCategory) {
		try {
			ContestCategory.manager.register(contestCategory);
			String encodedId = URLEncoder.encode(contestCategory.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContestCategory(ContestCategory contestCategory) {
		try {
			ContestCategory.manager.update(contestCategory);
			return Response.ok().build();
		} catch (JPokemonException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteContestCategory(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			ContestCategory.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
