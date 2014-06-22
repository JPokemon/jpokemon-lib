package org.jpokemon.overworld.interaction;

import java.util.ArrayList;
import java.util.List;

public class ActionSet {
	public static ActionSetManager manager;

	protected String name, context, option;
	protected List<Action> actions = new ArrayList<Action>();
	protected List<Requirement> requirements = new ArrayList<Requirement>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public ActionSet addAction(Action action) {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}

		actions.add(action);
		return this;
	}

	public ActionSet removeAction(Action action) {
		if (actions != null) {
			actions.remove(action);
		}

		return this;
	}

	public List<Action> getActions() {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}

		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public ActionSet addRequirement(Requirement requirement) {
		if (requirements == null) {
			requirements = new ArrayList<Requirement>();
		}

		requirements.add(requirement);
		return this;
	}

	public ActionSet removeRequirement(Requirement requirement) {
		if (requirements != null) {
			requirements.remove(requirement);
		}

		return this;
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
}
