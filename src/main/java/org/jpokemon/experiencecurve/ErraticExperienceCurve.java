package org.jpokemon.experiencecurve;

import org.jpokemon.api.ExperienceCurve;

/**
 * Provides a possible ExperienceCurve which has erratic growth.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ErraticExperienceCurve extends ExperienceCurve {
	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public int experienceRequiredForLevel(int level) {
		if (level <= 50) {
			return ((level * level * level) * (100 - level)) / 50;
		}
		else if (level <= 68) {
			return ((level * level * level) * (150 - level)) / 100;
		}
		else if (level <= 98) {
			return (int) ((level * level * level) * Math.floor((1911 - 10 * level) / 3)) / 500;
		}
		else {
			return ((level * level * level) * (160 - level)) / 100;
		}
	}
}
