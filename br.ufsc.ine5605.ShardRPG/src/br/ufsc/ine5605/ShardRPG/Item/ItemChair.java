package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Room;

public class ItemChair  extends Item implements Breakable{
	private String destroyMessage;
	
	public ItemChair(String description, String name, String[] alias) {
		super(description, name, alias);
	}


	public void destroy(Room room) {
		System.out.println(this.destroyMessage);
		room.removeObject(this);
	}

	
	public void setDestroyMessage(String string) {
		this.destroyMessage = string;
	}

}
