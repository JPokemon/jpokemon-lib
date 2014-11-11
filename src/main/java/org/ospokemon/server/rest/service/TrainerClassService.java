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
import org.ospokemon.TrainerClass;
import org.ospokemon.server.rest.resource.TrainerClassResource;

@Path("trainerclass")
public class TrainerClassService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrainerClassResource> getAll() {
		List<TrainerClassResource> trainerClassResources = new ArrayList<TrainerClassResource>();

		for (TrainerClass trainerClass : TrainerClass.manager.getAll()) {
			TrainerClassResource trainerClassResource = new TrainerClassResource(trainerClass);
			trainerClassResources.add(trainerClassResource);
		}

		return trainerClassResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrainerClassResource getTrainerClass(@PathParam("id") String id) {
		TrainerClassResource trainerClassResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			TrainerClass trainerClass = TrainerClass.manager.get(decodedId);

			if (trainerClass != null) {
				trainerClassResource = new TrainerClassResource(trainerClass);
			}
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trainerClassResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrainerClass(TrainerClassResource trainerClassResource) {
		try {
			TrainerClass trainerClass = trainerClassResource.buildTrainerClass();
			TrainerClass.manager.register(trainerClass);
			String encodedId = URLEncoder.encode(trainerClass.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTrainerClass(TrainerClassResource trainerClassResource) {
		try {
			TrainerClass trainerClass = trainerClassResource.buildTrainerClass();
			TrainerClass.manager.update(trainerClass);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteTrainerClass(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			TrainerClass.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().build();
	}
}
