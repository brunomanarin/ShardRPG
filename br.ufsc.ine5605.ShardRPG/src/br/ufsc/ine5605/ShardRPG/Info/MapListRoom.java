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
		final Room firstHub = new Room("Hub", "The walls seems to be made of chipped up stone, carefully avoiding the gaps in the ground you hold yourself against the wall not to fall. The light source seems to be brighter here and coming from\\n a door located to the east.",
				"The chandelier still flames on, you hear the rumbling of the candles in midst of the silence of the room.", null);
		final Room diningHall = new Room("Dining Quarters", "There are some leftovers of food here, some of it is spilled on the ground, some of the chairs are tumbled over. Is that blood on the walls?\\n This is some kind of dining hall.\\n There is a door to the east at the back of a bar table.",
				"The smell of rotten food and blood feels your senses, you can't distance yourself from the fact that something bad must've happened here.",null);
	final Room diningQuartersBack = new Room("Room behind dining quarters",
			"You open the door, you can't help but notice a chest. Somehow this room's energy fill you up with something. A feeling. A good feeling. It feels great! You smile away as you see a strange glow coming from insde the chest.",
			"This room still emanates a radiant energy, coming here makes you feel great! You smile! YOU SMILE MORE! ISN'T IT ALL JUST BEATIFUL? HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA.",null);
	final Room deathTrap1 = new Room("Death Trap 1",
			"The only and last thing you hear is the sound of a blade cutting the air. You didn't even notice where it came from, were dead long before then.","Wait, what? How did you? what? Are you cheating!?",null);
	final Room SecretRoom = new Room("Room behind dining quarters",
			"","",null);
	final Room corridor2 = new Room("Room behind dining quarters",
			"","",null);
	final Room restingQuarters = new Room("Room behind dining quarters",
			"","",null);
	final Room deathHub = new Room("Room behind dining quarters",
			"","",null);
	final Room deathTrap2 = new Room("Room behind dining quarters",
			"","",null);
	final Room deathTrap3 = new Room("Room behind dining quarters",
			"","",null);
	final Room deathTrap4 = new Room("Room behind dining quarters",
			"","",null);
	
		
		spawn.setAdjacentRoom(Action.ActionGoEast, Corridor);
		spawn.setWasVisited(true);
		return spawn;
	}

}
