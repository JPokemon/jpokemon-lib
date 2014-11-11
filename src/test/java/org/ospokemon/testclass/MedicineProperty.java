package org.ospokemon.testclass;

import org.ospokemon.Property;

public class MedicineProperty {
	protected String stat;

	protected int strength;

	protected boolean permanent;

	public MedicineProperty() {
	}

	public MedicineProperty(String stat, int strength, boolean permanent) {
		this.stat = stat;
		this.strength = strength;
		this.permanent = permanent;
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
	 * Provides an implementation of {@link Property} which can build
	 * {@link MedicineProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Builder implements org.ospokemon.Builder<Object> {
		@Override
		public String getId() {
			return MedicineProperty.class.getName();
		}

		@Override
		public Object construct(String optionString) {
			String[] options = optionString.split(",");

			String stat = options[0];
			int strength = Integer.parseInt(options[1]);
			boolean permanent = Boolean.parseBoolean(options[2]);

			return new MedicineProperty(stat, strength, permanent);
		}

		@Override
		public String destruct(Object object) {
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
