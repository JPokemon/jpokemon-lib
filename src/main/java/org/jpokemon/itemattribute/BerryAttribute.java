package org.jpokemon.itemattribute;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible item attribute describing qualities of berry items,
 * including growth rate and flavors.
 * 
 * <p>
 * 
 * NOTE: {@link #equals} returns true when the object in question is the same
 * class as this. This is to prevent an Item from storing multiple
 * BerryAttributes.
 * 
 * @author atheriel@gmail.com
 * @author zach
 * 
 * @since 0.1
 */
public class BerryAttribute {
	/** Indicates the time it takes to grow this berry */
	protected String growthTime;

	/** Indicates the flavors of this berry */
	protected Map<String, Integer> flavors;

	/** Provides the default constructor */
	public BerryAttribute() {
	}

	/** Gets the amount of time this berry requires to grow */
	public String getGrowthTime() {
		return growthTime;
	}

	/** Sets the amount of time this berry requires to grow */
	public BerryAttribute setGrowthTime(String growthTime) {
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
	public BerryAttribute setFlavor(String flavor, int strength) {
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
	public BerryAttribute setFlavors(Map<String, Integer> flavors) {
		this.flavors = flavors;
		return this;
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return o.getClass().equals(this.getClass());
	}

	/**
	 * Provides an implementation of {@link ItemAttributeFactory} which can build
	 * {@link BerryAttribute BerryAttributes}.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Factory extends ItemAttributeFactory {
		/** Provides the default constructor */
		public Factory() {
		}

		@Override
		public Class<BerryAttribute> getItemAttributeClass() {
			return BerryAttribute.class;
		}

		@Override
		public Object buildItemAttribute(String optionString) throws JPokemonException {
			BerryAttribute berryAttribute = new BerryAttribute();

			try {
				String[] options = optionString.split(",");
				int growthTime = Integer.parseInt(options[0]);
				berryAttribute.setGrowthTime(growthTime + "");

				for (int i = 1; i < options.length; i++) {
					String[] flavorAssignment = options[i].split("=");
					String flavor = flavorAssignment[0];
					int strength = Integer.parseInt(flavorAssignment[1]);
					berryAttribute.setFlavor(flavor, strength);
				}
			} catch (NumberFormatException e) {
				// Too hard to actually tell why this failed
				throw new JPokemonException(e);
			} catch (IndexOutOfBoundsException e) {
				// Too hard to actually tell why this failed
				throw new JPokemonException(e);
			}

			return berryAttribute;
		}

		@Override
		public String serializeItemAttribute(Object object) throws JPokemonException {
			if (!(object instanceof BerryAttribute)) {
				throw new JPokemonException("Expected berry item attribute object: " + object);
			}

			BerryAttribute berryAttribute = (BerryAttribute) object;
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(berryAttribute.getGrowthTime());
			for (Map.Entry<String, Integer> flavorAssignment : berryAttribute.getFlavors().entrySet()) {
				stringBuilder.append(',');
				stringBuilder.append(flavorAssignment.getKey());
				stringBuilder.append('=');
				stringBuilder.append(flavorAssignment.getValue());
			}

			return stringBuilder.toString();
		}
	}
}
