package org.jpokemon.requirement;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Requirement;
import org.jpokemon.api.RequirementFactory;

public class PokedexCountRequirement implements Requirement {
	protected int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean isSatisfied(PokemonTrainer pokemonTrainer) {
		// #TODO - add pokedex to pokemon trainer
		return true;
	}

	public static class Factory extends RequirementFactory {
		@Override
		public String getName() {
			return PokedexCountRequirement.class.getName();
		}

		@Override
		public Requirement buildRequirement(String options) throws JPokemonException {
			PokedexCountRequirement pokedexCountRequirement = new PokedexCountRequirement();
			pokedexCountRequirement.setCount(Integer.parseInt(options));
			return pokedexCountRequirement;
		}

		@Override
		public String serializeRequirement(Requirement requirement) throws JPokemonException {
			PokedexCountRequirement pokedexCountRequirement = (PokedexCountRequirement) requirement;
			return Integer.toString(pokedexCountRequirement.getCount());
		}
	}
}
