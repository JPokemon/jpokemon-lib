package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.PokemonContainer;
import org.ospokemon.Species;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

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
