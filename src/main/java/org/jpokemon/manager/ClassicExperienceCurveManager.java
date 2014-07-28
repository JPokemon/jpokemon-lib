package org.jpokemon.manager;

import org.jpokemon.api.ExperienceCurve;
import org.jpokemon.experiencecurve.ErraticExperienceCurve;
import org.jpokemon.experiencecurve.FastExperienceCurve;
import org.jpokemon.experiencecurve.FluctuatingExperienceCurve;
import org.jpokemon.experiencecurve.MediumFastExperienceCurve;
import org.jpokemon.experiencecurve.MediumSlowExperienceCurve;
import org.jpokemon.experiencecurve.SlowExperienceCurve;

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
