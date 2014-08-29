package org.jpokemon.action;

import org.jpokemon.api.Action;
import org.jpokemon.api.ActionFactory;
import org.jpokemon.api.ActionSet;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Stat;
import org.jpokemon.util.Calculator;

public class RestAction implements Action {
	@Override
	public void execute(Overworld overworld, OverworldEntity entity, ActionSet actionSet, PokemonTrainer pokemonTrainer) {
		for (Pokemon pokemon : pokemonTrainer.getPokemon()) {
			Stat healthStat = pokemon.getStat("health");
			int maxValue = Calculator.stat(pokemon, "health");
			healthStat.setValue(maxValue);
		}
	}

	public static class Factory extends ActionFactory {
		@Override
		public String getName() {
			return RestAction.class.getName();
		}

		@Override
		public Action buildAction(String options) throws JPokemonException {
			return new RestAction();
		}

		@Override
		public String serializeAction(Action action) throws JPokemonException {
			return null;
		}
	}
}
