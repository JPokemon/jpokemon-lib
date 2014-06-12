package org.jpokemon;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.SimpleItemAttributeFactoryManager;
import org.jpokemon.itemattribute.BallAttribute;
import org.jpokemon.itemattribute.BerryAttribute;
import org.jpokemon.itemattribute.EvolutionStoneAttribute;
import org.jpokemon.itemattribute.MachineAttribute;
import org.jpokemon.itemattribute.MedicineAttribute;
import org.jpokemon.itemattribute.PocketAttribute;

/**
 * Provides an example implementation of the
 * {@link ItemAttributeFactory#manager}, using several classic item attributes.
 * 
 * @see BallAttribute.Factory
 * @see BerryAttribute.Factory
 * @see EvolutionStoneAttribute.Factory
 * @see MachineAttribute.Factory
 * @see MedicineAttribute.Factory
 * @see PocketAttribute.Factory
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicItemAttributeFactoryManager extends SimpleItemAttributeFactoryManager {
	public ClassicItemAttributeFactoryManager() {
		register(new BallAttribute.Factory());
		register(new BerryAttribute.Factory());
		register(new EvolutionStoneAttribute.Factory());
		register(new MachineAttribute.Factory());
		register(new MedicineAttribute.Factory());
		register(new PocketAttribute.Factory());
	}

	/**
	 * Initializes a new ClassicItemAttributeFactoryManager as the
	 * {@link ItemAttributeFactory#manager}
	 * 
	 * @throws JPokemonException If the ItemAttributeFactory.manager is already
	 *           defined
	 */
	public static void init() throws JPokemonException {
		if (ItemAttributeFactory.manager != null) {
			throw new JPokemonException("ItemAttributeFactory.manager is already defined");
		}

		ItemAttributeFactory.manager = new ClassicItemAttributeFactoryManager();
	}
}
