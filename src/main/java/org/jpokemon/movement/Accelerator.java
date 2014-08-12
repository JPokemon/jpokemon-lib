package org.jpokemon.movement;

import org.jpokemon.api.MovementScheme;

public class Accelerator extends MovementScheme {
	protected String direction;

	public Accelerator() {
	}

	public Accelerator(String direction) {
		setDirection(direction);
	}

	public String getDirection() {
		return direction;
	}

	public Accelerator setDirection(String direction) {
		this.direction = direction;
		return this;
	}

	@Override
	public String getName() {
		return getClass().getName() + ':' + getDirection();
	}

	@Override
	public boolean canEnterFrom(String direction) {
		return true;
	}

	@Override
	public boolean canExitToward(String direction) {
		return direction != null && direction.equals(getDirection());
	}

	@Override
	public String mustExitToward() {
		return getDirection();
	}
}
