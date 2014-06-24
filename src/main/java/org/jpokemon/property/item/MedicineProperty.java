package org.jpokemon.property.item;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;

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

			try {
				String[] options = optionString.split(",");

				String stat = options[0];
				medicineProperty.setStat(stat);

				int strength = Integer.parseInt(options[1]);
				medicineProperty.setStrength(strength);

				boolean permanent = Boolean.parseBoolean(options[2]);
				medicineProperty.setPermanent(permanent);
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
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(medicineProperty.getStat());
			stringBuilder.append(',');
			stringBuilder.append(medicineProperty.getStrength());
			stringBuilder.append(',');
			stringBuilder.append(medicineProperty.isPermanent());

			return stringBuilder.toString();
		}
	}
}
