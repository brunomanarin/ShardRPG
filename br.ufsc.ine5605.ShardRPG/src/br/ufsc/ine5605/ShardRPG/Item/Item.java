package br.ufsc.ine5605.ShardRPG.Item;

import java.util.LinkedList;

public abstract class Item {

	private String description;

	private String name;

	private String[] alias;

	private static LinkedList<Item> allItemsList;

	private boolean isShard;


	public Item(String name, String description, String[] alias, boolean isShard) {
		this.name = name;
		this.description = description;
		setAlias(alias);
		this.isShard = isShard;
	}


	public String getDescription() {
		return description;
	}


	public static LinkedList<Item> getAllItemsList() {
		return allItemsList;
	}


	public static void setAllItemsList(LinkedList<Item> allItemsList) {
		Item.allItemsList = allItemsList;
	}


	public boolean isShard() {
		return isShard;
	}


	public void setShard(boolean isShard) {
		this.isShard = isShard;
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
		final Item shard = new ItemShard("Shard", "A glowing piece, what is it made of?", new String[] {"shard", "piece"});
		final Item painting = new ItemPainting("Painting", "A painting, looks antique.", new String[] {"Painting"});
		final Item chair = new ItemChair("Chair", "A chair, nothing special about it.", new String[] {"chair"});
		final Item rug = new ItemRug("Rug", "A soft and red rug.", new String[] {"rug"});
		final Item hammer = new ItemHammer("Hammer","A shiny and big hammer.", new String[] {"hammer"}, false);
		final Item pieceWood = new ItemPieceWood("A piece of wood","A piece of wood, it's sharp.",new String[] {"woodpiece"}, false);
		final Item table = new ItemDiningTable("A long dining table","The table has a lot of utensils and food spilled all over it.",new String[] {"table"});
		allItemsList = new LinkedList<>();
		allItemsList.add(shard);
		allItemsList.add(painting);
		allItemsList.add(chair);
		allItemsList.add(rug);
		allItemsList.add(hammer);
		allItemsList.add(pieceWood);
		allItemsList.add(table);
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
