package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.ContestCategory;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link ContestCategory contest categories}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteContestCategoryManager implements Manager<ContestCategory> {
	@Override
	public ContestCategory create() {
		return new ContestCategory();
	}

	@Override
	public boolean isRegistered(String contestCategoryId) {
		return get(contestCategoryId) != null;
	}

	@Override
	public List<ContestCategory> getAll() throws JPokemonException {
		List<ContestCategory> contestCategories = new ArrayList<ContestCategory>();

		try {
			for (Table.ContestCategory contestCategoryRow : SqlStatement.select(Table.ContestCategory.class).getList()) {
				ContestCategory contestCategory = create();
				contestCategory.setName(contestCategoryRow.getName());
				contestCategory.setFlavor(contestCategoryRow.getFlavor());
				contestCategory.setColor(contestCategoryRow.getColor());
				contestCategory.setStat(contestCategoryRow.getStat());

				buildContestCategoryReactions(contestCategory);
				contestCategories.add(contestCategory);
			}
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		return contestCategories;
	}

	@Override
	public ContestCategory get(String id) {
		ContestCategory contestCategory = null;

		try {
			Table.ContestCategory contestCategoryRow = SqlStatement.select(Table.ContestCategory.class).where("id").eq(id).getFirst();

			if (contestCategoryRow != null) {
				contestCategory = create();
				contestCategory.setName(contestCategoryRow.getName());
				contestCategory.setFlavor(contestCategoryRow.getFlavor());
				contestCategory.setColor(contestCategoryRow.getColor());
				contestCategory.setStat(contestCategoryRow.getStat());
				buildContestCategoryReactions(contestCategory);
			}
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		return contestCategory;
	}

	@Override
	public void register(ContestCategory contestCategory) throws JPokemonException {
		if (contestCategory == null) {
			throw new JPokemonException("Cannot register null contest category");
		}
		if (contestCategory.getId() == null) {
			throw new JPokemonException("Cannot register contest category without an id: " + contestCategory);
		}
		if (isRegistered(contestCategory.getId())) {
			throw new JPokemonException("A contest category is already registered with id: " + contestCategory.getId());
		}

		try {
			Table.ContestCategory contestCategoryRow = new Table.ContestCategory(contestCategory);
			SqlStatement.insert(contestCategoryRow).execute();
			insertContestCategoryReactions(contestCategory);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(ContestCategory contestCategory) throws JPokemonException {
		if (contestCategory == null) {
			throw new JPokemonException("Cannot register null contest category");
		}
		if (contestCategory.getId() == null) {
			throw new JPokemonException("Cannot register contest category without an id: " + contestCategory);
		}
		if (!isRegistered(contestCategory.getId())) {
			throw new JPokemonException("A contest category is not registered with id: " + contestCategory.getId());
		}

		try {
			Table.ContestCategory contestCategoryRow = new Table.ContestCategory(contestCategory);
			SqlStatement.update(contestCategoryRow).where("id").eq(contestCategory.getId()).execute();
			updateContestCategoryReactions(contestCategory);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void unregister(String id) throws JPokemonException {
		if (id == null) {
			throw new JPokemonException("Cannot unregister contest category without an id");
		}
		if (!isRegistered(id)) {
			throw new JPokemonException("A contest category is not registered with id: " + id);
		}

		try {
			SqlStatement.delete(Table.ContestCategory.class).where("id").eq(id).execute();
			deleteContestCategoryReactions(id);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	private static void buildContestCategoryReactions(ContestCategory contestCategory) throws DataConnectionException {
		List<Table.ContestCategoryReaction> contestCategoryReactionRows = SqlStatement.select(Table.ContestCategoryReaction.class).where("contestCategory1")
				.eq(contestCategory.getId()).getList();

		for (Table.ContestCategoryReaction contestCategoryReactionRow : contestCategoryReactionRows) {
			contestCategory.setReaction(contestCategoryReactionRow.getContestCategory2(), contestCategoryReactionRow.getReaction());
		}
	}

	private static void insertContestCategoryReactions(ContestCategory contestCategory) throws DataConnectionException {
		for (Map.Entry<String, String> reactionEntry : contestCategory.getReactions().entrySet()) {
			Table.ContestCategoryReaction contestCategoryReaction = new Table.ContestCategoryReaction();
			contestCategoryReaction.setContestCategory1(contestCategory.getId());
			contestCategoryReaction.setContestCategory2(reactionEntry.getKey());
			contestCategoryReaction.setReaction(reactionEntry.getValue());

			SqlStatement.insert(contestCategoryReaction).execute();
		}
	}

	private static void updateContestCategoryReactions(ContestCategory contestCategory) throws DataConnectionException {
		deleteContestCategoryReactions(contestCategory.getId());
		insertContestCategoryReactions(contestCategory);
	}

	private static void deleteContestCategoryReactions(String id) throws DataConnectionException {
		SqlStatement.delete(Table.ContestCategoryReaction.class).where("contestCategory1").eq(id).execute();
	}
}
