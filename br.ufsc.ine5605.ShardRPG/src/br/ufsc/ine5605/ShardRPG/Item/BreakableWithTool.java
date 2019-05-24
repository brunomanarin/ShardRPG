package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.Room;

public interface BreakableWithTool {
	
	
	public void destroy(Player player, Item item, Room room);

	public void setDestroyMessage(String string);
}
