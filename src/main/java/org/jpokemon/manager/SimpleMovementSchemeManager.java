package org.jpokemon.manager;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.MovementScheme;
import org.jpokemon.movement.Hollow;
import org.jpokemon.movement.Solid;

public class SimpleMovementSchemeManager extends SimpleManager<MovementScheme> {
	public SimpleMovementSchemeManager() throws JPokemonException {
		super(MovementScheme.class);

		register(new Solid());
		register(new Hollow());
	}
}
