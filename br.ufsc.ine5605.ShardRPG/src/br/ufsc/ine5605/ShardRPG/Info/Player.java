package br.ufsc.ine5605.ShardRPG.Info;

public class Player {

	private String name;

	private PlayerType type;

	private PlayerRace race;

	private Integer progress;

	private String password;

	private boolean isDead;

	private Room currentRoom;


	public Player(String name, PlayerType type, PlayerRace race, Integer progress, String password) {
		this.name = name;
		this.race = race;
		this.type = type;
		this.progress = progress;
		this.password = password;
		setDead(false);
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


	PlayerType getClasse() {
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
		System.out.println("You died a horrible and tragic death. Your score:" + progress + "out of 3 shards.");
		setDead(true);
	}
}
