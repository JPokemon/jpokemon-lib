package org.jpokemon.movement;

import org.jpokemon.api.MovementScheme;
import org.jpokemon.builder.SingletonBuilder;

public class Hollow extends MovementScheme {
	@Override
	public String getNextMove(String move) {
		return move;
	}

	public static class Builder extends SingletonBuilder<MovementScheme> {
		public Builder() {
			super(new Hollow());
		}
	}
}
