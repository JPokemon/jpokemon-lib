package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.junit.Test;
import org.ospokemon.Action;
import org.ospokemon.Builder;
import org.ospokemon.manager.sqlite.SqliteActionBuildersManager;
import org.ospokemon.testclass.EmptyActionBuilder;

import com.njkremer.Sqlite.DataConnectionManager;

public class SqliteActionBuildersManagerTest extends TestCase {
	@Test
	public void testCRD() {
		DataConnectionManager.init("src/resources/test.db");
		SqliteActionBuildersManager manager = new SqliteActionBuildersManager();

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());

		// Test create

		EmptyActionBuilder emptyActionBuilder = new EmptyActionBuilder();
		manager.register(emptyActionBuilder);

		Builder<Action> emptyActionBuilderFromDb = manager.get(EmptyActionBuilder.ID);

		assertTrue(manager.isRegistered(EmptyActionBuilder.ID));
		assertNotNull(emptyActionBuilderFromDb);
		assertTrue(emptyActionBuilderFromDb instanceof EmptyActionBuilder);

		// Can't really test update

		// Test delete

		manager.unregister(EmptyActionBuilder.ID);

		Builder<Action> nullBuilderFromDb = manager.get(EmptyActionBuilder.ID);

		assertFalse(manager.isRegistered(EmptyActionBuilder.ID));
		assertNull(nullBuilderFromDb);

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());
	}
}
