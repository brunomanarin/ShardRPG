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
			do {
				System.out.print("> ");
				try {
					input = scanner.nextInt();
					if (input != 1 && input != 2) {
						System.out.println("The input must be a number between 1 and 2.");
					}
				} catch (final Exception e) {
					System.out.println("The input must be a number between 1 and 2.");
					scanner.nextLine();
				}
			} while (input != 1 && input != 2);

			if (input == 1) {
				final File file = new File("PlayersList.json");
				try {
					final PlayerList playerList = new Gson().fromJson(
						JsonHandler.getJasonContent("PlayersList.json", StandardCharsets.UTF_8),
						PlayerList.class);
					if (playerList == null) {
						file.delete();
					}
				} catch (final Exception e) {
					file.delete();
				}
				player = playerHandler.registerNewPlayer();
				System.out.println(jsonHandler.registerPlayer(player));
			} else {
				String key;
				final String playersList = jsonHandler.playerListing();
				final File file = new File("PlayersList.json");
				if (playersList == null || !file.exists()) {
					System.out.println("\nNo save files exist! Let's have ourselves a new adventure! \n");
					file.delete();
					player = playerHandler.registerNewPlayer();
					System.out.println(jsonHandler.registerPlayer(player));

				} else {
					System.out.println(playersList);
					do {
						System.out.println("Choose a key: ");
						System.out.print("> ");
						scanner.nextLine();
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
		} catch (final Exception e) {
			System.out.println(e);
		}
	}


	public void start() {
		try {
			if (player.getCurrentRoom() == null) {
				player.setCurrentRoom(listRoom.shardDungeon());
				System.out.println("\n--------" + player.getCurrentRoom().getName().toUpperCase() + "--------\n"
					+ player.getCurrentRoom().getDescription() + "\n----------------");
			}
			String input = "";
			while (input.compareToIgnoreCase("quit") != 0) {
				System.out.print("> ");
				input = scanner.nextLine();
				final Action action = intepreter.stringInterpreter(input);
				final Item item = action.directObject();

				switch (action.getType()) {
				case TYPE_WALK:
					player.move(action);
					break;

				case TYPE_NOOBJECTACTION:
					switch (action) {
					case ActionHelp: {
						System.out.println("Ajuda");
					}
						break;
					case ActionDie: {
						player.die();
					}
						break;
					case ActionPass: {
						System.out.println("You do nothing.");
					}
						break;
					case ActionError: {
						System.out.println("Error! What have you done?");
					}
						break;
					default:

						break;
					}
				case TYPE_OBJECTACTION:
					switch (action) {
					case ActionLook: {
						if (!player.getCurrentRoom().getWasVisited()) {
							System.out.println(
								"\n--------" + player.getCurrentRoom().getName() + "--------");
							System.out.println(player.getCurrentRoom().getDescription());
							System.out.println("----------------");
						} else {
							System.out
								.println("\n--------" + player.getCurrentRoom().getName() + "-------");
							System.out.println(player.getCurrentRoom().getDescriptionAfter());
							System.out.println("----------------");
						}
						break;
					}
					default:
						break;
					}

				case TYPE_HASDIRECTOBJECT:
					switch (action) {
					case ActionPickUp: {
						player.pickUpItem(item);
						player.getCurrentRoom().remove(item);
						if (item.isShard()) {
							player.setProgress(player.getProgress() + 1);
						}
					}
						break;
					case ActionBreak: {
						if (item instanceof Breakable) {
							player.getCurrentRoom().remove(item);
							System.out.println(item.getName() + " Destruido!");
						}
					}
						break;
					case ActionInspect: {
						System.out.println(item.getDescription());
					}
						break;
					case ActionDrop: {
						if (player.getInventario().containsValue(item)) {
							player.getInventario().remove(item.getName());
							player.getCurrentRoom().setItem(item);

						} else {
							System.out.println("Voce não possui esse objeto.");
						}
					}
						break;
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
		new JsonHandler().saveGame(player);
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

}
