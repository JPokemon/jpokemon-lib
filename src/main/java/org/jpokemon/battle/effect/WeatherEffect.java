package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.Move;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.SkillContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class WeatherEffect implements BattleEffect {
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
	public int getPriority() {
		return 0;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		if (--duration == 0) {
			battle.removeBattleEffect(this);
		}
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
		Move move = Move.manager.getByName(attackDamage.getMove());

		if (move.getType().equals(type)) {
			attackDamage.setAmount((int) (attackDamage.getAmount() * getDamageModifier()));
		}
	}
}
