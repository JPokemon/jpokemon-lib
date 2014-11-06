package org.ospokemon.battle.effect;

import org.ospokemon.Battle;
import org.ospokemon.BattleEffect;
import org.ospokemon.PokemonContainer;
import org.ospokemon.Round;
import org.ospokemon.TrainerContainer;
import org.ospokemon.Turn;

public class BideEffect extends AbstractBattleEffect {
	protected BattleEffect flag = new AbstractBattleEffect();

	@Override
	public void affect(Battle battle, TrainerContainer trainerContainer, PokemonContainer pokemonContainer, Turn turn) {
		if (pokemonContainer.getBattleEffects().contains(flag)) {
			pokemonContainer.getBattleEffects().remove(flag);

			Round lastRound = battle.getRounds().get(battle.getRounds().size() - 1);
			int totalDamage = 0;

			for (Turn t : lastRound.getTurns().values()) {
				if (t.getTargetIndex() == t.getTarget().getPokemonContainers().indexOf(pokemonContainer)) {
					for (BattleEffect battleEffect : turn.getBattleEffects()) {
						if (battleEffect instanceof AttackDamageEffect) {
							totalDamage += ((AttackDamageEffect) battleEffect).getAmount();
						}
					}
				}
			}

			AttackDamageEffect attackDamageEffect = new AttackDamageEffect();
			attackDamageEffect.setAmount(totalDamage * 2);
			attackDamageEffect.affect(battle, trainerContainer, pokemonContainer, turn);
		}
		else {
			pokemonContainer.getBattleEffects().add(flag);

			Turn t = new Turn();
			t.setPriority(turn.getPriority());
			t.setTarget(turn.getTarget());
			t.setTargetIndex(turn.getTargetIndex());
			t.getBattleEffects().addAll(turn.getBattleEffects());

			battle.getNextRound().setTurn(pokemonContainer, t);
		}
	}
}
