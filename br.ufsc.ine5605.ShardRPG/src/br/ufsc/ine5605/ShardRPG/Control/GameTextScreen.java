package br.ufsc.ine5605.ShardRPG.Control;

import br.ufsc.ine5605.ShardRPG.Info.Player;

public class GameTextScreen {
	public static void shardLogoPrint() {
		System.out.println(
			"   SSSSSSSSSSSSSSS HHHHHHHHH     HHHHHHHHH               AAA               RRRRRRRRRRRRRRRRR   DDDDDDDDDDDDD        ");
		System.out.println(
			" SS:::::::::::::::SH:::::::H     H:::::::H              A:::A              R::::::::::::::::R  D::::::::::::DDD  ");
		System.out.println(
			"S:::::SSSSSS::::::SH:::::::H     H:::::::H             A:::::A             R::::::RRRRRR:::::R D:::::::::::::::DD ");
		System.out.println(
			"S:::::S     SSSSSSSHH::::::H     H::::::HH            A:::::::A            RR:::::R     R:::::RDDD:::::DDDDD:::::D  ");
		System.out.println(
			"S:::::S              H:::::H     H:::::H             A:::::::::A             R::::R     R:::::R  D:::::D    D:::::D ");
		System.out.println(
			"S:::::S              H:::::H     H:::::H            A:::::A:::::A            R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			" S::::SSSS           H::::::HHHHH::::::H           A:::::A A:::::A           R::::RRRRRR:::::R   D:::::D     D:::::D");
		System.out.println(
			"  SS::::::SSSSS      H:::::::::::::::::H          A:::::A   A:::::A          R:::::::::::::RR    D:::::D     D:::::D");
		System.out.println(
			"    SSS::::::::SS    H:::::::::::::::::H         A:::::A     A:::::A         R::::RRRRRR:::::R   D:::::D     D:::::D");
		System.out.println(
			"       SSSSSS::::S   H::::::HHHHH::::::H        A:::::AAAAAAAAA:::::A        R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			"            S:::::S  H:::::H     H:::::H       A:::::::::::::::::::::A       R::::R     R:::::R  D:::::D     D:::::D");
		System.out.println(
			"            S:::::S  H:::::H     H:::::H      A:::::AAAAAAAAAAAAA:::::A      R::::R     R:::::R  D:::::D    D:::::D");
		System.out.println(
			"SSSSSSS     S:::::SHH::::::H     H::::::HH   A:::::A             A:::::A   RR:::::R     R:::::RDDD:::::DDDDD:::::D");
		System.out.println(
			"S::::::SSSSSS:::::SH:::::::H     H:::::::H  A:::::A               A:::::A  R::::::R     R:::::RD:::::::::::::::DD");
		System.out.println(
			"S:::::::::::::::SS H:::::::H     H:::::::H A:::::A                 A:::::A R::::::R     R:::::RD::::::::::::DDD ");
		System.out.println(
			" SSSSSSSSSSSSSSS   HHHHHHHHH     HHHHHHHHHAAAAAAA                   AAAAAAARRRRRRRR     RRRRRRRDDDDDDDDDDDDD        \n\n");
	}
	public static void gameOver() {
		System.out.println("     .... NO! ...                  ... MNO! ...");
		System.out.println("   ..... MNO!! ...................... MNNOO! ...");
		System.out.println(" ..... MMNO! ......................... MNNOO!! .");
		System.out.println(".... MNOONNOO!   MMMMMMMMMMPPPOII!   MNNO!!!! .");
		System.out.println(" ... !O! NNO! MMMMMMMMMMMMMPPPOOOII!! NO! ....");
		System.out.println("    ...... ! MMMMMMMMMMMMMPPPPOOOOIII! ! ...");
		System.out.println("   ........ MMMMMMMMMMMMPPPPPOOOOOOII!! .....");
		System.out.println("   ........ MMMMMOOOOOOPPPPPPPPOOOOMII! ...  ");
		System.out.println("    ....... MMMMM..    OPPMMP    .,OMI! ....");
		System.out.println("     ...... MMMM::   o.,OPMP,.o   ::I!! ...");
		System.out.println("         .... NNM:::.,,OOPM!P,.::::!! ....");
		System.out.println("         .. MMNNNNNOOOOPMO!!IIPPO!!O! .....");
		System.out.println("         ... MMMMMNNNNOO:!!:!!IPPPPOO! ....");
		System.out.println("           .. MMMMMNNOOMMNNIIIPPPOO!! ......");
		System.out.println("         ...... MMMONNMMNNNIIIOO!..........");
		System.out.println("       ....... MN MOMMMNNNIIIIIO! OO ..........");
		System.out.println("    ......... MNO! IiiiiiiiiiiiI OOOO ...........");
		System.out.println("  ...... NNN.MNO! . O!!!!!!!!!O . OONO NO! ........");
		System.out.println("   .... MNNNNNO! ...OOOOOOOOOOO .  MMNNON!........");
		System.out.println("  ...... MNNNNO! .. PPPPPPPPP .. MMNON!........");
		System.out.println("     ...... OO! ................. ON! .......");
		System.out.println("        ................................");
		
	}
	public static void mainMenu() {
		System.out.println("       ______________________________            |>>>");
		System.out.println("      |  |                           |           |");
		System.out.println("      |  |         MAIN MENU:        |       _  _|_  _");
		System.out.println("      |  | Input the desired option: |      |;|_|;|_|;|");
		System.out.println("      |  |                           |      \\\\.    .  /");
		System.out.println("      |  |    1- START NEW GAME      |       \\\\:  .  /");
		System.out.println("      |  |                           |        ||:   |");
		System.out.println("      |  |    2- LOAD EXISTING FILE  |        ||:.  |");
		System.out.println("      |  |                           |        ||:  .|");
		System.out.println("      |  |    3- DELETE FILE         |        ||:   |       \\,/");
		System.out.println("      |  |                           |        ||: , |            /`\\");
		System.out.println("      |  |    4- CHANGE FILE NAME    |        ||:   |");
		System.out.println("      |__|___________________________|        ||: . |");
		System.out.println("               __                            _||_   |");
		System.out.println("     ____--`~    '--~~__            __ ----~    ~`---,              ___");
		System.out.println("-~--~                   ~---__ ,--~'                  ~~----_____-~'   `~----~~");
	}


	public static void help() {
		System.out.println("------ SHARD HELP ------");
		System.out.println("This is a help menu.");
		System.out
			.println("Shard is a text-based RPG, this means that all the actions in the game are done so by your inputs.");
		System.out.println("Some examples:");
		System.out.println("1- Object related actions:");
		System.out.println(
			"Some actions need objects to be performed. An example is picking up the shard to advance your progress in the game.");
		System.out.println("1.1- Take item:");
		System.out.println("You can do it by going into a room where exists a pickable object(shard) and typing 'get shard'");
		System.out.println("Input: '(get,pickup,aquire,grab,take) + desired object'");
		System.out.println("1.2- Drop item:");
		System.out.println("if you wish, you can drop an item on the ground after you've picked it up.");
		System.out.println("Input:'(drop)+ desired object'");
		System.out.println("1.3 - Inspect item:");
		System.out.println("There are some items you can't pick up, but they are still interactable via the inspect command.");
		System.out.println("Input:'(inspect,examine,read,view)+ desired object'");
		System.out.println("1.4 - Destroy item:");
		System.out.println("There is also a way to destroy an item. Careful though, \nafter you've destroyed it won't come back on that playthrough!");
		System.out.println("Input: '(break,smash,destroy,obliterate)+ desired object'");
		System.out.println("2 - Non-object related actions:");
		System.out.println("Some other actions can be done with no need of external stuff. This includes:");
		System.out.println("2.1 - Walking:");
		System.out.println("You can walk from room to room by using the command related to the direction you want to go in.");
		System.out.println("Input: 'go + (North,South,East,West)'");
		System.out.println("2.2 - Opening this menu:");
		System.out.println("Well you're already here so...");
		System.out.println("Input: '(h,help)'");
		System.out.println("2.3 - Suicide");
		System.out.println("Yes you can kill your character if you get stuck or if you just want to, you sadistic bastard.");
		System.out.println("Input:'(die,suicide)'");
		System.out.println("2.4 - Look");
		System.out.println(
			"You can use this command to look around the room you're in. Basically it will give you that room's description");
		System.out.println(
			"after you've already visited it. Useful if you're lost or just need to know where you're and don't want to");
		System.out.println("scroll all the way back to where you entered the room.");
		System.out.println("Input:'(look, l)'");
		System.out.println("2.5 - Quit");
		System.out.println("This command quits the game. Pretty self explanatory.");
		System.out.println("Input = '(quit)'");
		System.out.println(
			"Well that's pretty much it. If you got any more questions I am sorry, figure it out by yourself! I know you can, I believe in you.");
		System.out.println("----- ABOUT SHARD -----");
		System.out.println("This engine was created by Huan Shan and Bruno Manarin in May,2019.");
		System.out.println(
			"We did this as an asignment to our class at INE5603, Federal University of Santa Catarina (UFSC) , Brazil.");
		System.out.println("I hope you're having fun, Mr. Hauck.");
		System.out.println("------ END OF MENU -----");

	}
	public void ErrorMessages() {
		
	}
	public static void firstVisit(Player player) {
		System.out.println("\n--------" + player.getCurrentRoom().getName() + "--------");
		System.out.println(player.getCurrentRoom().getDescription());
		System.out.println("----------------");
		System.out.println("OBJECTS IN THE ROOM:");
		System.out.println(player.getCurrentRoom().visibleObjects());
		System.out.println("----------------");
		System.out.println("ADJACENT ROOMS:");
		System.out.println(player.getCurrentRoom().getAdjacentRooms());
		System.out.println("----------------");
	}


	public static void posteriorVisits(Player player) {
		System.out.println("\n--------" + player.getCurrentRoom().getName() + "-------");
		System.out.println(player.getCurrentRoom().getDescriptionAfter());
		System.out.println("----------------");
		System.out.println("OBJECTS IN THE ROOM:");
		System.out.println(player.getCurrentRoom().visibleObjects());
		System.out.println("----------------");
		System.out.println("ADJACENT ROOMS:");
		System.out.println(player.getCurrentRoom().getAdjacentRooms());
		System.out.println("----------------");
	}
	
	public static void gameOverScreen(Log log, Player player) {
		gameOver();
		System.out.println("---- GAME OVER ----");
		System.out.println("THESE ARE ALL THE ACTIONS YOU MADE IN THIS PLAYTHROUGH:");
		System.out.println(log.listAllActions());
		System.out.println("----------");
		player.die();
	}
	public static void actionPassText() {
		System.out.println("----------");
		System.out.println("You do nothing.");
		System.out.println("---------- \n");
	}
	public static void actionError() {
		System.out.println("----------");
		System.out.println("Invalid Action! What have you done?");
		System.out.println("---------- \n");
	}
	public static void youWinScreen(Log log, Player player) {
		System.out.println("---CONGRATULATIONS!---");
		System.out.println("THESE ARE ALL THE ACTIONS YOU MADE IN THIS PLAYTHROUGH:");
		System.out.println(log.listAllActions());
		System.out.println("You managed to get all Shards! Your score:" + player.getProgress() + " out of 3 shards.");
		System.out.println("----------");
	}
	public static void mainMenuText() {
		System.out.println("Press 1 to START a new game, 2 to LOAD a save file, 3 to DELETE an existing file or 4 to MODIFY a file:\n");
	}
	public static void newToGame() {
		System.out.println("If you're new to the game i suggest using the command 'help'");
	}
}
