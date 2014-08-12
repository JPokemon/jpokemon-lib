package org.jpokemon.movement;

import org.jpokemon.api.MovementScheme;

public class Hollow extends MovementScheme {

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public boolean canEnterFrom(String direction) {
		return true;
	}

	@Override
	public boolean canExitToward(String direction) {
		return true;
	}

	@Override
	public String mustExitToward() {
		return null;
	}
}
