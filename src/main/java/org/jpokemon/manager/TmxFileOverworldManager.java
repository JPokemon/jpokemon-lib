package org.jpokemon.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.jpokemon.api.Action;
import org.jpokemon.api.ActionFactory;
import org.jpokemon.api.ActionSet;
import org.jpokemon.api.JPokemonException;
import org.jpokemon.api.Manager;
import org.jpokemon.api.Overworld;
import org.jpokemon.api.OverworldEntity;
import org.jpokemon.api.Requirement;
import org.jpokemon.api.RequirementFactory;
import org.jpokemon.property.overworld.TmxFileProperties;
import org.jpokemon.util.Options;
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
	private static final String OBJECT_NODE_NAME = "object";
	private static final String PROPERTY_NODE_NAME = "property";
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
		overworld.setName(name);
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

					for (int j = 0; j < node.getChildNodes().getLength(); j++) {
						Node objectNode = node.getChildNodes().item(j);

						if (!OBJECT_NODE_NAME.equals(objectNode.getNodeName())) {
							continue;
						}

						OverworldEntity entity = new OverworldEntity();

						if (objectNode.getAttributes().getNamedItem("name") != null) {
							entity.setName(objectNode.getAttributes().getNamedItem("name").getNodeValue());
						}

						if (objectNode.getAttributes().getNamedItem("type") != null) {
							String objectType = objectNode.getAttributes().getNamedItem("type").getNodeValue();
							entity.setMovement(objectType);
						}
						else {
							entity.setMovement(org.jpokemon.movement.Solid.class.getName());
						}

						List<String> triggers = new ArrayList<String>();

						if (objectNode.getChildNodes().getLength() > 0) {
							ActionSet anonymousActionSet = new ActionSet();
							Node objectPropertiesNode = objectNode.getChildNodes().item(1);

							for (int k = 0; k < objectPropertiesNode.getChildNodes().getLength(); k++) {
								Node objectPropertyNode = objectPropertiesNode.getChildNodes().item(k);

								if (!PROPERTY_NODE_NAME.equals(objectPropertyNode.getNodeName())) {
									continue;
								}

								String objectPropertyName = objectPropertyNode.getAttributes().getNamedItem("name").getNodeValue();
								String objectPropertyValue = objectPropertyNode.getAttributes().getNamedItem("value").getNodeValue();

								if ("triggers".equals(objectPropertyName)) {
									List<String> triggersList = Options.parseArray(objectPropertyValue);

									if (triggersList.isEmpty()) {
										triggers.add(null);
										triggers.add("interact");
									}

									for (String newTrigger : triggersList) {
										triggers.add(newTrigger);
									}
								}
								else if ("actions".equals(objectPropertyName)) {
									List<String> actionDatas = Options.parseArray(objectPropertyValue);

									for (String ad : actionDatas) {
										String[] actionData = ad.split(":");
										String actionName = actionData[0];
										String actionOptions = actionData[1];

										ActionFactory actionFactory = ActionFactory.manager.getByName(actionName);
										Action action = actionFactory.buildAction(actionOptions);
										anonymousActionSet.addAction(action);
									}
								}
								else if ("requirements".equals(objectPropertyName)) {
									List<String> requirementDatas = Options.parseArray(objectPropertyValue);

									for (String rd : requirementDatas) {
										String[] requirementData = rd.split(":");
										String requirementName = requirementData[0];
										String requirementOptions = requirementData[1];

										RequirementFactory requirementFactory = RequirementFactory.manager.getByName(requirementName);
										Requirement requirement = requirementFactory.buildRequirement(requirementOptions);
										anonymousActionSet.addRequirement(requirement);
									}
								}
							}

							for (String trigger : triggers) {
								entity.getActionSets(trigger).add(anonymousActionSet);
							}
						}

						if (ActionSet.manager != null) {
							// do not name people the same as maps
							for (String trigger : triggers) {
								entity.getActionSets(trigger).addAll(ActionSet.manager.getAll(entity.getName(), trigger));
							}
						}

						if (objectNode.getAttributes().getNamedItem("actions") != null) {
							List<String> actionDatas = Options.parseArray(objectNode.getAttributes().getNamedItem("actions")
									.getNodeValue());
							ActionSet anonymousActionSet = new ActionSet();

							for (String ad : actionDatas) {
								String[] actionData = ad.split(":");
								String actionName = actionData[0];
								String actionOptions = actionData[1];

								ActionFactory actionFactory = ActionFactory.manager.getByName(actionName);
								Action action = actionFactory.buildAction(actionOptions);
								anonymousActionSet.addAction(action);
							}

							if (objectNode.getAttributes().getNamedItem("requirements") != null) {
								List<String> requirementDatas = Options.parseArray(objectNode.getAttributes().getNamedItem("actions")
										.getNodeValue());

								for (String rd : requirementDatas) {
									String[] requirementData = rd.split(":");
									String requirementName = requirementData[0];
									String requirementOptions = requirementData[1];

									RequirementFactory requirementFactory = RequirementFactory.manager.getByName(requirementName);
									Requirement requirement = requirementFactory.buildRequirement(requirementOptions);
									anonymousActionSet.addRequirement(requirement);
								}
							}

							for (String trigger : triggers) {
								entity.getActionSets(trigger).add(anonymousActionSet);
							}
						}

						int xPixel = Integer.parseInt(objectNode.getAttributes().getNamedItem("x").getNodeValue());
						int yPixel = Integer.parseInt(objectNode.getAttributes().getNamedItem("y").getNodeValue());
						int xTile = xPixel / tmxFileProperties.getTileSize();
						int yTile = yPixel / tmxFileProperties.getTileSize();
						int xPixelOffset = xPixel - xTile * tmxFileProperties.getTileSize();
						int yPixelOffset = yPixel - yTile * tmxFileProperties.getTileSize();
						int pixelWidth = xPixelOffset
								+ Integer.parseInt(objectNode.getAttributes().getNamedItem("width").getNodeValue());
						int pixelHeight = yPixelOffset
								+ Integer.parseInt(objectNode.getAttributes().getNamedItem("height").getNodeValue());

						entity.setX(xTile);
						entity.setY(yTile);
						entity.setWidth((int) Math.ceil(pixelWidth / tmxFileProperties.getTileSize()) + 1);
						entity.setHeight((int) Math.ceil(pixelHeight / tmxFileProperties.getTileSize()) + 1);

						overworld.addEntity(entity);
					}

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
}
