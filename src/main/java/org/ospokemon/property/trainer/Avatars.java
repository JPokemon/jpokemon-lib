package org.ospokemon.property.trainer;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.util.Options;

public class Avatars {
	protected String avatar;

	protected List<String> availableAvatars;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void addAvailableAvatar(String avatar) {
		getAvailableAvatars().add(avatar);
	}

	public void removeAvailableAvatar(String avatar) {
		getAvailableAvatars().remove(avatar);
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

	public static class Builder implements org.ospokemon.Builder<Object> {
		@Override
		public String getId() {
			return Avatars.class.getName();
		}

		@Override
		public Object construct(String options) {
			List<String> avatars = Options.parseArray(options);
			Avatars avatarsPropery = new Avatars();

			avatarsPropery.setAvatar(avatars.get(0));
			avatarsPropery.setAvailableAvatars(avatars);

			return avatarsPropery;
		}

		@Override
		public String destruct(Object object) {
			Avatars avatarsProperty = (Avatars) object;
			List<String> availableAvatars = avatarsProperty.getAvailableAvatars();
			availableAvatars.remove(avatarsProperty.getAvatar());
			List<String> avatars = new ArrayList<String>();
			avatars.add(avatarsProperty.getAvatar());
			avatars.addAll(availableAvatars);

			return Options.serializeArray(avatars);
		}
	}
}
