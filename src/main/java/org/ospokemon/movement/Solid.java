package org.ospokemon.movement;

import org.ospokemon.MovementScheme;
import org.ospokemon.builder.SingletonBuilder;

public class Solid extends MovementScheme {
	@Override
	public String getNextMove(String move) {
		return null;
	}

	public static class Builder extends SingletonBuilder<MovementScheme> {
		public Builder() {
			super(new Solid());
		}
	}
}
