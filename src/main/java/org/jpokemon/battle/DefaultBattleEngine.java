package org.jpokemon.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpokemon.api.Battle;
import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.BattleEngine;
import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Round;
import org.jpokemon.api.SkillContainer;
import org.jpokemon.api.TrainerContainer;
import org.jpokemon.api.Turn;

public class DefaultBattleEngine extends BattleEngine {
	protected Comparator<Map.Entry<PokemonContainer, Turn>> turnComparator;

	protected Comparator<BattleEffect> battleEffectComparator;

	public DefaultBattleEngine() {
	}

	public Comparator<Map.Entry<PokemonContainer, Turn>> getTurnComparator() {
		if (turnComparator == null) {
			turnComparator = new DefaultTurnComparator();
		}

		return turnComparator;
	}

	public DefaultBattleEngine setTurnComparator(Comparator<Map.Entry<PokemonContainer, Turn>> turnComparator) {
		this.turnComparator = turnComparator;
		return this;
	}

	public Comparator<BattleEffect> getBattleEffectComparator() {
		if (battleEffectComparator == null) {
			battleEffectComparator = new DefaultBattleEffectComparator();
		}

		return battleEffectComparator;
	}

	public DefaultBattleEngine setBattleEffectComparator(Comparator<BattleEffect> battleEffectComparator) {
		this.battleEffectComparator = battleEffectComparator;
		return this;
	}

	@Override
	public String getName() {
		return "default";
	}

	@Override
	public void processRound(Battle battle, Round round) {
		Map<PokemonContainer, TrainerContainer> pokemonContainerOwners = new HashMap<PokemonContainer, TrainerContainer>();

		// Apply effects from the battle to the round
		for (BattleEffect battleEffect : battle.getBattleEffects()) {
			battleEffect.affect(round);
		}

		for (TrainerContainer trainerContainer : battle.getTrainerContainers()) {
			// Apply effects of the round to the trainer containers
			for (BattleEffect battleEffect : round.getBattleEffects()) {
				battleEffect.affect(trainerContainer);
			}

			// Apply effects of the trainer container to each pokemon container
			for (PokemonContainer pokemonContainer : trainerContainer.getPokemonContainers()) {
				for (BattleEffect battleEffect : trainerContainer.getBattleEffects()) {
					battleEffect.affect(pokemonContainer);
				}

				// build the pokemon container owners map
				pokemonContainerOwners.put(pokemonContainer, trainerContainer);

				// Apply effects of each pokemon container to its' skill containers
				for (SkillContainer skillContainer : pokemonContainer.getSkillContainers()) {
					for (BattleEffect battleEffect : pokemonContainer.getBattleEffects()) {
						battleEffect.affect(skillContainer);
					}
				}
			}
		}

		List<Map.Entry<PokemonContainer, Turn>> turns = getSortedTurnList(round);

		for (Map.Entry<PokemonContainer, Turn> turnEntry : turns) {
			PokemonContainer pokemonContainer = turnEntry.getKey();
			TrainerContainer trainerContainer = pokemonContainerOwners.get(pokemonContainer);
			Turn turn = turnEntry.getValue();

			if (turn == null) {
				continue;
			}

			List<BattleEffect> turnEffects = getSortedBattleEffects(round, trainerContainer, pokemonContainer, turn);

			// Apply every turn affect to every turn affect with lower priority
			for (int i = 0; i < turnEffects.size(); i++) {
				BattleEffect battleEffect = turnEffects.get(i);

				for (int j = i + 1; j < turnEffects.size(); j++) {
					battleEffect.affect(turnEffects.get(j), battle, trainerContainer, pokemonContainer, turn);
				}
			}

			// Apply all effects to the battle
			for (BattleEffect turnEffect : turnEffects) {
				turnEffect.affect(battle, trainerContainer, pokemonContainer, turn);
			}
		}
	}

	protected List<Map.Entry<PokemonContainer, Turn>> getSortedTurnList(Round round) {
		List<Map.Entry<PokemonContainer, Turn>> turnList = new ArrayList<Map.Entry<PokemonContainer, Turn>>(round
				.getTurns().entrySet());
		Collections.sort(turnList, getTurnComparator());

		return turnList;
	}

	protected List<BattleEffect> getSortedBattleEffects(Round round, TrainerContainer trainerContainer,
			PokemonContainer pokemonContainer, Turn turn) {
		List<BattleEffect> battleEffects = new ArrayList<BattleEffect>();

		battleEffects.addAll(round.getBattleEffects());
		battleEffects.addAll(trainerContainer.getBattleEffects());
		battleEffects.addAll(pokemonContainer.getBattleEffects());
		battleEffects.addAll(turn.getBattleEffects());

		Collections.sort(battleEffects, getBattleEffectComparator());

		return battleEffects;
	}
}
