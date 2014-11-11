package org.ospokemon.server.rest.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.ospokemon.Move;
import org.ospokemon.server.rest.resource.MoveResource;

@Path("move")
public class MoveService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MoveResource> getAll() {
		List<MoveResource> moveResources = new ArrayList<MoveResource>();

		for (Move move : Move.manager.getAll()) {
			MoveResource moveResource = new MoveResource(move);
			moveResources.add(moveResource);
		}

		return moveResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MoveResource getMove(@PathParam("id") String id) {
		MoveResource moveResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Move move = Move.manager.get(decodedId);

			if (move != null) {
				moveResource = new MoveResource(move);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moveResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMove(MoveResource moveResource) {
		try {
			Move move = moveResource.buildMove();
			Move.manager.register(move);
			String encodedId = URLEncoder.encode(move.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMove(MoveResource moveResource) {
		try {
			Move move = moveResource.buildMove();
			Move.manager.update(move);
			return Response.ok().build();
		} catch (JPokemonException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteMove(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Move.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
