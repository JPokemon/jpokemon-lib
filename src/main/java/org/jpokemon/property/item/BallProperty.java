package org.jpokemon.property.item;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;

/**
 * Provides a possible property for items describing qualities of ball items,
 * including catch rate. This can be extended to include type modifiers, etc.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class BallProperty {
	/** Indicates the catch rate of this ball */
	protected int catchRate;

	/** Provides the default constructor */
	public BallProperty() {
	}

	/** Gets the catch rate of this ball */
	public int getCatchRate() {
		return this.catchRate;
	}

	/** Sets the catch rate of this ball */
	public BallProperty setCatchRate(int catchRate) {
		this.catchRate = catchRate;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link BallProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<BallProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public String getName() {
			return BallProperty.class.getName();
		}

		@Override
		public BallProperty build(String options) throws JPokemonException {
			BallProperty ballProperty = new BallProperty();

			try {
				int catchRate = Integer.parseInt(options);
				ballProperty.setCatchRate(catchRate);
			} catch (NumberFormatException e) {
				throw new JPokemonException(e);
			}

			return ballProperty;
		}

		@Override
		public String serialize(Object object) throws JPokemonException {
			BallProperty ballProperty = (BallProperty) object;
			return Integer.toString(ballProperty.getCatchRate());
		}
	}
}
