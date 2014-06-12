package org.jpokemon.itemattribute;

import org.jpokemon.api.ItemAttributeFactory;
import org.jpokemon.api.JPokemonException;

/**
 * Provides a possible item attribute describing qualities of machine items,
 * including move name. TM / HM consumability and naming differences should be
 * implemented as part of the Item class. Proper usage of this attribute is
 * attaching a move name.
 * 
 * <p>
 * 
 * NOTE: {@link #equals} returns true when the object in question is the same
 * class as this. This is to prevent an Item from storing multiple
 * MachineAttributes.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class MachineAttribute {
	/** Indicates the move name of this machine */
	protected String moveName;

	/** Provides the default constructor */
	public MachineAttribute() {
	}

	/** Gets the move name of this machine */
	public String getMoveName() {
		return moveName;
	}

	/** Sets the move name of this machine */
	public MachineAttribute setMoveName(String moveName) {
		this.moveName = moveName;
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
	 * {@link MachineAttribute}s.
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
		public Class<MachineAttribute> getItemAttributeClass() {
			return MachineAttribute.class;
		}

		@Override
		public Object buildItemAttribute(String options) {
			MachineAttribute machineAttribute = new MachineAttribute();
			machineAttribute.setMoveName(options);
			return machineAttribute;
		}

		@Override
		public String serializeItemAttribute(Object object) throws JPokemonException {
			if (!(object instanceof MachineAttribute)) {
				throw new JPokemonException("Expected machine item attribute object: " + object);
			}

			MachineAttribute machineAttribute = (MachineAttribute) object;
			return machineAttribute.getMoveName();
		}
	}
}
