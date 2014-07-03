package org.jpokemon.evolution;

import org.jpokemon.api.Evolution;
import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible evolution type for evolution stone usage
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class StoneEvolution implements Evolution {
	/** Indicates the stone type applicable to this evolution */
	protected String type;

	/** Indicates the species the Pokémon will evolve into. */
	protected String species;

	/** Getter for the stone type of this evolution */
	public String getType() {
		return this.type;
	}

	/** Setter for the stone type of this evolution */
	public StoneEvolution setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String getSpecies() {
		return this.species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Provides an {@link EvolutionFactory} which builds {@link StoneEvolution}
	 * instances. Assigns options as stone type.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Factory extends EvolutionFactory {
		@Override
		public Class<StoneEvolution> getEvolutionClass() {
			return StoneEvolution.class;
		}

		@Override
		public Evolution buildEvolution(String species, String options) {
			StoneEvolution stoneEvolution = new StoneEvolution();
			stoneEvolution.setSpecies(species);
			stoneEvolution.setType(options);
			return stoneEvolution;
		}

		@Override
		public String serializeEvolution(Evolution evolution) throws JPokemonException {
			if (!(evolution instanceof StoneEvolution)) {
				throw new JPokemonException("Expected stone evolution object: " + evolution);
			}

			StoneEvolution stoneEvolution = (StoneEvolution) evolution;
			return stoneEvolution.getType();
		}
	}
}
