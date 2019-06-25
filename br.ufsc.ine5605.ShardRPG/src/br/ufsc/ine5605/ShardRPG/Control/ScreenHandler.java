package br.ufsc.ine5605.ShardRPG.Control;

import java.io.IOException;

import br.ufsc.ine5605.ShardRPG.Screens.GameContainer;
import br.ufsc.ine5605.ShardRPG.Screens.LoadGame;
import br.ufsc.ine5605.ShardRPG.Screens.MainMenu;
import br.ufsc.ine5605.ShardRPG.Screens.RegisterCreate;

public class ScreenHandler {

	private static ScreenHandler instance;

	private ScreenHandler() {

	}


	public static ScreenHandler getInstance() {
		if (instance == null) {
			instance = new ScreenHandler();
		}
		return instance;
	}


	public void openMainMenu(int x, int y) {
		MainMenu.getInstance().setVisible(true);
		MainMenu.getInstance().setLocation(x, y);
	}


	public void openRegisterCreate(int x, int y) {
		RegisterCreate.getInstance().setVisible(true);
		RegisterCreate.getInstance().setLocation(x, y);
	}


	public void openGameContainer(int x, int y) {
		GameContainer.getInstance();
		GameContainer.getInstance().setLocation(x, y);
	}


	public void openLoadGame(int x, int y) throws IOException {
		LoadGame.getInstance().setVisible(true);
		LoadGame.getInstance().setLocation(x, y);
	}


	public void closeMainMenu() {
		MainMenu.getInstance().setVisible(false);
	}
	
	public void closeLoadGame() throws IOException {
		LoadGame.getInstance().setVisible(false);
	}


	public void closeRegisterCreate() {
		RegisterCreate.getInstance().setVisible(false);
	}


	public void closeGameContainer() {
		GameContainer.getInstance().setVisible(false);
	}


	public MainMenu getMainMenu() {
		return MainMenu.getInstance();
	}


	public RegisterCreate getRegisterCreate() {
		return RegisterCreate.getInstance();
	}

}
