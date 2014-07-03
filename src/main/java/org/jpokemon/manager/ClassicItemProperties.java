package org.jpokemon.manager;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;
import org.jpokemon.property.item.BallProperty;
import org.jpokemon.property.item.BerryProperty;
import org.jpokemon.property.item.EvolutionStoneProperty;
import org.jpokemon.property.item.MachineProperty;
import org.jpokemon.property.item.MedicineProperty;
import org.jpokemon.property.item.PocketProperty;

/**
 * Provides a static class which will initialize several classic item
 * properties. To initalize these classes, call {@link #init}.
 * 
 * @see BallProperty.Provider
 * @see BerryProperty.Provider
 * @see EvolutionStoneProperty.Provider
 * @see MachineProperty.Provider
 * @see MedicineProperty.Provider
 * @see PocketProperty.Provider
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicItemProperties {
	private ClassicItemProperties() {
	}

	/**
	 * Calls the {@link PropertyProvider} to initialize the item properties
	 * defined in this library.
	 */
	public static void init() throws JPokemonException {
		PropertyProvider.register(new BallProperty.Provider());
		PropertyProvider.register(new BerryProperty.Provider());
		PropertyProvider.register(new EvolutionStoneProperty.Provider());
		PropertyProvider.register(new MachineProperty.Provider());
		PropertyProvider.register(new MedicineProperty.Provider());
		PropertyProvider.register(new PocketProperty.Provider());
	}
}
