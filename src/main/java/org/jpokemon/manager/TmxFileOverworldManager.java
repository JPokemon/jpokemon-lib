package org.jpokemon.manager;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Manager;
import org.jpokemon.api.Overworld;
import org.jpokemon.property.overworld.TmxFileProperties;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Provides read-only management of TMX file-based maps, to be used as
 * overworlds. The TMX format is used by the popular open source tile-based map
 * editor <a href="http://www.mapeditor.org/">Tiled</a>.
 * 
 * @author zach
 *
 */
public class TmxFileOverworldManager implements Manager<Overworld> {
	protected final String folderPath;

	private static final String TILE_LAYER_NODE_NAME = "layer";
	private static final String OBJECT_LAYER_NODE_NAME = "objectgroup";
	private static final String TILESET_LAYER_NODE_NAME = "tileset";
	private static final String MAP_HEIGHT_PROPERTY = "height";
	private static final String MAP_WIDTH_PROPERTY = "width";
	private static final String MAP_TILE_SIZE_PROPERTY = "tileheight";
	private static final String TILESET_NAME_PROPERTY = "name";

	public TmxFileOverworldManager() {
		this("src/resources/maps");
	}

	public TmxFileOverworldManager(String folderPath) {
		this.folderPath = folderPath;
	}

	@Override
	public boolean isRegistered(String name) {
		return getByName(name) != null;
	}

	@Override
	public void register(Overworld managed) throws JPokemonException {
		throw new JPokemonException("Registration of overworlds via TMX file creation is unsupported");
	}

	@Override
	public List<Overworld> getAll() throws JPokemonException {
		return null;
	}

	@Override
	public Overworld getByName(String name) throws JPokemonException {
		Overworld overworld = new Overworld();
		TmxFileProperties tmxFileProperties = new TmxFileProperties();
		overworld.addProperty(tmxFileProperties);

		try {
			File mapFile = new File(folderPath, name + ".tmx");
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(mapFile);
			document.normalize();

			Node mapNode = document.getDocumentElement();

			overworld.setHeight(Integer.parseInt(mapNode.getAttributes().getNamedItem(MAP_HEIGHT_PROPERTY).getNodeValue()));
			overworld.setWidth(Integer.parseInt(mapNode.getAttributes().getNamedItem(MAP_WIDTH_PROPERTY).getNodeValue()));

			tmxFileProperties.setTileSize(Integer.parseInt(mapNode.getAttributes().getNamedItem(MAP_TILE_SIZE_PROPERTY)
					.getNodeValue()));

			int entityZIndex = 1;

			for (int i = 0; i < mapNode.getChildNodes().getLength(); i++) {
				Node node = mapNode.getChildNodes().item(i);

				if (TILESET_LAYER_NODE_NAME.equals(node.getNodeName())) {
					String tilesetName = node.getAttributes().getNamedItem(TILESET_NAME_PROPERTY).getNodeValue();
					tmxFileProperties.getTileSets().add(tilesetName);
					entityZIndex++;
				}
				else if (TILE_LAYER_NODE_NAME.equals(node.getNodeName())) {
					entityZIndex++;
				}
				else if (OBJECT_LAYER_NODE_NAME.equals(node.getNodeName())) {
					tmxFileProperties.setEntityZIndex(entityZIndex);

					// TODO - attach entities

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			overworld = null;
		}

		return overworld;
	}

	@Override
	public void update(Overworld managed) throws JPokemonException {
		throw new JPokemonException("Modification of overworlds via TMX file rewrite is unsupported");
	}

	@Override
	public void unregister(String name) throws JPokemonException {
		throw new JPokemonException("Removal of overworlds via TMX file deletion is unsupported");
	}

	/**
	 * Initializes a new TmxFileOverworldManager as the {@link Overworld#manager}
	 * 
	 * @throws JPokemonException If the Ability.manager is already defined
	 */
	public static void init(String folderPath) throws JPokemonException {
		if (Overworld.manager != null) {
			throw new JPokemonException("Overworld.manager already defined");
		}

		Overworld.manager = new TmxFileOverworldManager(folderPath);
	}
}
