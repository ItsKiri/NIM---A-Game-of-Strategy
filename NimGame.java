// Delong Huang 1125981 delongh
import java.util.Random;
import java.util.Scanner;

public class NimGame {
	
//who is the winner in this game
private static String playerwin;

public static void process(int initialstones, int upperbound, String username1, String username2) {
	int turn = 0;
	int startstone = initialstones;
	int bound = upperbound;
	String p1firstname = "p1f";
	String p1lastname = "p1l";
	String p2firstname = "p2f";
	String p2lastname = "p2l";
	//find the fullname of player1
	for(int a=0;a<Nimsys.getIndex();a++)
		if(Nimsys.getPlayers().get(a).getUsername().equals(username1)) {
			p1firstname = Nimsys.getPlayers().get(a).getFirstname();
			p1lastname = Nimsys.getPlayers().get(a).getLastname();
		}
	//find the fullname of player2
	for(int b=0;b<Nimsys.getIndex();b++)
		if(Nimsys.getPlayers().get(b).getUsername().equals(username2)) {
			p2firstname = Nimsys.getPlayers().get(b).getFirstname();
			p2lastname = Nimsys.getPlayers().get(b).getLastname();
		}
	String player1 = p1firstname;
	String player2 = p2firstname;
	System.out.println();
	System.out.println("Initial stone count: " + startstone);
	System.out.println("Maximum stone removal: " + bound);
	System.out.println("Player 1: " + p1firstname + " " + p1lastname);
	System.out.println("Player 2: " + p2firstname + " " + p2lastname);
	System.out.println();
	System.out.print(startstone + " stones left:");
	  for(int i=1;i<=startstone;i++)
	  {
		  System.out.print(" *");
	  }
	  System.out.println();
	  int stonecount = startstone;
	while(stonecount>0)
	  {
	  if(turn % 2 == 0)
	  System.out.println(player1 + "'s turn - remove how many?");
	  else if(turn % 2 == 1)
		  System.out.println(player2 + "'s turn - remove how many?");
	  boolean loop = false;
	  while(!loop) {
	  try {
	  int stoneremoval = Integer.valueOf(Nimsys.keyboard.nextLine());
	  if(stoneremoval>bound)
	  {
		  throw new Exception("Invalid move. You must remove between 1 and " + Math.min(bound,stonecount) + " stones.");
	  }
	  if(stoneremoval<1)
	  {
		  throw new Exception("Invalid move. You must remove between 1 and " + Math.min(bound,stonecount) + " stones.");
	  }
	  stonecount = stonecount - stoneremoval;
	  if(stonecount<0)
	  {
		  stonecount = stonecount + stoneremoval;
		  throw new Exception("Invalid attempt, only " + stonecount + " stones remaining! Try again:");
	  }
	  else loop = true;
	  }
	  catch (Exception e) {
		  System.out.println();
		  System.out.println(e.getMessage());
		  System.out.println();
		  System.out.print(stonecount + " stones left:");
		  for(int j=1;j<=stonecount;j++)
		  {
			  System.out.print(" *");
		  }
		  System.out.println();
		  if(turn % 2 == 0)
			  System.out.println(player1 + "'s turn - remove how many?");
			  else if(turn % 2 == 1)
				  System.out.println(player2 + "'s turn - remove how many?");
	  }
	  }
	  if(stonecount == 0)
		  ;
	  else if(stonecount > 0)
	  {
		  System.out.println();
	  System.out.print(stonecount + " stones left:");
	  for(int j=1;j<=stonecount;j++)
	  {
		  System.out.print(" *");
	  }
	  System.out.println();
	  turn = turn +1;
	  }
	  }
	System.out.println();
	System.out.println("Game Over");
	if(turn % 2 == 1) {
		playerwin = player1;
	}
	else if(turn % 2 == 0) {
		playerwin = player2;
	}
	for(int x=0;x<Nimsys.getIndex();x++)
		if(Nimsys.getPlayers().get(x).getFirstname().equals(playerwin))
			System.out.println(Nimsys.getPlayers().get(x).getFirstname()+" "+Nimsys.getPlayers().get(x).getLastname()+" wins!");
}

//getters and setters
public static String getPlayerwin() {
	return playerwin;
}

public static void setPlayerwin(String playerwin) {
	NimGame.playerwin = playerwin;
}
}
