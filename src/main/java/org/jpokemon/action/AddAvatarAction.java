package org.jpokemon.action;

import org.jpokemon.api.Action;
import org.jpokemon.api.ActionFactory;
import org.jpokemon.api.ActionSet;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.property.trainer.AvatarsProperty;

public class AddAvatarAction implements Action {
	protected String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public void execute(Overworld overworld, OverworldEntity entity, ActionSet actionSet, PokemonTrainer pokemonTrainer) {
		AvatarsProperty avatarsProperty = pokemonTrainer.getProperty(AvatarsProperty.class);

		if (!avatarsProperty.getAvailableAvatars().contains(getAvatar())) {
			avatarsProperty.addAvailableAvatar(getAvatar());
		}
	}

	public static class Factory extends ActionFactory {
		@Override
		public String getName() {
			return AddAvatarAction.class.getName();
		}

		@Override
		public Action buildAction(String options) throws JPokemonException {
			AddAvatarAction addAvatarAction = new AddAvatarAction();
			addAvatarAction.setAvatar(options);
			return addAvatarAction;
		}

		@Override
		public String serializeAction(Action action) throws JPokemonException {
			AddAvatarAction addAvatarAction = (AddAvatarAction) action;
			return addAvatarAction.getAvatar();
		}
	}
}
