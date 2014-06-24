package org.jpokemon;

import org.jpokemon.api.EvolutionFactory;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.evolution.HappinessEvolution;
import org.jpokemon.evolution.LevelEvolution;
import org.jpokemon.evolution.StoneEvolution;
import org.jpokemon.manager.SimpleEvolutionFactoryManager;

/**
 * Provides an example implementation of the {@link EvolutionFactory#manager},
 * using several classic evolution types
 * 
 * @see HappinessEvolution.Factory
 * @see LevelEvolution.Factory
 * @see StoneEvolution.Factory
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicEvolutionFactoryManager extends SimpleEvolutionFactoryManager {
	public ClassicEvolutionFactoryManager() {
		register(new HappinessEvolution.Factory());
		register(new LevelEvolution.Factory());
		register(new StoneEvolution.Factory());
	}

	/**
	 * Initializes a new ClassicEvolutionFactoryManager as the
	 * {@link EvolutionFactory#manager}
	 * 
	 * @throws JPokemonException If the EvolutionFactory.manager is already
	 *           defined
	 */
	public static void init() throws JPokemonException {
		if (EvolutionFactory.manager != null) {
			throw new JPokemonException("EvolutionFactory.manager is already defined");
		}

		EvolutionFactory.manager = new ClassicEvolutionFactoryManager();
	}
}
