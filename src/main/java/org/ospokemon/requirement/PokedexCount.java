package org.ospokemon.requirement;

import org.ospokemon.JPokemonException;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Requirement;

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

	public static class Builder implements org.ospokemon.Builder<Requirement> {
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
