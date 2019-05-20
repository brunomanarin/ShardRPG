package br.ufsc.ine5605.ShardRPG.Info;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class MapListRoom {

	public Room shardDungeon() {

		final Room spawn = new Room("Spawn",
			"You wake up in a dim and dank room. You're confused and can't remember anything that happened. \n As you gather yourself you notice a source of light coming from a corridor to the east.\n",
			"This room still gives you chills, the atmosphere feels your body and makes you unconfortable.\n If I were you, i'd get out of here.\n",
			null);

		final Room corridor = new Room(" Dark Corridor",
			"The walls seems to be made of chipped up stone, carefully avoiding the gaps in the ground you hold yourself against the wall not to fall.\n The light source seems to be brighter here and coming from a door located to the east.",
			"It is dark in here but no sign of anything new, be careful not to fall.\n", null);
		corridor.setItem(Item.getInstance("anel"));

		final Room firstHub = new Room("Hub",
			"The light source shows itself to be a big chandelier, you feel the softness of a rug touching your bare feet. The room has four doors leading to another areas.\n The door leading to the south is big and hostile looking. \n The door to the east is halfway open, a big table and what appears to be chairs are visible.\n The door to the north has a big painting on top of it and is close shut. \n Lastly, the door to the west goes back to where you came from.\n.",
			"The chandelier still flames on, you hear the rumbling of the candles in midst of the silence of the room.",
			null);
		final Room diningHall = new Room("Dining Quarters",
			"There are some leftovers of food here, some of it is spilled on the ground, some of the chairs are tumbled over. Is that blood on the walls?\\n This is some kind of dining hall.\\n There is a door to the east at the back of a bar table.",
			"The smell of rotten food and blood feels your senses, you can't distance yourself from the fact that something bad must've happened here.",
			null);
		final Room diningQuartersBack = new Room("Room behind dining quarters",
			"You open the door, you can't help but notice a chest. Somehow this room's energy fill you up with something. A feeling. A good feeling. It feels great! You smile away as you see a strange glow coming from insde the chest.",
			"This room still emanates a radiant energy, coming here makes you feel great! You smile! YOU SMILE MORE! ISN'T IT ALL JUST BEATIFUL? HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA.",
			null);
		diningQuartersBack.setItem(Item.getInstance("shard"));

		final Room deathTrap = new Room("Death Trap",
			"The only and last thing you hear is the sound of a blade cutting the air.",
			"Wait, what? How did you? what? Are you cheating!?", null);
		final Room secondHub = new Room("Second hub", "This room is almost uncorfortably empty, although there is a bump in the wall to the east. Could it be something?", "You look at the wall and feel smart, you know it os a secret passage because of how big your brain is.", null);

		final Room secretRoom = new Room("Secret Room", "Dust is everywhere but in a certain path in this room, strangely it looks like someone has been here recently. Bookshelfs are everywhere and it smells like mold in here. There is a glow coming from the back.", "You can't stop laughing, it fills you up. That bright and shiny ener... OH GOD WHAT IS HAPPENING TO YOU?", null);
		secretRoom.setItem(Item.getInstance("shard"));

		final Room corridor2 = new Room("Corridor", "This is a long hall, there are some decorations scattered aby. A shattered mirror is displayed at the wall, a blue rug on the ground is mushed to the left wall.", "Still a long hall. Nothing changed.", null);
		final Room restingQuarters = new Room("Resting quarters", "This place is a mess, there are pieces of ragged cloth everywhere. Was this a party or did something bad happen here also?", "You wish you could get some sleep on the nice and comfy beds. The room is still a mess.", null);
		restingQuarters.setItem(Item.getInstance("shard"));
		final Room deathHub = new Room("Third hub", "I wouldn't move too much if i were you...", "Why are you here? Are you crazy?", null);

		spawn.setAdjacentRoom(Action.ActionGoEast, corridor);
		spawn.setWasVisited(true);

		corridor.setAdjacentRoom(Action.ActionGoEast, firstHub);
		corridor.setAdjacentRoom(Action.ActionGoWest, spawn);

		firstHub.setAdjacentRoom(Action.ActionGoEast, diningHall);
		firstHub.setAdjacentRoom(Action.ActionGoSouth, deathTrap);
		firstHub.setAdjacentRoom(Action.ActionGoNorth, secondHub);
		firstHub.setAdjacentRoom(Action.ActionGoWest, corridor);
		
		diningHall.setAdjacentRoom(Action.ActionGoWest, firstHub);
		diningHall.setAdjacentRoom(Action.ActionGoEast, diningQuartersBack);
		diningQuartersBack.setAdjacentRoom(Action.ActionGoWest, diningHall);

		secondHub.setAdjacentRoom(Action.ActionGoEast, corridor2);
		secondHub.setAdjacentRoom(Action.ActionGoSouth, firstHub);
		secondHub.setAdjacentRoom(Action.ActionGoWest, secretRoom);
		
		secretRoom.setAdjacentRoom(Action.ActionGoEast, secondHub);

		corridor2.setAdjacentRoom(Action.ActionGoNorth, restingQuarters);
		corridor2.setAdjacentRoom(Action.ActionGoEast, deathHub);
		corridor2.setAdjacentRoom(Action.ActionGoWest, secondHub);

		deathHub.setAdjacentRoom(Action.ActionGoNorth, deathTrap);
		deathHub.setAdjacentRoom(Action.ActionGoSouth, deathTrap);
		deathHub.setAdjacentRoom(Action.ActionGoEast, deathTrap);
		deathHub.setAdjacentRoom(Action.ActionGoWest, corridor2);
		
		restingQuarters.setAdjacentRoom(Action.ActionGoSouth, corridor2);
		
		return spawn;
	}

}
