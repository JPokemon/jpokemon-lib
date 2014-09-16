package org.jpokemon.manager;

import org.jpokemon.api.Builder;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.MovementScheme;
import org.jpokemon.builder.SingletonBuilder;
import org.jpokemon.movement.Hollow;
import org.jpokemon.movement.Solid;

public class SimpleMovementSchemeManager extends SimpleManager<Builder<MovementScheme>> {
	@SuppressWarnings("unchecked")
	public SimpleMovementSchemeManager() throws JPokemonException {
		super((Class<Builder<MovementScheme>>) new SingletonBuilder<Object>(new Object()).getClass());
		register(new Solid.Builder());
		register(new Hollow.Builder());
	}
}
