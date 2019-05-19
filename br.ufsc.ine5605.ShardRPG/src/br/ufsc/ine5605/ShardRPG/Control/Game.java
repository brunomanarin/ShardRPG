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
			System.out.println("Digite 1 para começar um NOVO JOGO ou 2 para CARREGAR um jogo:");
			do {
				System.out.print("> ");
				try {
					input = scanner.nextInt();
					if (input != 1 && input != 2) {
						System.out.println("Digite um numero entre 1 e 2.");
					}
				} catch (final Exception e) {
					System.out.println("Digite um numero entre 1 e 2.");
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
					System.out.println("Não há jogos carregados!  Começaremos uma nova aventura: \n");
					file.delete();
					player = playerHandler.registerNewPlayer();
					System.out.println(jsonHandler.registerPlayer(player));

				} else {
					System.out.println(playersList);
					do {
						System.out.println("Escolha uma chave: ");
						System.out.print("> ");
						key = scanner.nextLine().toUpperCase();
						mapList = jsonHandler.allPlayers();
						if (!mapList.containsKey(key)) {
							System.out.println("Chave invalida!\n");
						}
					} while (!mapList.containsKey(key));
					player = mapList.get(key);
					System.out.println("Login efetuado com sucesso!\n");
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
				System.out.println(player.getCurrentRoom().getDescription());
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

				default:
					System.out.println("Não entendi seu comando, por favor escreva que nem gente. \n");
					break;
				}
			}
		} catch (final Exception e) {
			System.out.println("END GAME");
		}
	}


	private void move(Action action) {
		if (player.getCurrentRoom().canMoveToRoomInDirection(action)) {
			player.setCurrentRoom(player.getCurrentRoom().getNextRoomDirection(action));
			if (!player.getCurrentRoom().getWasVisited()) {
				System.out.println(player.getCurrentRoom().getDescription());
			} else {
				System.out.println(player.getCurrentRoom().getDescriptionAfter());
			}
			player.getCurrentRoom().setWasVisited(true);
		} else {
			System.out.println("Ouch! Você deu de cara com uma parede! Por favor tente outra direção. \n");
		}
	}

}
