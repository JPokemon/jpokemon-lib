package org.ospokemon.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

import org.ospokemon.Ability;
import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Property;

public class AbilityResource {
	protected String id, name, description;

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public AbilityResource() {
	}

	public AbilityResource(Ability ability) {
		setId(ability.getId());
		setName(ability.getName());
		setDescription(ability.getDescription());

		for (Map.Entry<String, Object> propertyEntry : ability.getProperties().entrySet()) {
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

	/*
	 * Method cannot be named 'getAbility' because of the reflection
	 */
	public Ability buildAbility() {
		Ability ability = new Ability();

		ability.setId(getId());
		ability.setName(getName());
		ability.setDescription(getDescription());

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

			ability.setProperty(propertyName, property);
		}

		return ability;
	}
}
