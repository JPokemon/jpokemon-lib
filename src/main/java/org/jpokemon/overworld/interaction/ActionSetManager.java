package org.jpokemon.overworld.interaction;

import java.util.List;

import org.jpokemon.api.JPokemonException;

public interface ActionSetManager {
	/**
	 * Registers an ActionSet with the manager so it can be looked up later
	 * 
	 * @param pokemon The ActionSet to be registered
	 * @throws JPokemonException If there is an error registering the ActionSet
	 */
	public void register(ActionSet actionSet) throws JPokemonException;

	/**
	 * Gets all ActionSets from this manager for an overworld entity
	 * 
	 * @param name The name of the overworld entity
	 * @return All ActionSets mapped to the entity
	 * @throws JPokemonException If there is an error retrieving ActionSets
	 */
	public List<ActionSet> getAll(String name) throws JPokemonException;

	/**
	 * Gets all ActionSets from this manager for an overworld entity under the
	 * specific context
	 * 
	 * @param name The name of the overworld entity
	 * @param context The context the ActionSet is available with
	 * @return All ActionSets mapped to the entity under the given context
	 * @throws JPokemonException If there is an error retrieving ActionSets
	 */
	public List<ActionSet> getAll(String name, String context) throws JPokemonException;

	/**
	 * Gets the ActionSet mapped to the specified overworld entity, with the given
	 * context, and the specified option name
	 * 
	 * @param name The name of the overworld entity
	 * @param context The context the ActionSet is available with
	 * @param option The name of the option that uniquely identifies the ActionSet
	 * @return ActionSet mapped to the entity under the given context with the
	 *         specified option name
	 * @throws JPokemonException If there is an error retrieving ActionSets
	 */
	public ActionSet get(String name, String context, String option) throws JPokemonException;

	/**
	 * Re-registers an ActionSet with the manager, overriding any conflicts with
	 * the already registered version
	 * 
	 * @param actionSet The ActionSet to update
	 * @throws JPokemonException If there is an error registering the ActionSet
	 */
	public void update(ActionSet actionSet) throws JPokemonException;

	/**
	 * Removes a ActionSet from the registry
	 * 
	 * @param actionSet The ActionSet to remove
	 * @throws JPokemonException If there is an error removing the ActionSet
	 */
	public void unregister(ActionSet actionSet) throws JPokemonException;
}
