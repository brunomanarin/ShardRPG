package br.ufsc.ine5605.ShardRPG.Info;

public enum Action {
	
	//Acoes direcionais
	ActionGoEast(new String[] {"east", "e"}),
	ActionGoWest(new String[] {"west", "w"}),
	ActionGoSouth(new String[] {"south", "s"}),
	ActionGoNorth(new String[] {"north", "n"}),
	
	// Acoes ligadas ao ambiente
	ActionLook(new String[] {"look", "l"}),
	ActionExamine(new String[] {"Examine", "e"}),
	ActionHelp(new String[] {"help", "h", "commands"}),
	ActionDie(new String[] {"die", "suicide"});
	
	private String[] aliases;
	
	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	Action(String[] aliases){
		this.aliases = aliases;
	}
	
}
