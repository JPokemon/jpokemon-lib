package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.Move;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.SkillContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class AccuracyEffect implements BattleEffect {
	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
	}

	@Override
	public void affect(Round round) {
		round.addBattleEffect(this);
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
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer,
			PokemonContainer pokemonContainer, Turn turn) {
		if (!(battleEffect instanceof AttackDamageEffect)) {
			return;
		}

		AttackDamageEffect attackDamage = (AttackDamageEffect) battleEffect;
		String moveName = attackDamage.getMove();
		Move move = Move.manager.getByName(moveName);

		if (move.getAccuracy() < Math.random()) {
			attackDamage.setAmount(0);
		}
	}
}
