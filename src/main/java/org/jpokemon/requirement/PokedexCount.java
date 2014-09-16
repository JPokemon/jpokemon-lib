package org.jpokemon.requirement;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Requirement;

public class PokedexCount extends Requirement {
	protected int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean test(PokemonTrainer pokemonTrainer, Pokemon pokemon) {
		// TODO - add pokedex to pokemon trainer
		return true;
	}

	public static class Builder implements org.jpokemon.api.Builder<Requirement> {
		@Override
		public String getId() {
			return PokedexCount.class.getName();
		}

		@Override
		public Requirement construct(String options) throws JPokemonException {
			PokedexCount pokedexCountRequirement = new PokedexCount();
			pokedexCountRequirement.setCount(Integer.parseInt(options));
			return pokedexCountRequirement;
		}

		@Override
		public String destruct(Requirement requirement) throws JPokemonException {
			PokedexCount pokedexCountRequirement = (PokedexCount) requirement;
			return Integer.toString(pokedexCountRequirement.getCount());
		}
	}
}
