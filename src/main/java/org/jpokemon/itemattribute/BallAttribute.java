package org.jpokemon.itemattribute;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible item attribute describing qualities of ball items,
 * including catch rate. This can be extended to include type modifiers, etc.
 * 
 * <p>
 * 
 * NOTE: {@link #equals} returns true when the object in question is the same
 * class as this. This is to prevent an Item from storing multiple
 * BallAttributes.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class BallAttribute {
	/** Indicates the catch rate of this ball */
	protected int catchRate;

	/** Provides the default constructor */
	public BallAttribute() {
	}

	/** Gets the catch rate of this ball */
	public int getCatchRate() {
		return this.catchRate;
	}

	/** Sets the catch rate of this ball */
	public BallAttribute setCatchRate(int catchRate) {
		this.catchRate = catchRate;
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
	 * {@link BallAttribute}s.
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
		public Class<BallAttribute> getItemAttributeClass() {
			return BallAttribute.class;
		}

		@Override
		public Object buildItemAttribute(String options) throws JPokemonException {
			BallAttribute ballAttribute = new BallAttribute();

			try {
				int catchRate = Integer.parseInt(options);
				ballAttribute.setCatchRate(catchRate);
			} catch (NumberFormatException e) {
				throw new JPokemonException("Expected catch rate integer score: " + options);
			}

			return ballAttribute;
		}

		@Override
		public String serializeItemAttribute(Object object) throws JPokemonException {
			if (!(object instanceof BallAttribute)) {
				throw new JPokemonException("Expected ball item attribute object: " + object);
			}

			BallAttribute ballAttribute = (BallAttribute) object;
			return Integer.toString(ballAttribute.getCatchRate());
		}
	}
}
