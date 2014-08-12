package org.jpokemon.action;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.Action;
import org.jpokemon.api.ActionFactory;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.property.trainer.TrainerAffinity;
import org.jpokemon.util.Options;

public class AffinityAction implements Action {

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
	public void execute(PokemonTrainer pokemonTrainer) throws JPokemonException {
		TrainerAffinity trainerAffinity = pokemonTrainer.getProperty(TrainerAffinity.class);

		for (Map.Entry<String, Integer> scoreBonus : getScores().entrySet()) {
			int oldScore = trainerAffinity.getScore(scoreBonus.getKey());
			trainerAffinity.setScore(scoreBonus.getKey(), oldScore + scoreBonus.getValue());
		}
	}

	public static class Factory extends ActionFactory {
		@Override
		public String getName() {
			return AffinityAction.class.getName();
		}

		@Override
		public Action buildAction(String options) throws JPokemonException {
			AffinityAction affinityAction = new AffinityAction();

			for (Map.Entry<String, String> option : Options.parseMap(options).entrySet()) {
				affinityAction.setScore(option.getKey(), Integer.parseInt(option.getValue()));
			}

			return affinityAction;
		}

		@Override
		public String serializeAction(Action action) throws JPokemonException {
			AffinityAction affinityAction = (AffinityAction) action;
			return Options.serializeMap(affinityAction.getScores());
		}
	}
}
