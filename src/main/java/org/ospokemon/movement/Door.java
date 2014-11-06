package org.ospokemon.movement;

import org.ospokemon.MovementScheme;
import org.ospokemon.builder.SingletonBuilder;

/*
 * This class is really just useful for semantic reasons
 */
public class Door extends Hollow {
	public static class Builder extends SingletonBuilder<MovementScheme> {
		public Builder() {
			super(new Door());
		}
	}
}
