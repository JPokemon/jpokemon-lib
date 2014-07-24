package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class MagneticHeldItemEffect extends AbstractBattleEffect {
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
}
