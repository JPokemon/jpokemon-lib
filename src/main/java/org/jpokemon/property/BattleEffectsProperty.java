package org.jpokemon.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.BattleEffectFactory;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;
import org.jpokemon.util.Options;

public class BattleEffectsProperty {
	protected List<BattleEffect> battleEffects;

	public BattleEffectsProperty addBattleEffect(BattleEffect battleEffect) {
		if (battleEffects == null) {
			battleEffects = new ArrayList<BattleEffect>();
		}

		battleEffects.add(battleEffect);
		return this;
	}

	public BattleEffectsProperty removeBattleEffect(BattleEffect battleEffect) {
		if (battleEffects != null) {
			battleEffects.remove(battleEffect);
		}

		return this;
	}

	public List<BattleEffect> getBattleEffects() {
		if (battleEffects == null) {
			battleEffects = new ArrayList<BattleEffect>();
		}

		return battleEffects;
	}

	public BattleEffectsProperty setBattleEffects(List<BattleEffect> battleEffects) {
		this.battleEffects = battleEffects;
		return this;
	}

	public static class Provider extends PropertyProvider<BattleEffectsProperty> {
		@Override
		public String getName() {
			return BattleEffectsProperty.class.getName();
		}

		@Override
		public BattleEffectsProperty build(String options) throws JPokemonException {
			if (BattleEffectFactory.manager == null) {
				throw new JPokemonException("Cannot build battle effects without BattleEffectFactory.manager");
			}

			BattleEffectsProperty battleEffectsProperty = new BattleEffectsProperty();
			Map<String, String> effects = Options.parseMap(options);

			for (Map.Entry<String, String> entry : effects.entrySet()) {
				String battleEffectName = entry.getKey();
				String battleEffectOptions = entry.getValue();

				BattleEffectFactory battleEffectFactory = BattleEffectFactory.manager.getByName(battleEffectName);
				BattleEffect battleEffect = battleEffectFactory.buildBattleEffect(battleEffectOptions);

				battleEffectsProperty.addBattleEffect(battleEffect);
			}

			return battleEffectsProperty;
		}

		@Override
		public String serialize(Object object) throws JPokemonException {
			if (BattleEffectFactory.manager == null) {
				throw new JPokemonException("Cannot serialize battle effects without BattleEffectFactory.manager");
			}

			BattleEffectsProperty battleEffectsProperty = (BattleEffectsProperty) object;
			Map<String, String> serializedEffectOptions = new HashMap<String, String>();

			for (BattleEffect battleEffect : battleEffectsProperty.getBattleEffects()) {
				String battleEffectName = battleEffect.getClass().getName();
				BattleEffectFactory battleEffectFactory = BattleEffectFactory.manager.getByName(battleEffectName);
				String battleEffectOptions = battleEffectFactory.serializeBattleEffect(battleEffect);
				serializedEffectOptions.put(battleEffectName, battleEffectOptions);
			}

			return Options.serializeMap(serializedEffectOptions);
		}
	}
}
