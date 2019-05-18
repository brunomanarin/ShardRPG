package br.ufsc.ine5605.ShardRPG.Info;

public class Room {
	private String name;
	private String description;
	private String descriptionAfter;
	private boolean wasVisited;
	private Item objects;
	public Room(String name, String descriptionFirstVisit, String descriptionAfter, Item objects) {
		this.name = name;
		this.description = descriptionFirstVisit;
		this.descriptionAfter = descriptionAfter;
		this.objects = objects;
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
	public String showDescription() {
		return this.wasVisited ? this.descriptionAfter : this.description + visibleObjects();
	}
	public String visibleObjects() {
		String visibleObjects = "";
		for(Item object: this.objects) {
			visibleObjects+= "There is a " + object.itemDescription() + "here \n";
		}
	}

}
