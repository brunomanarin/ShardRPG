package br.ufsc.ine5605.ShardRPG.Item;

import java.util.ArrayList;

public class Item {
	private String description;
	private String name;
	private String[] alias;
	private static ArrayList<Item> allItemsList;

	public Item(String description, String name, String[] alias) {
		this.name = name;
		this.description = description;
		this.setAlias(alias);
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

	public String[] getAlias() {
		return alias;
	}

	public void setAlias(String[] alias) {
		this.alias = alias;
	}
	public static void initAllItemsList() {
		allItemsList = new ArrayList<>();
		allItemsList.add(new ItemShard("Shard", "A glowing piece, what is it made of?", new String[] {"Shard", "piece"}));
		
		
		
	}
	public static Item getInstance(String string) {
		if(allItemsList == null) {
			initAllItemsList();
		}
		for(Item item: allItemsList) {
			for(String alias: item.getAlias()) {
				if(string.equals(alias)) {
					return item;
				}
			}
		}
		return null;
	}

}
