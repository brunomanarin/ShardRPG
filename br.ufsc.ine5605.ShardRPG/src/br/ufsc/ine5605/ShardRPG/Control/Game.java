package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.Gson;

import br.ufsc.ine5605.ShardRPG.Info.Action;
import br.ufsc.ine5605.ShardRPG.Info.Intepreter;
import br.ufsc.ine5605.ShardRPG.Info.MapListRoom;
import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerList;
import br.ufsc.ine5605.ShardRPG.Item.Breakable;
import br.ufsc.ine5605.ShardRPG.Item.BreakableWithTool;
import br.ufsc.ine5605.ShardRPG.Item.CanPickUp;
import br.ufsc.ine5605.ShardRPG.Item.Inspectable;
import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Game {

	private final JsonDao jsonHandler;

	private Player player;

	private final RegisterPlayerHandler playerHandler;

	private final Intepreter intepreter;

	private final MapListRoom listRoom;

	private final Log log = new Log();


	public Game() throws Exception {
		intepreter = new Intepreter();
		Map<String, Player> mapList;
		playerHandler = new RegisterPlayerHandler();
		jsonHandler = new JsonDao();
		listRoom = new MapListRoom();

		int input = 0;
		try {
			GameTextScreen.shardLogoPrint();
			GameTextScreen.mainMenu();

			final File file = new File("PlayersList.json");
			try {
				final PlayerList playerList = new Gson().fromJson(
					JsonDao.loadJsonContent("PlayersList.json", StandardCharsets.UTF_8), PlayerList.class);
				if (playerList == null) {
					file.delete();
					jsonHandler.registerPlayer(new Player(null, null, null, null, null));
				}
			} catch (final Exception e) {
				file.delete();
				jsonHandler.registerPlayer(new Player(null, null, null, null, null));
			}
			do {
				GameTextScreen.print("> ");
				try {
					input = GameTextScreen.receiveInteger();
					final int playerNumbers = jsonHandler.allPlayersMap().size();
					final String playersList = jsonHandler.playerListing();
					switch (input) {
					case 1: {
						player = playerHandler.registerNewPlayer();
						GameTextScreen.println(jsonHandler.registerPlayer(player));
					}
						break;
					case 2: {
						String key;
						if (playersList == null || !file.exists() || playerNumbers <= 1) {
							GameTextScreen.println(
								"\nNo save files exist! Let's have ourselves a new adventure! \n");
							file.delete();
							player = playerHandler.registerNewPlayer();
							GameTextScreen.println(jsonHandler.registerPlayer(player));

						} else {
							GameTextScreen.println(playersList);
							do {
								GameTextScreen.println("Choose a key: ");
								GameTextScreen.print("> ");
								key = GameTextScreen.receiveString().toUpperCase();
								mapList = jsonHandler.allPlayersMap();
								if (!mapList.containsKey(key)) {
									GameTextScreen.println("Invalid key!\n");
								}
							} while (!mapList.containsKey(key));
							player = mapList.get(key);
							GameTextScreen.println("Success! You've just logged in!\n");
						}
					}
						break;
					case 3: {
						if (playerNumbers > 1) {
							String key;
							do {
								GameTextScreen.println(playersList);
								GameTextScreen.println("Choose a valid key: ");
								GameTextScreen.print("> ");
								key = GameTextScreen.receiveString().toUpperCase();
							} while (!jsonHandler.allPlayersMap().containsKey(key));
							if (jsonHandler.allPlayersMap().containsKey(key)) {
								jsonHandler.deletePlayer(key);
								GameTextScreen.println("\nPlayer Deleted!");
							}
						} else {
							GameTextScreen.println("\n No players registered!");
						}
					}
						break;
					case 4: {
						if (playerNumbers > 1) {
							String key;
							do {
								GameTextScreen.println(playersList);
								GameTextScreen.println("Choose a valid key: ");
								GameTextScreen.print("> ");
								key = GameTextScreen.receiveString().toUpperCase();
							} while (!jsonHandler.allPlayersMap().containsKey(key));
							if (jsonHandler.allPlayersMap().containsKey(key)) {
								String name;
								GameTextScreen.println("Choose a new Name: ");
								GameTextScreen.print("> ");
								name = GameTextScreen.receiveString().toUpperCase();
								jsonHandler.changeName(key, name);
								GameTextScreen.println("Name Changed!");
							}
						} else {
							GameTextScreen.println("\n No players registered!");
						}
					}
						break;
					default:
						break;
					}

					if (input != 1 && input != 2) {
						GameTextScreen.mainMenuText();
					}
				} catch (final Exception e) {
					GameTextScreen.mainMenuText();
					GameTextScreen.receiveString();
				}
			} while (input != 1 && input != 2);
		} catch (final Exception e) {
			/* GameTextScreen.println(e); */
		}
	}


	public void start() {
		String input = "";
		try {
			listRoom.shardDungeon();
			if (player.getRoom() != null) {
				player.setCurrentRoom(listRoom.getRoom(player.getRoom()));
				GameTextScreen.posteriorVisits(player);
			}
			if (player.getCurrentRoom() == null) {
				player.setCurrentRoom(listRoom.getRoom("Spawn"));
				GameTextScreen.firstVisit(player);
				GameTextScreen.newToGame();
			}
			player.setRoom(player.getCurrentRoom().getName());
			while (input.compareToIgnoreCase("quit") != 0) {
				if (player.getProgress() >= 3) {
					GameTextScreen.youWinScreen(log, player);
					System.exit(0);
				}
				do {
					GameTextScreen.print("> ");
					input = GameTextScreen.receiveString();
				} while (input.equalsIgnoreCase(""));
				Action action = intepreter.stringInterpreter(input);
				final Item item = action.directObject();

				log.logActions(action, player);
				if (player.isDead()) {
					action = Action.ActionDie;
				}
				switch (action.getType()) {
				case TYPE_WALK:
					player.move(action);
					if (player.getCurrentRoom().getWasVisited()) {
						GameTextScreen.posteriorVisits(player);
					} else {
						GameTextScreen.firstVisit(player);
						player.getCurrentRoom().setWasVisited(true);
					}
					player.setRoom(player.getCurrentRoom().getName());
					jsonHandler.saveGame(player);
					break;

				case TYPE_NOOBJECTACTION:
					switch (action) {
					case ActionHelp: {
						GameTextScreen.help();
					}
						break;
					case ActionDie: {
						GameTextScreen.gameOverScreen(log, player);
						break;
					}
					case ActionPass: {
						GameTextScreen.actionPassText();
					}
						break;
					case ActionError: {
						GameTextScreen.actionError();
					}
						break;
					case ActionViewInventory: {
						GameTextScreen.println(player.listAllItems());
					}
					default:

						break;
					}
				case TYPE_OBJECTACTION:
					switch (action) {
					case ActionLook: {
						if (!player.getCurrentRoom().getWasVisited()) {
							GameTextScreen.firstVisit(player);
						} else {
							GameTextScreen.posteriorVisits(player);
						}
						break;
					}
					default:
						break;
					}

				case TYPE_HASDIRECTOBJECT:
					switch (action) {
					case ActionPickUp: {
						if (item instanceof CanPickUp) {
							try {
								player.pickUpItem(item);
								player.getCurrentRoom().remove(item);
								if (item.isShard()) {
									player.setProgress(player.getProgress() + 1);
									switch (player.getProgress()) {
									case 1: {
										GameTextScreen.println(
											"As you reach for the piece you feel a huge power force run through you.\n the hairs from your arms and legs stand up, strangely, you see some of them grow. What is happening?");
										break;
									}
									case 2: {
										GameTextScreen.println(
											"The glow from the shard fills your vision as you take it in your hands.\n The rush of the huge force comes back a second time, you feel an urge roaring through your insides.\n Some more hairs are visible on your body. I'd be careful if I were you.");
										break;
									}
									case 3: {
										GameTextScreen.println(
											"Your blood boils, is if you've just woken a spirit inside of you. You let out a huge scream of pain. You're transforming!");
										GameTextScreen.werewolf();
										GameTextScreen.println(
											player.getName() + " BECAME A WEREWOLF!");
										break;
									}
									}
								}
							} catch (final Exception e) {
							}
						} else {
							GameTextScreen.println("You can't pick this up.");
						}
					}
						break;
					case ActionBreak: {
						if (item instanceof Breakable) {
							((Breakable) item).destroy(player, item);
							GameTextScreen.println(item.getName() + " is destroyed!");
						} else if (item instanceof BreakableWithTool) {
							((BreakableWithTool) item).destroy(player, item, player.getCurrentRoom());
						} else {
							GameTextScreen.println("You can't break this object!");
						}
					}
						break;
					case ActionInspect: {
						if (player.getCurrentRoom().getItems().contains(item)) {
							if (item instanceof Inspectable) {
								((Inspectable) item).inspect();
							} else {
								GameTextScreen.println(item.getDescription());
							}
						} else {
							GameTextScreen.println("\nI can't see this object!");
						}
					}
						break;
					case ActionDrop: {
						if (player.getInventario().containsValue(item)) {
							GameTextScreen.println("Dropping " + item.getName() + " on the ground.");
							player.getInventario().remove(item.getName());
							player.getCurrentRoom().setItem(item);
							if (item.isShard()) {
								player.setProgress(player.getProgress() - 1);
							}
							break;
						}
					}
					default:

						break;
					}
				}

			}
		} catch (final Exception e) {
			/* GameTextScreen.println(e); */
		}
	}

}
