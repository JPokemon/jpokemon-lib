package org.jpokemon.util;

import org.jpokemon.api.Nature;
import org.jpokemon.api.Pokemon;
import org.jpokemon.api.Species;
import org.jpokemon.api.Stat;

public class Calculator {
	public static int stat(Pokemon pokemon, String statName) {
		Species species = Species.manager.getByName(pokemon.getSpecies());
		Stat stat = pokemon.getStat(statName);
		Nature nature = Nature.manager.getByName(pokemon.getNature());

		int base = species.getBaseStat(statName);
		int ev = stat.getEv();
		int iv = stat.getIv();
		int level = pokemon.getLevel();

		int value = iv + 2 * base + ev / 4 + 100;
		value *= level;
		value /= 100;
		value += 10;

		if (statName.equals(nature.getStatIncreased())) {
			value *= 1.1;
		}
		else if (statName.equals(nature.getStatDecreased())) {
			value *= 0.9;
		}

		return value;
	}
}
