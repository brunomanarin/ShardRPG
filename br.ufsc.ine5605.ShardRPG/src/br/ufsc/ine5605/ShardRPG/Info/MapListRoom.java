package br.ufsc.ine5605.ShardRPG.Info;

import br.ufsc.ine5605.ShardRPG.Item.ItemShard;

public class MapListRoom {

	public Room shardDungeon() {
		final Room Corridor = new Room("Corridor",
			"The walls seems to be made of chipped up stone, carefully avoiding the gaps in the ground you hold yourself against the wall not to fall. The light source seems to be brighter here and coming from\n a door located to the east.\n",
			"It is dark in here but no sign of anything new, be careful not to fall.\n", null);
		Corridor.setItem(
			new ItemShard("Anel amaldiçoado ", "Dizem que o anel pertençeu a Lúcifer", new String[] {"Anel", "shard"}));

		final Room spawn = new Room("Spawn",
			"You wake up in a dim and dank room. You're confused and can't remember anything that happened. \n As you gather yourself you notice a source of light coming from a corridor to the east.\n",
			"This room still gives you chills, the atmosphere feels your body and makes you unconfortable.\n If I were you, i'd get out of here.\n",
			null);
		spawn.setAdjacentRoom(Action.ActionGoEast, Corridor);
		spawn.setWasVisited(true);
		return spawn;
	}

}
