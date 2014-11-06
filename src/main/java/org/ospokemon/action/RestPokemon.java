package org.ospokemon.action;

import org.ospokemon.Action;
import org.ospokemon.Overworld;
import org.ospokemon.OverworldEntity;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Stat;
import org.ospokemon.builder.SingletonBuilder;
import org.ospokemon.util.Macro;

public class RestPokemon extends Action {
	@Override
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		for (Pokemon pokemon : pokemonTrainer.getPokemon()) {
			Stat healthStat = pokemon.getStat("health");
			int maxValue = Macro.calculateStatMaxValue(pokemon, "health");
			healthStat.setValue(maxValue);
		}
	}

	public static class Builder extends SingletonBuilder<Action> {
		public Builder() {
			super(new RestPokemon());
		}
	}
}
