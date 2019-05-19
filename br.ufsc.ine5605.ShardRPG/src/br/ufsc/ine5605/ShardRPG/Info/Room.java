package br.ufsc.ine5605.ShardRPG.Info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Room {

	private String name;

	private String description;

	private String descriptionAfter;

	private boolean wasVisited;

	private final ArrayList<Item> objects;

	private final Map<Action, Room> adjacentRooms;


	public Room(String name, String descriptionFirstVisit, String descriptionAfter, Item objects) {
		this.name = name;
		description = descriptionFirstVisit;
		this.descriptionAfter = descriptionAfter;
		this.objects = new ArrayList<>();
		adjacentRooms = new HashMap<>();
	}


	public void setAdjacentRoom(Action a, Room r) {
		setOneWayAdjacentRoom(a, r);
		r.setOneWayAdjacentRoom(a.getOppositeDirection(), this);
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


	public void addObjects(ArrayList<Item> objects) {
		for (final Item o : objects) {
			this.objects.add(o);
		}
	}


	public void addSingleObject(Item object) {
		objects.add(object);
	}


	public void removeObject(Item object) {
		boolean objectExists = false;
		for (final Item o : objects) {
			if (o.equals(object)) {
				objectExists = true;
			}
		}
		if (objectExists) {
			objects.remove(object);
			System.out.println("I'll take this " + object.getName() + ".");
		} else {
			System.out.println("I can't see this object here.");
		}

	}


	public String showDescription() {
		return wasVisited ? descriptionAfter : description + visibleObjects();
	}


	public Room getRoomForDirection(Action a) {
		if (canMoveToRoomInDirection(a)) {
			return adjacentRooms.get(a);
		}
		return null;
	}


	public boolean canMoveToRoomInDirection(Action a) {
		return adjacentRooms.containsKey(a);
	}


	public String visibleObjects() {
		String visibleObjects = "";
		for (final Item object : objects) {
			visibleObjects += "There is a " + object.getDescription() + "here \n";
		}
		return visibleObjects;
	}

}
