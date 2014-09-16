package org.jpokemon.property.trainer;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.util.Options;

public class TrainerAffinity {
	protected Map<String, Integer> scores = new HashMap<String, Integer>();

	public int getTotalScore() {
		int totalScore = 0;

		for (Integer score : scores.values()) {
			totalScore += score;
		}

		return totalScore;
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

	public static class Builder implements org.jpokemon.api.Builder<Object> {
		@Override
		public String getId() {
			return TrainerAffinity.class.getName();
		}

		@Override
		public Object construct(String o) throws JPokemonException {
			TrainerAffinity trainerAffinity = new TrainerAffinity();

			for (Map.Entry<String, String> option : Options.parseMap(o).entrySet()) {
				String type = option.getKey();
				int value = Integer.parseInt(option.getValue());

				trainerAffinity.setScore(type, value);
			}
			return null;
		}

		@Override
		public String destruct(Object object) throws JPokemonException {
			TrainerAffinity trainerAffinity = (TrainerAffinity) object;
			return Options.serializeMap(trainerAffinity.getScores());
		}
	}
}
