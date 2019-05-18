package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import br.ufsc.ine5605.ShardRPG.Info.Player;

public class Game {

	Scanner scanner;

	JsonHandler jsonHandler;

	Player player;


	public Game() throws Exception {
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
			System.out.println(jsonHandler.registerPlayer(new RegisterPlayerHandler().registerNewPlayer()));
		} else {
			String key;
			final String playersList = jsonHandler.playerListing();
			final File file = new File("PlayersList.json");
			if (playersList == null || file.exists()) {
				System.out.println("Não há jogos carregados!  Começaremos uma nova aventura: \n");
				jsonHandler.registerPlayer(new RegisterPlayerHandler().registerNewPlayer());
				file.delete();
			} else {
				System.out.println(playersList);
				do {
					System.out.println("Escolha uma chave: ");
					System.out.print("> ");
					key = scanner.nextLine().toUpperCase();
					mapList = jsonHandler.allPlayers();
				} while (!mapList.containsKey(key));
			}
		}
	}

}
