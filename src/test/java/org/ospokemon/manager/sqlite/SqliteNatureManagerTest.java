package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.junit.Test;
import org.ospokemon.Nature;
import org.ospokemon.manager.sqlite.SqliteNatureManager;

public class SqliteNatureManagerTest extends TestCase {
	@Test
	public void testBuild() {
		SqliteNatureManager manager = new SqliteNatureManager(); // example.db
		Nature sassy = manager.get("Sassy");

		assertNotNull(sassy);
		assertEquals("Sassy", sassy.getName());
		assertEquals("Special Defense", sassy.getStatIncreased());
		assertEquals("Speed", sassy.getStatDecreased());
		assertEquals("Bitter", sassy.getFlavorFavorite());
		assertEquals("Sweet", sassy.getFlavorDisliked());
	}

	public void testCRUD() {
		SqliteNatureManager manager = new SqliteNatureManager(); // example.db
		Nature hip = new Nature().setName("Hip").setStatIncreased("Personality").setStatDecreased("Empathy")
				.setFlavorFavorite("Starbucks").setFlavorDisliked("Red Meat");

		assertFalse(manager.isRegistered("Hip"));

		manager.register(hip);

		assertTrue(manager.isRegistered("Hip"));

		Nature hipClone = manager.get("Hip");

		assertEquals(hip.getName(), hipClone.getName());
		assertEquals(hip.getStatIncreased(), hipClone.getStatIncreased());
		assertEquals(hip.getStatDecreased(), hipClone.getStatDecreased());
		assertEquals(hip.getFlavorFavorite(), hipClone.getFlavorFavorite());
		assertEquals(hip.getFlavorDisliked(), hipClone.getFlavorDisliked());

		hip.setFlavorDisliked("Ramen");
		manager.update(hip);

		hipClone = manager.get("Hip");

		assertEquals(hip.getName(), hipClone.getName());
		assertEquals(hip.getStatIncreased(), hipClone.getStatIncreased());
		assertEquals(hip.getStatDecreased(), hipClone.getStatDecreased());
		assertEquals(hip.getFlavorFavorite(), hipClone.getFlavorFavorite());
		assertEquals(hip.getFlavorDisliked(), hipClone.getFlavorDisliked());

		manager.unregister("Hip");

		assertFalse(manager.isRegistered("Hip"));
	}
}
