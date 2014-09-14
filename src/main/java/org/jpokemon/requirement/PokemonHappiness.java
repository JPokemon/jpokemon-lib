package org.jpokemon.requirement;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Requirement;

public class PokemonHappiness extends Requirement {
	/** Indicates the happiness rating at which the Pokémon evolves */
	protected int happiness;

	/** Gets the happiness rating the Pokémon evolves at */
	public int getHappiness() {
		return happiness;
	}

	/** Sets the happiness rating the Pokémon evolves at */
	public PokemonHappiness setHappiness(int happiness) {
		this.happiness = happiness;
		return this;
	}

	@Override
	public boolean test(PokemonTrainer pokemonTrainer, Pokemon pokemon) {
		if (pokemon != null) { // we are testing the Pokémon
			return pokemon.getFriendship() >= getHappiness();
		}
		else { // we are testing all the trainers Pokémon
			for (Pokemon p : pokemonTrainer.getPokemon()) {
				if (p.getFriendship() >= getHappiness()) {
					return true;
				}
			}

			return false;
		}
	}

	public static class Builder implements org.jpokemon.api.Builder<Requirement> {
		@Override
		public Class<? extends Requirement> getOutputClass() {
			return PokemonHappiness.class;
		}

		@Override
		public Requirement construct(String options) throws JPokemonException {
			PokemonHappiness happinessEvolution = new PokemonHappiness();

			try {
				int happiness = Integer.parseInt(options);
				happinessEvolution.setHappiness(happiness);
			} catch (NumberFormatException e) {
				throw new JPokemonException("Expected happiness integer score: " + options);
			}

			return happinessEvolution;
		}

		@Override
		public String destruct(Requirement requirement) throws JPokemonException {
			if (!(requirement instanceof PokemonHappiness)) {
				throw new JPokemonException("Expected happiness evolution object: " + requirement);
			}

			PokemonHappiness happinessEvolution = (PokemonHappiness) requirement;
			return Integer.toString(happinessEvolution.getHappiness());
		}
	}
}