package org.ospokemon.manager.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ospokemon.Builder;
import org.ospokemon.JPokemonException;

public class Table {
	public static String DATABASE_PATH = "src/resources/empty.db";

	public static class Ability {
		protected String id, name, description;

		public Ability() {
		}

		public Ability(org.ospokemon.Ability ability) {
			id = ability.getId();
			name = ability.getName();
			description = ability.getDescription();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public static class AbilityProperty {
		protected String abilityId, propertyId, options;

		public AbilityProperty() {
		}

		@SuppressWarnings("unchecked")
		public AbilityProperty(String abilityId, String propertyId, String options) {
			this.abilityId = abilityId;
			this.propertyId = propertyId;
			this.options = options;
		}

		public String getAbilityId() {
			return abilityId;
		}

		public void setAbilityId(String abilityId) {
			this.abilityId = abilityId;
		}

		public String getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(String propertyId) {
			this.propertyId = propertyId;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}
	}

	public static class ActionBuilder {
		protected String id, builderClass;

		public ActionBuilder() {
		}

		public ActionBuilder(Builder<org.ospokemon.Action> builder) {
			id = builder.getId();
			builderClass = builder.getClass().getName();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBuilderClass() {
			return builderClass;
		}

		public void setBuilderClass(String builderClass) {
			this.builderClass = builderClass;
		}
	}

	public static class ActionSet {
		protected String id, description;

		public ActionSet() {
		}

		public ActionSet(org.ospokemon.ActionSet actionSet) {
			id = actionSet.getId();
			description = actionSet.getDescription();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public static class Action {
		protected String actionSetId, actionBuilderId, options;

		public Action() {
		}

		public Action(String actionSetId, String actionBuilderId, String options) {
			this.actionSetId = actionSetId;
			this.actionBuilderId = actionBuilderId;
			this.options = options;
		}

		public String getActionSetId() {
			return actionSetId;
		}

		public void setActionSetId(String actionSetId) {
			this.actionSetId = actionSetId;
		}

		public String getActionBuilderId() {
			return actionBuilderId;
		}

		public void setActionBuilderId(String actionBuilderId) {
			this.actionBuilderId = actionBuilderId;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}
	}

	public static class Requirement {
		protected String actionSetId, requirementBuilderId, options;

		public Requirement() {
		}

		public Requirement(String actionSetId, String requirementBuilderId, String options) {
			this.actionSetId = actionSetId;
			this.requirementBuilderId = requirementBuilderId;
			this.options = options;
		}

		public String getActionSetId() {
			return actionSetId;
		}

		public void setActionSetId(String actionSetId) {
			this.actionSetId = actionSetId;
		}

		public String getRequirementBuilderId() {
			return requirementBuilderId;
		}

		public void setRequirementBuilderId(String requirementBuilderId) {
			this.requirementBuilderId = requirementBuilderId;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}
	}

	public static class ActionSetProperty {
		protected String actionSetId, propertyId, options;

		public ActionSetProperty() {
		}

		public ActionSetProperty(String actionSetId, String propertyId, String options) {
			this.actionSetId = actionSetId;
			this.propertyId = propertyId;
			this.options = options;
		}

		public String getActionSetId() {
			return actionSetId;
		}

		public void setActionSetId(String actionSetId) {
			this.actionSetId = actionSetId;
		}

		public String getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(String propertyId) {
			this.propertyId = propertyId;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}
	}

	public static class BattleEffectBuilder {
		protected String id, builderClass;

		public BattleEffectBuilder() {
		}

		public BattleEffectBuilder(Builder<org.ospokemon.BattleEffect> builder) {
			id = builder.getId();
			builderClass = builder.getClass().getName();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBuilderClass() {
			return builderClass;
		}

		public void setBuilderClass(String builderClass) {
			this.builderClass = builderClass;
		}
	}

	public static class ContestCategory {
		protected String id, name, flavor, color, stat;

		public ContestCategory() {
		}

		public ContestCategory(org.ospokemon.ContestCategory contestCategory) {
			id = contestCategory.getId();
			name = contestCategory.getName();
			flavor = contestCategory.getFlavor();
			color = contestCategory.getColor();
			stat = contestCategory.getStat();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFlavor() {
			return flavor;
		}

		public void setFlavor(String flavor) {
			this.flavor = flavor;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getStat() {
			return stat;
		}

		public void setStat(String stat) {
			this.stat = stat;
		}
	}

	public static class ContestCategoryReaction {
		protected String contestCategory1, contestCategory2, reaction;

		public String getContestCategory1() {
			return contestCategory1;
		}

		public void setContestCategory1(String contestCategory1) {
			this.contestCategory1 = contestCategory1;
		}

		public String getContestCategory2() {
			return contestCategory2;
		}

		public void setContestCategory2(String contestCategory2) {
			this.contestCategory2 = contestCategory2;
		}

		public String getReaction() {
			return reaction;
		}

		public void setReaction(String reaction) {
			this.reaction = reaction;
		}
	}

	public static class ExperienceCurve {
		protected String id, experienceCurveClass;

		public ExperienceCurve() {
		}

		public ExperienceCurve(org.ospokemon.ExperienceCurve experienceCurve) {
			id = experienceCurve.getId();
			experienceCurveClass = experienceCurve.getClass().getName();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getExperienceCurveClass() {
			return experienceCurveClass;
		}

		public void setExperienceCurveClass(String experienceCurveClass) {
			this.experienceCurveClass = experienceCurveClass;
		}
	}

	public static class Item {
		protected String name, description;
		protected int salePrice;
		protected boolean sellable, usableOutsideBattle, usableDuringBattle, consumable, holdable;

		public Item() {
		}

		public Item(org.ospokemon.Item item) {
			name = item.getName();
			description = item.getDescription();
			salePrice = item.getSalePrice();
			sellable = item.isSellable();
			usableOutsideBattle = item.isUsableOutsideBattle();
			usableDuringBattle = item.isUsableDuringBattle();
			consumable = item.isConsumable();
			holdable = item.isHoldable();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(int salePrice) {
			this.salePrice = salePrice;
		}

		public boolean isSellable() {
			return sellable;
		}

		public void setSellable(boolean sellable) {
			this.sellable = sellable;
		}

		public boolean isUsableOutsideBattle() {
			return usableOutsideBattle;
		}

		public void setUsableOutsideBattle(boolean usableOutsideBattle) {
			this.usableOutsideBattle = usableOutsideBattle;
		}

		public boolean isUsableDuringBattle() {
			return usableDuringBattle;
		}

		public void setUsableDuringBattle(boolean usableDuringBattle) {
			this.usableDuringBattle = usableDuringBattle;
		}

		public boolean isConsumable() {
			return consumable;
		}

		public void setConsumable(boolean consumable) {
			this.consumable = consumable;
		}

		public boolean isHoldable() {
			return holdable;
		}

		public void setHoldable(boolean holdable) {
			this.holdable = holdable;
		}

		public org.ospokemon.Item buildItem() {
			org.ospokemon.Item item = new org.ospokemon.Item();
			item.setName(name);
			item.setDescription(description);
			item.setSalePrice(salePrice);
			item.setSellable(sellable);
			item.setUsableOutsideBattle(usableOutsideBattle);
			item.setUsableDuringBattle(usableDuringBattle);
			item.setConsumable(consumable);
			item.setHoldable(holdable);
			return item;
		}
	}

	public static class ItemProperty {
		protected String itemName, propertyName, options;

		public ItemProperty() {
		}

		public ItemProperty(String itemName, String propertyName, Object property) {
			this.itemName = itemName;
			this.propertyName = propertyName;

			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			if (propertyProvider != null) {
				options = propertyProvider.serialize(property);
			}
			else {
				options = property.toString();
			}
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public Object buildProperty() {
			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			Object property;

			if (propertyProvider != null) {
				property = propertyProvider.build(options);
			}
			else {
				property = options;
			}

			return property;
		}
	}

	public static class Move {
		protected String name, type, category, description, target, contestCategory, contestDescription;
		protected int PP, power, appeal, jam, priority;
		protected double accuracy;

		public Move() {
		}

		public Move(org.ospokemon.Move move) {
			name = move.getName();
			type = move.getType();
			category = move.getCategory();
			description = move.getDescription();
			target = move.getTarget();
			contestCategory = move.getContestCategory();
			contestDescription = move.getContestDescription();
			PP = move.getPP();
			power = move.getPower();
			appeal = move.getAppeal();
			jam = move.getJam();
			priority = move.getPriority();
			accuracy = move.getAccuracy();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		public String getContestCategory() {
			return contestCategory;
		}

		public void setContestCategory(String contestCategory) {
			this.contestCategory = contestCategory;
		}

		public String getContestDescription() {
			return contestDescription;
		}

		public void setContestDescription(String contestDescription) {
			this.contestDescription = contestDescription;
		}

		public int getPP() {
			return PP;
		}

		public void setPP(int pP) {
			PP = pP;
		}

		public int getPower() {
			return power;
		}

		public void setPower(int power) {
			this.power = power;
		}

		public int getAppeal() {
			return appeal;
		}

		public void setAppeal(int appeal) {
			this.appeal = appeal;
		}

		public int getJam() {
			return jam;
		}

		public void setJam(int jam) {
			this.jam = jam;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public double getAccuracy() {
			return accuracy;
		}

		public void setAccuracy(double accuracy) {
			this.accuracy = accuracy;
		}

		public org.ospokemon.Move buildMove() {
			org.ospokemon.Move move = new org.ospokemon.Move();
			move.setName(name);
			move.setType(type);
			move.setCategory(category);
			move.setDescription(description);
			move.setTarget(target);
			move.setContestCategory(contestCategory);
			move.setContestDescription(contestDescription);
			move.setPP(PP);
			move.setPower(power);
			move.setAppeal(appeal);
			move.setJam(jam);
			move.setPriority(priority);
			move.setAccuracy(accuracy);
			return move;
		}
	}

	public static class MoveProperty {
		protected String moveName, propertyName, options;

		public MoveProperty() {
		}

		public MoveProperty(String moveName, String propertyName, Object property) {
			this.moveName = moveName;
			this.propertyName = propertyName;

			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			if (propertyProvider != null) {
				options = propertyProvider.serialize(property);
			}
			else {
				options = property.toString();
			}
		}

		public String getMoveName() {
			return moveName;
		}

		public void setMoveName(String moveName) {
			this.moveName = moveName;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public Object buildProperty() {
			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			Object property;

			if (propertyProvider != null) {
				property = propertyProvider.build(options);
			}
			else {
				property = options;
			}

			return property;
		}
	}

	public static class PropertyProvider {
		protected String providerClass;

		public String getProviderClass() {
			return providerClass;
		}

		public void setProviderClass(String providerClass) {
			this.providerClass = providerClass;
		}
	}

	public static class Species {
		protected String name, tag, description, hiddenAbility, experienceCurve, bodyStyle, color;
		protected int number, catchRate, eggCyles, experienceYield, tameness;
		protected boolean genderless, breedable;
		protected double maleRatio, height, weight;

		public Species() {
		}

		public Species(org.ospokemon.Species species) {
			name = species.getName();
			tag = species.getTag();
			description = species.getDescription();
			number = species.getNumber();
			hiddenAbility = species.getHiddenAbility();
			genderless = species.isGenderless();
			maleRatio = species.getMaleRatio();
			catchRate = species.getCatchRate();
			breedable = species.isBreedable();
			eggCyles = species.getEggCyles();
			height = species.getHeight();
			weight = species.getWeight();
			experienceYield = species.getExperienceYield();
			experienceCurve = species.getExperienceCurve();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getHiddenAbility() {
			return hiddenAbility;
		}

		public void setHiddenAbility(String hiddenAbility) {
			this.hiddenAbility = hiddenAbility;
		}

		public String getExperienceCurve() {
			return experienceCurve;
		}

		public void setExperienceCurve(String experienceCurve) {
			this.experienceCurve = experienceCurve;
		}

		public String getBodyStyle() {
			return bodyStyle;
		}

		public void setBodyStyle(String bodyStyle) {
			this.bodyStyle = bodyStyle;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getCatchRate() {
			return catchRate;
		}

		public void setCatchRate(int catchRate) {
			this.catchRate = catchRate;
		}

		public int getEggCyles() {
			return eggCyles;
		}

		public void setEggCyles(int eggCyles) {
			this.eggCyles = eggCyles;
		}

		public int getExperienceYield() {
			return experienceYield;
		}

		public void setExperienceYield(int experienceYield) {
			this.experienceYield = experienceYield;
		}

		public int getTameness() {
			return tameness;
		}

		public void setTameness(int tameness) {
			this.tameness = tameness;
		}

		public boolean isGenderless() {
			return genderless;
		}

		public void setGenderless(boolean genderless) {
			this.genderless = genderless;
		}

		public boolean isBreedable() {
			return breedable;
		}

		public void setBreedable(boolean breedable) {
			this.breedable = breedable;
		}

		public double getMaleRatio() {
			return maleRatio;
		}

		public void setMaleRatio(double maleRatio) {
			this.maleRatio = maleRatio;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public org.ospokemon.Species buildSpecies() {
			org.ospokemon.Species species = new org.ospokemon.Species();
			species.setName(name);
			species.setTag(tag);
			species.setDescription(description);
			species.setNumber(number);
			species.setHiddenAbility(hiddenAbility);
			species.setGenderless(genderless);
			species.setMaleRatio(maleRatio);
			species.setCatchRate(catchRate);
			species.setBreedable(breedable);
			species.setEggCyles(eggCyles);
			species.setHeight(height);
			species.setWeight(weight);
			species.setExperienceYield(experienceYield);
			species.setExperienceCurve(experienceCurve);
			return species;
		}
	}

	public static class SpeciesAbility {
		protected String speciesName, abilityName;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getAbilityName() {
			return abilityName;
		}

		public void setAbilityName(String abilityName) {
			this.abilityName = abilityName;
		}
	}

	public static class SpeciesEffortValue {
		protected String speciesName, statName;
		protected int value;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getStatName() {
			return statName;
		}

		public void setStatName(String statName) {
			this.statName = statName;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public static class SpeciesEggGroup {
		protected String speciesName, eggGroupName;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getEggGroupName() {
			return eggGroupName;
		}

		public void setEggGroupName(String eggGroupName) {
			this.eggGroupName = eggGroupName;
		}
	}

	public static class SpeciesEvolution {
		protected String speciesName, nextSpeciesName, evolutionName, options;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getNextSpeciesName() {
			return nextSpeciesName;
		}

		public void setNextSpeciesName(String nextSpeciesName) {
			this.nextSpeciesName = nextSpeciesName;
		}

		public String getEvolutionName() {
			return evolutionName;
		}

		public void setEvolutionName(String evolutionName) {
			this.evolutionName = evolutionName;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public SpeciesEvolution fromApiEvolution(String speciesName, org.ospokemon.Evolution evolution) {
			this.speciesName = speciesName;
			nextSpeciesName = evolution.getSpecies();
			evolutionName = evolution.getClass().getName();

			EvolutionFactory evolutionFactory = EvolutionFactory.manager.get(evolutionName);

			if (evolutionFactory == null) {
				throw new JPokemonException("An evolution factory is not registered with evolution class: " + evolutionName);
			}

			options = evolutionFactory.serializeEvolution(evolution);

			return this;
		}

		public org.ospokemon.Evolution toApiEvolution() {
			EvolutionFactory evolutionFactory = EvolutionFactory.manager.get(evolutionName);

			if (evolutionFactory == null) {
				throw new JPokemonException("An evolution factory is not registered with evolution class: " + evolutionName);
			}

			org.ospokemon.Evolution evolution = evolutionFactory.buildEvolution(nextSpeciesName, options);

			return evolution;
		}
	}

	public static class SpeciesMove {
		protected String speciesName, moveName, level;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getMoveName() {
			return moveName;
		}

		public void setMoveName(String moveName) {
			this.moveName = moveName;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public void addToApiSpecies(org.ospokemon.Species species) {
			if ("egg".equals(level)) {
				species.addEggMove(moveName);
			}
			else if ("machine".equals(level)) {
				species.addMachineMove(moveName);
			}
			else if ("tutor".equals(level)) {
				species.addTutorMove(moveName);
			}
			else {
				try {
					species.addMove(Integer.parseInt(level), moveName);
				} catch (NumberFormatException e) { // TODO - log
					e.printStackTrace();
				}
			}
		}

		public static List<SpeciesMove> fromApiSpecies(org.ospokemon.Species species) {
			List<SpeciesMove> speciesMoves = new ArrayList<SpeciesMove>();

			for (String moveName : species.getEggMoves()) {
				SpeciesMove speciesMove = new SpeciesMove();
				speciesMove.setSpeciesName(species.getName());
				speciesMove.setMoveName(moveName);
				speciesMove.setLevel("egg");
				speciesMoves.add(speciesMove);
			}
			for (String moveName : species.getMachineMoves()) {
				SpeciesMove speciesMove = new SpeciesMove();
				speciesMove.setSpeciesName(species.getName());
				speciesMove.setMoveName(moveName);
				speciesMove.setLevel("machine");
				speciesMoves.add(speciesMove);
			}
			for (String moveName : species.getTutorMoves()) {
				SpeciesMove speciesMove = new SpeciesMove();
				speciesMove.setSpeciesName(species.getName());
				speciesMove.setMoveName(moveName);
				speciesMove.setLevel("tutor");
				speciesMoves.add(speciesMove);
			}
			for (Integer level : species.getMoveLists().keySet()) {
				for (String moveName : species.getMoveList(level)) {
					SpeciesMove speciesMove = new SpeciesMove();
					speciesMove.setSpeciesName(species.getName());
					speciesMove.setMoveName(moveName);
					speciesMove.setLevel(Integer.toString(level));
					speciesMoves.add(speciesMove);
				}
			}

			return speciesMoves;
		}
	}

	public static class SpeciesStat {
		protected String speciesName, statName;
		protected int value;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getStatName() {
			return statName;
		}

		public void setStatName(String statName) {
			this.statName = statName;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public static class SpeciesType {
		protected String speciesName, typeName;

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
	}

	public static class SpeciesProperty {
		protected String speciesName, propertyName, options;

		public SpeciesProperty() {
		}

		public SpeciesProperty(String speciesName, String propertyName, Object property) {
			this.speciesName = speciesName;
			this.propertyName = propertyName;

			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			if (propertyProvider != null) {
				options = propertyProvider.serialize(property);
			}
			else {
				options = property.toString();
			}
		}

		public String getSpeciesName() {
			return speciesName;
		}

		public void setSpeciesName(String speciesName) {
			this.speciesName = speciesName;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public Object buildProperty() {
			org.ospokemon.Property<?> propertyProvider = org.ospokemon.Property.get(propertyName);
			Object property;

			if (propertyProvider != null) {
				property = propertyProvider.build(options);
			}
			else {
				property = options;
			}

			return property;
		}
	}

	public static class Type {
		protected String name;

		public Type() {
		}

		public Type(org.ospokemon.Type type) {
			name = type.getId();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public org.ospokemon.Type buildType() {
			org.ospokemon.Type type = new org.ospokemon.Type();
			type.setId(name);
			return type;
		}
	}

	public static class TypeEffectiveness {
		protected String type1, type2, effectiveness;

		public String getType1() {
			return type1;
		}

		public void setType1(String type1) {
			this.type1 = type1;
		}

		public String getType2() {
			return type2;
		}

		public void setType2(String type2) {
			this.type2 = type2;
		}

		public String getEffectiveness() {
			return effectiveness;
		}

		public void setEffectiveness(String effectiveness) {
			this.effectiveness = effectiveness;
		}

		public static List<TypeEffectiveness> fromApiType(org.ospokemon.Type type) {
			List<TypeEffectiveness> typeEffectivenesses = new ArrayList<TypeEffectiveness>();

			for (Map.Entry<String, String> effectivenessEntry : type.getEffectiveness().entrySet()) {
				TypeEffectiveness typeEffectiveness = new TypeEffectiveness();
				typeEffectiveness.setType1(type.getId());
				typeEffectiveness.setType2(effectivenessEntry.getKey());
				typeEffectiveness.setEffectiveness(effectivenessEntry.getValue());
				typeEffectivenesses.add(typeEffectiveness);
			}

			return typeEffectivenesses;
		}
	}
}
