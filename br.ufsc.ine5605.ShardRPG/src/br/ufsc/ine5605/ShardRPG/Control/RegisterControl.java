package br.ufsc.ine5605.ShardRPG.Control;

import java.io.IOException;
import java.util.Scanner;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.PlayerRace;
import br.ufsc.ine5605.ShardRPG.Info.PlayerType;


public class RegisterControl {
	Scanner input = new Scanner(System.in);
	Control control = new Control();
	String playerName;
	String playerRaceInteger;
	String playerTypeInteger;
	PlayerRace playerRace;
	PlayerType playerType;

	public Player registerNewPlayer() throws IOException {
		System.out.println("Bem vindo!");
		System.out.println("Antes de sua aventura começar, tenho algumas perguntas para você...");
		System.out.println("Primeiro de tudo, como você se chama?");
		String playerName = receiveString();
		System.out.println(playerName + " han? Interessante.");
		System.out.println("Diga, "+ playerName +", você se identifica como ser humano?");
		System.out.println("1- Humano");
		System.out.println("2- Orc");
		int playerRaceInteger = receiveInteger();
		PlayerRace playerRace = null;
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
		}
		System.out.println("Bom, tenho mais uma pergunta antes de deixá-lo ir.");
		System.out.println("Qual sua especialidade?");
		System.out.println("1- Guerreiro");
		System.out.println("2- Mago");
		System.out.println("3- Ladino");
		int playerTypeInteger = receiveInteger();
		PlayerType playerType = null;
		switch(playerTypeInteger) {
			case 1:{
				System.out.println("Não vê pelo meu porte que sou um guerreiro?");
				playerType = PlayerType.warrior;
				System.out.println("Oras mero mortal, me desafie e serás feito pó.");
				break;
			}
			case 2:{
				System.out.println("Me atenho as obras arcanas. Busque conhecimento.");
				playerType = PlayerType.mage;
				System.out.println("Um apreciador de encantos! Boa sorte em sua aventura.");
				break;
			}
			case 3:{
				System.out.println("Você não notou que seu colar sumiu?");
				playerType = PlayerType.rogue;
				System.out.println("Ladino! Volte aqui!");
				break;
			}
		}
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
			input.hasNextLine();
		} catch(Exception e){
			System.out.println("Valor invalido! Apenas numeros inteiros sao parametro.");
		}
		return numberInput;
	}
}
