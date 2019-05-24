package br.ufsc.ine5605.ShardRPG.Item;

import br.ufsc.ine5605.ShardRPG.Control.GameTextScreen;
import br.ufsc.ine5605.ShardRPG.Info.Player;
import br.ufsc.ine5605.ShardRPG.Info.Room;

public class ItemDiningTable extends Item implements BreakableWithTool{

		private String destroyMessage;


		public String getDestroyMessage() {
			return destroyMessage;
		}


		public ItemDiningTable(String name, String description, String[] alias) {
			super(name, description, alias, false);
			destroyMessage = "You use your hammer and with the might of the warrior you're you smash the table into pieces. \n Rotten food and wood flies everywhere.";
		}


		@Override
		public void destroy(Player player, Item item, Room room) {
			if(player.hasItem(Item.getInstance("hammer"))) {
				GameTextScreen.println(destroyMessage);
				player.getCurrentRoom().remove(item);
				room.setItem(Item.getInstance("woodpiece"));
			} else {
				GameTextScreen.println("You need the item: " + Item.getInstance("hammer").getName() + " to perform this action.");
			}
		}


		@Override
		public void setDestroyMessage(String string) {
			destroyMessage = string;
		}


}
