package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.TrainerClass;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

public class SqliteTrainerClassManager implements Manager<TrainerClass> {
	/** Indicates the path to the sqlite3 database */
	protected String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteTrainerClassManager() {
	}

	/** Instantiates a SqlAbilityManager with the provided database path */
	public SqliteTrainerClassManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String trainerClassName) {
		if (trainerClassName == null) {
			return false;
		}

		return get(trainerClassName) != null;
	}

	@Override
	public void register(TrainerClass trainerClass) throws JPokemonException {
		if (trainerClass == null) {
			throw new JPokemonException("Cannot register null trainer class");
		}
		if (trainerClass.getName() == null) {
			throw new JPokemonException("Cannot register trainer class without a name: " + trainerClass);
		}
		if (isRegistered(trainerClass.getName())) {
			throw new JPokemonException("A trainer class is already registered with name: " + trainerClass.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.insert(trainerClass).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TrainerClass> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);

		try {
			return SqlStatement.select(TrainerClass.class).getList();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return new ArrayList<TrainerClass>();
	}

	@Override
	public TrainerClass get(String name) throws JPokemonException {
		DataConnectionManager.init(databasePath);
		TrainerClass trainerClass = null;

		try {
			trainerClass = SqlStatement.select(TrainerClass.class).where("name").eq(name).getFirst();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return trainerClass;
	}

	@Override
	public void update(TrainerClass trainerClass) throws JPokemonException {
		if (trainerClass == null) {
			throw new JPokemonException("Cannot register null trainer class");
		}
		if (trainerClass.getName() == null) {
			throw new JPokemonException("Cannot register trainer class without a name: " + trainerClass);
		}
		if (!isRegistered(trainerClass.getName())) {
			throw new JPokemonException("A trainer class is not registered with name: " + trainerClass.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.update(trainerClass).where("name").eq(trainerClass.getName()).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot unregister trainer class without a name");
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("A trainer class is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(TrainerClass.class).where("name").eq(name).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}
}
