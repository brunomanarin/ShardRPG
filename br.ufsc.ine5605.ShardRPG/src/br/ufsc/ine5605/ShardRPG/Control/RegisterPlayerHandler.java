package br.ufsc.ine5605.ShardRPG.Control;

import java.util.Scanner;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerRace;
import br.ufsc.ine5605.ShardRPG.Info.PlayerType;

public class RegisterPlayerHandler {

	Scanner input = new Scanner(System.in);

	String playerName;

	String playerRaceInteger;

	String playerTypeInteger;

	PlayerRace playerRace;

	PlayerType playerType;


	public Player registerNewPlayer() throws Exception {
		do {
			System.out.println("Welcome!\n");
			System.out.println("Before you begin, may i ask you some questions?");
			System.out.println("First things first, what is your name?");
			while (playerName == null || playerName.length() == 0 || playerName.matches("^\\s+$")) {
				System.out.print("> ");
				playerName = receiveString();
				if (playerName.length() == 0 || playerName == null || playerName.matches("^\\s+$")) {
					System.out.println("Please enter a valid name.");
				}
			}
			System.out.println(playerName + " han? Interesting.\n");
			System.out.println("So, " + playerName + ", do you consider yourself to be a human?");
			System.out.println("1- Yes sure, human as it can be.");
			System.out.println("2- I am an orc.");
			do {
				try {
					System.out.print("> ");
					final int playerRaceInteger = input.nextInt();
					playerRace = null;
					switch (playerRaceInteger) {
					case 1: {
						System.out.println("Got it...");
						playerRace = PlayerRace.human;
						break;
					}
					case 2: {
						System.out.println("Oh, a fellow orc! May your battles be fierce!\n");
						playerRace = PlayerRace.orc;
						break;
					}
					default: {
						System.out.println("The input must be a number between 1 and 2.");
						continue;
					}

					}
				} catch (final Exception e) {
					System.out.println("The input must be a number between 1 and 2.");
					input.next();
					continue;
				}
			} while (playerRace == null);

			System.out.println("One more before you go.");
			System.out.println("What do you consider to be your speciality?\n");
			System.out.println("1- I am a Warrior, a soldier. Can't you see?");
			System.out.println("2- I am a Mage, knowledge is my duty.");
			System.out.println("3- I am a rogue, can't you see your medallion just vanished?");
			do {
				try {
					System.out.print("> ");
					final int playerTypeInteger = receiveInteger();
					playerType = null;
					switch (playerTypeInteger) {
					case 1: {
						playerType = PlayerType.warrior;
						System.out.println("Challenge me and i will crush you.");
						break;
					}
					case 2: {
						playerType = PlayerType.mage;
						System.out.println("Oh, i see, an apreciator of the arcane arts.");
						break;
					}
					case 3: {
						playerType = PlayerType.rogue;
						System.out.println("You thief! Get Back here!");
						break;
					}
					default: {
						System.out.println("The input be a number between 1 and 3.");
						input.next();
						continue;
					}
					}
				} catch (final Exception e) {
					System.out.println("The input be a number between 1 and 3.");
					input.next();
				}
			} while (playerType == null);
			if (playerName == null || playerName == "" || playerType == null || playerRace == null) {
				System.out.println(
					"An error ocurred while handling your registry! Please check your data and try again:\n"
						+ "Name:" + playerName + "\n Race:" + playerRace + ",\n Class:" + playerType);
			}
		} while (playerName == null || playerName == "" || playerType == null || playerRace == null);
		final Player player = new Player(playerName, playerType, playerRace, 0, playerName.toUpperCase());
		return player;
	}


	public String receiveString() {
		return input.nextLine();
	}


	public int receiveInteger() {
		Integer numberInput = 0;
		try {
			numberInput = input.nextInt();
			input.nextLine();
		} catch (final Exception e) {
			System.out.println("Invalid input");
			input.next();
		}
		return numberInput;
	}
}
