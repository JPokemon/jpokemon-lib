package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Stat;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class AttackDamageEffect extends AbstractBattleEffect {
	protected String move;

	protected int amount;

	protected String type;

	protected boolean cancelled;

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public int getAmount() {
		if (cancelled) {
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

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		if (isCancelled()) {
			return;
		}

		PokemonContainer targetPokemonContainer = turn.getTarget().getPokemonContainers().get(turn.getTargetIndex());
		Stat targetHealth = targetPokemonContainer.getPokemon().getStat("health");
		targetHealth.setValue(targetHealth.getValue() - getAmount());
	}
}
