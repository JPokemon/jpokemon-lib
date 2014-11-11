package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.Item;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Property;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Item items}. Optionally provides
 * {@link Item#properties} using the {@link Property}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteItemManager implements Manager<Item> {
	/** Indicates the path to the sqlite3 database */
	protected String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteItemManager() {
	}

	/** Instantiates a SqliteItemManager with the provided database path */
	public SqliteItemManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String itemName) {
		if (itemName == null) {
			return false;
		}

		return get(itemName) != null;
	}

	@Override
	public void register(Item item) throws JPokemonException {
		if (item == null) {
			throw new JPokemonException("Cannot register null item");
		}
		if (item.getName() == null) {
			throw new JPokemonException("Cannot register item without a name: " + item);
		}
		if (isRegistered(item.getName())) {
			throw new JPokemonException("An item is already registered with name: " + item.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			Table.Item itemRow = new Table.Item(item);
			SqlStatement.insert(itemRow).execute();
			insertItemProperties(item);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);
		List<Item> items = new ArrayList<Item>();

		try {
			for (Table.Item itemRow : SqlStatement.select(Table.Item.class).getList()) {
				Item item = itemRow.buildItem();
				buildItemProperties(item);
				items.add(item);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public Item get(String name) {
		DataConnectionManager.init(databasePath);
		Item item = null;

		try {
			Table.Item itemRow = SqlStatement.select(Table.Item.class).where("name").eq(name).getFirst();

			if (itemRow != null) {
				item = itemRow.buildItem();
				buildItemProperties(item);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return item;
	}

	@Override
	public void update(Item item) throws JPokemonException {
		if (item == null) {
			throw new JPokemonException("Cannot register null item");
		}
		if (item.getName() == null) {
			throw new JPokemonException("Cannot register item without a name: " + item);
		}
		if (!isRegistered(item.getName())) {
			throw new JPokemonException("An item is not registered with name: " + item.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			Table.Item itemRow = new Table.Item(item);
			SqlStatement.update(itemRow).where("name").eq(item.getName()).execute();
			updateItemProperties(item);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot unregister item without a name");
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("An item is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(Table.Item.class).where("name").eq(name).execute();
			deleteItemProperties(name);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	private static void buildItemProperties(Item item) throws DataConnectionException {
		List<Table.ItemProperty> propertyRows = SqlStatement.select(Table.ItemProperty.class).where("itemName")
				.eq(item.getName()).getList();

		for (Table.ItemProperty propertyRow : propertyRows) {
			item.setProperty(propertyRow.getPropertyName(), propertyRow.buildProperty());
		}
	}

	private static void insertItemProperties(Item item) throws DataConnectionException {
		String itemName = item.getName();

		for (Map.Entry<String, Object> propertyEntry : item.getProperties().entrySet()) {
			String propertyName = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			Table.ItemProperty itemProperyRow = new Table.ItemProperty(itemName, propertyName, property);
			SqlStatement.insert(itemProperyRow).execute();
		}
	}

	private static void updateItemProperties(Item item) throws DataConnectionException {
		deleteItemProperties(item.getName());
		insertItemProperties(item);
	}

	private static void deleteItemProperties(String name) throws DataConnectionException {
		SqlStatement.delete(Table.ItemProperty.class).where("itemName").eq(name).execute();
	}
}
