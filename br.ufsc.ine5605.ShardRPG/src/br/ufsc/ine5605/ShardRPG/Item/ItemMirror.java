package br.ufsc.ine5605.ShardRPG.Item;

public class ItemMirror extends Item implements Inspectable {

	private String inspectMessage;


	public ItemMirror(String description, String name, String[] alias) {
		super(description, name, alias, false);
	}


	@Override
	public void inspect() {
		System.out.println(getDescription());
	}


	public String getInspectMessage() {
		return inspectMessage;
	}


	@Override
	public void setInspectMessage(String string) {
		if (inspectMessage != null) {
			System.out.println(getInspectMessage());
		} else {
			System.out.println("This is a " + this + ".");
		}

	}

}
