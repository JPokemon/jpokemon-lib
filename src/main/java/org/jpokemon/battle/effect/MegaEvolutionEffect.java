package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.Evolution;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Species;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;
import org.jpokemon.evolution.MegaEvolution;

public class MegaEvolutionEffect extends AbstractBattleEffect {
	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		Species species = Species.manager.getByName(pokemonContainer.getSpecies());
		MegaEvolution megaEvolution = null;

		for (Evolution evolution : species.getEvolutions()) {
			if (evolution instanceof MegaEvolution) {
				megaEvolution = (MegaEvolution) evolution;
				break;
			}
		}

		if (megaEvolution != null) {
			pokemonContainer.setSpecies(megaEvolution.getSpecies());
		}
	}
}
