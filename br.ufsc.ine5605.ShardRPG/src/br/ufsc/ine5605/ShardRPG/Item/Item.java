package br.ufsc.ine5605.ShardRPG.Item;

import java.util.LinkedList;

public class Item {

	private String description;

	private String name;

	private String[] alias;

	private static LinkedList<Item> allItemsList;


	public Item(String description, String name, String[] alias) {
		this.name = name;
		this.description = description;
		setAlias(alias);
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
		final Item anel = new ItemShard("Anel amaldiçoado ", "Dizem que o anel pertençeu a Lúcifer", new String[] {"Anel"});
		final Item shard = new ItemShard("Shard", "A glowing piece, what is it made of?", new String[] {"Shard", "piece"});
		final Item painting = new ItemPainting("Painting", "A painting, looks antique.", new String[] {"Painting"});
		final Item chair = new ItemChair("Chair", "A chair, nothing special about it.", new String[] {"chair"});
		allItemsList = new LinkedList<>();
		allItemsList.add(shard);
		allItemsList.add(anel);
		allItemsList.add(painting);
		allItemsList.add(chair);
	}


	public static Item getInstance(String string) {
		if (allItemsList == null) {
			initAllItemsList();
		}
		for (final Item item : allItemsList) {
			for (final String alias : item.getAlias()) {
				if (string.equalsIgnoreCase(alias)) {
					return item;
				}
			}
		}
		return null;
	}

}
