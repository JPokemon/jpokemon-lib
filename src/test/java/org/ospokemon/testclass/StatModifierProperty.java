package org.ospokemon.testclass;

public class StatModifierProperty {
	protected String statName;
	protected double value;

	public StatModifierProperty() {
	}

	public StatModifierProperty(String statName, double value) {
		this.statName = statName;
		this.value = value;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public static class Builder implements org.ospokemon.Builder<Object> {
		@Override
		public String getId() {
			return StatModifierProperty.class.getName();
		}

		@Override
		public Object construct(String options) {
			String[] arguments = options.split(",");

			String statName = arguments[0];
			double value = Double.parseDouble(arguments[1]);

			return new StatModifierProperty(statName, value);
		}

		@Override
		public String destruct(Object object) {
			StatModifierProperty statModifier = (StatModifierProperty) object;
			return statModifier.getStatName() + ',' + statModifier.getValue();
		}
	}
}
