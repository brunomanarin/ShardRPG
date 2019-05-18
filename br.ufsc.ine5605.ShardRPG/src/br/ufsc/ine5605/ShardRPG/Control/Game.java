package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import br.ufsc.ine5605.ShardRPG.Info.Player;

public class Game {

	Scanner scanner;

	JsonHandler jsonHandler;

	Player player;

	RegisterPlayerHandler playerHandler;


	public Game() throws Exception {
		try {
			playerHandler = new RegisterPlayerHandler();
			scanner = new Scanner(System.in);
			jsonHandler = new JsonHandler();
			Map<String, Player> mapList;
			int input;
			System.out.println("Digite 1 para começar um NOVO JOGO ou 2 para CARREGAR um jogo:");
			do {
				System.out.println("Digite um numero válido!");
				System.out.print("> ");
				input = scanner.nextInt();
				scanner.nextLine();
			} while (input != 1 && input != 2);

			if (input == 1) {
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
					} while (!mapList.containsKey(key));
					player = mapList.get(key);
					System.out.println("Login efetuado com sucesso!");
				}
			}
		} catch (final Exception e) {
			System.out.println(e);
		}
	}


	public void start() {
		try {
			final String input = "";
			while (input.compareToIgnoreCase("quit") != 0) {

			}
		} catch (final Exception e) {
			System.out.println("END GAME");
		}
	}

}