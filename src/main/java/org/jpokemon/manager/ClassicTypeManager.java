package org.jpokemon.manager;

import org.jpokemon.api.Type;

/**
 * Provides a possible implementation of the {@link Type#manager}, using the 18
 * classic types
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class ClassicTypeManager extends SimpleManager<Type> {
	/** Indicates the name of the "???" type */
	public static final String TYPE_QUESTION = "???";

	/** Indicates the name of the normal type */
	public static final String TYPE_NORMAL = "Normal";

	/** Indicates the name of the fire type */
	public static final String TYPE_FIRE = "Fire";

	/** Indicates the name of the water type */
	public static final String TYPE_WATER = "Water";

	/** Indicates the name of the grass type */
	public static final String TYPE_GRASS = "Grass";

	/** Indicates the name of the electric type */
	public static final String TYPE_ELECTRIC = "Electric";

	/** Indicates the name of the ice type */
	public static final String TYPE_ICE = "Ice";

	/** Indicates the name of the fighting type */
	public static final String TYPE_FIGHTING = "Fighting";

	/** Indicates the name of the poison type */
	public static final String TYPE_POISON = "Poison";

	/** Indicates the name of the ground type */
	public static final String TYPE_GROUND = "Ground";

	/** Indicates the name of the flying type */
	public static final String TYPE_FLYING = "Flying";

	/** Indicates the name of the psychic type */
	public static final String TYPE_PSYCHIC = "Psychic";

	/** Indicates the name of the bug type */
	public static final String TYPE_BUG = "Bug";

	/** Indicates the name of the rock type */
	public static final String TYPE_ROCK = "Rock";

	/** Indicates the name of the ghost type */
	public static final String TYPE_GHOST = "Ghost";

	/** Indicates the name of the dragon type */
	public static final String TYPE_DRAGON = "Dragon";

	/** Indicates the name of the dark type */
	public static final String TYPE_DARK = "Dark";

	/** Indicates the name of the steel type */
	public static final String TYPE_STEEL = "Steel";

	/** Indicates the name of the fairy type */
	public static final String TYPE_FAIRY = "Fairy";

	/** Indicates the value that represents "super effective" */
	public static final String EFFECTIVENESS_SUPER = "super";

	/** Indicates the value that represents "not very effective" */
	public static final String EFFECTIVENESS_NOT_VERY = "not very";

	/** Indicates the value that represents "ineffective" */
	public static final String EFFECTIVENESS_INEFFECTIVE = "ineffective";

	/** Provides the default constructor */
	public ClassicTypeManager() {
		super(Type.class);
		register(new Type().setName(TYPE_QUESTION));

		register(new Type().setName(TYPE_NORMAL).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GHOST, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_FIRE).setEffectiveness(TYPE_BUG, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_GRASS, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_ICE, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_WATER, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_WATER).setEffectiveness(TYPE_FIRE, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_WATER, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_GRASS).setEffectiveness(TYPE_WATER, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_POISON, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_FLYING, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_BUG, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_ELECTRIC).setEffectiveness(TYPE_WATER, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FLYING, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_ELECTRIC, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_GRASS, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_ICE).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FLYING, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_WATER, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_ICE, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_FIGHTING).setEffectiveness(TYPE_NORMAL, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ICE, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_DARK, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_STEEL, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_POISON, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_FLYING, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_PSYCHIC, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_BUG, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GHOST, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_POISON).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_POISON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_GHOST, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_STEEL, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_GROUND).setEffectiveness(TYPE_FIRE, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ELECTRIC, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_POISON, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ROCK, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_STEEL, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_BUG, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_FLYING, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_FLYING).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_BUG, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ELECTRIC, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_ROCK, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_PSYCHIC).setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_POISON, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_PSYCHIC, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_DARK, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_BUG).setEffectiveness(TYPE_GRASS, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_PSYCHIC, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_DARK, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_POISON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_FLYING, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_GHOST, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_ROCK).setEffectiveness(TYPE_FIRE, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ICE, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FLYING, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_BUG, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_GROUND, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_GHOST).setEffectiveness(TYPE_GHOST, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_PSYCHIC, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_DARK, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_NORMAL, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_DRAGON).setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_INEFFECTIVE));

		register(new Type().setName(TYPE_DARK).setEffectiveness(TYPE_PSYCHIC, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_GHOST, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_DARK, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_STEEL).setEffectiveness(TYPE_ICE, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_ROCK, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FAIRY, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_WATER, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_ELECTRIC, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));

		register(new Type().setName(TYPE_FAIRY).setEffectiveness(TYPE_DARK, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_DRAGON, EFFECTIVENESS_SUPER).setEffectiveness(TYPE_FIGHTING, EFFECTIVENESS_SUPER)
				.setEffectiveness(TYPE_FIRE, EFFECTIVENESS_NOT_VERY).setEffectiveness(TYPE_POISON, EFFECTIVENESS_NOT_VERY)
				.setEffectiveness(TYPE_STEEL, EFFECTIVENESS_NOT_VERY));
	}
}
