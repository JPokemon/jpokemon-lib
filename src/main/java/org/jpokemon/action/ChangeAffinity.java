package org.jpokemon.action;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.Action;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.property.trainer.TrainerAffinity;
import org.jpokemon.util.Options;

public class ChangeAffinity extends Action {
	protected Map<String, Integer> scores = new HashMap<String, Integer>();

	public int getScore(String type) {
		return scores.get(type);
	}

	public void setScore(String type, int score) {
		scores.put(type, score);
	}

	public void removeScore(String type) {
		scores.remove(type);
	}

	public Map<String, Integer> getScores() {
		return scores;
	}

	public void setScores(Map<String, Integer> scores) {
		this.scores = scores;
	}

	@Override
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		TrainerAffinity trainerAffinity = pokemonTrainer.getProperty(TrainerAffinity.class);

		for (Map.Entry<String, Integer> scoreBonus : getScores().entrySet()) {
			int oldScore = trainerAffinity.getScore(scoreBonus.getKey());
			trainerAffinity.setScore(scoreBonus.getKey(), oldScore + scoreBonus.getValue());
		}
	}

	public static class Builder implements org.jpokemon.api.Builder<Action> {
		@Override
		public String getId() {
			return ChangeAffinity.class.getName();
		}

		@Override
		public Action construct(String options) throws JPokemonException {
			ChangeAffinity affinityAction = new ChangeAffinity();

			for (Map.Entry<String, String> option : Options.parseMap(options).entrySet()) {
				affinityAction.setScore(option.getKey(), Integer.parseInt(option.getValue()));
			}

			return affinityAction;
		}

		@Override
		public String destruct(Action action) throws JPokemonException {
			ChangeAffinity affinityAction = (ChangeAffinity) action;
			return Options.serializeMap(affinityAction.getScores());
		}
	}
}
