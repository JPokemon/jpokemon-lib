package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.SkillContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class MagneticHeldItemEffect implements BattleEffect {
	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		boolean hasEffect = false;

		for (BattleEffect battleEffect : battle.getNextRound().getBattleEffects()) {
			if (battleEffect instanceof TypeBlockSwapEffect) {
				hasEffect = true;
				break;
			}
		}

		if (!hasEffect) {
			battle.getNextRound().addBattleEffect(new TypeBlockSwapEffect("Steel"));
		}
	}

	@Override
	public void affect(Round round) {
	}

	@Override
	public void affect(TrainerContainer trainerContainer) {
	}

	@Override
	public void affect(PokemonContainer pokemonContainer) {
	}

	@Override
	public void affect(SkillContainer skillContainer) {
	}

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
	}
}
