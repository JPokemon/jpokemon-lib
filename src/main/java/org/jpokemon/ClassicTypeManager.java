package org.jpokemon;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Type;
import org.jpokemon.manager.SimpleTypeManager;

/**
 * Provides a possible implementation of the {@link Type#manager}, using the 18
 * classic types
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicTypeManager extends SimpleTypeManager {
	/** Indicates the name of the "???" type */
	public static final String QUESTION_NAME = "???";

	/** Indicates the name of the normal type */
	public static final String NORMAL_NAME = "Normal";

	/** Indicates the name of the fire type */
	public static final String FIRE_NAME = "Fire";

	/** Indicates the name of the water type */
	public static final String WATER_NAME = "Water";

	/** Indicates the name of the grass type */
	public static final String GRASS_NAME = "Grass";

	/** Indicates the name of the electric type */
	public static final String ELECTRIC_NAME = "Electric";

	/** Indicates the name of the ice type */
	public static final String ICE_NAME = "Ice";

	/** Indicates the name of the fighting type */
	public static final String FIGHTING_NAME = "Fighting";

	/** Indicates the name of the poison type */
	public static final String POISON_NAME = "Poison";

	/** Indicates the name of the ground type */
	public static final String GROUND_NAME = "Ground";

	/** Indicates the name of the flying type */
	public static final String FLYING_NAME = "Flying";

	/** Indicates the name of the psychic type */
	public static final String PSYCHIC_NAME = "Psychic";

	/** Indicates the name of the bug type */
	public static final String BUG_NAME = "Bug";

	/** Indicates the name of the rock type */
	public static final String ROCK_NAME = "Rock";

	/** Indicates the name of the ghost type */
	public static final String GHOST_NAME = "Ghost";

	/** Indicates the name of the dragon type */
	public static final String DRAGON_NAME = "Dragon";

	/** Indicates the name of the dark type */
	public static final String DARK_NAME = "Dark";

	/** Indicates the name of the steel type */
	public static final String STEEL_NAME = "Steel";

	/** Indicates the name of the fairy type */
	public static final String FAIRY_NAME = "Fairy";

	/** Provides the default constructor */
	public ClassicTypeManager() {
		register(new Type().setName(QUESTION_NAME));
		register(new Type().setName(NORMAL_NAME).setNotVeryEffectiveAgainst(ROCK_NAME, STEEL_NAME)
				.setIneffectiveAgainst(GHOST_NAME));
		register(new Type().setName(FIRE_NAME).setSuperEffectiveAgainst(BUG_NAME, GRASS_NAME, ICE_NAME, STEEL_NAME)
				.setNotVeryEffectiveAgainst(DRAGON_NAME, FIRE_NAME, ROCK_NAME, WATER_NAME));
		register(new Type().setName(WATER_NAME).setSuperEffectiveAgainst(FIRE_NAME, GROUND_NAME, ROCK_NAME)
				.setNotVeryEffectiveAgainst(WATER_NAME, GRASS_NAME, DRAGON_NAME));
		register(new Type().setName(GRASS_NAME).setSuperEffectiveAgainst(WATER_NAME, GROUND_NAME, ROCK_NAME)
				.setNotVeryEffectiveAgainst(FIRE_NAME, GRASS_NAME, POISON_NAME, FLYING_NAME, BUG_NAME, DRAGON_NAME, STEEL_NAME));
		register(new Type().setName(ELECTRIC_NAME).setSuperEffectiveAgainst(WATER_NAME, FLYING_NAME)
				.setNotVeryEffectiveAgainst(ELECTRIC_NAME, GRASS_NAME, DRAGON_NAME).setIneffectiveAgainst(GROUND_NAME));
		register(new Type().setName(ICE_NAME).setSuperEffectiveAgainst(GRASS_NAME, GROUND_NAME, FLYING_NAME, DRAGON_NAME)
				.setNotVeryEffectiveAgainst(FIRE_NAME, WATER_NAME, ICE_NAME, STEEL_NAME));
		register(new Type().setName(FIGHTING_NAME)
				.setSuperEffectiveAgainst(NORMAL_NAME, ICE_NAME, ROCK_NAME, DARK_NAME, STEEL_NAME)
				.setNotVeryEffectiveAgainst(POISON_NAME, FLYING_NAME, PSYCHIC_NAME, BUG_NAME, FAIRY_NAME)
				.setIneffectiveAgainst(GHOST_NAME));
		register(new Type().setName(POISON_NAME).setSuperEffectiveAgainst(GRASS_NAME, FAIRY_NAME)
				.setNotVeryEffectiveAgainst(POISON_NAME, GROUND_NAME, ROCK_NAME, GHOST_NAME).setIneffectiveAgainst(STEEL_NAME));
		register(new Type().setName(GROUND_NAME)
				.setSuperEffectiveAgainst(FIRE_NAME, ELECTRIC_NAME, POISON_NAME, ROCK_NAME, STEEL_NAME)
				.setNotVeryEffectiveAgainst(BUG_NAME, GRASS_NAME).setIneffectiveAgainst(FLYING_NAME));
		register(new Type().setName(FLYING_NAME).setSuperEffectiveAgainst(GRASS_NAME, FIGHTING_NAME, BUG_NAME)
				.setNotVeryEffectiveAgainst(ELECTRIC_NAME, ROCK_NAME, STEEL_NAME));
		register(new Type().setName(PSYCHIC_NAME).setSuperEffectiveAgainst(FIGHTING_NAME, POISON_NAME)
				.setNotVeryEffectiveAgainst(PSYCHIC_NAME, STEEL_NAME).setIneffectiveAgainst(DARK_NAME));
		register(new Type()
				.setName(BUG_NAME)
				.setSuperEffectiveAgainst(GRASS_NAME, PSYCHIC_NAME, DARK_NAME)
				.setNotVeryEffectiveAgainst(FAIRY_NAME, FIRE_NAME, FIGHTING_NAME, POISON_NAME, FLYING_NAME, GHOST_NAME,
						STEEL_NAME));
		register(new Type().setName(ROCK_NAME).setSuperEffectiveAgainst(FIRE_NAME, ICE_NAME, FLYING_NAME, BUG_NAME)
				.setNotVeryEffectiveAgainst(FIGHTING_NAME, GROUND_NAME, STEEL_NAME));
		register(new Type().setName(GHOST_NAME).setSuperEffectiveAgainst(GHOST_NAME, PSYCHIC_NAME)
				.setNotVeryEffectiveAgainst(DARK_NAME).setIneffectiveAgainst(NORMAL_NAME));
		register(new Type().setName(DRAGON_NAME).setSuperEffectiveAgainst(DRAGON_NAME)
				.setNotVeryEffectiveAgainst(STEEL_NAME).setIneffectiveAgainst(FAIRY_NAME));
		register(new Type().setName(DARK_NAME).setSuperEffectiveAgainst(PSYCHIC_NAME, GHOST_NAME)
				.setNotVeryEffectiveAgainst(FIGHTING_NAME, DARK_NAME, FAIRY_NAME));
		register(new Type().setName(STEEL_NAME).setSuperEffectiveAgainst(ICE_NAME, ROCK_NAME, FAIRY_NAME)
				.setNotVeryEffectiveAgainst(FIRE_NAME, WATER_NAME, ELECTRIC_NAME, STEEL_NAME));
		register(new Type().setName(FAIRY_NAME).setSuperEffectiveAgainst(DARK_NAME, DRAGON_NAME, FIGHTING_NAME)
				.setNotVeryEffectiveAgainst(FIRE_NAME, POISON_NAME, STEEL_NAME));
	}

	/**
	 * Initializes a new ClassicTypeManager as the {@link Type#manager}
	 * 
	 * @throws JPokemonException If the Type.manager is already defined
	 */
	public static void init() throws JPokemonException {
		if (Type.manager != null) {
			throw new JPokemonException("Type.manager is already defined");
		}

		Type.manager = new ClassicTypeManager();
	}
}
