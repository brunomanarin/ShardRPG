package br.ufsc.ine5605.ShardRPG.Control;

import br.ufsc.ine5605.ShardRPG.Screens.GameContainer;
import br.ufsc.ine5605.ShardRPG.Screens.MainMenu;
import br.ufsc.ine5605.ShardRPG.Screens.RegisterCreate;
import br.ufsc.ine5605.ShardRPG.Screens.RegisterModify;

public class ScreenHandler {

	private static ScreenHandler instance;
	
	private ScreenHandler() {
		
	}
	public static ScreenHandler getInstance() {
		if(instance == null) {
			instance = new ScreenHandler();
		}
		return instance;
	}
	
	public void openMainMenu() {
		MainMenu.getInstance().setVisible(true);
	}
	public void openRegisterCreate() {
		RegisterCreate.getInstance().setVisible(true);
	}
	public void openGameContainer() {
		GameContainer.getInstance().setVisible(true);
	}
	public void closeMainMenu() {
		MainMenu.getInstance().setVisible(false);
	}
	public void closeRegisterCreate() {
		RegisterCreate.getInstance().setVisible(false);
	}
	public void closeGameContainer() {
		GameContainer.getInstance().setVisible(false);
	}
	
	
}
