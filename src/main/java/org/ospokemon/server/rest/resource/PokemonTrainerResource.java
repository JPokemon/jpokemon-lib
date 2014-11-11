package org.ospokemon.server.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Property;

public class PokemonTrainerResource {
	protected String id, name, trainerClass;

	protected int trainerID;

	protected Map<String, Integer> items = new HashMap<String, Integer>();

	protected List<PokemonResource> pokemon = new ArrayList<PokemonResource>();

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public PokemonTrainerResource() {
	}

	public PokemonTrainerResource(PokemonTrainer pokemonTrainer) {
		setName(pokemonTrainer.getName());
		setTrainerClass(pokemonTrainer.getTrainerClass());
		setTrainerID(pokemonTrainer.getTrainerID());
		items.putAll(pokemonTrainer.getItems());

		for (Pokemon pokemon : pokemonTrainer.getPokemon()) {
			PokemonResource pokemonResource = new PokemonResource(pokemon);
			getPokemon().add(pokemonResource);
		}

		for (Map.Entry<String, Object> abilityPropertyEntry : pokemonTrainer.getProperties().entrySet()) {
			String propertyId = abilityPropertyEntry.getKey();
			Object property = abilityPropertyEntry.getValue();
			Builder<Object> builder = Property.builders.get(propertyId);

			if (builder != null) {
				String options = builder.destruct(property);
				getProperties().put(propertyId, options);
			}
			else {
				getProperties().put(propertyId, property);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainerClass() {
		return trainerClass;
	}

	public void setTrainerClass(String trainerClass) {
		this.trainerClass = trainerClass;
	}

	public int getTrainerID() {
		return trainerID;
	}

	public void setTrainerID(int trainerID) {
		this.trainerID = trainerID;
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

	public List<PokemonResource> getPokemon() {
		return pokemon;
	}

	public void setPokemon(List<PokemonResource> pokemon) {
		this.pokemon = pokemon;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public PokemonTrainer buildPokemonTrainer() {
		PokemonTrainer pokemonTrainer = new PokemonTrainer();

		pokemonTrainer.setId(getId());
		pokemonTrainer.setName(getName());
		pokemonTrainer.setTrainerClass(getTrainerClass());
		pokemonTrainer.setTrainerID(getTrainerID());
		pokemonTrainer.getItems().putAll(getItems());

		for (PokemonResource pokemonResource : getPokemon()) {
			Pokemon pokemon = pokemonResource.buildPokemon();
			pokemonTrainer.addPokemon(pokemon);
		}

		for (Map.Entry<String, Object> propertyEntry : getProperties().entrySet()) {
			String propertyName = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			Builder<Object> builder = Property.builders.get(propertyName);

			if (builder == null) {
				continue;
			}

			try {
				property = builder.construct(property.toString());
			} catch (JPokemonException e) {
				// JPokemonException comes from propertyProvider.build
			}

			pokemonTrainer.setProperty(propertyName, property);
		}

		return pokemonTrainer;
	}
}
