package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.Action;
import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for loading {@link Action action builders}. This
 * is achieved through reflection over the classpath and the empty constructor
 * of each builder class.
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteActionBuildersManager implements Manager<Builder<Action>> {
	@Override
	public Builder<Action> create() throws JPokemonException {
		throw new JPokemonException("Cannot create a generic builder instance");
	}

	@Override
	public boolean isRegistered(String id) {
		return get(id) != null;
	}

	@Override
	public List<Builder<Action>> getAll() throws JPokemonException {
		List<Builder<Action>> list = new ArrayList<Builder<Action>>();

		try {
			for (Table.ActionBuilder actionBuilderRow : SqlStatement.select(Table.ActionBuilder.class).getList()) {
				list.add(getBuilderForClassName(actionBuilderRow.getBuilderClass()));
			}
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return list;
	}

	@Override
	public Builder<Action> get(String id) throws JPokemonException {
		Builder<Action> builder = null;

		try {
			Table.ActionBuilder actionBuilderRow = SqlStatement.select(Table.ActionBuilder.class).where("id").eq(id).getFirst();

			if (actionBuilderRow != null) {
				builder = getBuilderForClassName(actionBuilderRow.getBuilderClass());
			}
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return builder;
	}

	@Override
	public void register(Builder<Action> builder) throws JPokemonException {
		if (builder == null) {
			throw new JPokemonException("Cannot register null action builder");
		}
		if (builder.getId() == null) {
			throw new JPokemonException("Cannot register action builder without an id: " + builder);
		}
		if (isRegistered(builder.getId())) {
			throw new JPokemonException("An action builder is already registered with id: " + builder.getId());
		}

		try {
			Table.ActionBuilder actionBuilderRow = new Table.ActionBuilder(builder);
			SqlStatement.insert(actionBuilderRow).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(Builder<Action> builder) throws JPokemonException {
		if (builder == null) {
			throw new JPokemonException("Cannot register null action builder");
		}
		if (builder.getId() == null) {
			throw new JPokemonException("Cannot register action builder without an id: " + builder);
		}
		if (!isRegistered(builder.getId())) {
			throw new JPokemonException("An action builder is not registered with id: " + builder.getId());
		}

		try {
			Table.ActionBuilder actionBuilderRow = new Table.ActionBuilder(builder);
			SqlStatement.update(actionBuilderRow).where("id").eq(builder.getId()).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void unregister(String id) throws JPokemonException {
		if (id == null) {
			throw new JPokemonException("Cannot unregister action builder without an id");
		}

		try {
			SqlStatement.delete(Table.ActionBuilder.class).where("id").eq(id).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private Builder<Action> getBuilderForClassName(String builderClassName) throws Exception {
		Class<?> builderClass = Class.forName(builderClassName);
		Builder<Action> builder = (Builder<Action>) builderClass.newInstance();
		return builder;
	}
}
