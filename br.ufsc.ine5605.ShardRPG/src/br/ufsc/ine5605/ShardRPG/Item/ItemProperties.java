package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.Room;

interface Inspectable {
	public void inspect();
	public void setInspectMessage(String string);
}
interface Visible {
	public boolean isVisible();
	public void setVisible(boolean isVisible);
}
interface PlotDevice{
	public void advancePlayerProgress(Player player);
}
interface Breakable{
	public void destroy(Room room);
	public void setDestroyMessage(String string);
}
interface canDrop{
	public void drop();
}
interface canPickUp{
	public void pickUp();
}