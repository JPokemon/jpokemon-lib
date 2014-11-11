package org.ospokemon.manager.sqlite;

import java.util.List;

import org.ospokemon.JPokemonException;
import org.ospokemon.Property;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides a mechanism to load PropertyProvider classes from the Sqlite
 * database, using reflection
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqlitePropertyProvider {
	public static void load(String databasePath) throws JPokemonException {
		DataConnectionManager.init(databasePath);
		List<Table.PropertyProvider> propertyProviderRows = null;

		try {
			propertyProviderRows = SqlStatement.select(Table.PropertyProvider.class).getList();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		for (Table.PropertyProvider propertyProviderRow : propertyProviderRows) {
			Property<?> propertyProvider = null;

			try {
				Class<?> propertyProviderClass = Class.forName(propertyProviderRow.getProviderClass());
				propertyProvider = (Property<?>) propertyProviderClass.newInstance();
			} catch (Exception e) { // TODO - log
				continue;
			}

			Property.register(propertyProvider);
		}
	}
}
