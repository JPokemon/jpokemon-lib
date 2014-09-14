package org.jpokemon.action;

import java.util.HashMap;
import java.util.Map;

import org.jpokemon.api.Action;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.PokemonTrainer;
import org.jpokemon.util.Options;

public class GiveItem extends Action {
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
	public void execute(Overworld overworld, OverworldEntity entity, PokemonTrainer pokemonTrainer) {
		pokemonTrainer.setItemQuantity(getItem(), pokemonTrainer.getItemQuantity(getItem()) + getAmount());
	}

	public static class Builder implements org.jpokemon.api.Builder<Action> {
		@Override
		public Class<? extends Action> getOutputClass() {
			return GiveItem.class;
		}

		@Override
		public Action construct(String o) throws JPokemonException {
			Map<String, String> options = Options.parseMap(o);
			GiveItem a = new GiveItem();

			a.setItem(options.get("item"));

			if (options.containsKey("amount")) {
				a.setAmount(Integer.parseInt(options.get("amount")));
			}

			return a;
		}

		@Override
		public String destruct(Action action) throws JPokemonException {
			GiveItem a = (GiveItem) action;

			Map<String, Object> options = new HashMap<String, Object>();
			options.put("item", a.getItem());
			options.put("amount", a.getAmount());

			return Options.serializeMap(options);
		}
	}
}
