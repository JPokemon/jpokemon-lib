package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.Action;
import org.ospokemon.ActionSet;
import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Property;
import org.ospokemon.Requirement;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

public class SqliteActionSetManager implements Manager<ActionSet> {
	@Override
	public ActionSet create() throws JPokemonException {
		return new ActionSet();
	}

	@Override
	public boolean isRegistered(String id) {
		return get(id) != null;
	}

	@Override
	public List<ActionSet> getAll() throws JPokemonException {
		List<ActionSet> list = new ArrayList<ActionSet>();

		try {
			for (Table.ActionSet actionSetRow : SqlStatement.select(Table.ActionSet.class).getList()) {
				ActionSet actionSet = create();
				actionSet.setId(actionSetRow.getId());
				actionSet.setDescription(actionSetRow.getDescription());
				buildActionSetActions(actionSet);
				buildActionSetRequirements(actionSet);
				buildActionSetProperties(actionSet);
			}
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return list;
	}

	@Override
	public ActionSet get(String id) throws JPokemonException {
		ActionSet actionSet = null;

		try {
			Table.ActionSet actionSetRow = SqlStatement.select(Table.ActionSet.class).where("id").eq(id).getFirst();

			if (actionSetRow != null) {
				actionSet = create();
				actionSet.setId(actionSetRow.getId());
				actionSet.setDescription(actionSetRow.getDescription());
				buildActionSetActions(actionSet);
				buildActionSetRequirements(actionSet);
				buildActionSetProperties(actionSet);
			}
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		return null;
	}

	@Override
	public void register(ActionSet actionSet) throws JPokemonException {
		if (actionSet == null) {
			throw new JPokemonException("Cannot register null action set");
		}
		if (actionSet.getId() == null) {
			throw new JPokemonException("Cannot register action set without an id: " + actionSet);
		}
		if (isRegistered(actionSet.getId())) {
			throw new JPokemonException("An action set is already registered with id: " + actionSet.getId());
		}

		try {
			Table.ActionSet actionSetRow = new Table.ActionSet(actionSet);
			SqlStatement.insert(actionSetRow).execute();
			insertActionSetActions(actionSet);
			insertActionSetRequirements(actionSet);
			insertActionSetProperties(actionSet);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(ActionSet actionSet) throws JPokemonException {
		if (actionSet == null) {
			throw new JPokemonException("Cannot register null action set");
		}
		if (actionSet.getId() == null) {
			throw new JPokemonException("Cannot register action set without an id: " + actionSet);
		}
		if (!isRegistered(actionSet.getId())) {
			throw new JPokemonException("An action set is not registered with id: " + actionSet.getId());
		}

		try {
			Table.ActionSet actionSetRow = new Table.ActionSet(actionSet);
			SqlStatement.update(actionSetRow).where("id").eq(actionSet.getId()).execute();
			updateActionSetActions(actionSet);
			updateActionSetRequirements(actionSet);
			updateActionSetProperties(actionSet);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void unregister(String id) throws JPokemonException {
		if (id == null) {
			throw new JPokemonException("Cannot unregister action set without an id");
		}

		try {
			SqlStatement.delete(Table.ActionSet.class).where("id").eq(id).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	private static void buildActionSetActions(ActionSet actionSet) throws JPokemonException, DataConnectionException {
		for (Table.Action actionRow : SqlStatement.select(Table.Action.class).where("actionSetId").eq(actionSet.getId()).getList()) {
			Builder<Action> builder = Action.builders.get(actionRow.getActionBuilderId());
			Action action = builder.construct(actionRow.getOptions());
			actionSet.addAction(action);
		}
	}

	private static void insertActionSetActions(ActionSet actionSet) throws DataConnectionException {
		String actionSetId = actionSet.getId();

		for (Action action : actionSet.getActions()) {
			String actionClassName = action.getClass().getName();
			String options = null;

			Builder<Action> builder = Action.builders.get(actionClassName);
			if (builder != null) {
				options = builder.destruct(action);
			}
			else {
				options = action.toString();
			}

			Table.Action actionRow = new Table.Action(actionSetId, actionClassName, options);
			SqlStatement.insert(actionRow).execute();
		}
	}

	private static void updateActionSetActions(ActionSet actionSet) throws DataConnectionException {
		deleteActionSetActions(actionSet.getId());
		insertActionSetActions(actionSet);
	}

	private static void deleteActionSetActions(String actionSetId) throws DataConnectionException {
		SqlStatement.delete(Table.Action.class).where("actionSetId").eq(actionSetId).execute();
		;
	}

	private static void buildActionSetRequirements(ActionSet actionSet) throws JPokemonException, DataConnectionException {
		for (Table.Requirement actionRow : SqlStatement.select(Table.Requirement.class).where("actionSetId").eq(actionSet.getId()).getList()) {
			Builder<Requirement> builder = Requirement.builders.get(actionRow.getRequirementBuilderId());
			Requirement requirement = builder.construct(actionRow.getOptions());
			actionSet.addRequirement(requirement);
		}
	}

	private static void insertActionSetRequirements(ActionSet actionSet) throws DataConnectionException {
		String actionSetId = actionSet.getId();

		for (Requirement requirement : actionSet.getRequirements()) {
			String requirementClassName = requirement.getClass().getName();
			String options = null;

			Builder<Requirement> builder = Requirement.builders.get(requirementClassName);
			if (builder != null) {
				options = builder.destruct(requirement);
			}
			else {
				options = requirement.toString();
			}

			Table.Requirement requirementRow = new Table.Requirement(actionSetId, requirementClassName, options);
			SqlStatement.insert(requirementRow).execute();
		}
	}

	private static void updateActionSetRequirements(ActionSet actionSet) throws DataConnectionException {
		deleteActionSetRequirements(actionSet.getId());
		insertActionSetRequirements(actionSet);
	}

	private static void deleteActionSetRequirements(String actionSetId) throws DataConnectionException {
		SqlStatement.delete(Table.Requirement.class).where("actionSetId").eq(actionSetId).execute();
	}

	private static void buildActionSetProperties(ActionSet actionSet) throws DataConnectionException {
		List<Table.ActionSetProperty> propertyRows = SqlStatement.select(Table.ActionSetProperty.class).where("actionSetId").eq(actionSet.getId()).getList();

		for (Table.ActionSetProperty propertyRow : propertyRows) {
			Builder<Object> builder = Property.builders.get(propertyRow.getPropertyId());
			Object property;

			if (builder != null) {
				property = builder.construct(propertyRow.getOptions());
			}
			else {
				property = propertyRow.getOptions();
			}

			actionSet.setProperty(propertyRow.getPropertyId(), property);
		}
	}

	private static void insertActionSetProperties(ActionSet actionSet) throws DataConnectionException {
		String actionSetId = actionSet.getId();

		for (Map.Entry<String, Object> propertyEntry : actionSet.getProperties().entrySet()) {
			String propertyId = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			String options = null;

			Builder<Object> builder = Property.builders.get(propertyId);
			if (builder != null) {
				options = builder.destruct(property);
			}
			else {
				options = property.toString();
			}

			Table.ActionSetProperty propertyRow = new Table.ActionSetProperty(actionSetId, propertyId, options);
			SqlStatement.insert(propertyRow).execute();
		}
	}

	private static void updateActionSetProperties(ActionSet actionSet) throws DataConnectionException {
		deleteActionSetProperties(actionSet.getId());
		insertActionSetProperties(actionSet);
	}

	private static void deleteActionSetProperties(String id) throws DataConnectionException {
		SqlStatement.delete(Table.ActionSetProperty.class).where("actionSetId").eq(id).execute();
	}
}
