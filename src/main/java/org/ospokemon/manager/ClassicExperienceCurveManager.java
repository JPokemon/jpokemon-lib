package org.ospokemon.manager;

import org.ospokemon.ExperienceCurve;
import org.ospokemon.experiencecurve.ErraticExperienceCurve;
import org.ospokemon.experiencecurve.FastExperienceCurve;
import org.ospokemon.experiencecurve.FluctuatingExperienceCurve;
import org.ospokemon.experiencecurve.MediumFastExperienceCurve;
import org.ospokemon.experiencecurve.MediumSlowExperienceCurve;
import org.ospokemon.experiencecurve.SlowExperienceCurve;
import org.ospokemon.manager.SimpleManager;

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
public class ClassicExperienceCurveManager extends SimpleManager<ExperienceCurve> {
	public ClassicExperienceCurveManager() {
		super(ExperienceCurve.class);
		register(new ErraticExperienceCurve());
		register(new FastExperienceCurve());
		register(new FluctuatingExperienceCurve());
		register(new MediumFastExperienceCurve());
		register(new MediumSlowExperienceCurve());
		register(new SlowExperienceCurve());
	}
}
