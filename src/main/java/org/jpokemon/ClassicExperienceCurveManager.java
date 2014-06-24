package org.jpokemon;

import org.jpokemon.api.ExperienceCurve;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.experiencecurve.ErraticExperienceCurve;
import org.jpokemon.experiencecurve.FastExperienceCurve;
import org.jpokemon.experiencecurve.FluctuatingExperienceCurve;
import org.jpokemon.experiencecurve.MediumFastExperienceCurve;
import org.jpokemon.experiencecurve.MediumSlowExperienceCurve;
import org.jpokemon.experiencecurve.SlowExperienceCurve;
import org.jpokemon.manager.SimpleExperienceCurveManager;

/**
 * Provides an example implementation of the {@link ExperienceCurve#manager},
 * using several classic experience curves
 * 
 * @see ErraticExperienceCurve
 * @see FastExperienceCurve
 * @see FluctuatingExperienceCurve
 * @see MediumFastExperienceCurve
 * @see MediumSlowExperienceCurve
 * @see SlowExperienceCurve
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicExperienceCurveManager extends SimpleExperienceCurveManager {
	public ClassicExperienceCurveManager() {
		register(new ErraticExperienceCurve());
		register(new FastExperienceCurve());
		register(new FluctuatingExperienceCurve());
		register(new MediumFastExperienceCurve());
		register(new MediumSlowExperienceCurve());
		register(new SlowExperienceCurve());
	}

	/**
	 * Initializes a new ClassicExperienceCurveManager as the
	 * {@link ExperienceCurve#manager}
	 * 
	 * @throws JPokemonException If the ExperienceCurve.manager is already defined
	 */
	public static void init() throws JPokemonException {
		if (ExperienceCurve.manager != null) {
			throw new JPokemonException("ExperienceCurve.manager is already defined");
		}

		ExperienceCurve.manager = new ClassicExperienceCurveManager();
	}
}
