package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import br.ufsc.ine5605.ShardRPG.Info.Action;
import br.ufsc.ine5605.ShardRPG.Info.Intepreter;
import br.ufsc.ine5605.ShardRPG.Info.MapListRoom;
import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerList;
import br.ufsc.ine5605.ShardRPG.Item.Breakable;
import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Game {

	private final Scanner scanner;

	private final JsonHandler jsonHandler;

	private Player player;

	private final RegisterPlayerHandler playerHandler;

	private final Intepreter intepreter;

	private final MapListRoom listRoom;

	private final Log log = new Log();


	public Game() throws Exception {
		intepreter = new Intepreter();
		Map<String, Player> mapList;
		playerHandler = new RegisterPlayerHandler();
		scanner = new Scanner(System.in);
		jsonHandler = new JsonHandler();
		listRoom = new MapListRoom();

		int input = 0;
		try {
			shardLogoPrint();
			System.out.println("Press 1 to START a new game or 2 to LOAD a save file:\n");
			final File file = new File("PlayersList.json");
			try {
				final PlayerList playerList = new Gson().fromJson(
					JsonHandler.getJasonContent("PlayersList.json", StandardCharsets.UTF_8), PlayerList.class);
				if (playerList == null) {
					file.delete();
					jsonHandler.registerPlayer(new Player(null, null, null, null, null));
				}
			} catch (final Exception e) {
				file.delete();
				jsonHandler.registerPlayer(new Player(null, null, null, null, null));
			}
			do {
				System.out.print("> ");
				try {
					input = scanner.nextInt();
					final int playerNumbers = jsonHandler.allPlayersMap().size();
					final String playersList = jsonHandler.playerListing();
					switch (input) {
					case 1: {
						player = playerHandler.registerNewPlayer();
						System.out.println(jsonHandler.registerPlayer(player));
					}
						break;
					case 2: {
						String key;
						if (playersList == null || !file.exists() || playerNumbers <= 1) {
							System.out.println(
								"\nNo save files exist! Let's have ourselves a new adventure! \n");
							file.delete();
							player = playerHandler.registerNewPlayer();
							System.out.println(jsonHandler.registerPlayer(player));

						} else {
							System.out.println(playersList);
							do {
								System.out.println("Choose a key: ");
								System.out.print("> ");
								key = scanner.nextLine().toUpperCase();
								mapList = jsonHandler.allPlayers();
								if (!mapList.containsKey(key)) {
									System.out.println("Invalid key!\n");
								}
							} while (!mapList.containsKey(key));
							player = mapList.get(key);
							System.out.println("Success! You've just logged in!\n");
						}
					}
						break;
					case 3: {
						if (playerNumbers > 1) {
							String key;
							do {
								System.out.println(playersList);
								System.out.println("Choose a valid key: ");
								System.out.print("> ");
								key = scanner.nextLine().toUpperCase();
							} while (!jsonHandler.allPlayersMap().containsKey(key));
							if (jsonHandler.allPlayersMap().containsKey(key)) {
								jsonHandler.deletePlayer(key);
								System.out.println("\nPlayer Deleted!");
							}
						} else {
							System.out.println("\n Not players registred!");
						}
					}
						break;
					case 4: {
						if (playerNumbers > 1) {
							String key;
							do {
								System.out.println(playersList);
								System.out.println("Choose a valid key: ");
								System.out.print("> ");
								key = scanner.nextLine().toUpperCase();
							} while (!jsonHandler.allPlayersMap().containsKey(key));
							if (jsonHandler.allPlayersMap().containsKey(key)) {
								String name;
								System.out.println("Choose a new Name: ");
								System.out.print("> ");
								name = scanner.nextLine().toUpperCase();
								jsonHandler.changeName(key, name);
								System.out.println("Name Changed!");
							}
						} else {
							System.out.println("\n Not players registred!");
						}
					}
						break;
					default:
						break;
					}

					if (input != 1 && input != 2) {
						System.out.println("Press 1 to START a new game or 2 to LOAD a save file:\n");
					}
				} catch (final Exception e) {
					System.out.println("Press 1 to START a new game or 2 to LOAD a save file:\n");
					scanner.nextLine();
				}
			} while (input != 1 && input != 2);
		} catch (final Exception e) {
			System.out.println(e);
		}
	}


	public void start() {
		String input = "";
		try {
			if (player.getCurrentRoom() == null) {
				player.setCurrentRoom(listRoom.shardDungeon());
				firstVisit();
				System.out.println("If you're new to the game i suggest using the command 'help'");
			}
			while (input.compareToIgnoreCase("quit") != 0) {
				if(player.getProgress()>=3) {
					System.out.println("---CONGRATULATIONS!---");
					System.out.println("THESE ARE ALL THE ACTIONS YOU MADE IN THIS PLAYTHROUGH:");
					System.out.println(log.listAllActions());
					System.out.println("You managed to get all Shards! Your score:" + player.getProgress() + " out of 3 shards.");
					System.out.println("----------");
					System.exit(0);
				}
				do{
					System.out.print("> ");
					input = scanner.nextLine();
				}while(input.equalsIgnoreCase(""));
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
						posteriorVisits();
					} else {
						firstVisit();
						player.getCurrentRoom().setWasVisited(true);
					}
					break;

				case TYPE_NOOBJECTACTION:
					switch (action) {
					case ActionHelp: {
						help();
					}
						break;
					case ActionDie: {
						System.out.println("---- GAME OVER ----");
						System.out.println("THESE ARE ALL THE ACTIONS YOU MADE IN THIS PLAYTHROUGH:");
						System.out.println(log.listAllActions());
						System.out.println("----------");
						player.die();
						break;
					}
					case ActionPass: {
						System.out.println("----------");
						System.out.println("You do nothing.");
						System.out.println("---------- \n");
					}
						break;
					case ActionError: {
						System.out.println("----------");
						System.out.println("Invalid Action! What have you done?");
						System.out.println("---------- \n");
					}
						break;
					default:

						break;
					}
				case TYPE_OBJECTACTION:
					switch (action) {
					case ActionLook: {
						if (!player.getCurrentRoom().getWasVisited()) {
							firstVisit();
						} else {
							posteriorVisits();
						}
						break;
					}
					default:
						break;
					}

				case TYPE_HASDIRECTOBJECT:
					switch (action) {
					case ActionPickUp: {
						try {
							player.pickUpItem(item);
							player.getCurrentRoom().remove(item);
							if (item.isShard()) {
								player.setProgress(player.getProgress() + 1);
							}
						} catch (final Exception e) {
						}
					}
						break;
					case ActionBreak: {
						if (item instanceof Breakable) {
							player.getCurrentRoom().remove(item);
							System.out.println(item.getName() + " is destroyed!");
						} else {
							System.out.println("You can't break this object!");
						}
					}
						break;
					case ActionInspect: {
						if (player.getCurrentRoom().getItems().contains(item)) {
							System.out.println(item.getDescription());
						} else {
							System.out.println("\nObject in not visible!");
						}
					}
						break;
					case ActionDrop: {
						if (player.getInventario().containsValue(item)) {
							System.out.println("Dropping " + item.getName() + " on the ground.");
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
			System.out.println(e);
		}
	}


	public void saveTheGame(Player player) throws IOException {
	}


	public void shardLogoPrint() {
		System.out.println(
			"   SSSSSSSSSSSSSSS HHHHHHHHH     HHHHHHHHH               AAA               RRRRRRRRRRRRRRRRR   DDDDDDDDDDDDD        ");
		System.out.println(
			" SS:::::::::::::::SH:::::::H     H:::::::H              A:::A              R::::::::::::::::R  D::::::::::::DDD  ");
		System.out.println(
			"S:::::SSSSSS::::::SH:::::::H     H:::::::H             A:::::A             R::::::RRRRRR:::::R D:::::::::::::::DD ");
		System.out.println(
			"S:::::S     SSSSSSSHH::::::H     H::::::HH            A:::::::A            RR:::::R     R:::::RDDD:::::DDDDD:::::D  ");
		System.out.println(
			"S:::::S              H:::::H     H:::::H             A:::::::::A             R::::R     R:::::R  D:::::D    D:::::D ");
		System.out.println(
			"S:::::S              H:::::H     H:::::H            A:::::A:::::A            R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			" S::::SSSS           H::::::HHHHH::::::H           A:::::A A:::::A           R::::RRRRRR:::::R   D:::::D     D:::::D");
		System.out.println(
			"  SS::::::SSSSS      H:::::::::::::::::H          A:::::A   A:::::A          R:::::::::::::RR    D:::::D     D:::::D");
		System.out.println(
			"    SSS::::::::SS    H:::::::::::::::::H         A:::::A     A:::::A         R::::RRRRRR:::::R   D:::::D     D:::::D");
		System.out.println(
			"       SSSSSS::::S   H::::::HHHHH::::::H        A:::::AAAAAAAAA:::::A        R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			"            S:::::S  H:::::H     H:::::H       A:::::::::::::::::::::A       R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			"            S:::::S  H:::::H     H:::::H      A:::::AAAAAAAAAAAAA:::::A      R::::R     R:::::R  D:::::D    D:::::D");
		System.out.println(
			"SSSSSSS     S:::::SHH::::::H     H::::::HH   A:::::A             A:::::A   RR:::::R     R:::::RDDD:::::DDDDD:::::D");
		System.out.println(
			"S::::::SSSSSS:::::SH:::::::H     H:::::::H  A:::::A               A:::::A  R::::::R     R:::::RD:::::::::::::::DD");
		System.out.println(
			"S:::::::::::::::SS H:::::::H     H:::::::H A:::::A                 A:::::A R::::::R     R:::::RD::::::::::::DDD ");
		System.out.println(
			" SSSSSSSSSSSSSSS   HHHHHHHHH     HHHHHHHHHAAAAAAA                   AAAAAAARRRRRRRR     RRRRRRRDDDDDDDDDDDDD        \n\n");
	}


	public void help() {
		System.out.println("------ SHARD HELP ------");
		System.out.println("This is a help menu.");
		System.out
			.println("Shard is a text-based RPG, this means that all the actions in the game are done so by your inputs.");
		System.out.println("Some examples:");
		System.out.println("1- Object related actions:");
		System.out.println(
			"Some actions need objects to be performed. An example is picking up the shard to advance your progress in the game.");
		System.out.println("You can do it by going into a room where exists a pickable object(shard) and typing 'get shard'");
		System.out.println(
			"If you done it correctly, you should see a message telling you that it was added to your inventory.");
		System.out.println("Else, your character will tell you that he couldn't find the object you are looking for.");
		System.out.println("2 - Non-object related actions:");
		System.out.println("Some other actions can be done with no need of external stuff. This includes:");
		System.out.println("2.1 - Walking:");
		System.out.println("You can walk from room to room by using the command related to the direction you want to go in.");
		System.out.println("Input = 'go + (North,South,East,West)'");
		System.out.println("2.2 - Opening this menu:");
		System.out.println("Well you're already here so...");
		System.out.println("Input = '(h,help)'");
		System.out.println("2.3 - Suicide");
		System.out.println("Yes you can kill your character if you get stuck or if you just want to, you sadistic bastard.");
		System.out.println("Input = '(die,suicide)'");
		System.out.println("2.4 - Look");
		System.out.println(
			"You can use this command to look around the room you're in. Basically it will give you that room's description");
		System.out.println(
			"after you've already visited it. Useful if you're lost or just need to know where you're and don't want to");
		System.out.println("scroll all the way back to where you entered the room.");
		System.out.println("Input = '(look, l)'");
		System.out.println("2.5 - Quit");
		System.out.println("This command quits the game. Pretty self explanatory.");
		System.out.println("Input = '(quit)'");
		System.out.println(
			"Well that's pretty much it. If you got any more questions I am sorry, figure it out by yourself! I know you can, I believe in you.");
		System.out.println("----- ABOUT SHARD -----");
		System.out.println("This engine was created by Huan Shan and Bruno Manarin in May,2019.");
		System.out.println(
			"We did this as an asignment to our class at INE5603, Federal University of Santa Catarina (UFSC) , Brazil.");
		System.out.println("I hope you're having fun, Mr. Hauck.");
		System.out.println("------ END OF MENU -----");

	}


	public void firstVisit() {
		System.out.println("\n--------" + player.getCurrentRoom().getName() + "--------");
		System.out.println(player.getCurrentRoom().getDescription());
		System.out.println("----------------");
		System.out.println("OBJECTS IN THE ROOM:");
		System.out.println(player.getCurrentRoom().visibleObjects());
		System.out.println("----------------");
		System.out.println("ADJACENT ROOMS:");
		System.out.println(player.getCurrentRoom().getAdjacentRooms());
		System.out.println("----------------");
	}


	public void posteriorVisits() {
		System.out.println("\n--------" + player.getCurrentRoom().getName() + "-------");
		System.out.println(player.getCurrentRoom().getDescriptionAfter());
		System.out.println("----------------");
		System.out.println("OBJECTS IN THE ROOM:");
		System.out.println(player.getCurrentRoom().visibleObjects());
		System.out.println("----------------");
		System.out.println("ADJACENT ROOMS:");
		System.out.println(player.getCurrentRoom().getAdjacentRooms());
		System.out.println("----------------");
	}

}
