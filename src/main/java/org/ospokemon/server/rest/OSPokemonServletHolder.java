package org.ospokemon.server.rest;

import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class OSPokemonServletHolder extends ServletHolder {
	public OSPokemonServletHolder() {
		super(ServletContainer.class);
		setInitParameter("com.sun.jersey.config.property.packages", "org.jpokemon.server.rest.api");
		setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		setInitOrder(1);
	}
}
