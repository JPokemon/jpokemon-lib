package org.jpokemon.action;

import org.jpokemon.api.Action;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.api.Stat;
import org.jpokemon.extra.DefaultBuilder;
import org.jpokemon.util.Calculator;

public class RestPokemon extends Action {
	@Override
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		for (Pokemon pokemon : pokemonTrainer.getPokemon()) {
			Stat healthStat = pokemon.getStat("health");
			int maxValue = Calculator.stat(pokemon, "health");
			healthStat.setValue(maxValue);
		}
	}

	public static class Builder extends DefaultBuilder<Action> {
		public Builder() {
			super(new RestPokemon());
		}
	}
}
