package org.ospokemon.server;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.security.MappedLoginService;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.util.security.Credential;
import org.ospokemon.PokemonTrainer;
import org.ospokemon.property.trainer.ServerIdentity;

/**
 * Provides an implementation of MappedLoginService which authenticates using
 * {@link PokemonTrainer#metaData}
 * 
 * @author zach
 * 
 * @see PasswordData
 * @see RoleData
 */
public class OSPokemonLoginService extends MappedLoginService {
	@Override
	protected void loadUsers() throws IOException {
		List<PokemonTrainer> pokemonTrainers = PokemonTrainer.manager.getAll();

		for (PokemonTrainer pokemonTrainer : pokemonTrainers) {
			ServerIdentity userIdentity = pokemonTrainer
					.getProperty(ServerIdentity.class);

			if (userIdentity != null) {
				Credential credential = Credential.getCredential(userIdentity
						.getPassword());
				String[] roles = userIdentity.getRoles().toArray(
						new String[userIdentity.getRoles().size()]);
				putUser(pokemonTrainer.getName(), credential, roles);
			}
		}
	}

	@Override
	protected UserIdentity loadUser(String username) {
		System.out.println(username);
		return this._users.get(username);
	}
}
