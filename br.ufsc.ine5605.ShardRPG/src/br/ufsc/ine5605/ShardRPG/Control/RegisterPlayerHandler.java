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
		System.out.println("Bem vindo!");
		System.out.println("Antes de sua aventura começar, tenho algumas perguntas para você...");
		System.out.println("Primeiro de tudo, como você se chama?");
		while(playerName == null || playerName.length() == 0 || playerName.matches("^\\s+$")) {
		System.out.print("> ");
		 playerName = receiveString();
		 if(playerName.length() == 0 || playerName == null || playerName.matches("^\\s+$")) {
			 System.out.println("Por favor digite um nome valido.");
		 }
		} 
		System.out.println(playerName + " han? Interessante.");
		System.out.println("Diga, "+ playerName +", você se identifica como ser humano?");
		System.out.println("1- Humano");
		System.out.println("2- Orc");
		do {
			try {
				System.out.print("> ");
				int playerRaceInteger = input.nextInt();
				playerRace = null;
				switch(playerRaceInteger) {
					case 1 :{
						System.out.println("Entendi...");
						playerRace = PlayerRace.human;
						break;
					}
					case 2:{
						System.out.println("Ah, um companheiro Orc! Sucesso seja suas lutas!");
						playerRace = PlayerRace.orc;
						break;
					}
					default:{
						System.out.println("Por favor Insira um numero entre 1 e 2.");
						continue;
					}
				
				}
				}catch(Exception e) {
					System.out.println("Por favor Insira um numero entre 1 e 2.");
					input.next();
					continue;
				}
		} while(playerRace == null);
		
		System.out.println("Bom, tenho mais uma pergunta antes de deixá-lo ir.");
		System.out.println("Qual sua especialidade?");
		System.out.println("1- Guerreiro");
		System.out.println("2- Mago");
		System.out.println("3- Ladino");
		do {
			try {
				System.out.print("> ");
				int playerTypeInteger = receiveInteger();
				playerType = null;
				switch(playerTypeInteger) {
					case 1:{
						System.out.println(playerName +":Não vê pelo meu porte que sou um guerreiro?");
						playerType = PlayerType.warrior;
						System.out.println("Oras mero mortal, me desafie e serás feito pó.");
						break;
					}
					case 2:{
						System.out.println(playerName +":Me atenho as obras arcanas. Busque conhecimento.");
						playerType = PlayerType.mage;
						System.out.println("Um apreciador de encantos! Boa sorte em sua aventura.");
						break;
					}
					case 3:{
						System.out.println(playerName + ":Você não notou que seu colar sumiu?");
						playerType = PlayerType.rogue;
						System.out.println("Ladino! Volte aqui!");
						break;
					}
					default:{
						System.out.println("Por favor digite um numero entre 1 e 3.");
						input.next();
						continue;
					}
				}
			}catch(Exception e) {
				System.out.println("Por favor digite um numero entre 1 e 3.");
				input.next();
			}
		} while(playerType == null);
			if(playerName == null || playerName == "" || playerType == null || playerRace == null) {
				System.out.println("Ocorreu um erro em seu registro! Verifique os dados e tente novamente! \n"+ "Seu nome:"+ playerName +"\n Sua raca:"+ playerRace+",\n Sua classe:"+ playerType);
			}
		}while(playerName == null || playerName == "" || playerType == null || playerRace == null);
		Player player = new Player(playerName, playerType, playerRace,0, playerName.toUpperCase());
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
				} catch(Exception e) {
					System.out.println("Valor Invalido");
					input.next();
				}
		return numberInput;
	}
}
