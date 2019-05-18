package br.ufsc.ine5605.ShardRPG.Control;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception {
		JsonHandler control = new JsonHandler();
		System.out.println(control.registerPlayer(new RegisterPlayerHandler().registerNewPlayer()));
	}
}
