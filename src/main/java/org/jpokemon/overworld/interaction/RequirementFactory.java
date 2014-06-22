package org.jpokemon.overworld.interaction;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Manager;

public abstract class RequirementFactory {
	public static Manager<RequirementFactory> manager;

	public abstract Class<? extends Requirement> getRequirementClass();

	public abstract Requirement buildRequirement(String options) throws JPokemonException;

	public abstract String serializeRequirement(Requirement requirement) throws JPokemonException;
}
