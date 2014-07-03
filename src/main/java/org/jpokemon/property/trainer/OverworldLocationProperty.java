package org.jpokemon.property.trainer;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;

public class OverworldLocationProperty {
	protected String overworld;

	protected int x, y;

	public OverworldLocationProperty() {
	}

	public String getOverworld() {
		return overworld;
	}

	public void setOverworld(String overworld) {
		this.overworld = overworld;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static class Provider extends PropertyProvider<OverworldLocationProperty> {
		@Override
		public String getName() {
			return OverworldLocationProperty.class.getName();
		}

		@Override
		public OverworldLocationProperty build(String options) throws JPokemonException {
			String[] args = options.split(",");
			OverworldLocationProperty property = new OverworldLocationProperty();
			property.setOverworld(args[0]);
			property.setX(Integer.parseInt(args[1]));
			property.setY(Integer.parseInt(args[2]));
			return property;
		}

		@Override
		public String serialize(Object object) throws JPokemonException {
			OverworldLocationProperty property = (OverworldLocationProperty) object;
			return property.getOverworld() + ',' + property.getX() + ',' + property.getY();
		}
	}
}
