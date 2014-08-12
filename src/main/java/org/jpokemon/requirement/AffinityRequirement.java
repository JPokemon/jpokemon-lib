package org.jpokemon.requirement;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Requirement;
import org.jpokemon.api.RequirementFactory;
import org.jpokemon.property.trainer.TrainerAffinity;
import org.jpokemon.util.Options;

public class AffinityRequirement implements Requirement {

	protected int totalScore;

	protected Map<String, Integer> scores = new HashMap<String, Integer>();

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

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
	public boolean isSatisfied(PokemonTrainer pokemonTrainer) {
		TrainerAffinity trainerAffinity = pokemonTrainer.getProperty(TrainerAffinity.class);

		if (getTotalScore() > 0 && getTotalScore() > trainerAffinity.getTotalScore()) {
			return false;
		}

		for (Map.Entry<String, Integer> scoreRequirement : getScores().entrySet()) {
			if (trainerAffinity.getScore(scoreRequirement.getKey()) < scoreRequirement.getValue()) {
				return false;
			}
		}

		return true;
	}

	public static class Factory extends RequirementFactory {
		@Override
		public String getName() {
			return AffinityRequirement.class.getName();
		}

		@Override
		public Requirement buildRequirement(String o) throws JPokemonException {
			AffinityRequirement affinityRequirement = new AffinityRequirement();

			for (Map.Entry<String, String> option : Options.parseMap(o).entrySet()) {
				String key = option.getKey();
				int value = Integer.parseInt(option.getValue());

				if ("total".equals(key)) {
					affinityRequirement.setTotalScore(value);
				}
				else {
					affinityRequirement.setScore(key, value);
				}
			}

			return affinityRequirement;
		}

		@Override
		public String serializeRequirement(Requirement requirement) throws JPokemonException {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
