package org.jpokemon.property.item;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.util.Options;

/**
 * Provides a possible property for items describing qualities of medicine
 * items, including stat affected, medicine strength, and permanence.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class MedicineProperties {
	/** Indicates the stat that this medicine affects */
	protected String stat;

	/** Indicates the strength of this medicine */
	protected int strength;

	/** Indicates whether this medicine will wear off */
	protected boolean permanent;

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
	public MedicineProperties setStrength(int strength) {
		this.strength = strength;
		return this;
	}

	/** Gets whether this medicine will wear off */
	public boolean isPermanent() {
		return permanent;
	}

	/** Sets whether this medicine will wear off */
	public MedicineProperties setPermanent(boolean permanent) {
		this.permanent = permanent;
		return this;
	}

	public static class Builder implements org.jpokemon.api.Builder<Object> {
		@Override
		public String getId() {
			return MedicineProperties.class.getName();
		}

		@Override
		public Object construct(String optionString) throws JPokemonException {
			MedicineProperties medicineProperty = new MedicineProperties();
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
		public String destruct(Object object) {
			MedicineProperties medicineProperty = (MedicineProperties) object;
			Map<String, String> options = new HashMap<String, String>();

			options.put("stat", medicineProperty.getStat());
			options.put("strength", Integer.toString(medicineProperty.getStrength()));
			options.put("permanent", Boolean.toString(medicineProperty.isPermanent()));

			return Options.serializeMap(options);
		}
	}
}
