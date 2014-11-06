package org.ospokemon.builder;

import org.ospokemon.Builder;

public class SingletonBuilder<T> implements Builder<T> {
	protected T object;

	public SingletonBuilder(T object) {
		this.object = object;
	}

	@Override
	public String getId() {
		return object.getClass().getName();
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
