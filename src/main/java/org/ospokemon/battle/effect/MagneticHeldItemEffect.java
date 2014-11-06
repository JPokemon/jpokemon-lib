package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.PokemonContainer;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

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
