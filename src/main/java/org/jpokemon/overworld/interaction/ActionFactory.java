package org.jpokemon.overworld.interaction;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Manager;

public abstract class ActionFactory {
	public static Manager<ActionFactory> manager;

	public abstract Class<? extends Action> getActionClass();

	public abstract Action buildAction(String options) throws JPokemonException;

	public abstract String serializeAction(Action action) throws JPokemonException;
}
