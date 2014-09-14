package org.jpokemon.manager;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.MovementScheme;
import org.jpokemon.movement.HollowBuilder;
import org.jpokemon.movement.SolidBuilder;

public class SimpleMovementSchemeManager extends SimpleBuildersManager<MovementScheme> {
	public SimpleMovementSchemeManager() throws JPokemonException {
		register(new SolidBuilder());
		register(new HollowBuilder());
	}
}
