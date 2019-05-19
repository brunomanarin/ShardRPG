package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Room;

public class ItemMirror extends Item implements Inspectable{
	private String inspectMessage;



	public ItemMirror(String description, String name, String[] alias) {
		super(description, name, alias);
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
