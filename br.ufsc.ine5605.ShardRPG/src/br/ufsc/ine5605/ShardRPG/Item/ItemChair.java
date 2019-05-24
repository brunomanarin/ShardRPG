package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Control.GameTextScreen;
import br.ufsc.ine5605.ShardRPG.Info.Player;

public class ItemChair extends Item implements Breakable {

	private String destroyMessage;


	public String getDestroyMessage() {
		return destroyMessage;
	}


	public ItemChair(String name, String description, String[] alias) {
		super(name, description, alias, false);
		destroyMessage = "You use your fury to smash up the chair, it's pieces fly everywhere. Why have you done this?";
	}


	@Override
	public void destroy(Player player, Item item) {
		GameTextScreen.println(destroyMessage);
		player.getCurrentRoom().remove(item);
	}


	@Override
	public void setDestroyMessage(String string) {
		destroyMessage = string;
	}

}
