package br.ufsc.ine5605.ShardRPG.Info;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public enum Action {

	// Acoes direcionais
	ActionGoEast(new String[] {"east", "e"}, ActionType.TYPE_WALK), ActionGoWest(new String[] {"west", "w"},
		ActionType.TYPE_WALK), ActionGoSouth(new String[] {"south", "s"},
			ActionType.TYPE_WALK), ActionGoNorth(new String[] {"north", "n"}, ActionType.TYPE_WALK),

	// Acoes ligadas ao ambiente
	ActionLook(new String[] {"look", "l"}, ActionType.TYPE_OBJECTACTION), ActionExamine(new String[] {"Examine", "e"},
		ActionType.TYPE_OBJECTACTION),
	// Acoes ligadas ao jogador
	ActionHelp(new String[] {"help", "h", "commands"}, ActionType.TYPE_NOOBJECTACTION), ActionDie(new String[] {"die", "suicide"},
		ActionType.TYPE_NOOBJECTACTION), ActionPass(new String[] {"", " ", "nothing"},
			ActionType.TYPE_NOOBJECTACTION), ActionError(new String[] {}, ActionType.TYPE_NOOBJECTACTION);

	private String[] aliases;

	private ActionType type;

	private Item relatedObject;

	private Action opposite;


	public String[] getAliases() {
		return aliases;
	}


	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}


	public ActionType getType() {
		return type;
	}


	public void setType(ActionType type) {
		this.type = type;
	}


	public Item getRelatedObject() {
		return relatedObject;
	}


	public void setRelatedObject(Item relatedObject) {
		this.relatedObject = relatedObject;
	}


	Action(String[] aliases, ActionType type) {
		this.aliases = aliases;
		this.type = type;
	}


	Action getOppositeDirection() {

		if (type == ActionType.TYPE_WALK) {
			return opposite;
		} else {
			return null;
		}
	}

};

enum ActionType {
	TYPE_WALK, TYPE_OBJECTACTION, TYPE_NOOBJECTACTION;
}
