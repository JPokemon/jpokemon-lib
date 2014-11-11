package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.StatusCondition;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

public class SqliteStatusConditionManager implements Manager<StatusCondition> {
	/** Indicates the path to the sqlite3 database */
	protected String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteStatusConditionManager() {
	}

	/** Instantiates a SqlAbilityManager with the provided database path */
	public SqliteStatusConditionManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String statusConditionName) {
		if (statusConditionName == null) {
			return false;
		}

		return get(statusConditionName) != null;
	}

	@Override
	public void register(StatusCondition statusCondition) throws JPokemonException {
		if (statusCondition == null) {
			throw new JPokemonException("Cannot register null status condition");
		}
		if (statusCondition.getName() == null) {
			throw new JPokemonException("Cannot register status condition without a name: " + statusCondition);
		}
		if (isRegistered(statusCondition.getName())) {
			throw new JPokemonException("A status condition is already registered with name: " + statusCondition.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.insert(statusCondition).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<StatusCondition> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);

		try {
			return SqlStatement.select(StatusCondition.class).getList();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return new ArrayList<StatusCondition>();
	}

	@Override
	public StatusCondition get(String name) throws JPokemonException {
		DataConnectionManager.init(databasePath);
		StatusCondition statusCondition = null;

		try {
			statusCondition = SqlStatement.select(StatusCondition.class).where("name").eq(name).getFirst();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return statusCondition;
	}

	@Override
	public void update(StatusCondition statusCondition) throws JPokemonException {
		if (statusCondition == null) {
			throw new JPokemonException("Cannot register null status condition");
		}
		if (statusCondition.getName() == null) {
			throw new JPokemonException("Cannot register status condition without a name: " + statusCondition);
		}
		if (!isRegistered(statusCondition.getName())) {
			throw new JPokemonException("A status condition is not registered with name: " + statusCondition.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.update(statusCondition).where("name").eq(statusCondition.getName()).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String statusConditionName) throws JPokemonException {
		if (statusConditionName == null) {
			throw new JPokemonException("Cannot register status condition without a name");
		}
		if (!isRegistered(statusConditionName)) {
			throw new JPokemonException("A status condition is not registered with name: " + statusConditionName);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(StatusCondition.class).where("name").eq(statusConditionName).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}
}
