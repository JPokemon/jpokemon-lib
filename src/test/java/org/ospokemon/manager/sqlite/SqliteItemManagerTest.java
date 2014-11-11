package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.junit.Test;
import org.ospokemon.Item;
import org.ospokemon.Property;
import org.ospokemon.manager.sqlite.SqliteItemManager;
import org.ospokemon.testclass.MedicineProperty;

public class SqliteItemManagerTest extends TestCase {
	@Test
	public void testBuild() {
		SqliteItemManager manager = new SqliteItemManager(); // example.db

		assertTrue(manager.isRegistered("Potion"));

		Item item = manager.get("Potion");

		assertNotNull(item);
		assertEquals("Potion", item.getName());
		assertEquals("Heals a Pokémon by 20 HP.", item.getDescription());
		assertTrue(item.isSellable());
		assertEquals(100, item.getSalePrice());
		assertTrue(item.isUsableOutsideBattle());
		assertTrue(item.isUsableDuringBattle());
		assertTrue(item.isConsumable());
		assertFalse(item.isHoldable());
	}

	@Test
	public void testCRUD() {
		SqliteItemManager manager = new SqliteItemManager(); // example.db

		assertFalse(manager.isRegistered("Moon Stone"));

		Item moonStone = new Item();
		moonStone.setName("Moon Stone");
		moonStone.setDescription("A shiny stone that glows. Causes some Pokemon to evolve.");
		moonStone.setSalePrice(50);

		assertFalse(manager.isRegistered("Moon Stone"));

		manager.register(moonStone);

		assertTrue(manager.isRegistered("Moon Stone"));

		Item moonStoneClone = manager.get("Moon Stone");

		assertEquals(moonStone.getName(), moonStoneClone.getName());
		assertEquals(moonStone.getDescription(), moonStoneClone.getDescription());
		assertEquals(moonStone.getSalePrice(), moonStoneClone.getSalePrice());

		moonStone.setSalePrice(100);
		manager.update(moonStone);

		moonStoneClone = manager.get("Moon Stone");

		assertEquals(moonStone.getName(), moonStoneClone.getName());
		assertEquals(moonStone.getDescription(), moonStoneClone.getDescription());
		assertEquals(moonStone.getSalePrice(), moonStoneClone.getSalePrice());

		manager.unregister("Moon Stone");

		assertFalse(manager.isRegistered("Moon Stone"));
	}

	@Test
	public void testProperties() {
		SqliteItemManager manager = new SqliteItemManager(); // example.db
		Item item = manager.get("Potion");
		String medicinePropertyOptions = (String) item.getProperty(MedicineProperty.class.getName());

		assertEquals(1, item.getProperties().size());
		assertNotNull(medicinePropertyOptions);
		assertEquals("Health,20,false", medicinePropertyOptions);

		Property.builders.register(new MedicineProperty.Builder());
		item = manager.get("Potion");
		MedicineProperty medicineProperty = item.getProperty(MedicineProperty.class);

		assertNotNull(medicineProperty);
		assertEquals(1, item.getProperties().size());
		assertEquals("Health", medicineProperty.getStat());
		assertEquals(20, medicineProperty.getStrength());
		assertFalse(medicineProperty.isPermanent());

		Property.builders.unregister(MedicineProperty.class.getName());
	}
}
