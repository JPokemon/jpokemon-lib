package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.SkillContainer;
import org.jpokemon.api.Stat;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class AttackDamageEffect implements BattleEffect {
	protected String move;

	protected int amount;

	protected String type;

	protected boolean miss;

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public int getAmount() {
		if (miss) {
			return 0;
		}

		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isMiss() {
		return miss;
	}

	public void setMiss(boolean miss) {
		this.miss = miss;
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		PokemonContainer targetPokemonContainer = turn.getTarget();
		Stat targetHealth = targetPokemonContainer.getPokemon().getStat("health");
		targetHealth.setValue(targetHealth.getValue() - getAmount());
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
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer,
			PokemonContainer pokemonContainer, Turn turn) {
	}
}
