package org.jpokemon.property.overworld;

import java.util.ArrayList;
import java.util.List;

public class TmxFileProperties {
	protected int tileSize, entityZIndex;
	protected List<String> tileSets = new ArrayList<String>();

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getEntityZIndex() {
		return entityZIndex;
	}

	public void setEntityZIndex(int entityZIndex) {
		this.entityZIndex = entityZIndex;
	}

	public List<String> getTileSets() {
		return tileSets;
	}

	public void setTileSets(List<String> tileSets) {
		this.tileSets = tileSets;
	}
}
