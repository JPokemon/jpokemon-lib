package org.jpokemon.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpokemon.api.BattleEffect;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.util.Options;

public class BattleEffectsContainer {
	protected List<BattleEffect> battleEffects;

	public BattleEffectsContainer addBattleEffect(BattleEffect battleEffect) {
		getBattleEffects().add(battleEffect);
		return this;
	}

	public BattleEffectsContainer removeBattleEffect(BattleEffect battleEffect) {
		getBattleEffects().remove(battleEffect);
		return this;
	}

	public List<BattleEffect> getBattleEffects() {
		if (battleEffects == null) {
			battleEffects = new ArrayList<BattleEffect>();
		}
		return battleEffects;
	}

	public BattleEffectsContainer setBattleEffects(List<BattleEffect> battleEffects) {
		this.battleEffects = battleEffects;
		return this;
	}

	public static class Builder implements org.jpokemon.api.Builder<Object> {
		@Override
		public String getId() {
			return BattleEffectsContainer.class.getName();
		}

		@Override
		public BattleEffectsContainer construct(String options) throws JPokemonException {
			if (BattleEffect.builders == null) {
				throw new JPokemonException("Cannot build battle effects without BattleEffect.builders");
			}

			BattleEffectsContainer battleEffectsProperty = new BattleEffectsContainer();
			Map<String, String> effects = Options.parseMap(options);

			for (Map.Entry<String, String> entry : effects.entrySet()) {
				String battleEffectName = entry.getKey();
				String battleEffectOptions = entry.getValue();

				org.jpokemon.api.Builder<BattleEffect> battleEffectBuilder = BattleEffect.builders.get(battleEffectName);
				BattleEffect battleEffect = battleEffectBuilder.construct(battleEffectOptions);

				battleEffectsProperty.addBattleEffect(battleEffect);
			}

			return battleEffectsProperty;
		}

		@Override
		public String destruct(Object object) throws JPokemonException {
			if (BattleEffect.builders == null) {
				throw new JPokemonException("Cannot serialize battle effects without BattleEffect.builders");
			}

			BattleEffectsContainer battleEffectsProperty = (BattleEffectsContainer) object;
			Map<String, String> serializedEffectOptions = new HashMap<String, String>();

			for (BattleEffect battleEffect : battleEffectsProperty.getBattleEffects()) {
				String battleEffectName = battleEffect.getClass().getName();
				org.jpokemon.api.Builder<BattleEffect> battleEffectBuilder = BattleEffect.builders.get(battleEffectName);
				String battleEffectOptions = battleEffectBuilder.destruct(battleEffect);
				serializedEffectOptions.put(battleEffectName, battleEffectOptions);
			}

			return Options.serializeMap(serializedEffectOptions);
		}
	}
}
