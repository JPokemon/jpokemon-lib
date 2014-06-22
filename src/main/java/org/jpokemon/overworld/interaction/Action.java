package org.jpokemon.overworld.interaction;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PokemonTrainer;

public interface Action {
	public void execute(PokemonTrainer pokemonTrainer) throws JPokemonException;
}
