package org.jpokemon.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Requirement;
import org.jpokemon.util.Options;

public class RequirementsContainer {
	protected List<Requirement> requirements;

	public void addRequirement(Requirement requirement) {
		getRequirements().add(requirement);
	}

	public void removeRequirement(Requirement requirement) {
		getRequirements().remove(requirement);
	}

	public List<Requirement> getRequirements() {
		if (requirements == null) {
			requirements = new ArrayList<Requirement>();
		}
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public static class Builder implements org.jpokemon.api.Builder<Object> {

		@Override
		public Class<? extends Object> getOutputClass() {
			return RequirementsContainer.class;
		}

		@Override
		public RequirementsContainer construct(String options) throws JPokemonException {
			if (Requirement.builders == null) {
				throw new JPokemonException("Cannot build requirements without Requirement.builders");
			}

			RequirementsContainer requirementsProperty = new RequirementsContainer();

			for (Map.Entry<String, String> requirementConfig : Options.parseMap(options).entrySet()) {
				String requirementId = requirementConfig.getKey();
				org.jpokemon.api.Builder<Requirement> requirementBuilder = Requirement.builders.get(requirementId);

				if (requirementBuilder == null) {
					// TODO - log
					continue;
				}

				Requirement requirement = requirementBuilder.construct(requirementConfig.getValue());
				requirementsProperty.addRequirement(requirement);
			}

			return requirementsProperty;
		}

		@Override
		public String destruct(Object object) {
			RequirementsContainer requirementsProperty = (RequirementsContainer) object;

			if (Requirement.builders == null) {
				throw new JPokemonException("Cannot serialize requirements without Requirement.builders");
			}

			Map<String, String> requirementConfig = new HashMap<String, String>();

			for (Requirement requirement : requirementsProperty.getRequirements()) {
				String requirementClassName = requirement.getClass().getName();
				org.jpokemon.api.Builder<Requirement> requirementBuilder = Requirement.builders.get(requirementClassName);

				if (requirementBuilder == null) {
					// #TODO log
					continue;
				}

				String requirementOptions = requirementBuilder.destruct(requirement);
				requirementConfig.put(requirementClassName, requirementOptions);
			}

			return Options.serializeMap(requirementConfig);
		}
	}
}
