package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.ospokemon.Type;
import org.ospokemon.manager.sqlite.SqliteTypeManager;

public class SqliteTypeManagerTest extends TestCase {
	public void testGetByName() {
		SqliteTypeManager manager = new SqliteTypeManager(); // example.db
		Type grass = manager.get("Grass");

		assertEquals("Grass", grass.getId());
	}

	public void testEffectivenessMappings() {
		SqliteTypeManager manager = new SqliteTypeManager(); // example.db
		Type fire = manager.get("Fire");

		// "SUPER" and "NOT VERY" are values from the db
		assertTrue("SUPER".equals(fire.getEffectiveness("Grass")));
		assertTrue("NOT VERY".equals(fire.getEffectiveness("Water")));
		assertTrue(fire.getEffectiveness("BlahblahType") == null);
	}

	public void testIsRegistered() {
		SqliteTypeManager manager = new SqliteTypeManager(); // example.db

		assertTrue(manager.isRegistered("Water"));
		Type water = manager.get("Water");
		assertNotNull(water);

		assertTrue(!manager.isRegistered("Hip"));
		new Type().setId("Hip");
		assertTrue(!manager.isRegistered("Hip"));
	}
}
