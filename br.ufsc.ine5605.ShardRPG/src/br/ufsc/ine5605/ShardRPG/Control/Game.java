package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import br.ufsc.ine5605.ShardRPG.Info.Action;
import br.ufsc.ine5605.ShardRPG.Info.Intepreter;
import br.ufsc.ine5605.ShardRPG.Info.MapListRoom;
import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerList;
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
			System.out.println("Press 1 to START a new game or 2 to LOAD a save file:");
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
				try {
					new Gson().fromJson(JsonHandler.getJasonContent("PlayersList.json", StandardCharsets.UTF_8),
						PlayerList.class);
				} catch (final Exception e) {
					final File file = new File("PlayersList.json");
					file.delete();
				}
				player = playerHandler.registerNewPlayer();
				System.out.println(jsonHandler.registerPlayer(player));
			} else {
				String key;
				final String playersList = jsonHandler.playerListing();
				final File file = new File("PlayersList.json");
				if (playersList == null || !file.exists()) {
					System.out.println("No save files exist! Let's have ourselves a new adventure! \n");
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
				System.out.println("--------"+ player.getCurrentRoom().getName().toUpperCase()+"--------\n"+player.getCurrentRoom().getDescription()+"\n----------------");
			}
			String input = "";
			while (input.compareToIgnoreCase("quit") != 0) {
				System.out.print("> ");
				input = scanner.nextLine();
				final Action action = intepreter.stringInterpreter(input);

				switch (action.getType()) {
				case TYPE_WALK:
					move(action);
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
							System.out.println("--------"+player.getCurrentRoom().getName()+"--------");
							System.out.println(player.getCurrentRoom().getDescription());
							System.out.println("----------------");
						} else {
							System.out.println("--------"+player.getCurrentRoom().getName()+"-------");
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
						final Item item = action.directObject();
						player.pickUpItem(item);
						player.getCurrentRoom().remove(item);
						System.out.println();
					}
						break;
					case ActionBreak: {
						System.out.println("Quebra");
					}
						break;
					case ActionInspect: {
						System.out.println("inspect item");
					}
						break;
					case ActionDrop: {
						System.out.println("Joga item fora");
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


	private void move(Action action) {
		if (player.getCurrentRoom().canMoveToRoomInDirection(action)) {
			player.setCurrentRoom(player.getCurrentRoom().getNextRoomDirection(action));
			if(player.getCurrentRoom().getName().equals("Death Trap")) {
				System.out.println(player.getCurrentRoom().getDescription());
				player.die();
			}
			if (!player.getCurrentRoom().getWasVisited()) {
				System.out.println("--------"+player.getCurrentRoom().getName()+"--------");
				System.out.println(player.getCurrentRoom().getDescription());
				System.out.println("----------------");
			} else {
				System.out.println("--------"+player.getCurrentRoom().getName()+"-------");
				System.out.println(player.getCurrentRoom().getDescriptionAfter());
				System.out.println("----------------");
			}
			player.getCurrentRoom().setWasVisited(true);
		} else {
			System.out.println("Ouch! You've just hit a wall! Try changing your directions you goof! \n");
		}
	}
	public void shardLogoPrint() {
		System.out.println ("   SSSSSSSSSSSSSSS HHHHHHHHH     HHHHHHHHH               AAA               RRRRRRRRRRRRRRRRR   DDDDDDDDDDDDD        ");    
		  System.out.println (" SS:::::::::::::::SH:::::::H     H:::::::H              A:::A              R::::::::::::::::R  D::::::::::::DDD  ");  
		  System.out.println ("S:::::SSSSSS::::::SH:::::::H     H:::::::H             A:::::A             R::::::RRRRRR:::::R D:::::::::::::::DD ");  
		  System.out.println ("S:::::S     SSSSSSSHH::::::H     H::::::HH            A:::::::A            RR:::::R     R:::::RDDD:::::DDDDD:::::D  ");
		  System.out.println ("S:::::S              H:::::H     H:::::H             A:::::::::A             R::::R     R:::::R  D:::::D    D:::::D ");
		  System.out.println ("S:::::S              H:::::H     H:::::H            A:::::A:::::A            R::::R     R:::::R  D:::::D     D:::::D");
		  System.out.println (" S::::SSSS           H::::::HHHHH::::::H           A:::::A A:::::A           R::::RRRRRR:::::R   D:::::D     D:::::D");      
		  System.out.println ("  SS::::::SSSSS      H:::::::::::::::::H          A:::::A   A:::::A          R:::::::::::::RR    D:::::D     D:::::D");     
		  System.out.println ("    SSS::::::::SS    H:::::::::::::::::H         A:::::A     A:::::A         R::::RRRRRR:::::R   D:::::D     D:::::D");  
		  System.out.println ("       SSSSSS::::S   H::::::HHHHH::::::H        A:::::AAAAAAAAA:::::A        R::::R     R:::::R  D:::::D     D:::::D");  
		  System.out.println ("            S:::::S  H:::::H     H:::::H       A:::::::::::::::::::::A       R::::R     R:::::R  D:::::D     D:::::D"); 
		  System.out.println ("            S:::::S  H:::::H     H:::::H      A:::::AAAAAAAAAAAAA:::::A      R::::R     R:::::R  D:::::D    D:::::D");
		  System.out.println ("SSSSSSS     S:::::SHH::::::H     H::::::HH   A:::::A             A:::::A   RR:::::R     R:::::RDDD:::::DDDDD:::::D");
		  System.out.println ("S::::::SSSSSS:::::SH:::::::H     H:::::::H  A:::::A               A:::::A  R::::::R     R:::::RD:::::::::::::::DD");
		  System.out.println ("S:::::::::::::::SS H:::::::H     H:::::::H A:::::A                 A:::::A R::::::R     R:::::RD::::::::::::DDD ");
		  System.out.println (" SSSSSSSSSSSSSSS   HHHHHHHHH     HHHHHHHHHAAAAAAA                   AAAAAAARRRRRRRR     RRRRRRRDDDDDDDDDDDDD        \n\n");
	}

}
