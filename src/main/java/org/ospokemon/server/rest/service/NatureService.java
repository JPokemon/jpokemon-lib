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
import org.ospokemon.Nature;

@Path("nature")
public class NatureService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nature> getAll() {
		return Nature.manager.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Nature getNature(@PathParam("id") String id) {
		Nature nature = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			nature = Nature.manager.get(decodedId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nature;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNature(Nature nature) {
		try {
			Nature.manager.register(nature);
			String encodedId = URLEncoder.encode(nature.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateNature(Nature nature) {
		try {
			Nature.manager.update(nature);
			return Response.ok().build();
		} catch (JPokemonException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteNature(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Nature.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
