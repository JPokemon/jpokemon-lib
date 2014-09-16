package org.jpokemon.movement;

import org.jpokemon.api.MovementScheme;

public class Blocker extends MovementScheme {
	protected String direction;

	public Blocker() {
	}

	public Blocker(String direction) {
		setDirection(direction);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String getNextMove(String move) {
		if (move.equals(getDirection())) {
			return null;
		}
		return move;
	}

	public static class Builder implements org.jpokemon.api.Builder<MovementScheme> {
		@Override
		public String getId() {
			return Blocker.class.getName();
		}

		@Override
		public MovementScheme construct(String options) {
			Blocker ms = new Blocker(options);
			return ms;
		}

		@Override
		public String destruct(MovementScheme object) {
			Blocker ms = (Blocker) object;
			return ms.getDirection();
		}
	}
}
