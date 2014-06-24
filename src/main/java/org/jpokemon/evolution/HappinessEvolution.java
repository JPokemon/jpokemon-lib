package org.jpokemon.evolution;

import org.jpokemon.api.Evolution;
import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible evolution type for happiness
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class HappinessEvolution implements Evolution {
	/** Indicates the happiness rating at which the Pokémon evolves */
	protected int happiness;

	/** Indicates the species the Pokémon will evolve into. */
	protected String species;

	/** Gets the happiness rating the Pokémon evolves at */
	public int getHappiness() {
		return happiness;
	}

	/** Sets the happiness rating the Pokémon evolves at */
	public HappinessEvolution setHappiness(int happiness) {
		this.happiness = happiness;
		return this;
	}

	@Override
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Provides an {@link EvolutionFactory} which builds
	 * {@link HappinessEvolution} instances. Expects integer happiness score as
	 * options.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Factory extends EvolutionFactory {
		@Override
		public Class<HappinessEvolution> getEvolutionClass() {
			return HappinessEvolution.class;
		}

		@Override
		public Evolution buildEvolution(String options) throws JPokemonException {
			HappinessEvolution happinessEvolution = new HappinessEvolution();

			try {
				int happiness = Integer.parseInt(options);
				happinessEvolution.setHappiness(happiness);
			} catch (NumberFormatException e) {
				throw new JPokemonException("Expected happiness integer score: " + options);
			}

			return happinessEvolution;
		}

		@Override
		public String serializeEvolution(Evolution evolution) throws JPokemonException {
			if (!(evolution instanceof HappinessEvolution)) {
				throw new JPokemonException("Expected happiness evolution object: " + evolution);
			}

			HappinessEvolution happinessEvolution = (HappinessEvolution) evolution;
			return Integer.toString(happinessEvolution.getHappiness());
		}
	}
}
