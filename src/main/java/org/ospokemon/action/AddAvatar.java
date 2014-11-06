package org.ospokemon.action;

import org.ospokemon.Action;
import org.ospokemon.Overworld;
import org.ospokemon.OverworldEntity;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.property.trainer.Avatars;

public class AddAvatar extends Action {
	protected String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		Avatars avatarsProperty = pokemonTrainer.getProperty(Avatars.class);

		if (!avatarsProperty.getAvailableAvatars().contains(getAvatar())) {
			avatarsProperty.addAvailableAvatar(getAvatar());
		}
	}

	public static class Builder implements org.ospokemon.Builder<Action> {
		@Override
		public String getId() {
			return AddAvatar.class.getName();
		}

		@Override
		public Action construct(String options) {
			AddAvatar a = new AddAvatar();
			a.setAvatar(options);
			return a;
		}

		@Override
		public String destruct(Action object) {
			AddAvatar a = (AddAvatar) object;
			return a.getAvatar();
		}
	}
}
