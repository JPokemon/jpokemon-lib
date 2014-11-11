package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Move;
import org.ospokemon.Property;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Move moves}. Optionally provides
 * {@link Move#properties} using the {@link Property}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteMoveManager implements Manager<Move> {
	/** Indicates the path to the sqlite3 database */
	private String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteMoveManager() {
	}

	/** Instantiates a SqliteMoveManager with the provided database path */
	public SqliteMoveManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String moveName) {
		if (moveName == null) {
			return false;
		}

		return get(moveName) != null;
	}

	@Override
	public void register(Move move) throws JPokemonException {
		if (move == null) {
			throw new JPokemonException("Cannot register null move");
		}
		if (move.getName() == null) {
			throw new JPokemonException("Cannot register move without a name: " + move);
		}
		if (isRegistered(move.getName())) {
			throw new JPokemonException("A move is already registered with name: " + move.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			Table.Move moveRow = new Table.Move(move);
			SqlStatement.insert(moveRow).execute();
			insertMoveProperties(move);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Move> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);
		List<Move> moves = new ArrayList<Move>();

		try {
			for (Table.Move moveRow : SqlStatement.select(Table.Move.class).getList()) {
				Move move = moveRow.buildMove();
				buildMoveProperties(move);
				moves.add(move);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return moves;
	}

	@Override
	public Move get(String name) {
		DataConnectionManager.init(databasePath);
		Move move = null;

		try {
			Table.Move moveRow = SqlStatement.select(Table.Move.class).where("name").eq(name).getFirst();

			if (moveRow != null) {
				move = moveRow.buildMove();
				buildMoveProperties(move);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return move;
	}

	@Override
	public void update(Move move) throws JPokemonException {
		if (move == null) {
			throw new JPokemonException("Cannot register null move");
		}
		if (move.getName() == null) {
			throw new JPokemonException("Cannot register move without a name: " + move);
		}
		if (!isRegistered(move.getName())) {
			throw new JPokemonException("A move is not registered with name: " + move.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			Table.Move moveRow = new Table.Move(move);
			SqlStatement.update(moveRow).where("name").eq(move.getName()).execute();
			updateMoveProperties(move);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot register move without a name");
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("A move is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(Table.Move.class).where("name").eq(name).execute();
			deleteMoveProperties(name);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	private static void buildMoveProperties(Move move) throws DataConnectionException {
		List<Table.MoveProperty> propertyRows = SqlStatement.select(Table.MoveProperty.class).where("moveName")
				.eq(move.getName()).getList();

		for (Table.MoveProperty propertyRow : propertyRows) {
			move.setProperty(propertyRow.getPropertyName(), propertyRow.buildProperty());
		}
	}

	private static void insertMoveProperties(Move move) throws DataConnectionException {
		String moveName = move.getName();

		for (Map.Entry<String, Object> propertyEntry : move.getProperties().entrySet()) {
			String propertyName = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			Table.MoveProperty moveProperyRow = new Table.MoveProperty(moveName, propertyName, property);
			SqlStatement.insert(moveProperyRow).execute();
		}
	}

	private static void updateMoveProperties(Move move) throws DataConnectionException {
		deleteMoveProperties(move.getName());
		insertMoveProperties(move);
	}

	private static void deleteMoveProperties(String name) throws DataConnectionException {
		SqlStatement.delete(Table.MoveProperty.class).where("moveName").eq(name).execute();
	}
}
