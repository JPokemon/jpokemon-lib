package org.ospokemon.manager;

import org.ospokemon.JPokemonException;
import org.ospokemon.MovementScheme;
import org.ospokemon.manager.SimpleBuildersManager;
import org.ospokemon.movement.Accelerator;
import org.ospokemon.movement.Blocker;
import org.ospokemon.movement.Door;
import org.ospokemon.movement.Hollow;
import org.ospokemon.movement.Solid;

public class SimpleMovementSchemeManager extends SimpleBuildersManager<MovementScheme> {
	public SimpleMovementSchemeManager() throws JPokemonException {
		register(new Solid.Builder());
		register(new Hollow.Builder());
		register(new Door.Builder());
		register(new Accelerator.Builder());
		register(new Blocker.Builder());
	}
}
