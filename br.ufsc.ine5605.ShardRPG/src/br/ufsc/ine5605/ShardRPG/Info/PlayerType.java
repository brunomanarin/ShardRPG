package br.ufsc.ine5605.ShardRPG.Info;

public enum PlayerType {
	warrior("warrior"), mage("mage"), rogue("rogue");

	private String idClass;

	private PlayerType(String id) {
		idClass = id;
	}

	public String getIdClass() {
		return idClass;
	}

}