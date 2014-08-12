package org.jpokemon.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.PropertyProvider;
import org.jpokemon.api.Requirement;
import org.jpokemon.api.RequirementFactory;
import org.jpokemon.util.Options;

public class RequirementsProperty {

	protected List<Requirement> requirements = new ArrayList<Requirement>();

	public void addRequirement(Requirement requirement) {
		requirements.add(requirement);
	}

	public void removeRequirement(Requirement requirement) {
		requirements.remove(requirement);
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public static class Provider extends PropertyProvider<RequirementsProperty> {

		@Override
		public String getName() {
			return RequirementsProperty.class.getName();
		}

		@Override
		public RequirementsProperty build(String options) throws JPokemonException {
			if (RequirementFactory.manager == null) {
				throw new JPokemonException("Cannot build requirements without RequirementFactory.manager");
			}

			RequirementsProperty requirementsProperty = new RequirementsProperty();

			for (Map.Entry<String, String> requirementConfig : Options.parseMap(options).entrySet()) {
				String requirementName = requirementConfig.getKey();
				RequirementFactory requirementFactory = RequirementFactory.manager.getByName(requirementName);

				if (requirementFactory == null) {
					// #TODO log
					continue;
				}

				Requirement requirement = requirementFactory.buildRequirement(requirementConfig.getValue());
				requirementsProperty.addRequirement(requirement);
			}

			return requirementsProperty;
		}

		@Override
		public String serialize(Object object) throws JPokemonException {
			RequirementsProperty requirementsProperty = (RequirementsProperty) object;

			if (RequirementFactory.manager == null) {
				throw new JPokemonException("Cannot serialize requirements without RequirementFactory.manager");
			}

			Map<String, String> requirementConfig = new HashMap<String, String>();

			for (Requirement requirement : requirementsProperty.getRequirements()) {
				String requirementClassName = requirement.getClass().getName();
				RequirementFactory requirementFactory = RequirementFactory.manager.getByName(requirementClassName);

				if (requirementFactory == null) {
					// #TODO log
					continue;
				}

				String requirementOptions = requirementFactory.serializeRequirement(requirement);
				requirementConfig.put(requirementClassName, requirementOptions);
			}

			return Options.serializeMap(requirementConfig);
		}
	}
}
