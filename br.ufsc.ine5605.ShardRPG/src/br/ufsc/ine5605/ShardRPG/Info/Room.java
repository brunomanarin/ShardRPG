package br.ufsc.ine5605.ShardRPG.Info;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Room {

	private String name;

	private String description;

	private String descriptionAfter;

	private boolean wasVisited;

	private final LinkedList<Item> items;

	private final Map<Action, Room> adjacentRooms;


	public Room(String name, String descriptionFirstVisit, String descriptionAfter, Item objects) {
		this.name = name;
		description = descriptionFirstVisit;
		this.descriptionAfter = descriptionAfter;
		items = new LinkedList<>();
		adjacentRooms = new HashMap<>();
	}


	public Room getNextRoomDirection(Action action) {
		if (canMoveToRoomInDirection(action)) {
			return adjacentRooms.get(action);
		}
		return null;
	}


	public void setAdjacentRoom(Action a, Room r) {
		setOneWayAdjacentRoom(a, r);
		r.setOneWayAdjacentRoom(a.getOppositeDirection(), this);
	}
	public String getAdjacentRooms() {
		String adjacent = "";
		String roomDirection = "";
		for(Action action: adjacentRooms.keySet()) {
			if(action!=null) {
				Room room = adjacentRooms.get(action);
				switch(action.name()) {
					case "ActionGoSouth":{
						roomDirection = "South";
						break;
					}
					case "ActionGoNorth":{
						roomDirection = "North";
						break;
					}
					case "ActionGoWest":{
						roomDirection = "West";
						break;
					}
					case "ActionGoEast":{
						roomDirection = "East";
						break;
					}
					default:{
						roomDirection = "Secret";
						break;
					}
				}
				if(room != null) {
					if(room.getName()!="" && !room.getName().matches("^\\s+$")) {
						adjacent+= roomDirection+":"+room.getName()+"\n";
					}
				}
			}
		}
		return adjacent;
	}


	public Boolean canMoveToRoomInDirection(Action action) {
		return adjacentRooms.containsKey(action);
	}


	public void setOneWayAdjacentRoom(Action a, Room r) {
		adjacentRooms.put(a, r);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LinkedList<Item> getItems() {
		return items;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDescriptionAfter() {
		return descriptionAfter;
	}


	public void setDescriptionAfter(String descriptionAfter) {
		this.descriptionAfter = descriptionAfter;
	}


	public boolean getWasVisited() {
		return wasVisited;
	}


	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}


	public void checkPlayerFirstVisit() {
		if (!wasVisited) {
			wasVisited = true;
		}
	}


	public void putItems(LinkedList<Item> items) {
		for (final Item i : items) {
			this.items.add(i);
		}
	}


	public void setItem(Item item) {
		items.add(item);
	}


	public boolean remove(Item item) {
		if (items.contains(item)) {
			items.remove(item);
			return true;
		} else {
			System.out.println("I can't see this object here.");
			return false;
		}
	}


	public String showDescription() {
		return wasVisited ? descriptionAfter : description + visibleObjects();
	}


	public String visibleObjects() {
		String visibleObjects = "";
		for (final Item object : items) {
			visibleObjects += "There is a " + object.getDescription() + "here \n";
		}
		if(visibleObjects.length()==0) {
			return "There are no visible objects here at the moment.";
		} else {
			return visibleObjects;
		}
	}

}
