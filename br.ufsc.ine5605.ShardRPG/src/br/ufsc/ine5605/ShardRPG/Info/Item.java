package br.ufsc.ine5605.ShardRPG.Info;

public class Item {
	private String description;
	private String name;

	public Item(String description, String name) {
		this.name = name;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
