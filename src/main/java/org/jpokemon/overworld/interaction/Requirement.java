package org.jpokemon.overworld.interaction;

import org.jpokemon.api.PokemonTrainer;

public interface Requirement {
	public boolean isSatisfied(PokemonTrainer pokemonTrainer);
}
