package org.ospokemon.server.rest.resource;

import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Move;
import org.ospokemon.Property;

public class MoveResource {
	protected String id, name, type, category, description, target, contestCategory, contestDescription;

	protected int PP, power, appeal, jam, priority;

	protected double accuracy;

	protected Map<String, Object> properties;

	public MoveResource() {
	}

	public MoveResource(Move move) {
		setId(move.getId());
		setName(move.getName());
		setType(move.getType());
		setCategory(move.getCategory());
		setDescription(move.getDescription());
		setTarget(move.getTarget());
		setContestCategory(move.getContestCategory());
		setContestDescription(move.getContestDescription());
		setPP(move.getPP());
		setPower(move.getPower());
		setAppeal(move.getAppeal());
		setJam(move.getJam());
		setPriority(move.getPriority());
		setAccuracy(move.getAccuracy());

		for (Map.Entry<String, Object> propertyEntry : move.getProperties().entrySet()) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContestCategory() {
		return contestCategory;
	}

	public void setContestCategory(String contestCategory) {
		this.contestCategory = contestCategory;
	}

	public String getContestDescription() {
		return contestDescription;
	}

	public void setContestDescription(String contestDescription) {
		this.contestDescription = contestDescription;
	}

	public int getPP() {
		return PP;
	}

	public void setPP(int pP) {
		PP = pP;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getAppeal() {
		return appeal;
	}

	public void setAppeal(int appeal) {
		this.appeal = appeal;
	}

	public int getJam() {
		return jam;
	}

	public void setJam(int jam) {
		this.jam = jam;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/*
	 * Method cannot be named 'getMove' because of the reflection
	 */
	public Move buildMove() {
		Move move = new Move();

		move.setId(getId());
		move.setName(getName());
		move.setType(getType());
		move.setCategory(getCategory());
		move.setDescription(getDescription());
		move.setTarget(getTarget());
		move.setContestCategory(getContestCategory());
		move.setContestDescription(getContestDescription());
		move.setPP(getPP());
		move.setPower(getPower());
		move.setAppeal(getAppeal());
		move.setJam(getJam());
		move.setPriority(getPriority());
		move.setAccuracy(getAccuracy());

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

			move.setProperty(propertyName, property);
		}

		return move;
	}
}
