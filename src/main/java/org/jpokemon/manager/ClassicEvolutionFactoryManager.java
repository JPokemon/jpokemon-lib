package org.jpokemon.manager;

import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.evolution.HappinessEvolution;
import org.jpokemon.evolution.LevelEvolution;
import org.jpokemon.evolution.MegaEvolution;
import org.jpokemon.evolution.StoneEvolution;

/**
 * Provides an example implementation of the {@link EvolutionFactory#manager},
 * using several classic evolution types
 * 
 * @see HappinessEvolution.Factory
 * @see LevelEvolution.Factory
 * @see StoneEvolution.Factory
 * @see MegaEvolution.Factory
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicEvolutionFactoryManager extends SimpleManager<EvolutionFactory> {
	public ClassicEvolutionFactoryManager() {
		super(EvolutionFactory.class);
		register(new HappinessEvolution.Factory());
		register(new LevelEvolution.Factory());
		register(new StoneEvolution.Factory());
		register(new MegaEvolution.Factory());
	}
}
