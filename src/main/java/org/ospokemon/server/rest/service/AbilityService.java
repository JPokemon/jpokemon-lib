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

import org.ospokemon.Ability;
import org.ospokemon.JPokemonException;
import org.ospokemon.server.rest.resource.AbilityResource;

@Path("ability")
public class AbilityService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AbilityResource> getAll() {
		List<AbilityResource> abilityResources = new ArrayList<AbilityResource>();

		for (Ability ability : Ability.manager.getAll()) {
			AbilityResource abilityResource = new AbilityResource(ability);
			abilityResources.add(abilityResource);
		}

		return abilityResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AbilityResource getAbility(@PathParam("id") String id) {
		AbilityResource abilityResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Ability ability = Ability.manager.get(decodedId);

			if (ability != null) {
				abilityResource = new AbilityResource(ability);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return abilityResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAbility(AbilityResource abilityResource) {
		try {
			Ability ability = abilityResource.buildAbility();
			Ability.manager.register(ability);
			String encodedId = URLEncoder.encode(ability.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAbility(AbilityResource abilityResource) {
		try {
			Ability ability = abilityResource.buildAbility();
			Ability.manager.update(ability);
			return Response.ok().build();
		} catch (JPokemonException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteAbility(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Ability.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (JPokemonException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
