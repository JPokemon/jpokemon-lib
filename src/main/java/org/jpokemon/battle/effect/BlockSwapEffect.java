package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public abstract class BlockSwapEffect extends AbstractBattleEffect {
	public abstract boolean blocks(SwapEffect swapEffect, Battle battle, TrainerContainer trainerContainer,
			PokemonContainer pokemonContainer, Turn turn);

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
		if (!(battleEffect instanceof SwapEffect)) {
			return;
		}

		SwapEffect swapEffect = (SwapEffect) battleEffect;

		if (blocks(swapEffect, battle, trainerContainer, pokemonContainer, turn)) {
			swapEffect.setCancelled(true);
		}
	}
}
