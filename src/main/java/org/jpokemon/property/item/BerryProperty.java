package org.jpokemon.property.item;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;
import org.jpokemon.util.Options;

/**
 * Provides a possible property for items describing qualities of berry items,
 * including growth rate and flavors.
 * 
 * @author atheriel@gmail.com
 * @author zach
 * 
 * @since 0.1
 */
public class BerryProperty {
	/** Indicates the time it takes to grow this berry */
	protected String growthTime;

	/** Indicates the flavors of this berry */
	protected Map<String, Integer> flavors;

	/** Provides the default constructor */
	public BerryProperty() {
	}

	/** Gets the amount of time this berry requires to grow */
	public String getGrowthTime() {
		return growthTime;
	}

	/** Sets the amount of time this berry requires to grow */
	public BerryProperty setGrowthTime(String growthTime) {
		this.growthTime = growthTime;
		return this;
	}

	/** Gets the amount of flavor for the given flavor. */
	public int getFlavor(String flavor) {
		if (flavors == null || !flavors.containsKey(flavor)) {
			return -1;
		}

		return flavors.get(flavor);
	}

	/** Sets the amount of bitter flavor for this berry */
	public BerryProperty setFlavor(String flavor, int strength) {
		if (flavors == null) {
			flavors = new HashMap<String, Integer>();
		}

		flavors.put(flavor, strength);
		return this;
	}

	/** Gets all flavor strengths for this berry */
	public Map<String, Integer> getFlavors() {
		return flavors;
	}

	/** Sets all flavor strengths for this berry */
	public BerryProperty setFlavors(Map<String, Integer> flavors) {
		this.flavors = flavors;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link BerryProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<BerryProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public String getName() {
			return BerryProperty.class.getName();
		}

		@Override
		public BerryProperty build(String optionString) throws JPokemonException {
			BerryProperty berryProperty = new BerryProperty();
			Map<String, String> options = Options.parseMap(optionString);

			try {
				berryProperty.setGrowthTime(options.get("growthtime"));

				for (Map.Entry<String, String> entry : options.entrySet()) {
					String flavor = entry.getKey();
					String strength = entry.getValue();

					if (flavor.equals("growthtime")) {
						continue;
					}

					berryProperty.setFlavor(flavor, Integer.parseInt(strength));
				}
			} catch (NumberFormatException e) {
				throw new JPokemonException(e);
			}

			return berryProperty;
		}

		@Override
		public String serialize(Object object) {
			BerryProperty berryProperty = (BerryProperty) object;
			Map<String, String> options = new HashMap<String, String>();

			for (Map.Entry<String, Integer> entry : berryProperty.getFlavors().entrySet()) {
				options.put(entry.getKey(), Integer.toString(entry.getValue()));
			}

			options.put("growthtime", berryProperty.getGrowthTime());

			return Options.serializeMap(options);
		}
	}
}
