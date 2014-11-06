package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.PokemonContainer;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

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
