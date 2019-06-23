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
import br.ufsc.ine5605.ShardRPG.Screens.GameContainer;

public class Game {

	private final JsonDao dao;

	private Player player;

	private final Intepreter intepreter;

	private final MapListRoom listRoom;

	private final Log log = new Log();


	public Game() throws Exception {
		intepreter = new Intepreter();
		final Map<String, Player> mapList;
		dao = new JsonDao();
		listRoom = new MapListRoom();

		try {
			final File file = new File("PlayersList.json");
			try {
				final PlayerList playerList = new Gson().fromJson(
					JsonDao.loadJsonContent("PlayersList.json", StandardCharsets.UTF_8), PlayerList.class);
				if (playerList == null) {
					file.delete();
					dao.registerPlayer(new Player(null, null, null, null, null));
				}
			} catch (final Exception e) {
				file.delete();
				dao.registerPlayer(new Player(null, null, null, null, null));
			}
			do {
				ScreenHandler.getInstance().openMainMenu(0, 0);
				player = ScreenHandler.getInstance().getPlayer();
			} while (player == null);
		} catch (final Exception e) {
			/* GameTextScreen.println(e); */
		}
	}


	public void start() {
		ScreenHandler.getInstance().openGameContainer(0, 0);
		System.out.println("AQUI");
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
					GameContainer.getInstance();
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
					dao.saveGame(player);
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
