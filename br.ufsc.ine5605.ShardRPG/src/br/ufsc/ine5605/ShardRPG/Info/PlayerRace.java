package br.ufsc.ine5605.ShardRPG.Info;

public enum PlayerRace {
	orc("orc"), human("human");

	private String raceId;

	private PlayerRace(String id) {
		raceId = id;
	}

	public String getClassId() {
		return raceId;
	}

}
