package org.ospokemon.manager.sqlite;

import junit.framework.TestCase;

import org.junit.Test;
import org.ospokemon.ContestCategory;
import org.ospokemon.manager.sqlite.SqliteContestCategoryManager;

public class SqliteContestCategoryManagerTest extends TestCase {
	@Test
	public void testCRUD() {
		SqliteContestCategoryManager manager = new SqliteContestCategoryManager(); // example.db

		assertTrue(manager.isRegistered("Beauty"));

		ContestCategory beauty = manager.get("Beauty");

		assertNotNull(beauty);
		assertEquals("Beauty", beauty.getName());
		assertEquals("Dry", beauty.getFlavor());
		assertEquals("Blue", beauty.getColor());
		assertEquals("Special Attack", beauty.getStat());

		assertFalse(manager.isRegistered("Hip"));

		ContestCategory hip = new ContestCategory().setName("Hip").setFlavor("Starbucks").setColor("Black").setStat("Ruby");
		manager.register(hip);

		assertTrue(manager.isRegistered("Hip"));

		ContestCategory hipClone = manager.get("Hip");
		assertEquals(hipClone.getName(), hip.getName());
		assertEquals(hipClone.getName(), hip.getName());
		assertEquals(hipClone.getFlavor(), hip.getFlavor());
		assertEquals(hipClone.getColor(), hip.getColor());
		assertEquals(hipClone.getStat(), hip.getStat());

		hip.setStat("Swift");
		manager.update(hip);

		hipClone = manager.get("Hip");
		assertEquals(hipClone.getStat(), hip.getStat());

		manager.unregister("Hip");
		assertFalse(manager.isRegistered("Hip")); // The Hip Ability
	}

	@Test
	public void testBuildReactions() {
		SqliteContestCategoryManager manager = new SqliteContestCategoryManager(); // example.db

		assertTrue(manager.isRegistered("Beauty"));

		ContestCategory beauty = manager.get("Beauty");

		assertNotNull(beauty.getReactions());
		assertEquals(1, beauty.getReactions().size());
		assertTrue("EXCITEMENT".equals(beauty.getReaction("Beauty")));
	}
}
