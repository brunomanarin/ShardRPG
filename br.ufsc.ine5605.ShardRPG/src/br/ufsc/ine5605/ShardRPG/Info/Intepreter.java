package br.ufsc.ine5605.ShardRPG.Info;

import java.util.Arrays;

import br.ufsc.ine5605.ShardRPG.Item.Item;

public class Intepreter {

	public Action stringInterpreter(String string) {
		if (string.equals("") || string.matches("^\\s+$")) {
			return Action.ActionPass;
		}
		return action(string.toLowerCase().split(" "));
	}


	public Action action(String[] string) {
		if (string == null || string.length == 0) {
			return Action.ActionPass;
		}
		if (string[0].compareTo("goto") == 0 || string[0].compareTo("go") == 0) {
			final String[] command = Arrays.copyOfRange(string, 1, string.length);
			return action(command);
		} else {
			final String input = string[0];
			Action action = null;
			out: {
				for (final Action a : Action.values()) {
					for (final String alias : a.getAliases()) {
						if (input.compareTo(alias) == 0) {
							action = a;
							break out;
						}
					}
				}
			}
			if (action == null) {
				return Action.ActionError;
			}
			switch (action.getType()) {
			case TYPE_WALK: {
				return action;
			}
			case TYPE_HASDIRECTOBJECT:
				// test direct object
				// "throw SHOVEL"
				if (string.length > 1) {

					final String d = string[1];
					final Item item = Item.getInstance(d);
					//
					// item is the direct object of the action
					action.setDirectObject(item);
					return action;
				} else {
					System.out.println("You must supply a direct object.");
					return Action.ActionPass;
				}
			case TYPE_OBJECTACTION: {
				if (string.length > 1) {
					final String secondString = string[1];

					final Item item = Item.getInstance(secondString);

					action.setRelatedObject(item);
					return action;
				} else {
					System.out.println("You need an object to perform this action!");
					return Action.ActionPass;
				}
			}
			case TYPE_NOOBJECTACTION: {
				return action;
			}
			default: {
				System.out.println("Unknown type");
				break;
			}

			}
			return Action.ActionPass;
		}
	}
}
