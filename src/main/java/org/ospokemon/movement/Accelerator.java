package org.ospokemon.movement;

import org.ospokemon.MovementScheme;

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
	public String getNextMove(String move) {
		// allow comparison with ==
		if (move.equals(direction)) {
			return move;
		}

		return direction;
	}

	public static class Builder implements org.ospokemon.Builder<MovementScheme> {
		@Override
		public String getId() {
			return Accelerator.class.getName();
		}

		@Override
		public MovementScheme construct(String options) {
			Accelerator ms = new Accelerator(options);
			return ms;
		}

		@Override
		public String destruct(MovementScheme object) {
			Accelerator ms = (Accelerator) object;
			return ms.getDirection();
		}
	}
}
