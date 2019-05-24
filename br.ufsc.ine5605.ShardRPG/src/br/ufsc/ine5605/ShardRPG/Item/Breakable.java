package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Player;

public interface Breakable {

	public void destroy(Player player, Item item);


	public void setDestroyMessage(String string);
}
