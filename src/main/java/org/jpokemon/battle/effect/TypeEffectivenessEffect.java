package org.jpokemon.battle.effect;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.Species;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;
import org.jpokemon.api.Type;

public class TypeEffectivenessEffect extends AbstractBattleEffect {
	@Override
	public void affect(Round round) {
		round.addBattleEffect(this);
	}

	@Override
	public void affect(BattleEffect battleEffect, Battle battle, TrainerContainer trainerContainer,
			PokemonContainer pokemonContainer, Turn turn) {
		if (!(battleEffect instanceof AttackDamageEffect)) {
			return;
		}

		AttackDamageEffect attackDamage = (AttackDamageEffect) battleEffect;
		PokemonContainer targetPokemonContainer = turn.getTarget().getPokemonContainers().get(turn.getTargetIndex());
		Type attackType = Type.manager.getByName(attackDamage.getType());
		Species targetPokemonSpecies = Species.manager.getByName(targetPokemonContainer.getPokemon().getSpecies());

		for (String targetType : targetPokemonSpecies.getTypes()) {
			if ("super".equals(attackType.getEffectiveness(targetType))) {
				attackDamage.setAmount((int) (attackDamage.getAmount() * 1.5));
			}
			else if ("not very".equals(attackType.getEffectiveness(targetType))) {
				attackDamage.setAmount((int) (attackDamage.getAmount() * 0.5));
			}
			else if ("ineffective".equals(attackType.getEffectiveness(targetType))) {
				attackDamage.setAmount(0);
			}
		}
	}
}
