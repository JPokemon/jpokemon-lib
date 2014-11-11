package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.Evolution;
import org.ospokemon.JPokemonException;
import org.ospokemon.Manager;
import org.ospokemon.Property;
import org.ospokemon.Species;

import com.njkremer.Sqlite.DataConnectionException;
import com.njkremer.Sqlite.DataConnectionManager;
import com.njkremer.Sqlite.SqlStatement;

/**
 * Provides SqliteORM bindings for {@link Species}. Optionally provides
 * {@link Species#properties} using the {@link Property}
 * 
 * @author zach
 * 
 * @since 0.1
 */
public class SqliteSpeciesManager implements Manager<Species> {
	/** Indicates the path to the sqlite3 database */
	private String databasePath = "src/resources/example.db";

	/** Provides the default constructor */
	public SqliteSpeciesManager() {
	}

	/** Creates a new SqliteSpeciesManager with the specified database path */
	public SqliteSpeciesManager(String databasePath) {
		this.databasePath = databasePath;
	}

	@Override
	public boolean isRegistered(String speciesName) {
		if (speciesName == null) {
			return false;
		}

		return get(speciesName) != null;
	}

	@Override
	public void register(Species species) throws JPokemonException {
		if (species == null) {
			throw new JPokemonException("Cannot register null species");
		}
		if (species.getName() == null) {
			throw new JPokemonException("Cannot register species without a name: " + species);
		}
		if (isRegistered(species.getName())) {
			throw new JPokemonException("A species is already registered with name: " + species.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.insert(new Table.Species(species)).execute();

			insertAbilities(species);
			insertEffortValues(species);
			insertEggGroups(species);
			insertEvolutions(species);
			insertMoves(species);
			insertStats(species);
			insertTypes(species);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Species> getAll() throws JPokemonException {
		DataConnectionManager.init(databasePath);
		List<Species> registeredSpecies = new ArrayList<Species>();

		try {
			for (Table.Species speciesRow : SqlStatement.select(Table.Species.class).getList()) {
				Species species = speciesRow.buildSpecies();

				buildAbilities(species);
				buildEffortValues(species);
				buildEggGroups(species);
				buildEvolutions(species);
				buildMoves(species);
				buildStats(species);
				buildTypes(species);
				buildProperties(species);

				registeredSpecies.add(species);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return registeredSpecies;
	}

	@Override
	public Species get(String name) {
		DataConnectionManager.init(databasePath);
		Species species = null;

		try {
			Table.Species speciesRow = SqlStatement.select(Table.Species.class).where("name").eq(name).getFirst();

			if (speciesRow != null) {
				species = speciesRow.buildSpecies();

				buildAbilities(species);
				buildEffortValues(species);
				buildEggGroups(species);
				buildEvolutions(species);
				buildMoves(species);
				buildStats(species);
				buildTypes(species);
				buildProperties(species);
			}
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}

		return species;
	}

	@Override
	public void update(Species species) throws JPokemonException {
		if (species == null) {
			throw new JPokemonException("Cannot register null species");
		}
		if (species.getName() == null) {
			throw new JPokemonException("Cannot register species without a name: " + species);
		}
		if (!isRegistered(species.getName())) {
			throw new JPokemonException("A species is not registered with name: " + species.getName());
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.update(new Table.Species(species)).where("name").eq(species.getName()).execute();

			updateAbilities(species);
			updateEffortValues(species);
			updateEggGroups(species);
			updateEvolutions(species);
			updateMoves(species);
			updateStats(species);
			updateTypes(species);
			updateProperties(species);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		if (name == null) {
			throw new JPokemonException("Cannot unregister species without a name");
		}
		if (!isRegistered(name)) {
			throw new JPokemonException("A species is not registered with name: " + name);
		}

		DataConnectionManager.init(databasePath);

		try {
			SqlStatement.delete(Table.Species.class).where("name").eq(name).execute();

			deleteAbilities(name);
			deleteEffortValues(name);
			deleteEggGroups(name);
			deleteEvolutions(name);
			deleteMoves(name);
			deleteStats(name);
			deleteTypes(name);
			deleteProperties(name);
		} catch (DataConnectionException e) {
			e.printStackTrace();
		}
	}

	private static void buildAbilities(Species species) throws DataConnectionException {
		List<Table.SpeciesAbility> speciesAbilityRows = SqlStatement.select(Table.SpeciesAbility.class)
				.where("speciesName").eq(species.getName()).getList();

		for (Table.SpeciesAbility speciesAbiliityRow : speciesAbilityRows) {
			species.addAbility(speciesAbiliityRow.getAbilityName());
		}
	}

	private static void insertAbilities(Species species) throws DataConnectionException {
		for (String abilityName : species.getAbilities()) {
			Table.SpeciesAbility speciesAbilityRow = new Table.SpeciesAbility();
			speciesAbilityRow.setSpeciesName(species.getName());
			speciesAbilityRow.setAbilityName(abilityName);
			SqlStatement.insert(speciesAbilityRow).execute();
		}
	}

	private static void updateAbilities(Species species) throws DataConnectionException {
		deleteAbilities(species.getName());
		insertAbilities(species);
	}

	private static void deleteAbilities(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesAbility.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildEffortValues(Species species) throws DataConnectionException {
		List<Table.SpeciesEffortValue> speciesEffortValueRows = SqlStatement.select(Table.SpeciesEffortValue.class)
				.where("speciesName").eq(species.getName()).getList();

		for (Table.SpeciesEffortValue speciesEffortValueRow : speciesEffortValueRows) {
			species.setEffortValue(speciesEffortValueRow.getStatName(), speciesEffortValueRow.getValue());
		}
	}

	private static void insertEffortValues(Species species) throws DataConnectionException {
		for (String statName : species.getEffortValues().keySet()) {
			Table.SpeciesEffortValue speciesEffortValue = new Table.SpeciesEffortValue();
			speciesEffortValue.setSpeciesName(species.getName());
			speciesEffortValue.setStatName(statName);
			speciesEffortValue.setValue(species.getEffortValue(statName));

			SqlStatement.insert(speciesEffortValue).execute();
		}
	}

	private static void updateEffortValues(Species species) throws DataConnectionException {
		deleteEffortValues(species.getName());
		insertEffortValues(species);
	}

	private static void deleteEffortValues(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesEffortValue.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildEggGroups(Species species) throws DataConnectionException {
		List<Table.SpeciesEggGroup> speciesEggGroupRows = SqlStatement.select(Table.SpeciesEggGroup.class)
				.where("speciesName").eq(species.getName()).getList();

		for (Table.SpeciesEggGroup speciesEggGroupRow : speciesEggGroupRows) {
			species.addEggGroup(speciesEggGroupRow.getEggGroupName());
		}
	}

	private static void insertEggGroups(Species species) throws DataConnectionException {
		for (String eggGroup : species.getEggGroups()) {
			Table.SpeciesEggGroup speciesEggGroup = new Table.SpeciesEggGroup();
			speciesEggGroup.setSpeciesName(species.getName());
			speciesEggGroup.setEggGroupName(eggGroup);

			SqlStatement.insert(speciesEggGroup).execute();
		}
	}

	private static void updateEggGroups(Species species) throws DataConnectionException {
		deleteEggGroups(species.getName());
		insertEggGroups(species);
	}

	private static void deleteEggGroups(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesEggGroup.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildEvolutions(Species species) throws DataConnectionException {
		if (EvolutionFactory.manager == null) {
			return;
		}

		List<Table.SpeciesEvolution> speciesEvolutionRows = SqlStatement.select(Table.SpeciesEvolution.class)
				.where("speciesName").eq(species.getName()).getList();

		for (Table.SpeciesEvolution speciesEvolutionRow : speciesEvolutionRows) {
			species.addEvolution(speciesEvolutionRow.toApiEvolution());
		}
	}

	private static void insertEvolutions(Species species) throws DataConnectionException {
		if (EvolutionFactory.manager == null) {
			return;
		}

		for (Evolution evolution : species.getEvolutions()) {
			SqlStatement.insert(new Table.SpeciesEvolution().fromApiEvolution(species.getName(), evolution)).execute();
		}
	}

	private static void updateEvolutions(Species species) throws DataConnectionException {
		deleteEvolutions(species.getName());
		insertEvolutions(species);
	}

	private static void deleteEvolutions(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesEvolution.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildMoves(Species species) throws DataConnectionException {
		List<Table.SpeciesMove> speciesMoveRows = SqlStatement.select(Table.SpeciesMove.class).where("speciesName")
				.eq(species.getName()).getList();

		for (Table.SpeciesMove speciesMoveRow : speciesMoveRows) {
			speciesMoveRow.addToApiSpecies(species);
		}
	}

	private static void insertMoves(Species species) throws DataConnectionException {
		List<Table.SpeciesMove> speciesMoveRows = Table.SpeciesMove.fromApiSpecies(species);

		for (Table.SpeciesMove speciesMoveRow : speciesMoveRows) {
			SqlStatement.insert(speciesMoveRow).execute();
		}
	}

	private static void updateMoves(Species species) throws DataConnectionException {
		deleteMoves(species.getName());
		insertMoves(species);
	}

	private static void deleteMoves(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesMove.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildStats(Species species) throws DataConnectionException {
		List<Table.SpeciesStat> speciesStatRows = SqlStatement.select(Table.SpeciesStat.class).where("speciesName")
				.eq(species.getName()).getList();

		for (Table.SpeciesStat speciesStatRow : speciesStatRows) {
			species.setBaseStat(speciesStatRow.getStatName(), speciesStatRow.getValue());
		}
	}

	private static void insertStats(Species species) throws DataConnectionException {
		for (String statName : species.getBaseStats().keySet()) {
			Table.SpeciesStat speciesStat = new Table.SpeciesStat();
			speciesStat.setSpeciesName(species.getName());
			speciesStat.setStatName(statName);
			speciesStat.setValue(species.getBaseStat(statName));

			SqlStatement.insert(speciesStat).execute();
		}
	}

	private static void updateStats(Species species) throws DataConnectionException {
		deleteStats(species.getName());
		insertStats(species);
	}

	private static void deleteStats(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesStat.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildTypes(Species species) throws DataConnectionException {
		List<Table.SpeciesType> speciesTypeRows = SqlStatement.select(Table.SpeciesType.class).where("speciesName")
				.eq(species.getName()).getList();

		for (Table.SpeciesType speciesTypeRow : speciesTypeRows) {
			species.addType(speciesTypeRow.getTypeName());
		}
	}

	private static void insertTypes(Species species) throws DataConnectionException {
		for (String typeName : species.getTypes()) {
			Table.SpeciesType speciesType = new Table.SpeciesType();
			speciesType.setSpeciesName(species.getName());
			speciesType.setTypeName(typeName);

			SqlStatement.insert(speciesType).execute();
		}
	}

	private static void updateTypes(Species species) throws DataConnectionException {
		deleteTypes(species.getName());
		insertTypes(species);
	}

	private static void deleteTypes(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesType.class).where("speciesName").eq(speciesName).execute();
	}

	private static void buildProperties(Species species) throws DataConnectionException {
		List<Table.SpeciesProperty> propertyRows = SqlStatement.select(Table.SpeciesProperty.class).where("speciesName")
				.eq(species.getName()).getList();

		for (Table.SpeciesProperty propertyRow : propertyRows) {
			species.setProperty(propertyRow.getPropertyName(), propertyRow.buildProperty());
		}
	}

	private static void insertProperties(Species species) throws DataConnectionException {
		String speciesName = species.getName();

		for (Map.Entry<String, Object> propertyEntry : species.getProperties().entrySet()) {
			String propertyName = propertyEntry.getKey();
			Object property = propertyEntry.getValue();
			Table.SpeciesProperty abilityProperyRow = new Table.SpeciesProperty(speciesName, propertyName, property);
			SqlStatement.insert(abilityProperyRow).execute();
		}
	}

	private static void updateProperties(Species species) throws DataConnectionException {
		deleteProperties(species.getName());
		insertProperties(species);
	}

	private static void deleteProperties(String speciesName) throws DataConnectionException {
		SqlStatement.delete(Table.SpeciesProperty.class).where("speciesName").eq(speciesName).execute();
	}
}
