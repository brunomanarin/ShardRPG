package br.ufsc.ine5605.ShardRPG.Info;

import java.util.Arrays;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Intepreter {
	
	public Action stringInterpreter(String string) {
		if(string.equals("") || string.matches("^\\s+$")) {
			return Action.ActionPass;
		}
		return this.action(string.toLowerCase().split(" "));
	}
	public Action action(String[] string) {
		if(string == null || string.length == 0) {
			return Action.ActionPass;
		}
		if(string[0].compareTo("goto") == 0 || string[0].compareTo("go") == 0) {
			String[] command = Arrays.copyOfRange(string, 1, string.length);
			return action(command);
		}
		else {
			String input = string[0];
			Action action = null;
			out:{
				for(Action a: Action.values()) {
					for(String alias: a.getAliases()) {
						if(input.compareTo(alias) == 0) {
							action = a;
									break out;
						}
					}
				}
			}
		if(action == null) {
			return Action.ActionError;
		}
		switch(action.getType()) {
		case TYPE_WALK:{
			return action;
		}
		case TYPE_OBJECTACTION:{
			if(string.length>1) {
				String secondString = string[1];
				
				Item item = Item.getInstance(secondString);
				
				action.setRelatedObject(item);
				return action;
			}else {
				System.out.println("You need an object to perform this action!");
				return Action.ActionPass;
			}
		}
		case TYPE_NOOBJECTACTION:{
			return action;
		}
		default:{
			System.out.println("Unknown type");
			break;
		}
			
	}
		return Action.ActionPass;
	}
	}
}
