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
import org.ospokemon.Species;
import org.ospokemon.server.rest.resource.SpeciesResource;

@Path("species")
public class SpeciesService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SpeciesResource> getAll() {
		List<SpeciesResource> speciesResources = new ArrayList<SpeciesResource>();

		for (Species species : Species.manager.getAll()) {
			SpeciesResource speciesResource = new SpeciesResource(species);
			speciesResources.add(speciesResource);
		}

		return speciesResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SpeciesResource getSpecies(@PathParam("id") String id) {
		SpeciesResource speciesResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Species species = Species.manager.get(decodedId);

			if (species != null) {
				speciesResource = new SpeciesResource(species);
			}
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}

		return speciesResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSpecies(SpeciesResource speciesResource) {
		try {
			Species species = speciesResource.buildSpecies();
			Species.manager.register(species);
			String encodedId = URLEncoder.encode(species.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSpecies(SpeciesResource speciesResource) {
		try {
			Species species = speciesResource.buildSpecies();
			Species.manager.update(species);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteSpecies(@PathParam("id") String id) {
		try {
			Species.manager.unregister(id);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().build();
	}
}
