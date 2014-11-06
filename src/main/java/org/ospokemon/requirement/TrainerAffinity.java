package org.ospokemon.requirement;

import java.util.HashMap;
import java.util.Map;

import org.ospokemon.JPokemonException;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Requirement;
import org.ospokemon.util.Options;

public class TrainerAffinity extends Requirement {
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
	public boolean test(PokemonTrainer pokemonTrainer, Pokemon pokemon) {
		org.ospokemon.property.trainer.TrainerAffinity trainerAffinity = pokemonTrainer.getProperty(org.ospokemon.property.trainer.TrainerAffinity.class);

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

	public static class Builder implements org.ospokemon.Builder<Requirement> {
		@Override
		public String getId() {
			return TrainerAffinity.class.getName();
		}

		@Override
		public Requirement construct(String o) throws JPokemonException {
			TrainerAffinity affinityRequirement = new TrainerAffinity();

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
		public String destruct(Requirement requirement) throws JPokemonException {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
