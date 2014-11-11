package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.Ability;
import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Property;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Ability abilities}. Optionally
 * provides {@link Ability#properties} using the {@link Property#builders}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteAbilityManager implements Manager<Ability> {
	@Override
	public Ability create() {
		return new Ability();
	}

	@Override
	public boolean isRegistered(String id) {
		return get(id) != null;
	}

	@Override
	public List<Ability> getAll() throws JPokemonException {
		List<Ability> abilities = new ArrayList<Ability>();

		try {
			for (Table.Ability abilityRow : SqlStatement.select(Table.Ability.class).getList()) {
				Ability ability = create();
				ability.setId(abilityRow.getId());
				ability.setName(abilityRow.getName());
				ability.setDescription(abilityRow.getDescription());

				buildAbilityProperties(ability);
				abilities.add(ability);
			}
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		return abilities;
	}

	@Override
	public Ability get(String id) throws JPokemonException {
		Ability ability = null;

		try {
			Table.Ability abilityRow = SqlStatement.select(Table.Ability.class).where("id").eq(id).getFirst();

			if (abilityRow != null) {
				ability = create();
				ability.setId(abilityRow.getId());
				ability.setName(abilityRow.getName());
				ability.setDescription(abilityRow.getDescription());
				buildAbilityProperties(ability);
			}
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}

		return ability;
	}

	@Override
	public void register(Ability ability) throws JPokemonException {
		if (ability == null) {
			throw new JPokemonException("Cannot register null ability");
		}
		if (ability.getId() == null) {
			throw new JPokemonException("Cannot register ability without an id: " + ability);
		}
		if (isRegistered(ability.getId())) {
			throw new JPokemonException("An ability is already registered with id: " + ability.getId());
		}

		try {
			Table.Ability abilityRow = new Table.Ability(ability);
			SqlStatement.insert(abilityRow).execute();
			insertAbilityProperties(ability);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void update(Ability ability) throws JPokemonException {
		if (ability == null) {
			throw new JPokemonException("Cannot register null ability");
		}
		if (ability.getId() == null) {
			throw new JPokemonException("Cannot register ability without an id: " + ability);
		}
		if (!isRegistered(ability.getId())) {
			throw new JPokemonException("An ability is not registered with id: " + ability.getId());
		}

		try {
			Table.Ability abilityRow = new Table.Ability(ability);
			SqlStatement.update(abilityRow).where("id").eq(ability.getId()).execute();
			updateAbilityProperties(ability);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	@Override
	public void unregister(String id) throws JPokemonException {
		if (id == null) {
			throw new JPokemonException("Cannot unregister ability without an id");
		}

		try {
			SqlStatement.delete(Table.Ability.class).where("id").eq(id).execute();
			deleteAbilityProperties(id);
		} catch (DataConnectionException e) {
			throw new JPokemonException(e);
		}
	}

	private static void buildAbilityProperties(Ability ability) throws DataConnectionException {
		List<Table.AbilityProperty> propertyRows = SqlStatement.select(Table.AbilityProperty.class).where("abilityId").eq(ability.getId()).getList();

		for (Table.AbilityProperty propertyRow : propertyRows) {
			Builder<Object> builder = Property.builders.get(propertyRow.getPropertyId());
			Object property;

			if (builder != null) {
				property = builder.construct(propertyRow.getOptions());
			}
			else {
				property = propertyRow.getOptions();
			}

			ability.setProperty(propertyRow.getPropertyId(), property);
		}
	}

	private static void insertAbilityProperties(Ability ability) throws DataConnectionException {
		String abilityId = ability.getId();

		for (Map.Entry<String, Object> propertyEntry : ability.getProperties().entrySet()) {
			String propertyId = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			String options = null;

			Builder<Object> builder = Property.builders.get(propertyId);
			if (builder != null) {
				options = builder.destruct(property);
			}
			else {
				options = property.toString();
			}

			Table.AbilityProperty propertyRow = new Table.AbilityProperty(abilityId, propertyId, options);
			SqlStatement.insert(propertyRow).execute();
		}
	}

	private static void updateAbilityProperties(Ability ability) throws DataConnectionException {
		deleteAbilityProperties(ability.getId());
		insertAbilityProperties(ability);
	}

	private static void deleteAbilityProperties(String id) throws DataConnectionException {
		SqlStatement.delete(Table.AbilityProperty.class).where("abilityId").eq(id).execute();
	}
}
