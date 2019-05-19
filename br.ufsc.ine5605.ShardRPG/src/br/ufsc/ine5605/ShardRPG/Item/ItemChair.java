package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Room;

public class ItemChair extends Item implements Breakable {

	private String destroyMessage;


	public String getDestroyMessage() {
		return destroyMessage;
	}


	public ItemChair(String description, String name, String[] alias) {
		super(description, name, alias, false);
		destroyMessage = "You use your fury to smash up the chair, it's pieces fly everywhere. Why have you done this?";
	}


	@Override
	public void destroy(Room room) {
		System.out.println(destroyMessage);
		room.remove(this);
	}


	@Override
	public void setDestroyMessage(String string) {
		destroyMessage = string;
	}

}
