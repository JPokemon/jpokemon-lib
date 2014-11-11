package org.ospokemon.server.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.Evolution;
import org.ospokemon.JPokemonException;
import org.ospokemon.Property;
import org.ospokemon.Species;

public class SpeciesResource {
	protected String id, name, tag, description, hiddenAbility, experienceCurve, bodyStyle, color;

	protected int number, catchRate, eggCyles, experienceYield, tameness;

	protected double maleRatio, height, weight;

	protected boolean genderless, breedable;

	protected List<String> types = new ArrayList<String>(), abilities = new ArrayList<String>(),
			eggGroups = new ArrayList<String>(), eggMoves = new ArrayList<String>(), machineMoves = new ArrayList<String>(),
			tutorMoves = new ArrayList<String>();

	protected Map<String, Integer> effortValues = new HashMap<String, Integer>(),
			baseStats = new HashMap<String, Integer>();

	protected List<Evolution> evolutions;

	protected Map<Integer, List<String>> moveLists = new HashMap<Integer, List<String>>();

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public SpeciesResource() {
	}

	public SpeciesResource(Species species) {
		setId(species.getId());
		setName(species.getName());
		setTag(species.getTag());
		setDescription(species.getDescription());
		setNumber(species.getNumber());
		setTypes(species.getTypes());
		setAbilities(species.getAbilities());
		setHiddenAbility(species.getHiddenAbility());
		setGenderless(species.isGenderless());
		setMaleRatio(species.getMaleRatio());
		setCatchRate(species.getCatchRate());
		setBreedable(species.isBreedable());
		setEggGroups(species.getEggGroups());
		setEggCyles(species.getEggCyles());
		setHeight(species.getHeight());
		setWeight(species.getWeight());
		setExperienceYield(species.getExperienceYield());
		setExperienceCurve(species.getExperienceCurve());
		setBodyStyle(species.getBodyStyle());
		setColor(species.getColor());
		setTameness(species.getTameness());
		getTypes().addAll(species.getTypes());
		getAbilities().addAll(species.getAbilities());
		getEggGroups().addAll(species.getEggGroups());
		getEggMoves().addAll(species.getEggMoves());
		getMachineMoves().addAll(species.getMachineMoves());
		getTutorMoves().addAll(species.getTutorMoves());
		getEffortValues().putAll(species.getEffortValues());
		getBaseStats().putAll(species.getBaseStats());

		// TODO evolution resources

		for (Map.Entry<Integer, List<String>> moveListEntry : species.getMoveLists().entrySet()) {
			List<String> moveList = new ArrayList<String>(moveListEntry.getValue());
			moveLists.put(moveListEntry.getKey(), moveList);
		}

		for (Map.Entry<String, Object> propertyEntry : species.getProperties().entrySet()) {
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHiddenAbility() {
		return hiddenAbility;
	}

	public void setHiddenAbility(String hiddenAbility) {
		this.hiddenAbility = hiddenAbility;
	}

	public String getExperienceCurve() {
		return experienceCurve;
	}

	public void setExperienceCurve(String experienceCurve) {
		this.experienceCurve = experienceCurve;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCatchRate() {
		return catchRate;
	}

	public void setCatchRate(int catchRate) {
		this.catchRate = catchRate;
	}

	public int getEggCyles() {
		return eggCyles;
	}

	public void setEggCyles(int eggCyles) {
		this.eggCyles = eggCyles;
	}

	public int getExperienceYield() {
		return experienceYield;
	}

	public void setExperienceYield(int experienceYield) {
		this.experienceYield = experienceYield;
	}

	public int getTameness() {
		return tameness;
	}

	public void setTameness(int tameness) {
		this.tameness = tameness;
	}

	public double getMaleRatio() {
		return maleRatio;
	}

	public void setMaleRatio(double maleRatio) {
		this.maleRatio = maleRatio;
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

	public boolean isGenderless() {
		return genderless;
	}

	public void setGenderless(boolean genderless) {
		this.genderless = genderless;
	}

	public boolean isBreedable() {
		return breedable;
	}

	public void setBreedable(boolean breedable) {
		this.breedable = breedable;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<String> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<String> abilities) {
		this.abilities = abilities;
	}

	public List<String> getEggGroups() {
		return eggGroups;
	}

	public void setEggGroups(List<String> eggGroups) {
		this.eggGroups = eggGroups;
	}

	public List<String> getEggMoves() {
		return eggMoves;
	}

	public void setEggMoves(List<String> eggMoves) {
		this.eggMoves = eggMoves;
	}

	public List<String> getMachineMoves() {
		return machineMoves;
	}

	public void setMachineMoves(List<String> machineMoves) {
		this.machineMoves = machineMoves;
	}

	public List<String> getTutorMoves() {
		return tutorMoves;
	}

	public void setTutorMoves(List<String> tutorMoves) {
		this.tutorMoves = tutorMoves;
	}

	public Map<String, Integer> getEffortValues() {
		return effortValues;
	}

	public void setEffortValues(Map<String, Integer> effortValues) {
		this.effortValues = effortValues;
	}

	public Map<String, Integer> getBaseStats() {
		return baseStats;
	}

	public void setBaseStats(Map<String, Integer> baseStats) {
		this.baseStats = baseStats;
	}

	public List<Evolution> getEvolutions() {
		return evolutions;
	}

	public void setEvolutions(List<Evolution> evolutions) {
		this.evolutions = evolutions;
	}

	public Map<Integer, List<String>> getMoveLists() {
		return moveLists;
	}

	public void setMoveLists(Map<Integer, List<String>> moveLists) {
		this.moveLists = moveLists;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Species buildSpecies() {
		Species species = new Species();

		species.setId(getId());
		species.setName(getName());
		species.setTag(getTag());
		species.setDescription(getDescription());
		species.setNumber(getNumber());
		species.setTypes(getTypes());
		species.setAbilities(getAbilities());
		species.setHiddenAbility(getHiddenAbility());
		species.setGenderless(isGenderless());
		species.setMaleRatio(getMaleRatio());
		species.setCatchRate(getCatchRate());
		species.setBreedable(isBreedable());
		species.setEggGroups(getEggGroups());
		species.setEggCyles(getEggCyles());
		species.setHeight(getHeight());
		species.setWeight(getWeight());
		species.setExperienceYield(getExperienceYield());
		species.setExperienceCurve(getExperienceCurve());
		species.setBodyStyle(getBodyStyle());
		species.setColor(getColor());
		species.setTameness(getTameness());
		species.getTypes().addAll(getTypes());
		species.getAbilities().addAll(getAbilities());
		species.getEggGroups().addAll(getEggGroups());
		species.getEggMoves().addAll(getEggMoves());
		species.getMachineMoves().addAll(getMachineMoves());
		species.getTutorMoves().addAll(getTutorMoves());
		species.getEffortValues().putAll(getEffortValues());
		species.getBaseStats().putAll(getBaseStats());

		// TODO evolution resources

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

			species.setProperty(propertyName, property);
		}

		return species;
	}
}
