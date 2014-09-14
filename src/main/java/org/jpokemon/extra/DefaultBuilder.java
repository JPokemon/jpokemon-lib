package org.jpokemon.extra;

import org.jpokemon.api.Builder;

public class DefaultBuilder<T> implements Builder<T> {
	protected T object;

	public DefaultBuilder(T object) {
		this.object = object;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<T> getOutputClass() {
		return (Class<T>) object.getClass();
	}

	@Override
	public T construct(String options) {
		return object;
	}

	@Override
	public String destruct(T object) {
		return null;
	}
}
