package org.jpokemon.property.trainer;

import java.util.ArrayList;
import java.util.List;

import org.jpokemon.api.PropertyProvider;
import org.jpokemon.util.Options;

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
			List<String> avatars = Options.parseArray(options);
			AvatarsProperty avatarsPropery = new AvatarsProperty();

			avatarsPropery.setAvatar(avatars.get(0));
			avatarsPropery.setAvailableAvatars(avatars);

			return avatarsPropery;
		}

		@Override
		public String serialize(Object object) {
			AvatarsProperty avatarsProperty = (AvatarsProperty) object;
			List<String> availableAvatars = avatarsProperty.getAvailableAvatars();
			availableAvatars.remove(avatarsProperty.getAvatar());
			List<String> avatars = new ArrayList<String>();
			avatars.add(avatarsProperty.getAvatar());
			avatars.addAll(availableAvatars);

			return Options.serializeArray(avatars);
		}
	}
}
