package br.ufsc.ine5605.ShardRPG.Control;

import java.util.ArrayList;

import br.ufsc.ine5605.ShardRPG.Info.Action;
import br.ufsc.ine5605.ShardRPG.Info.Player;

public class Log {
	private ArrayList<String> allActionsPerformed;
	
	public Log() {
		allActionsPerformed = new ArrayList<>();
	}
	public void logActions(Action action, Player player) {
			String loggedAction = "";
			switch (action.getType()) {
			case TYPE_WALK:{
				loggedAction+="You moved. \n";
				break;
			}
			case TYPE_NOOBJECTACTION:
				switch (action) {
				case ActionHelp: {
					loggedAction+="You used the help command. \n";
				}
					break;
				case ActionDie: {
					loggedAction+="You died. \n";
				}
					break;
				case ActionPass: {
					loggedAction+="You did nothing for this turn. \n";
				}
					break;
				case ActionError: {
					loggedAction+="You used an invalid action. \n";
				}
					break;
				default:
		
					break;
				}
			case TYPE_OBJECTACTION:{
				switch (action) {
				case ActionLook: {
					loggedAction+="You looked around the room you were in. \n";
					break;
				}
				default:
					break;
				}
			}
		
			case TYPE_HASDIRECTOBJECT:
				switch (action) {
				case ActionPickUp: {
					loggedAction+="You picked up an object. \n";
				}
					break;
				case ActionBreak: {
					loggedAction+="You broke something! What a savage!. \n";
				}
					break;
				case ActionInspect: {
					loggedAction+="You inspected an object. \n";
				}
					break;
				case ActionDrop: {
					loggedAction+="You dropped something on the ground. \n";
				}
					break;
				default:{
		
					break;
				}
			}
		}
			addAction(loggedAction);
	}
	
	public ArrayList<String> getAllActionsPerformed() {
		return allActionsPerformed;
	}
	public void addAction(String string) {
		allActionsPerformed.add(string);
	}
	public String listAllActions() {
		String allActions = "";
		for(String action: allActionsPerformed) {
			allActions += action;
		}
		return allActions;
	}
}
