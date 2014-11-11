package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;

import org.ospokemon.ExperienceCurve;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

public class SqliteExperienceCurveManager implements Manager<ExperienceCurve> {
	@Override
	public ExperienceCurve create() throws JPokemonException {
		throw new JPokemonException("Cannot create generic experience curve instance");
	}

	@Override
	public boolean isRegistered(String id) {
		return get(id) != null;
	}

	@Override
	public List<ExperienceCurve> getAll() throws JPokemonException {
		List<ExperienceCurve> list = new ArrayList<ExperienceCurve>();

		try {
			for (Table.ExperienceCurve experienceCurveRow : SqlStatement.select(Table.ExperienceCurve.class).getList()) {
				list.add(getExperienceCurveForClassName(experienceCurveRow.getExperienceCurveClass()));
			}
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return list;
	}

	@Override
	public ExperienceCurve get(String id) throws JPokemonException {
		ExperienceCurve experienceCurve = null;

		try {
			Table.ExperienceCurve experienceCurveRow = SqlStatement.select(Table.ExperienceCurve.class).where("id").eq(id).getFirst();
			experienceCurve = getExperienceCurveForClassName(experienceCurveRow.getExperienceCurveClass());
		} catch (Exception e) {
			throw new JPokemonException(e);
		}

		return experienceCurve;
	}

	@Override
	public void register(ExperienceCurve experienceCurve) throws JPokemonException {
		if (experienceCurve == null) {
			throw new JPokemonException("Cannot register null experience curve");
		}
		if (experienceCurve.getId() == null) {
			throw new JPokemonException("Cannot register experience curve without an id: " + experienceCurve);
		}
		if (isRegistered(experienceCurve.getId())) {
			throw new JPokemonException("An experience curve is already registered with id: " + experienceCurve.getId());
		}

		try {
			Table.ExperienceCurve experienceCurveRow = new Table.ExperienceCurve(experienceCurve);
			SqlStatement.insert(experienceCurveRow).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(ExperienceCurve experienceCurve) throws JPokemonException {
		if (experienceCurve == null) {
			throw new JPokemonException("Cannot register null experience curve");
		}
		if (experienceCurve.getId() == null) {
			throw new JPokemonException("Cannot register experience curve without an id: " + experienceCurve);
		}
		if (!isRegistered(experienceCurve.getId())) {
			throw new JPokemonException("An experience curve is not registered with id: " + experienceCurve.getId());
		}

		try {
			Table.ExperienceCurve experienceCurveRow = new Table.ExperienceCurve(experienceCurve);
			SqlStatement.update(experienceCurveRow).where("id").eq(experienceCurve.getId()).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void unregister(String id) throws JPokemonException {
		if (id == null) {
			throw new JPokemonException("Cannot unregister experience curve without an id");
		}

		try {
			SqlStatement.delete(Table.ExperienceCurve.class).where("id").eq(id).execute();
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	private ExperienceCurve getExperienceCurveForClassName(String experienceCurveClassName) throws Exception {
		Class<?> experienceCurveClass = Class.forName(experienceCurveClassName);
		ExperienceCurve experienceCurve = (ExperienceCurve) experienceCurveClass.newInstance();
		return experienceCurve;
	}
}
