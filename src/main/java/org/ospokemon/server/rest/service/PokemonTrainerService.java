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
import org.ospokemon.PokemonTrainer;
import org.ospokemon.server.rest.resource.PokemonTrainerResource;

@Path("pokemontrainer")
public class PokemonTrainerService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PokemonTrainerResource> getAll() {
		List<PokemonTrainerResource> pokemonTrainerResources = new ArrayList<PokemonTrainerResource>();

		for (PokemonTrainer pokemonTrainer : PokemonTrainer.manager.getAll()) {
			PokemonTrainerResource pokemonTrainerResource = new PokemonTrainerResource(pokemonTrainer);
			pokemonTrainerResources.add(pokemonTrainerResource);
		}

		return pokemonTrainerResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PokemonTrainerResource getPokemonTrainer(@PathParam("id") String id) {
		PokemonTrainerResource pokemonTrainerResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			PokemonTrainer pokemonTrainer = PokemonTrainer.manager.get(decodedId);

			if (pokemonTrainer != null) {
				pokemonTrainerResource = new PokemonTrainerResource(pokemonTrainer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pokemonTrainerResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPokemonTrainer(PokemonTrainerResource pokemonTrainerResource) {
		try {
			PokemonTrainer pokemonTrainer = pokemonTrainerResource.buildPokemonTrainer();
			PokemonTrainer.manager.register(pokemonTrainer);
			String encodedId = URLEncoder.encode(pokemonTrainer.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePokemonTrainer(PokemonTrainerResource pokemonTrainerResource) {
		try {
			PokemonTrainer pokemonTrainer = pokemonTrainerResource.buildPokemonTrainer();
			PokemonTrainer.manager.update(pokemonTrainer);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletePokemonTrainer(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			PokemonTrainer.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
