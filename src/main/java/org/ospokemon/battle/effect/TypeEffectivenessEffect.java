package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.PokemonContainer;
import org.ospokemon.Round;
import org.ospokemon.Species;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;
import org.ospokemon.Type;

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
		Type attackType = Type.manager.get(attackDamage.getType());
		Species targetPokemonSpecies = Species.manager.get(targetPokemonContainer.getPokemon().getSpecies());

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
