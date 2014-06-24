package org.jpokemon.property.item;

import org.jpokemon.api.PropertyProvider;

/**
 * Provides a possible property for items describing qualities of machine items,
 * including move name. TM / HM consumability and naming differences should be
 * implemented as part of the Item class. Proper usage of this attribute is
 * attaching a move name.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class MachineProperty {
	/** Indicates the move name of this machine */
	protected String moveName;

	/** Provides the default constructor */
	public MachineProperty() {
	}

	/** Gets the move name of this machine */
	public String getMoveName() {
		return moveName;
	}

	/** Sets the move name of this machine */
	public MachineProperty setMoveName(String moveName) {
		this.moveName = moveName;
		return this;
	}

	/**
	 * Provides an implementation of {@link PropertyProvider} which can build
	 * {@link MachineProperty}s.
	 * 
	 * @author zach
	 * 
	 * @since 0.1
	 */
	public static class Provider extends PropertyProvider<MachineProperty> {
		/** Provides the default constructor */
		public Provider() {
		}

		@Override
		public String getName() {
			return MachineProperty.class.getName();
		}

		@Override
		public MachineProperty build(String options) {
			MachineProperty machineProperty = new MachineProperty();
			machineProperty.setMoveName(options);
			return machineProperty;
		}

		@Override
		public String serialize(Object object) {
			MachineProperty machineProperty = (MachineProperty) object;
			return machineProperty.getMoveName();
		}
	}
}
