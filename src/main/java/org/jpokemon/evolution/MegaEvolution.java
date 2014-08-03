package org.jpokemon.evolution;

import org.jpokemon.api.Evolution;
import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.api.JPokemonException;

public class MegaEvolution implements Evolution {
	protected String species;

	public MegaEvolution(String species) {
		this.species = species;
	}

	@Override
	public String getSpecies() {
		return species;
	}

	public static class Factory extends EvolutionFactory {
		@Override
		public String getName() {
			return MegaEvolution.class.getName();
		}

		@Override
		public Evolution buildEvolution(String species, String options) throws JPokemonException {
			return new MegaEvolution(species);
		}

		@Override
		public String serializeEvolution(Evolution evolution) throws JPokemonException {
			MegaEvolution megaEvolution = (MegaEvolution) evolution;
			return megaEvolution.getSpecies();
		}
	}
}
