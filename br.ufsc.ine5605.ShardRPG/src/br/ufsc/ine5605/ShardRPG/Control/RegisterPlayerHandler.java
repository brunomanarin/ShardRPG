package br.ufsc.ine5605.ShardRPG.Control;

import java.io.File;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerRace;
import br.ufsc.ine5605.ShardRPG.Info.PlayerType;

public class RegisterPlayerHandler {

	String playerName;

	String playerRaceInteger;

	String playerTypeInteger;

	PlayerRace playerRace;

	PlayerType playerType;


	public Player registerNewPlayer() throws Exception {
		do {
			GameTextScreen.println("Welcome!\n");
			GameTextScreen.println("Before you begin, may i ask you some questions?");
			GameTextScreen.println("First things first, what is your name?");
			final File file = new File("PlayersList.json");
			if (!file.exists()) {
				new JsonDao().registerPlayer(new Player(null, null, null, null, null));
			}
			while (playerName == null || playerName.length() == 0 || playerName.matches("^\\s+$")
				|| new JsonDao().allPlayersMap().containsKey(playerName.toUpperCase())) {
				GameTextScreen.print("> ");
				playerName = GameTextScreen.receiveString();
				if (playerName.length() == 0 || playerName == null || playerName.matches("^\\s+$")) {
					GameTextScreen.println("Please enter a valid name.");
				}
				if (new JsonDao().allPlayersMap().containsKey(playerName.toUpperCase())) {
					GameTextScreen.println("\nThis name is already in use, please choose another one!");
				}
			}
			GameTextScreen.println(playerName + " han? Interesting.\n");
			GameTextScreen.println("So, " + playerName + ", do you consider yourself to be a human?");
			GameTextScreen.println("1- Yes sure, human as it can be.");
			GameTextScreen.println("2- I am an orc.");
			do {
				try {
					GameTextScreen.print("> ");
					final int playerRaceInteger = GameTextScreen.receiveInteger();
					playerRace = null;
					switch (playerRaceInteger) {
					case 1: {
						GameTextScreen.println("Got it...");
						playerRace = PlayerRace.human;
						break;
					}
					case 2: {
						GameTextScreen.println("Oh, a fellow orc! May your battles be fierce!\n");
						playerRace = PlayerRace.orc;
						break;
					}
					default: {
						GameTextScreen.println("The input must be a number between 1 and 2.");
						continue;
					}

					}
				} catch (final Exception e) {
					GameTextScreen.println("The input must be a number between 1 and 2.");

					continue;
				}
			} while (playerRace == null);

			GameTextScreen.println("One more before you go.");
			GameTextScreen.println("What do you consider to be your speciality?\n");
			GameTextScreen.println("1- I am a Warrior, a soldier. Can't you see?");
			GameTextScreen.println("2- I am a Mage, knowledge is my duty.");
			GameTextScreen.println("3- I am a rogue, can't you see your medallion just vanished?");
			do {
				try {
					GameTextScreen.print("> ");
					final int playerTypeInteger = GameTextScreen.receiveInteger();
					playerType = null;
					switch (playerTypeInteger) {
					case 1: {
						playerType = PlayerType.warrior;
						GameTextScreen.println("Challenge me and i will crush you.");
						break;
					}
					case 2: {
						playerType = PlayerType.mage;
						GameTextScreen.println("Oh, i see, an apreciator of the arcane arts.");
						break;
					}
					case 3: {
						playerType = PlayerType.rogue;
						GameTextScreen.println("You thief! Get Back here!");
						break;
					}
					default: {
						GameTextScreen.println("The input be a number between 1 and 3.");
						continue;
					}
					}
				} catch (final Exception e) {
					GameTextScreen.println("The input be a number between 1 and 3.");
				}
			} while (playerType == null);
			if (playerName == null || playerName == "" || playerType == null || playerRace == null) {
				GameTextScreen.println(
					"An error ocurred while handling your registry! Please check your data and try again:\n"
						+ "Name:" + playerName + "\n Race:" + playerRace + ",\n Class:" + playerType);
			}
		} while (playerName == null || playerName == "" || playerType == null || playerRace == null);
		final Player player = new Player(playerName, playerType, playerRace, 0, playerName.toUpperCase());
		return player;
	}

}
