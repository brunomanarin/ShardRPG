package br.ufsc.ine5605.ShardRPG.Screens;

public class RegisterModify {

	private static RegisterModify instance;
	
	public static RegisterModify getInstance() {
		if(instance == null) {
			instance = new RegisterModify();
		}
		return instance;
	}
}
