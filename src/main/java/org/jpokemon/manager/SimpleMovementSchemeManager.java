package org.jpokemon.manager;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.MovementScheme;
import org.jpokemon.movement.Accelerator;
import org.jpokemon.movement.Blocker;
import org.jpokemon.movement.Door;
import org.jpokemon.movement.Hollow;
import org.jpokemon.movement.Solid;

public class SimpleMovementSchemeManager extends SimpleBuildersManager<MovementScheme> {
	public SimpleMovementSchemeManager() throws JPokemonException {
		register(new Solid.Builder());
		register(new Hollow.Builder());
		register(new Door.Builder());
		register(new Accelerator.Builder());
		register(new Blocker.Builder());
	}
}
