package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.BattleEffect;
import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

public class SqliteBattleEffectBuildersManager implements Manager<Builder<BattleEffect>> {
	@Override
	public Builder<BattleEffect> create() throws JPokemonException {
		throw new JPokemonException("Cannot create a generic builder instance");
	}

	@Override
	public boolean isRegistered(String id) {
		return get(id) != null;
	}

	@Override
	public List<Builder<BattleEffect>> getAll() throws JPokemonException {
		List<Builder<BattleEffect>> list = new ArrayList<Builder<BattleEffect>>();

		try {
			for (Table.BattleEffectBuilder battleEffectBuilderRow : SqlStatement.select(Table.BattleEffectBuilder.class).getList()) {
				list.add(getBuilderForClassName(battleEffectBuilderRow.getBuilderClass()));
			}
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return list;
	}

	@Override
	public Builder<BattleEffect> get(String id) throws JPokemonException {
		Builder<BattleEffect> builder = null;

		try {
			Table.BattleEffectBuilder battleEffectBuilderRow = SqlStatement.select(Table.BattleEffectBuilder.class).where("id").eq(id).getFirst();
			builder = getBuilderForClassName(battleEffectBuilderRow.getBuilderClass());
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return builder;
	}

	@Override
	public void register(Builder<BattleEffect> builder) throws JPokemonException {
		if (builder == null) {
			throw new JPokemonException("Cannot register null battle effect builder");
		}
		if (builder.getId() == null) {
			throw new JPokemonException("Cannot register battle effect builder without an id: " + builder);
		}
		if (isRegistered(builder.getId())) {
			throw new JPokemonException("A battle effect builder is already registered with id: " + builder.getId());
		}

		try {
			Table.BattleEffectBuilder battleEffectBuilderRow = new Table.BattleEffectBuilder(builder);
			SqlStatement.insert(battleEffectBuilderRow).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(Builder<BattleEffect> builder) throws JPokemonException {
		if (builder == null) {
			throw new JPokemonException("Cannot register null battle effect builder");
		}
		if (builder.getId() == null) {
			throw new JPokemonException("Cannot register battle effect builder without an id: " + builder);
		}
		if (!isRegistered(builder.getId())) {
			throw new JPokemonException("A battle effect builder is not registered with id: " + builder.getId());
		}

		try {
			Table.BattleEffectBuilder battleEffectBuilderRow = new Table.BattleEffectBuilder(builder);
			SqlStatement.update(battleEffectBuilderRow).where("id").eq(builder.getId()).execute();
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
			SqlStatement.delete(Table.BattleEffectBuilder.class).where("id").eq(id).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private Builder<BattleEffect> getBuilderForClassName(String builderClassName) throws Exception {
		Class<?> builderClass = Class.forName(builderClassName);
		Builder<BattleEffect> builder = (Builder<BattleEffect>) builderClass.newInstance();
		return builder;
	}
}
