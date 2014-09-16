package org.jpokemon.requirement;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Requirement;

/**
 * Provides a possible evolution type for level-up
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class PokemonLevel extends Requirement {
	protected int level;

	/** Gets the level the Pokémon will evolve at. */
	public int getLevel() {
		return this.level;
	}

	/** Sets the level the Pokémon will evolve at. */
	public PokemonLevel setLevel(int level) {
		this.level = level;
		return this;
	}

	@Override
	public boolean test(PokemonTrainer pokemonTrainer, Pokemon pokemon) {
		if (pokemon != null) { // we are testing the Pokémon
			return pokemon.getLevel() >= getLevel();
		}
		else { // we are testing all the trainers Pokémon
			for (Pokemon p : pokemonTrainer.getPokemon()) {
				if (p.getLevel() >= getLevel()) {
					return true;
				}
			}

			return false;
		}
	}

	public static class Builder implements org.jpokemon.api.Builder<Requirement> {
		@Override
		public String getId() {
			return PokemonLevel.class.getName();
		}

		@Override
		public Requirement construct(String options) throws JPokemonException {
			PokemonLevel levelRequirement = new PokemonLevel();

			try {
				int level = Integer.parseInt(options);
				levelRequirement.setLevel(level);
			} catch (NumberFormatException e) {
				throw new JPokemonException("Expected integer level: " + options);
			}

			return levelRequirement;
		}

		@Override
		public String destruct(Requirement evolution) throws JPokemonException {
			if (!(evolution instanceof PokemonLevel)) {
				throw new JPokemonException("Expected level evolution object: " + evolution);
			}

			PokemonLevel levelEvolution = (PokemonLevel) evolution;
			return Integer.toString(levelEvolution.getLevel());
		}
	}
}
