package org.jpokemon.action;

import org.jpokemon.api.Action;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.property.trainer.Avatars;

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

	public static class Builder implements org.jpokemon.api.Builder<Action> {
		@Override
		public Class<? extends Action> getOutputClass() {
			return AddAvatar.class;
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
