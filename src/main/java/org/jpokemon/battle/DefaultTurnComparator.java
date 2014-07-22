package org.jpokemon.battle;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import org.jpokemon.api.PokemonContainer;
import org.jpokemon.api.Turn;

public class DefaultTurnComparator implements Comparator<Map.Entry<PokemonContainer, Turn>> {
	@Override
	public int compare(Entry<PokemonContainer, Turn> arg0, Entry<PokemonContainer, Turn> arg1) {
		return arg0.getValue().getPriority() - arg1.getValue().getPriority();
	}
}
