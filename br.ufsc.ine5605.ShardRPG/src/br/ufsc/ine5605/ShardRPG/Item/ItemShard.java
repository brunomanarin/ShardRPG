package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Player;

public class ItemShard extends Item implements PlotDevice{
	public ItemShard(String name, String description, String[] alias) {
		super(name,description, alias);
	}

	public void advancePlayerProgress(Player player) {
		player.setProgress(player.getProgress()+1);
	}

}
