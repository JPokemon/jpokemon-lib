package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.junit.Test;
import org.ospokemon.Ability;
import org.ospokemon.Property;
import org.ospokemon.manager.SimpleBuildersManager;
import org.ospokemon.manager.sqlite.SqliteAbilityManager;
import org.ospokemon.testclass.StatModifierProperty;

import com.njkremer.Sqlite.DataConnectionManager;

public class SqliteAbilityManagerTest extends TestCase {
	@Test
	public void testCRUD() {
		DataConnectionManager.init("src/resources/test.db");
		SqliteAbilityManager manager = new SqliteAbilityManager();

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());

		// Test create

		Ability harvest = new Ability();
		harvest.setId("org.jpokemon.ability.Harvest");
		harvest.setName("Harvest");
		harvest.setDescription("Restores any held Berry after the turn on which it is used.");
		manager.register(harvest);

		Ability harvestFromDb = manager.get(harvest.getId());

		assertTrue(manager.isRegistered("org.jpokemon.ability.Harvest"));
		assertNotNull(harvestFromDb);
		assertEquals(harvest.getId(), harvestFromDb.getId());
		assertEquals(harvest.getName(), harvestFromDb.getName());
		assertEquals(harvest.getDescription(), harvestFromDb.getDescription());

		// Test update

		harvest.setName("Harvest2");
		manager.update(harvest);

		Ability harvest2FromDb = manager.get(harvest.getId());

		assertTrue(manager.isRegistered(harvest.getId()));
		assertNotNull(harvestFromDb);
		assertEquals(harvest.getId(), harvest2FromDb.getId());
		assertEquals(harvest.getName(), harvest2FromDb.getName());
		assertEquals(harvest.getDescription(), harvest2FromDb.getDescription());

		// Test delete

		manager.unregister(harvest.getId());

		Ability nullAbilityFromDb = manager.get(harvest.getId());

		assertFalse(manager.isRegistered(harvest.getId()));
		assertNull(nullAbilityFromDb);

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());

		// Cleanup
		Property.builders = null;
	}

	@Test
	public void testPropertiesHappyPath() {
		DataConnectionManager.init("src/resources/test.db");
		SqliteAbilityManager manager = new SqliteAbilityManager();
		Property.builders = new SimpleBuildersManager<Object>();
		Property.builders.register(new StatModifierProperty.Builder());

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());

		Ability purePower = new Ability();
		purePower.setId("org.jpokemon.ability.PurePower");
		purePower.setName("Pure Power");
		purePower.setDescription("Boosts the Pokémon's Attack stat.");
		StatModifierProperty statModifierProperty = new StatModifierProperty();
		statModifierProperty.setStatName("Attack");
		statModifierProperty.setValue(0.1);
		purePower.setProperty(StatModifierProperty.class, statModifierProperty);

		manager.register(purePower);

		Ability purePowerFromDb = manager.get(purePower.getId());

		assertTrue(manager.isRegistered("org.jpokemon.ability.PurePower"));
		assertNotNull(purePowerFromDb);
		assertEquals(purePower.getId(), purePowerFromDb.getId());
		assertEquals(purePower.getName(), purePowerFromDb.getName());
		assertEquals(purePower.getDescription(), purePowerFromDb.getDescription());
		assertEquals(1, purePowerFromDb.getProperties().size());

		Object property = purePowerFromDb.getProperty(StatModifierProperty.class.getName());

		assertTrue(property instanceof StatModifierProperty);

		StatModifierProperty statModifierPropertyFromDb = (StatModifierProperty) property;

		assertEquals("Attack", statModifierPropertyFromDb.getStatName());
		assertEquals(0.1, statModifierPropertyFromDb.getValue());

		manager.unregister(purePower.getId());

		// Make sure the database is empty
		assertEquals(0, manager.getAll().size());

		// Cleanup
		Property.builders = null;
	}
}
