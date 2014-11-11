package org.ospokemon.server;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.ospokemon.Ability;
import org.ospokemon.Action;
import org.ospokemon.ActionSet;
import org.ospokemon.BattleEffect;
import org.ospokemon.ContestCategory;
import org.ospokemon.ExperienceCurve;
import org.ospokemon.Item;
import org.ospokemon.Move;
import org.ospokemon.Nature;
import org.ospokemon.Overworld;
import org.ospokemon.Pokemon;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.Property;
import org.ospokemon.Requirement;
import org.ospokemon.Species;
import org.ospokemon.StatusCondition;
import org.ospokemon.TargetingScheme;
import org.ospokemon.TrainerClass;
import org.ospokemon.Type;
import org.ospokemon.manager.SimpleBuildersManager;
import org.ospokemon.manager.SimpleManager;
import org.ospokemon.property.trainer.ServerIdentity;
import org.ospokemon.server.rest.OSPokemonServletHolder;

/**
 * A Server with hooks for configuration, and by default, a ServletHolder
 * configured for the OSPokemon JSON REST API
 * 
 * @author zach
 */
public class OSPokemonServer extends Server {
	protected final ServletContextHandler context;

	/** Provides the default constructor */
	public OSPokemonServer() {
		this(8080);
	}

	/** Provides the port-configurable constructor */
	public OSPokemonServer(int port) {
		super(port);
		context = new ServletContextHandler(this, "/",
				ServletContextHandler.SESSIONS);
	}

	public ServletContextHandler getContext() {
		return context;
	}

	public void configureSecurity() {
		context.setSecurityHandler(new ConstraintSecurityHandler());
		context.getSecurityHandler()
				.setLoginService(new OSPokemonLoginService());
	}

	public void configureServlets() {
		context.addServlet(new OSPokemonServletHolder(), "/api/*");
		context.addServlet(new ServletHolder(new FileServlet()), "/*");
	}

	/**
	 * Initialize managers, append any additional server config
	 */
	public void configure() {
		// Initialize all simple managers
		Ability.manager = new SimpleManager<Ability>(Ability.class);
		Action.builders = new SimpleBuildersManager<Action>();
		ActionSet.manager = new SimpleManager<ActionSet>(ActionSet.class);
		BattleEffect.builders = new SimpleBuildersManager<BattleEffect>();
		ContestCategory.manager = new SimpleManager<ContestCategory>(ContestCategory.class);
		ExperienceCurve.manager = new SimpleManager<ExperienceCurve>(ExperienceCurve.class);
		Item.manager = new SimpleManager<Item>(Item.class);
		Move.manager = new SimpleManager<Move>(Move.class);
		Nature.manager = new SimpleManager<Nature>(Nature.class);
		Overworld.manager = new SimpleManager<Overworld>(Overworld.class);
		Pokemon.manager = new SimpleManager<Pokemon>(Pokemon.class);
		PokemonTrainer.manager = new SimpleManager<PokemonTrainer>(PokemonTrainer.class);
		Property.builders = new SimpleBuildersManager<Object>();
		Requirement.builders = new SimpleBuildersManager<Requirement>();
		Species.manager = new SimpleManager<Species>(Species.class);
		StatusCondition.manager = new SimpleManager<StatusCondition>(StatusCondition.class);
		TargetingScheme.manager = new SimpleManager<TargetingScheme>(TargetingScheme.class);
		TrainerClass.manager = new SimpleManager<TrainerClass>(TrainerClass.class);
		Type.manager = new SimpleManager<Type>(Type.class);

		// Register UserIdentityProperty so it can be bound to PokemonTrainers
		Property.builders.register(new ServerIdentity.Builder());

		// Build the default admin
		PokemonTrainer adminTrainer = new PokemonTrainer();
		adminTrainer.setId("admin");
		adminTrainer.setName("admin");
		ServerIdentity adminIdentity = new ServerIdentity();
		adminIdentity.setPassword("admin");
		adminIdentity.addRole("user");
		adminIdentity.addRole("admin");
		adminTrainer.setProperty(ServerIdentity.class, adminIdentity);
		PokemonTrainer.manager.register(adminTrainer);

		// Build the default user
		PokemonTrainer userTrainer = new PokemonTrainer();
		userTrainer.setId("user");
		userTrainer.setName("user");
		ServerIdentity userIdentity = new ServerIdentity();
		userIdentity.setPassword("user");
		userIdentity.addRole("user");
		userTrainer.setProperty(ServerIdentity.class, userIdentity);
		PokemonTrainer.manager.register(userTrainer);
	}

	public static void main(String... args) throws Exception {
		OSPokemonServer server = new OSPokemonServer(8080);
		server.configure();
		// server.configureSecurity(); noop
		server.configureServlets();
		server.start();
		server.join();
	}
}
