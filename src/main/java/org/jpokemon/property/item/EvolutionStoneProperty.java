package org.jpokemon.property.item;

import org.jpokemon.api.PropertyProvider;

/**
 * Provides a possible property for items describing qualities of evolution
 * stones, namely its' type.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class EvolutionStoneProperty {
	/** Indicates the type that evolves with this evolution stone item */
	protected String type;

	public EvolutionStoneProperty() {
	}

	public String getType() {
		return type;
	}

	public EvolutionStoneProperty setType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link EvolutionStoneProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<EvolutionStoneProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public String getName() {
			return EvolutionStoneProperty.class.getName();
		}

		@Override
		public EvolutionStoneProperty build(String options) {
			EvolutionStoneProperty evolutionStoneProperty = new EvolutionStoneProperty();
			evolutionStoneProperty.setType(options);
			return evolutionStoneProperty;
		}

		@Override
		public String serialize(Object object) {
			EvolutionStoneProperty evolutionStoneProperty = (EvolutionStoneProperty) object;
			return evolutionStoneProperty.getType();
		}
	}
}
