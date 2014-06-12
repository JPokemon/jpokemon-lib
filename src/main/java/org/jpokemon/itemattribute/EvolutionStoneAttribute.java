package org.jpokemon.itemattribute;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible item attribute describing qualities of evolution stone
 * items, namely its' type.
 * 
 * <p>
 * 
 * NOTE: {@link #equals} returns true when the object in question is the same
 * class as this. This is to prevent an Item from storing multiple
 * EvolutionStoneAttributes.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class EvolutionStoneAttribute {
	/** Indicates the type that evolves with this evolution stone item */
	protected String type;

	public EvolutionStoneAttribute() {
	}

	public String getType() {
		return type;
	}

	public EvolutionStoneAttribute setType(String type) {
		this.type = type;
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
	 * {@link EvolutionStoneAttribute}s.
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
		public Class<EvolutionStoneAttribute> getItemAttributeClass() {
			return EvolutionStoneAttribute.class;
		}

		@Override
		public Object buildItemAttribute(String options) {
			EvolutionStoneAttribute evolutionStoneAttribute = new EvolutionStoneAttribute();
			evolutionStoneAttribute.setType(options);
			return evolutionStoneAttribute;
		}

		@Override
		public String serializeItemAttribute(Object object) throws JPokemonException {
			if (!(object instanceof EvolutionStoneAttribute)) {
				throw new JPokemonException("Expected evolution stone item attribute object: " + object);
			}

			EvolutionStoneAttribute evolutionStoneAttribute = (EvolutionStoneAttribute) object;
			return evolutionStoneAttribute.getType();
		}
	}
}
