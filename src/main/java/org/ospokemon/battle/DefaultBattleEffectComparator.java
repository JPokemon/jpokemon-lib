package org.ospokemon.battle;

import java.util.Comparator;

import org.ospokemon.BattleEffect;

public class DefaultBattleEffectComparator implements Comparator<BattleEffect> {
	@Override
	public int compare(BattleEffect arg0, BattleEffect arg1) {
		return arg0.getPriority() - arg1.getPriority();
	}
}
