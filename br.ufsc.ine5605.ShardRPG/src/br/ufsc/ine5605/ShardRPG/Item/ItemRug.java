package br.ufsc.ine5605.ShardRPG.Item;

public class ItemRug extends Item implements Inspectable{
	
	private String inspectMessage;


	public ItemRug(String description, String name, String[] alias) {
		super(description, name, alias, false);
		inspectMessage = "What are you doing? Stop smelling the carpet you weirdo.";
	}


	@Override
	public void inspect() {
		if (inspectMessage != null) {
			System.out.println(inspectMessage);
		} else {
			System.out.println("This is a " + this + ".");
		}

	}


	public String getInspectMessage() {
		return inspectMessage;
	}


	@Override
	public void setInspectMessage(String string) {
		inspectMessage = string;
	}

}
