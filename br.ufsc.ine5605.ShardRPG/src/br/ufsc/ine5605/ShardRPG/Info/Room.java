package br.ufsc.ine5605.ShardRPG.Info;

import java.util.ArrayList;

public class Room {
	private String name;
	private String description;
	private String descriptionAfter;
	private boolean wasVisited;
	private ArrayList<Item> objects;
	
	public Room(String name, String descriptionFirstVisit, String descriptionAfter, Item objects) {
		this.name = name;
		this.description = descriptionFirstVisit;
		this.descriptionAfter = descriptionAfter;
		this.objects = new ArrayList<>();
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
		if(!wasVisited) {
			wasVisited = true;
		}	
	}
	public void addObjects(ArrayList<Item> objects){
		for(Item o: objects) {
			this.objects.add(o);
		}
	}
	public void addSingleObject(Item object) {
		this.objects.add(object);
	}
	public void removeObject(Item object) {
		boolean objectExists = false;
		for(Item o: this.objects) {
			if(o.equals(object)) {
				objectExists = true;
			}
		}
		if(objectExists) {
			objects.remove(object);
			System.out.println("I'll take this "+ object.getName()+".");
		} else {
			System.out.println("I can't see this object here.");
		}
		
		
	}
	public String showDescription() {
		return this.wasVisited ? this.descriptionAfter : this.description + visibleObjects();
	}
	public String visibleObjects() {
		String visibleObjects = "";
		for(Item object: this.objects) {
			visibleObjects+= "There is a " + object.getDescription() + "here \n";
		}
		return visibleObjects;
	}

}
