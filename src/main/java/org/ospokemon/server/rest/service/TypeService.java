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
import org.ospokemon.Type;

@Path("type")
public class TypeService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Type> getAll() {
		return Type.manager.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Type getType(@PathParam("id") String id) {
		Type type = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			type = Type.manager.get(decodedId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createType(Type type) {
		try {
			Type.manager.register(type);
			String encodedId = URLEncoder.encode(type.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateType(Type type) {
		try {
			Type.manager.update(type);
			return Response.ok().build();
		} catch (JPokemonException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteType(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Type.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
