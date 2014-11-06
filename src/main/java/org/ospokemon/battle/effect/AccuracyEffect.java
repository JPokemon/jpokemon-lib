package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.Move;
import org.ospokemon.PokemonContainer;
import org.ospokemon.Round;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

public class AccuracyEffect extends AbstractBattleEffect {
	@Override
	public void affect(Round round) {
		round.addBattleEffect(this);
	}

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
		if (!(battleEffect instanceof AttackDamageEffect)) {
			return;
		}

		AttackDamageEffect attackDamage = (AttackDamageEffect) battleEffect;
		String moveName = attackDamage.getMove();
		Move move = Move.manager.get(moveName);

		if (move.getAccuracy() < Math.random()) {
			attackDamage.setCancelled(true);
		}
	}
}
