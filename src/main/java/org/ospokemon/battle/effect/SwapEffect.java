package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.PokemonContainer;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

public class SwapEffect extends AbstractBattleEffect {
	protected final PokemonContainer target;

	protected boolean cancelled;

	public SwapEffect(PokemonContainer target) {
		this.target = target;
	}

	public PokemonContainer getTarget() {
		return target;
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

		int toIndex = trainerContainer.getPokemonContainers().indexOf(pokemonContainer);
		int fromIndex = trainerContainer.getPokemonContainers().indexOf(target);

		trainerContainer.getPokemonContainers().set(fromIndex, pokemonContainer);
		trainerContainer.getPokemonContainers().set(toIndex, target);

		for (BattleEffect battleEffect : target.getBattleEffects()) {
			battleEffect.affect(battle, trainerContainer, pokemonContainer, turn);
		}
	}

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer,
			Turn turn) {
		// TODO Auto-generated method stub
	}
}
