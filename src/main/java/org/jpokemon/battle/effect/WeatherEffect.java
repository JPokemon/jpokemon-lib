package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.Move;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class WeatherEffect extends AbstractBattleEffect {
	protected String type;

	protected double damageModifier;

	protected int duration;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDamageModifier() {
		return damageModifier;
	}

	public void setDamageModifier(double damageModifier) {
		this.damageModifier = damageModifier;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		if (!battle.getNextRound().getBattleEffects().contains(this) && --duration > 0) {
			battle.getNextRound().addBattleEffect(this);
		}
		else {
			battle.removeBattleEffect(this);
		}
	}

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
		if (!(battleEffect instanceof AttackDamageEffect)) {
			return;
		}

		AttackDamageEffect attackDamage = (AttackDamageEffect) battleEffect;
		Move move = Move.manager.get(attackDamage.getMove());

		if (move.getType().equals(type)) {
			attackDamage.setAmount((int) (attackDamage.getAmount() * getDamageModifier()));
		}
	}
}
