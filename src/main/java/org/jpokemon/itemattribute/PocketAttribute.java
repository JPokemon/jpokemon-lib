package org.jpokemon.itemattribute;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible item attribute describing the pocket an item belongs to.
 * 
 * <p>
 * 
 * NOTE: {@link #equals} returns true when the object in question is the same
 * class as this. This is to prevent an Item from storing multiple
 * MedicineAttributes.
 * 
 * @author atheriel@gmail.com
 * @author zach
 * 
 * @since 0.1
 */
public class PocketAttribute {
	private String pocketName;

	/** Provides the default constructor. */
	public PocketAttribute() {
	}

	/** Gets the name of the pocket this item belongs to. */
	public String getPocketName() {
		return pocketName;
	}

	/** Sets the name of the pocket this item belongs to. */
	public PocketAttribute setPocketName(String pocketName) {
		this.pocketName = pocketName;
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
	 * {@link PocketAttribute}s.
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
		public Class<PocketAttribute> getItemAttributeClass() {
			return PocketAttribute.class;
		}

		@Override
		public Object buildItemAttribute(String options) {
			PocketAttribute pocketAttribute = new PocketAttribute();
			pocketAttribute.setPocketName(options);
			return pocketAttribute;
		}

		@Override
		public String serializeItemAttribute(Object object) throws JPokemonException {
			if (!(object instanceof PocketAttribute)) {
				throw new JPokemonException("Expected pocket item attribute object: " + object);
			}

			PocketAttribute pocketAttribute = (PocketAttribute) object;
			return pocketAttribute.getPocketName();
		}
	}
}
