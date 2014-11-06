package org.ospokemon.requirement;

import org.ospokemon.JPokemonException;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Requirement;

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

	public static class Builder implements org.ospokemon.Builder<Requirement> {
		@Override
		public String getId() {
			return PokemonHappiness.class.getName();
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