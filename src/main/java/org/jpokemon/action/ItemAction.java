package org.jpokemon.action;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.Action;
import org.jpokemon.api.ActionFactory;
import org.jpokemon.api.ActionSet;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.util.Options;

public class ItemAction implements Action {
	protected String item;

	protected int amount;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void execute(Overworld overworld, OverworldEntity entity, ActionSet actionSet, PokemonTrainer pokemonTrainer) {
		pokemonTrainer.setItemQuantity(getItem(), pokemonTrainer.getItemQuantity(getItem()) + getAmount());
	}

	public static class Factory extends ActionFactory {
		@Override
		public String getName() {
			return ItemAction.class.getName();
		}

		@Override
		public Action buildAction(String o) throws JPokemonException {
			Map<String, String> options = Options.parseMap(o);
			ItemAction itemAction = new ItemAction();

			itemAction.setItem(options.get("item"));

			if (options.containsKey("amount")) {
				itemAction.setAmount(Integer.parseInt(options.get("amount")));
			}

			return itemAction;
		}

		@Override
		public String serializeAction(Action action) throws JPokemonException {
			ItemAction itemAction = (ItemAction) action;

			Map<String, Object> options = new HashMap<String, Object>();
			options.put("item", itemAction.getItem());
			options.put("amount", itemAction.getAmount());

			return Options.serializeMap(options);
		}
	}
}
