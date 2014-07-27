package org.jpokemon.property.item;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;
import org.jpokemon.util.Options;

/**
 * Provides a possible property for items describing qualities of medicine
 * items, including stat affected, medicine strength, and permanence.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class MedicineProperty {
	/** Indicates the stat that this medicine affects */
	protected String stat;

	/** Indicates the strength of this medicine */
	protected int strength;

	/** Indicates whether this medicine will wear off */
	protected boolean permanent;

	/** Provides the default constructor */
	public MedicineProperty() {
	}

	/** Gets the stat that this medicine affects */
	public String getStat() {
		return stat;
	}

	/** Sets the stat that this medicine affects */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/** Gets the strength of this medicine */
	public int getStrength() {
		return strength;
	}

	/** Sets the strength of this medicine */
	public MedicineProperty setStrength(int strength) {
		this.strength = strength;
		return this;
	}

	/** Gets whether this medicine will wear off */
	public boolean isPermanent() {
		return permanent;
	}

	/** Sets whether this medicine will wear off */
	public MedicineProperty setPermanent(boolean permanent) {
		this.permanent = permanent;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link MedicineProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<MedicineProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public String getName() {
			return MedicineProperty.class.getName();
		}

		@Override
		public MedicineProperty build(String optionString) throws JPokemonException {
			MedicineProperty medicineProperty = new MedicineProperty();
			Map<String, String> options = Options.parseMap(optionString);

			try {
				medicineProperty.setStat(options.get("stat"));
				medicineProperty.setStrength(Integer.parseInt(options.get("strength")));
				medicineProperty.setPermanent(Boolean.parseBoolean(options.get("permanent")));
			} catch (NumberFormatException e) {
				throw new JPokemonException(e);
			} catch (IndexOutOfBoundsException e) {
				throw new JPokemonException(e);
			}

			return medicineProperty;
		}

		@Override
		public String serialize(Object object) {
			MedicineProperty medicineProperty = (MedicineProperty) object;
			Map<String, String> options = new HashMap<String, String>();

			options.put("stat", medicineProperty.getStat());
			options.put("strength", Integer.toString(medicineProperty.getStrength()));
			options.put("permanent", Boolean.toString(medicineProperty.isPermanent()));

			return Options.serializeMap(options);
		}
	}
}
