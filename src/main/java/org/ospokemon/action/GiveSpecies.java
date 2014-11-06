package org.ospokemon.action;

import java.util.HashMap;
import java.util.Map;

import org.ospokemon.Action;
import org.ospokemon.JPokemonException;
import org.ospokemon.Overworld;
import org.ospokemon.OverworldEntity;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.util.Options;

public class GiveSpecies extends Action {
	protected String species;

	protected boolean giving = true;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public boolean isGiving() {
		return giving;
	}

	public void setGiving(boolean giving) {
		this.giving = giving;
	}

	@Override
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		if (isGiving()) {
			// TODO PokemonFactory
			Pokemon pokemon = Pokemon.manager.create();
			pokemon.setSpecies(getSpecies());

			pokemonTrainer.addPokemon(pokemon);

			if (pokemon.getOriginalTrainerName() != null && pokemon.getOriginalTrainerID() == 0) {
				pokemon.setOriginalTrainerID(pokemonTrainer.getTrainerID());
				pokemon.setOriginalTrainerName(pokemonTrainer.getName());
			}
		}
		else {
			for (Pokemon pokemon : pokemonTrainer.getPokemon()) {
				if (species.equals(pokemon.getSpecies())) {
					pokemonTrainer.removePokemon(pokemon);
					break; // don't use our iterator ever again
				}
			}
		}
	}

	public static class Builder implements org.ospokemon.Builder<Action> {
		@Override
		public String getId() {
			return GiveSpecies.class.getName();
		}

		@Override
		public Action construct(String o) throws JPokemonException {
			Map<String, String> options = Options.parseMap(o);
			GiveSpecies pokemonAction = new GiveSpecies();

			pokemonAction.setSpecies(options.get("species"));

			if (options.containsKey("giving")) {
				pokemonAction.setGiving(Boolean.parseBoolean(options.get("giving")));
			}

			return pokemonAction;
		}

		@Override
		public String destruct(Action action) throws JPokemonException {
			GiveSpecies pokemonAction = (GiveSpecies) action;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("giving", pokemonAction.isGiving());
			map.put("species", pokemonAction.getSpecies());

			return Options.serializeMap(map);
		}
	}
}
