package org.jpokemon.property.trainer;

import java.util.ArrayList;
import java.util.List;

import org.jpokemon.api.PropertyProvider;

public class AvatarsProperty {
	protected String avatar;

	protected List<String> availableAvatars;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void addAvailableAvatar(String avatar) {
		if (availableAvatars == null) {
			availableAvatars = new ArrayList<String>();
		}

		availableAvatars.add(avatar);
	}

	public void removeAvailableAvatar(String avatar) {
		if (availableAvatars != null) {
			availableAvatars.remove(avatar);
		}
	}

	public List<String> getAvailableAvatars() {
		if (availableAvatars == null) {
			availableAvatars = new ArrayList<String>();
		}

		return availableAvatars;
	}

	public void setAvailableAvatars(List<String> availableAvatars) {
		this.availableAvatars = availableAvatars;
	}

	public static class Provider extends PropertyProvider<AvatarsProperty> {
		@Override
		public String getName() {
			return AvatarsProperty.class.getName();
		}

		@Override
		public AvatarsProperty build(String options) {
			int firstCommaIndex = options.indexOf(',');
			AvatarsProperty avatarsPropery = new AvatarsProperty();

			avatarsPropery.setAvatar(options.substring(0, firstCommaIndex));
			for (String avatar : options.substring(firstCommaIndex + 1).split(",")) {
				avatarsPropery.addAvailableAvatar(avatar);
			}

			return avatarsPropery;
		}

		@Override
		public String serialize(Object object) {
			AvatarsProperty avatarsProperty = (AvatarsProperty) object;
			return avatarsProperty.getAvatar() + ','
					+ avatarsProperty.getAvailableAvatars().toString().substring(1).replace("]", "");
		}
	}
}
