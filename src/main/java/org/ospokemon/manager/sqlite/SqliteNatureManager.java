package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Nature;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Nature natures}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteNatureManager implements Manager<Nature> {
	/** Indicates the path to the sqlite3 database */
	private String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteNatureManager() {
	}

	/** Creates a new SqliteNatureManager with the specified database path */
	public SqliteNatureManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String natureName) {
		if (natureName == null) {
			return false;
		}

		return get(natureName) != null;
	}

	@Override
	public void register(Nature nature) throws JPokemonException {
		if (nature == null) {
			throw new JPokemonException("Cannot register null nature");
		}
		if (nature.getName() == null) {
			throw new JPokemonException("Cannot register nature without a name: " + nature);
		}
		if (isRegistered(nature.getName())) {
			throw new JPokemonException("A nature is already registered with name: " + nature.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.insert(nature).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Nature> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);

		try {
			return SqlStatement.select(Nature.class).getList();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return new ArrayList<Nature>();
	}

	@Override
	public Nature get(String name) {
		DataConnectionManager.init(databasePath);
		Nature nature = null;

		try {
			nature = SqlStatement.select(Nature.class).where("name").eq(name).getFirst();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return nature;
	}

	@Override
	public void update(Nature nature) throws JPokemonException {
		if (nature == null) {
			throw new JPokemonException("Cannot register null nature");
		}
		if (nature.getName() == null) {
			throw new JPokemonException("Cannot register nature without a name: " + nature);
		}
		if (!isRegistered(nature.getName())) {
			throw new JPokemonException("A nature is not registered with name: " + nature.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.update(nature).where("name").eq(nature.getName()).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot register nature without a name: " + name);
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("A nature is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(Nature.class).where("name").eq(name).execute();
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}
}
