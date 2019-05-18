package br.ufsc.ine5605.ShardRPG.Info;

import java.util.Map;

public class PlayerList {

	private Map<String, Player> PlayersList;

	public PlayerList(Map<String, Player> list) {
		PlayersList = list;
	}

	public Map<String, Player> getPlayersList() {
		return PlayersList;
	}

	public void setPlayersList(Map<String, Player> playersList) {
		PlayersList = playersList;
	}

}
