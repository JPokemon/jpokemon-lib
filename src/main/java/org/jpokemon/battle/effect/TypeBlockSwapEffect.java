package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Species;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class TypeBlockSwapEffect extends BlockSwapEffect {
	protected final String type;

	public TypeBlockSwapEffect(String type) {
		this.type = type;
	}

	@Override
	public boolean blocks(SwapEffect swapEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
		Species species = Species.manager.get(pokemonContainer.getSpecies());
		return species.getTypes().contains(type);
	}
}
