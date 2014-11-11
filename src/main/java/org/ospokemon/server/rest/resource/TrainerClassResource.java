package org.ospokemon.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Property;
import org.ospokemon.TrainerClass;

public class TrainerClassResource {
	protected String name, description;

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public TrainerClassResource() {
	}

	public TrainerClassResource(TrainerClass trainerClass) {
		setName(trainerClass.getName());
		setDescription(trainerClass.getDescription());

		for (Map.Entry<String, Object> propertyEntry : trainerClass.getProperties().entrySet()) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public TrainerClass buildTrainerClass() {
		TrainerClass trainerClass = new TrainerClass();

		trainerClass.setName(getName());
		trainerClass.setDescription(getDescription());

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

			trainerClass.setProperty(propertyName, property);
		}

		return trainerClass;
	}
}
