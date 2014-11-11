package org.ospokemon.testclass;

import org.ospokemon.Action;
import org.ospokemon.JPokemonException;
import org.ospokemon.Overworld;
import org.ospokemon.OverworldEntity;
import org.ospokemon.PokemonTrainer;

public class EmptyActionBuilder implements org.ospokemon.Builder<Action> {
	public static String ID = "empty";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public Action construct(String options) {
		return new Action() {
			@Override
			public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) throws JPokemonException {
			}
		};
	}

	@Override
	public String destruct(Action object) {
		return null;
	}
}
