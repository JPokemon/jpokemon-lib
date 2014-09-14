package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.Move;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

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
