package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Room;

public class ItemMirror extends Item implements Breakable, Inspectable{
	private String destroyMessage;
	private String inspectMessage;



	public ItemMirror(String description, String name, String[] alias) {
		super(description, name, alias);
		destroyMessage = "You destroy the remainders of the already shattered mirror.";
	}

	public void destroy(Room room) {
		System.out.println(this.getDestroyMessage());
		room.removeObject(this);
		
	}

	public void setDestroyMessage(String string) {
		this.destroyMessage = string;		
	}

	public String getDestroyMessage() {
		return this.destroyMessage;
	}
	public void inspect() {
		// TODO Auto-generated method stub
		
	}
	
	public String getInspectMessage() {
		return inspectMessage;
	}

	public void setInspectMessage(String string) {
		if(this.inspectMessage!= null) {
				System.out.println(this.getInspectMessage());
		} else {
			System.out.println("This is a "+ this +".");
		}
		
	}

}
