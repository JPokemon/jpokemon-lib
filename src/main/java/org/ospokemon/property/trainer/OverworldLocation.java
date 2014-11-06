package org.ospokemon.property.trainer;

import org.ospokemon.JPokemonException;

public class OverworldLocation {
	protected String overworld;

	protected int x, y;

	protected String direction;

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public static class Builder implements org.ospokemon.Builder<Object> {
		@Override
		public String getId() {
			return OverworldLocation.class.getName();
		}

		@Override
		public Object construct(String options) throws JPokemonException {
			String[] args = options.split(",");
			OverworldLocation property = new OverworldLocation();
			property.setOverworld(args[0]);
			property.setX(Integer.parseInt(args[1]));
			property.setY(Integer.parseInt(args[2]));
			property.setDirection(args[3]);
			return property;
		}

		@Override
		public String destruct(Object object) throws JPokemonException {
			OverworldLocation property = (OverworldLocation) object;
			return property.getOverworld() + ',' + property.getX() + ',' + property.getY() + ',' + property.getDirection();
		}
	}
}
