package org.ospokemon.battle;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import org.ospokemon.PokemonContainer;
import org.ospokemon.Turn;

public class DefaultTurnComparator implements Comparator<Map.Entry<PokemonContainer, Turn>> {
	@Override
	public int compare(Entry<PokemonContainer, Turn> arg0, Entry<PokemonContainer, Turn> arg1) {
		return arg0.getValue().getPriority() - arg1.getValue().getPriority();
	}
}
