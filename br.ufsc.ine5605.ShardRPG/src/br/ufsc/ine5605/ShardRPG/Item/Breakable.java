package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Room;

public interface Breakable {

	public void destroy(Room room);


	public void setDestroyMessage(String string);
}
