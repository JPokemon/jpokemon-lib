package org.ospokemon.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.Item;
import org.ospokemon.JPokemonException;
import org.ospokemon.Property;

public class ItemResource {
	protected String id, name, description;

	protected int salePrice;

	protected boolean sellable, usableOutsideBattle, usableDuringBattle, consumable, holdable;

	protected Map<String, Object> properties = new HashMap<String, Object>();

	public ItemResource() {
	}

	public ItemResource(Item item) {
		setId(item.getId());
		setName(item.getName());
		setDescription(item.getDescription());
		setSalePrice(item.getSalePrice());
		setSellable(item.isSellable());
		setUsableOutsideBattle(item.isUsableOutsideBattle());
		setUsableDuringBattle(item.isUsableDuringBattle());
		setConsumable(item.isConsumable());
		setHoldable(item.isHoldable());

		for (Map.Entry<String, Object> propertyEntry : item.getProperties().entrySet()) {
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

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public boolean isSellable() {
		return sellable;
	}

	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}

	public boolean isUsableOutsideBattle() {
		return usableOutsideBattle;
	}

	public void setUsableOutsideBattle(boolean usableOutsideBattle) {
		this.usableOutsideBattle = usableOutsideBattle;
	}

	public boolean isUsableDuringBattle() {
		return usableDuringBattle;
	}

	public void setUsableDuringBattle(boolean usableDuringBattle) {
		this.usableDuringBattle = usableDuringBattle;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public boolean isHoldable() {
		return holdable;
	}

	public void setHoldable(boolean holdable) {
		this.holdable = holdable;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/*
	 * Method cannot be named 'getItem' because of the reflection
	 */
	public Item buildItem() {
		Item item = new Item();

		item.setId(getId());
		item.setName(getName());
		item.setDescription(getDescription());
		item.setSalePrice(getSalePrice());
		item.setSellable(isSellable());
		item.setUsableOutsideBattle(isUsableOutsideBattle());
		item.setUsableDuringBattle(isUsableDuringBattle());
		item.setConsumable(isConsumable());
		item.setHoldable(isHoldable());

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

			item.setProperty(propertyName, property);
		}

		return item;
	}
}
