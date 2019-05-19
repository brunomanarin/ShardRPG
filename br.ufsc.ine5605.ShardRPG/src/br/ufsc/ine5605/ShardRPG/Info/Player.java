package br.ufsc.ine5605.ShardRPG.Info;

import java.util.HashMap;
import java.util.Map;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Player {

	private String name;

	private PlayerType type;

	private PlayerRace race;

	private Integer progress;

	private String password;

	private boolean isDead;

	private Room currentRoom;

	Map<String, Item> inventory;


	public Player(String name, PlayerType type, PlayerRace race, Integer progress, String password) {
		this.name = name;
		this.race = race;
		this.type = type;
		this.progress = progress;
		this.password = password;
		inventory = new HashMap<>();
		setDead(false);
	}


	public void pickUpItem(Item item) {
		try {
			if (currentRoom.getItems().contains(item)) {
				inventory.put(item.getName(), item);
				System.out.println(item.getName() + " added to inventory! ");
			}
		} catch (final Exception e) {
			System.out.println(e);
		}
	}


	public void move(Action action) {
		if (this.getCurrentRoom().canMoveToRoomInDirection(action)) {
			this.setCurrentRoom(this.getCurrentRoom().getNextRoomDirection(action));
			if(this.getCurrentRoom().getName().equals("Death Trap")) {
				setDead(true);
			}
			if (!this.getCurrentRoom().getWasVisited()) {
				System.out.println("--------"+this.getCurrentRoom().getName()+"--------");
				System.out.println(this.getCurrentRoom().getDescription());
				System.out.println("----------------");
			} else {
				System.out.println("--------"+this.getCurrentRoom().getName()+"-------");
				System.out.println(this.getCurrentRoom().getDescriptionAfter());
				System.out.println("----------------");
			}
			this.getCurrentRoom().setWasVisited(true);
		} else {
			System.out.println("Ouch! You've just hit a wall! Try changing your directions you goof! \n");
		}
	}


	public PlayerType getType() {
		return type;
	}


	public void setType(PlayerType type) {
		this.type = type;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getProgress() {
		return progress;
	}


	public void setProgress(Integer progress) {
		this.progress = progress;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Map<String, Item> getInventario() {
		return inventory;
	}


	public void setInventario(Map<String, Item> invantory) {
		inventory = invantory;
	}


	public PlayerType getClasse() {
		return type;
	}


	public void setClasse(PlayerType type) {
		this.type = type;
	}


	public PlayerRace getRace() {
		return race;
	}


	public void setRace(PlayerRace race) {
		this.race = race;
	}


	public boolean isDead() {
		return isDead;
	}


	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}


	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}


	public void die() {
		System.out.println("You died a horrible and tragic death. Your score:" + progress + " out of 3 shards.");
		setDead(true);
		System.exit(0);
	}
}
