package org.jpokemon.property.item;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;

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
		public Class<BerryProperty> getPropertyClass() {
			return BerryProperty.class;
		}

		@Override
		public BerryProperty build(String optionString) throws JPokemonException {
			BerryProperty berryProperty = new BerryProperty();

			try {
				String[] options = optionString.split(",");
				int growthTime = Integer.parseInt(options[0]);
				berryProperty.setGrowthTime(growthTime + "");

				for (int i = 1; i < options.length; i++) {
					String[] flavorAssignment = options[i].split("=");
					String flavor = flavorAssignment[0];
					int strength = Integer.parseInt(flavorAssignment[1]);
					berryProperty.setFlavor(flavor, strength);
				}
			} catch (NumberFormatException e) {
				throw new JPokemonException(e);
			} catch (IndexOutOfBoundsException e) {
				throw new JPokemonException(e);
			}

			return berryProperty;
		}

		@Override
		public String serialize(Object object) {
			BerryProperty berryProperty = (BerryProperty) object;
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(berryProperty.getGrowthTime());
			for (Map.Entry<String, Integer> flavorAssignment : berryProperty.getFlavors().entrySet()) {
				stringBuilder.append(',');
				stringBuilder.append(flavorAssignment.getKey());
				stringBuilder.append('=');
				stringBuilder.append(flavorAssignment.getValue());
			}

			return stringBuilder.toString();
		}
	}
}
