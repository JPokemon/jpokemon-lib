package org.ospokemon.property.trainer;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.ospokemon.JPokemonException;

/**
 * Defines a property for Pokemon trainers to attach a user identity with the
 * server
 * 
 * @author zach
 * 
 */
public class ServerIdentity {
	protected String password;

	protected List<String> roles;

	public String getPassword() {
		return password;
	}

	public ServerIdentity setPassword(String password) {
		this.password = password;
		return this;
	}

	public ServerIdentity addRole(String role) {
		if (roles == null) {
			roles = new ArrayList<String>();
		}

		roles.add(role);
		return this;
	}

	public ServerIdentity removeRole(String role) {
		if (roles != null) {
			roles.remove(role);
		}

		return this;
	}

	public List<String> getRoles() {
		if (roles == null) {
			roles = new ArrayList<String>();
		}

		return roles;
	}

	public ServerIdentity setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}

	public static class Builder implements org.ospokemon.Builder<Object> {
		@Override
		public String getId() {
			return ServerIdentity.class.getName();
		}

		@Override
		public ServerIdentity construct(String options) throws JPokemonException {
			try {
				ServerIdentity userIdentityProperty = new ServerIdentity();
				JSONObject json = new JSONObject(options);

				userIdentityProperty.setPassword(json.getString("password"));

				JSONArray roles = json.getJSONArray("roles");
				for (int i = 0; i < roles.length(); i++) {
					userIdentityProperty.addRole(roles.getString(i));
				}

				return userIdentityProperty;
			} catch (JSONException e) {
				throw new JPokemonException(e);
			}
		}

		@Override
		public String destruct(Object object) {
			ServerIdentity userIdentityProperty = (ServerIdentity) object;
			JSONObject json = new JSONObject();
			JSONArray roles = new JSONArray();

			try {
				json.put("password", userIdentityProperty.getPassword());
				for (String role : userIdentityProperty.getRoles()) {
					roles.put(role);
				}
				json.put("roles", roles);
			} catch (JSONException e) {
			}

			return json.toString();
		}
	}
}
