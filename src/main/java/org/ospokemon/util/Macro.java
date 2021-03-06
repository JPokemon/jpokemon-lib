package org.ospokemon.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ospokemon.Move;
import org.ospokemon.Nature;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonContainer;
import org.ospokemon.Skill;
import org.ospokemon.SkillContainer;
import org.ospokemon.Species;
import org.ospokemon.Stat;

public final class Macro {
	public static int calculateStatMaxValue(Pokemon pokemon, String statName) {
		Species species = Species.manager.get(pokemon.getSpecies());
		Stat stat = pokemon.getStat(statName);
		Nature nature = Nature.manager.get(pokemon.getNature());

		int base = species.getBaseStat(statName);
		int ev = stat.getEv();
		int iv = stat.getIv();
		int level = pokemon.getLevel();

		int value = iv + 2 * base + ev / 4 + 100;
		value *= level;
		value /= 100;
		value += 10;

		if (statName.equals(nature.getStatIncreased())) {
			value *= 1.1;
		}
		else if (statName.equals(nature.getStatDecreased())) {
			value *= 0.9;
		}

		return value;
	}

	public static final Pokemon buildPokemonWithSpeciesAndLevel(String speciesName, int level) {
		return buildPokemonWithSpeciesAndLevel(Species.manager.get(speciesName), level);
	}

	public static final Pokemon buildPokemonWithSpeciesAndLevel(Species species, int level) {
		Pokemon pokemon = new Pokemon();
		pokemon.setSpecies(species.getName());
		pokemon.setLevel(level);

		int abilityNumber = (int) (Math.random() * species.getAbilities().size());
		String abilityName = species.getAbilities().get(abilityNumber);
		pokemon.setAbility(abilityName);

		List<Nature> allNatures = Nature.manager.getAll();
		int natureNumber = (int) (Math.random() * allNatures.size());
		Nature nature = allNatures.get(natureNumber);
		pokemon.setNature(nature.getName());

		for (Map.Entry<String, Integer> baseStat : species.getBaseStats().entrySet()) {
			String statName = baseStat.getKey();
			Stat stat = new Stat();
			pokemon.addStat(statName, stat);

			int iv = (int) (Math.random() * 32);
			stat.setIv(iv);
			int value = Macro.calculateStatMaxValue(pokemon, statName);
			stat.setValue(value);
		}

		Set<String> moveNames = new HashSet<String>();
		for (String moveName : species.getEggMoves()) {
			moveNames.add(moveName);
		}
		for (Map.Entry<Integer, List<String>> moveList : species.getMoveLists().entrySet()) {
			if (moveList.getKey() <= level) {
				moveNames.addAll(moveList.getValue());
			}
		}

		for (String moveName : moveNames) {
			Move move = Move.manager.get(moveName);
			Skill skill = new Skill();
			skill.setMove(moveName);
			skill.setCurrentPP(move.getPP());
			skill.setMaxPP(move.getPP());

			pokemon.addSkill(skill);
		}

		if (Math.random() < 0.01) {
			pokemon.setShiny(true);
		}

		double height = 1.1 * species.getHeight() - (Math.random() * 0.2 * species.getHeight());
		pokemon.setHeight(height);

		double weight = 1.1 * species.getWeight() - (Math.random() * 0.2 * species.getWeight());
		pokemon.setWeight(weight);

		return pokemon;
	}

	public static final PokemonContainer buildPokemonContainerWithPokemon(Pokemon pokemon) {
		PokemonContainer pokemonContainer = new PokemonContainer();
		pokemonContainer.setPokemon(pokemon);
		pokemonContainer.setSpecies(pokemon.getSpecies());

		for (Skill skill : pokemon.getSkills()) {
			SkillContainer skillContainer = new SkillContainer();
			skillContainer.setSkill(skill);

			pokemonContainer.getSkillContainers().add(skillContainer);
		}

		return pokemonContainer;
	}
}
