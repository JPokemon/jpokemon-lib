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

import org.ospokemon.Item;
import org.ospokemon.JPokemonException;
import org.ospokemon.server.rest.resource.ItemResource;

@Path("item")
public class ItemService {
	@GET
	@Produces
	public List<ItemResource> getAll() {
		List<ItemResource> itemResources = new ArrayList<ItemResource>();

		for (Item item : Item.manager.getAll()) {
			ItemResource itemResource = new ItemResource(item);
			itemResources.add(itemResource);
		}

		return itemResources;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemResource getItem(@PathParam("id") String id) {
		ItemResource itemResource = null;

		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Item item = Item.manager.get(decodedId);

			if (item != null) {
				itemResource = new ItemResource(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemResource;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createItem(ItemResource itemResource) {
		try {
			Item item = itemResource.buildItem();
			Item.manager.register(item);
			String encodedId = URLEncoder.encode(item.getId(), "UTF-8");
			return Response.created(new URI(encodedId)).build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(ItemResource itemResource) {
		try {
			Item item = itemResource.buildItem();
			Item.manager.update(item);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") String id) {
		try {
			String decodedId = URLDecoder.decode(id, "UTF-8");
			Item.manager.unregister(decodedId);
			return Response.ok().build();
		} catch (JPokemonException e) { // do nothing
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
}
