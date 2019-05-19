package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Info.Player;

interface Inspectable {

	public void inspect();


	public void setInspectMessage(String string);
}

interface Visible {

	public boolean isVisible();


	public void setVisible(boolean isVisible);
}

interface PlotDevice {

	public void advancePlayerProgress(Player player);
}

interface canDrop {

	public void drop();
}

interface canPickUp {

	public void pickUp();
}