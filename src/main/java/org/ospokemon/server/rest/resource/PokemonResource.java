package org.ospokemon.server.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Pokemon;
import org.ospokemon.Property;
import org.ospokemon.Skill;
import org.ospokemon.Stat;

public class PokemonResource {
	protected String id, name, species, gender, originalTrainerName, ability, heldItem;

	protected int originalTrainerID, level, experience, friendship;

	protected double height, weight;

	protected boolean shiny;

	protected List<String> statusConditions = new ArrayList<String>();

	protected List<Skill> skills = new ArrayList<Skill>();

	protected Map<String, Stat> stats = new HashMap<String, Stat>();

	protected List<String> markings = new ArrayList<String>();

	protected List<String> ribbons = new ArrayList<String>();

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public PokemonResource() {
	}

	public PokemonResource(Pokemon pokemon) {
		setId(pokemon.getId());
		setName(pokemon.getName());
		setSpecies(pokemon.getSpecies());
		setGender(pokemon.getGender());
		setOriginalTrainerID(pokemon.getOriginalTrainerID());
		setOriginalTrainerName(pokemon.getOriginalTrainerName());
		setLevel(pokemon.getLevel());
		setExperience(pokemon.getExperience());
		setFriendship(pokemon.getFriendship());
		setAbility(pokemon.getAbility());
		setHeight(pokemon.getHeight());
		setWeight(pokemon.getWeight());
		setShiny(pokemon.isShiny());
		setHeldItem(pokemon.getHeldItem());
		getStatusConditions().addAll(pokemon.getStatusConditions());
		getSkills().addAll(pokemon.getSkills());
		getStats().putAll(pokemon.getStats());
		getRibbons().addAll(pokemon.getRibbons());

		for (Map.Entry<String, Object> propertyEntry : pokemon.getProperties().entrySet()) {
			String propertyId = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOriginalTrainerName() {
		return originalTrainerName;
	}

	public void setOriginalTrainerName(String originalTrainerName) {
		this.originalTrainerName = originalTrainerName;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getHeldItem() {
		return heldItem;
	}

	public void setHeldItem(String heldItem) {
		this.heldItem = heldItem;
	}

	public int getOriginalTrainerID() {
		return originalTrainerID;
	}

	public void setOriginalTrainerID(int originalTrainerID) {
		this.originalTrainerID = originalTrainerID;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getFriendship() {
		return friendship;
	}

	public void setFriendship(int friendship) {
		this.friendship = friendship;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}

	public List<String> getStatusConditions() {
		return statusConditions;
	}

	public void setStatusConditions(List<String> statusConditions) {
		this.statusConditions = statusConditions;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Map<String, Stat> getStats() {
		return stats;
	}

	public void setStats(Map<String, Stat> stats) {
		this.stats = stats;
	}

	public List<String> getMarkings() {
		return markings;
	}

	public void setMarkings(List<String> markings) {
		this.markings = markings;
	}

	public List<String> getRibbons() {
		return ribbons;
	}

	public void setRibbons(List<String> ribbons) {
		this.ribbons = ribbons;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Pokemon buildPokemon() {
		Pokemon pokemon = new Pokemon();

		pokemon.setId(getId());
		pokemon.setName(getName());
		pokemon.setSpecies(getSpecies());
		pokemon.setGender(getGender());
		pokemon.setOriginalTrainerID(getOriginalTrainerID());
		pokemon.setOriginalTrainerName(getOriginalTrainerName());
		pokemon.setLevel(getLevel());
		pokemon.setExperience(getExperience());
		pokemon.setFriendship(getFriendship());
		pokemon.setAbility(getAbility());
		pokemon.setHeight(getHeight());
		pokemon.setWeight(getWeight());
		pokemon.setShiny(isShiny());
		pokemon.setHeldItem(getHeldItem());
		pokemon.getStatusConditions().addAll(getStatusConditions());
		pokemon.getSkills().addAll(getSkills());
		pokemon.getStats().putAll(getStats());
		pokemon.getRibbons().addAll(getRibbons());

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

			pokemon.setProperty(propertyName, property);
		}

		return pokemon;
	}
}
