package org.jpokemon.property.item;

import org.jpokemon.api.PropertyProvider;

/**
 * Provides a possible property for items describing the pocket an item belongs
 * in.
 * 
 * @author atheriel@gmail.com
 * @author zach
 * 
 * @since 0.1
 */
public class PocketProperty {
	private String pocketName;

	/** Provides the default constructor. */
	public PocketProperty() {
	}

	/** Gets the name of the pocket this item belongs to. */
	public String getPocketName() {
		return pocketName;
	}

	/** Sets the name of the pocket this item belongs to. */
	public PocketProperty setPocketName(String pocketName) {
		this.pocketName = pocketName;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link PocketProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<PocketProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public Class<PocketProperty> getPropertyClass() {
			return PocketProperty.class;
		}

		@Override
		public PocketProperty build(String options) {
			PocketProperty pocketProperty = new PocketProperty();
			pocketProperty.setPocketName(options);
			return pocketProperty;
		}

		@Override
		public String serialize(Object object) {
			PocketProperty pocketProperty = (PocketProperty) object;
			return pocketProperty.getPocketName();
		}
	}
}
