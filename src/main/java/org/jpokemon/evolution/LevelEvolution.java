package org.jpokemon.evolution;

import org.jpokemon.api.Evolution;
import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible evolution type for level-up
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class LevelEvolution implements Evolution {
	/** Indicates the level at which the Pokémon evolves */
	protected int level;

	/** Indicates the species the Pokémon will evolve into. */
	protected String species;

	/** Gets the level the Pokémon will evolve at. */
	public int getLevel() {
		return this.level;
	}

	/** Sets the level the Pokémon will evolve at. */
	public LevelEvolution setLevel(int level) {
		this.level = level;
		return this;
	}

	@Override
	public String getSpecies() {
		return this.species;
	}

	@Override
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Provides an {@link EvolutionFactory} which builds {@link LevelEvolution}
	 * instances. Expects integer level as options.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Factory extends EvolutionFactory {
		@Override
		public Class<LevelEvolution> getEvolutionClass() {
			return LevelEvolution.class;
		}

		@Override
		public Evolution buildEvolution(String options) throws JPokemonException {
			LevelEvolution levelEvolution = new LevelEvolution();

			try {
				int level = Integer.parseInt(options);
				levelEvolution.setLevel(level);
			} catch (NumberFormatException e) {
				throw new JPokemonException("Expected integer level: " + options);
			}

			return levelEvolution;
		}

		@Override
		public String serializeEvolution(Evolution evolution) throws JPokemonException {
			if (!(evolution instanceof LevelEvolution)) {
				throw new JPokemonException("Expected level evolution object: " + evolution);
			}

			LevelEvolution levelEvolution = (LevelEvolution) evolution;
			return Integer.toString(levelEvolution.getLevel());
		}
	}
}
