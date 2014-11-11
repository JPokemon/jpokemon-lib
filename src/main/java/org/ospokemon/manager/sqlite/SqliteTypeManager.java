package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Type;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Type types}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteTypeManager implements Manager<Type> {
	/** Indicates the path to the sqlite3 database */
	private String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteTypeManager() {
	}

	/** Creates a new SqliteTypeManager with the specified database path */
	public SqliteTypeManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String typeName) {
		if (typeName == null) {
			return false;
		}

		return get(typeName) != null;
	}

	@Override
	public void register(Type type) throws JPokemonException {
		if (type == null) {
			throw new JPokemonException("Cannot register null type");
		}
		if (type.getId() == null) {
			throw new JPokemonException("Cannot register type without a name: " + type);
		}
		if (isRegistered(type.getId())) {
			throw new JPokemonException("A type is already registered with name: " + type.getId());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.insert(new Table.Type(type)).execute();
			insertTypeEffectiveness(type);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Type> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);
		List<Type> types = new ArrayList<Type>();

		try {
			for (Table.Type typeRow : SqlStatement.select(Table.Type.class).getList()) {
				Type type = typeRow.buildType();
				buildTypeEffectiveness(type);
				types.add(type);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return types;
	}

	@Override
	public Type get(String name) {
		DataConnectionManager.init(databasePath);
		Type type = null;

		try {
			Table.Type typeRow = SqlStatement.select(Table.Type.class).where("name").eq(name).getFirst();

			if (typeRow != null) {
				type = typeRow.buildType();
				buildTypeEffectiveness(type);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return type;
	}

	@Override
	public void update(Type type) throws JPokemonException {
		if (type == null) {
			throw new JPokemonException("Cannot register null type");
		}
		if (type.getId() == null) {
			throw new JPokemonException("Cannot register type without a name: " + type);
		}
		if (!isRegistered(type.getId())) {
			throw new JPokemonException("A type is not registered with name: " + type.getId());
		}

		DataConnectionManager.init(databasePath);

		try {
			// No need to update type since it is a 1 column table
			updateTypeEffectiveness(type);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot register type without a name: " + name);
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("A type is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(Table.Type.class).where("name").eq(name).execute();
			deleteTypeEffectiveness(name);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	private static void buildTypeEffectiveness(Type type) throws DataConnectionException {
		List<Table.TypeEffectiveness> typeEffectivenessRows = SqlStatement.select(Table.TypeEffectiveness.class)
				.where("type1").eq(type.getId()).getList();

		for (Table.TypeEffectiveness typeEffectivenessRow : typeEffectivenessRows) {
			type.setEffectiveness(typeEffectivenessRow.getType2(), typeEffectivenessRow.getEffectiveness());
		}
	}

	private static void insertTypeEffectiveness(Type type) throws DataConnectionException {
		List<Table.TypeEffectiveness> typeEffectivenessRows = Table.TypeEffectiveness.fromApiType(type);

		for (Table.TypeEffectiveness typeEffectivenessRow : typeEffectivenessRows) {
			SqlStatement.insert(typeEffectivenessRow).execute();
		}
	}

	private static void updateTypeEffectiveness(Type type) throws DataConnectionException {
		deleteTypeEffectiveness(type.getId());
		insertTypeEffectiveness(type);
	}

	private static void deleteTypeEffectiveness(String name) throws DataConnectionException {
		SqlStatement.delete(Table.TypeEffectiveness.class).where("type1").eq(name).execute();
	}
}
