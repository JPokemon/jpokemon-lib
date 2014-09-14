package org.jpokemon.experiencecurve;

import org.jpokemon.api.ExperienceCurve;

/**
 * Provides a possible ExperienceCurve which has slow growth.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SlowExperienceCurve extends ExperienceCurve {
	@Override
	public String getId() {
		return getClass().getName();
	}

	@Override
	public int experienceRequiredForLevel(int level) {
		return 5 * level * level * level / 4;
	}
}
