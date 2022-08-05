// Delong Huang 1125981 delongh
import java.util.ArrayList;
import java.io.Serializable;

import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Collections;

public class Nimsys{
	
private static int upperbound;
private static String username1;
private static String username2;
public static final Scanner keyboard = new Scanner(System.in);
private static int initialstones;
//ArrayList to save players' information
private static ArrayList<Nimplayer> players;
//index of ArrayList
private static int index = 0;
private static String command;

	public static void main(String[] args) {
		//keep the command loop until exit
		boolean loop = true;
		players = new ArrayList<Nimplayer>();
		String filename = "players.dat";
		//file I/O
		File fileObject = new File(filename);
		if(fileObject.exists())
		Nimsys.loadGame(filename);
		//load the index
		index = index + players.size();
		System.out.println("Welcome to Nim");
		System.out.println();
		System.out.println("Please enter a command to continue or type 'help' for more information");
		System.out.println();
		while(loop == true) {
			try {
		command = keyboard.nextLine();
		//help
		if(command.equals("help"))
			Nimsys.help();
		//help
		else if (command.equals("exit")) {
			Nimsys.exit(filename);
			loop = false;
			System.out.println("$ ");
		}
		//commands
		else if (command.equals("commands"))
			Nimsys.commands();
		//rankings
		else if (command.substring(0,4).equals("rank"))
			Nimsys.rankings();
		//addplayer
		else if (command.substring(0,4).equals("addp")) {
			if(command.length()<21)
				throw new Exception("$ Incorrect number of arguments supplied to command.");
			if(index>0) {
				String addplayer = command.substring(10);
				String[] buff = addplayer.split(",");
				for(int a=0;a<index;a++)
					if(players.get(a).getUsername().equals(buff[0]))
						throw new Exception("$ The player already exists.");
				Nimsys.addplayer();
			}
			else {
				Nimsys.addplayer();
			}
		}
		//startgame
		else if (command.substring(0,5).equals("start")) {
			if(command.length()<20)
				throw new Exception("$ Incorrect number of arguments supplied to command.");
			boolean checkstart = true;
			String startgame = command.substring(10);
			String[] buff = startgame.split(",");
			String player1 = buff[2].substring(1);
			String player2 = buff[3].substring(1);
			for(int g=0;g<index;g++)
				if(players.get(g).getUsername().equals(player1))
						for(int h=0;h<index;h++)
							if(players.get(h).getUsername().equals(player2))
								{Nimsys.startgame();
								checkstart = false;
								}
			if(checkstart)
				throw new Exception("$ One of the players does not exist.");
		}
		//editplayer
		else if (command.substring(0,5).equals("editp")) {
			if(command.length()<18)
				throw new Exception("$ Incorrect number of arguments supplied to command.");
			boolean checkeditp = true;
				String editplayer = command.substring(11);
				String[] buff = editplayer.split(",");
				for(int b=0;b<index;b++)
					if(players.get(b).getUsername().equals(buff[0]))
						{Nimsys.editplayer();
						checkeditp = false;
						}
				if(checkeditp)
					throw new Exception("$ The player does not exist.");
		}
		//resetstats
		else if (command.substring(0,5).equals("reset")) {
			boolean checkreset = true;
			if(command.length()==10)
				Nimsys.resetstats();
			else {
			String resetstats = command.substring(11);
			for(int f=0;f<index;f++)
				if(players.get(f).getUsername().equals(resetstats)) {
					Nimsys.resetstats();
					checkreset = false;}
			if(checkreset)
				throw new Exception("$ The player does not exist.");}
		}
		//addaiplayer
		else if (command.substring(0,6).equals("addaip")) {
			if(command.length()<19)
				throw new Exception("$ Incorrect number of arguments supplied to command.");
			if(index>0) {
				String addaiplayer = command.substring(12);
				String[] buff = addaiplayer.split(",");
				for(int c=0;c<index;c++)
					if(players.get(c).getUsername().equals(buff[0]))
						throw new Exception("$ The player already exists.");
				Nimsys.addaiplayer();
			}
			else {
				Nimsys.addaiplayer();
			}
		}
		//removeplayer
		else if (command.substring(0,7).equals("removep")) {
			boolean checkremovep = true;
			if(command.length()==12)
				Nimsys.removeplayer();
			else {
				String removeplayer = command.substring(13);
				for(int k=0;k<index;k++)
					if(players.get(k).getUsername().equals(removeplayer)) {
						Nimsys.removeplayer();
						checkremovep = false;
					}
				if(checkremovep)
					throw new Exception("$ The player does not exist.");
			}
		}
		//displayplayer
		else if (command.substring(0,8).equals("displayp")) {
			boolean checkdisplayp = true;
			if(command.length()==13)
				Nimsys.displayplayer();
			else {
			String displayplayer = command.substring(14);
			for(int d=0;d<index;d++)
				if(players.get(d).getUsername().equals(displayplayer)) {
					Nimsys.displayplayer();
					checkdisplayp = false;}
			if(checkdisplayp)
				throw new Exception("$ The player does not exist.");}
		}
		//not a valid command
		else throw new Exception("$ '"+command.substring(0, 12)+"'"+" is not a valid command.");}
			catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
		}
}
	
	public static void help() {
		System.out.println("$ help");
		System.out.println("Type ’commands’ to list all available commands");
		System.out.println("Type ’startgame’ to play game");
		System.out.println("The player that removes the last stone loses!");
		System.out.println();
	}
	
	public static void commands() {
		System.out.println("$  "+"1: exit"+"              "+"(no parameters)");
		System.out.println(" "+"2: addplayer"+"         "+"(username, secondname, firstname)");
		System.out.println(" "+"3: addaiplayer"+"       "+"(username, secondname, firstname)");
		System.out.println(" "+"4: removeplayer"+"      "+"(optional username)");
		System.out.println(" "+"5: editplayer"+"        "+"(username, secondname, firstname)");
		System.out.println(" "+"6: resetstats"+"        "+"(optional username)");
		System.out.println(" "+"7: displayplayer"+"     "+"(optional username)");
		System.out.println(" "+"8: rankings"+"          "+"(optional asc)");
		System.out.println(" "+"9: startgame"+"         "+"(initialstones, upperbound, username1, username2)");
		System.out.println("10: startadvancedgame"+" "+"(initialstones, username1, username2)");
		System.out.println("11: commands"+"          "+"(no parameters)");
		System.out.println("12: help"+"              "+"(no parameters)");
		System.out.println();
	}
	
	public static void addplayer() {
		String addplayer = command.substring(10);
		String[] buff = addplayer.split(",");
		buff[1] = buff[1].substring(1);
		buff[2] = buff[2].substring(1);
		//read 3 arguments
		Nimplayer player = new Nimplayer(buff[0], buff[2], buff[1], 0, 0);
		players.add(player);
		index = index + 1;
		System.out.println("$ ");
		System.out.println();
	}
	
	public static void addaiplayer() {
		String addaiplayer = command.substring(12);
		String[] buff = addaiplayer.split(",");
		buff[1] = buff[1].substring(1);
		buff[2] = buff[2].substring(1);
		//read 3 arguments
		NimAIPlayer aiplayer = new NimAIPlayer(buff[0], buff[2], buff[1], 0, 0);
		players.add(aiplayer);
		index = index + 1;
		System.out.println("$ ");
		System.out.println();
	}
	
	public static void removeplayer(){
		if(command.length()!=12) {
		String removeplayer = command.substring(13);
		//find the player to remove
		for(int i=0;i<index;i++)
			if(players.get(i).getUsername().equals(removeplayer)) {
				players.remove(i);
				break;
			}
		index = index -1;}
		else {
			System.out.println("$ Are you sure you want to remove all players? (y/n)");
			String confirm = keyboard.nextLine();
			if(confirm.equals("y"))
				players.clear();
			index = 0;
		}
		System.out.println("$ ");
		System.out.println();
	}
	
	public static void editplayer(){
		String editplayer = command.substring(11);
		String[] buff = editplayer.split(",");
		buff[1] = buff[1].substring(1);
		buff[2] = buff[2].substring(1);
		//find the player to edit
		for(int i=0;i<index;i++) {
			if(players.get(i).getUsername().equals(buff[0])) {
				players.get(i).setFirstname(buff[2]);
				players.get(i).setLastname(buff[1]);
			}
		}
		System.out.println("$ ");
		System.out.println();
	}
	
	public static void resetstats(){
		if(command.length()!=10) {
		String resetstats = command.substring(11);
		//find the player to reset
		for(int i=0;i<index;i++) {
			if(players.get(i).getUsername().equals(resetstats)) {
				players.get(i).setGamesplayed(0);
				players.get(i).setGameswon(0);;
			}
		}
		}
		else {
			System.out.println("$ Are you sure you want to reset all player statistics? (y/n)");
			String confirm = keyboard.nextLine();
			if(confirm.equals("y"))
			for(int j=0;j<index;j++) {
				players.get(j).setGamesplayed(0);
				players.get(j).setGameswon(0);
			}
		}
		System.out.println("$ ");
		System.out.println();
	}
	
	public static void startgame() {
		System.out.println("$ ");
		//check if player1 is aiplayer
		boolean condition1 = false;
		//check if player2 is aiplayer
		boolean condition2 = false;
		String startgame = command.substring(10);
		String[] buff = startgame.split(",");
		initialstones = Integer.valueOf(buff[0]);
		upperbound = Integer.valueOf(buff[1].substring(1));
		username1 = buff[2].substring(1);
		username2 = buff[3].substring(1);
		//check if player1 is aiplayer
		for(int a =0;a<index;a++) {
			if(players.get(a).getUsername().equals(username1))
				if(players.get(a) instanceof NimAIPlayer)
					condition1 = true;
		}
		//check if player2 is aiplayer
		for(int b =0;b<index;b++) {
			if(players.get(b).getUsername().equals(username2))
				if(players.get(b) instanceof NimAIPlayer)
					condition2 = true;
		}
		//NimAIGame and aiplayer moves first
		if(condition1)
			NimAIGame.aiprocess1(initialstones, upperbound, username1, username2);
		//NimAIGame and aiplayer moves second
		else if(condition2)
			NimAIGame.aiprocess2(initialstones, upperbound, username1, username2);
		//NimGame
		else NimGame.process(initialstones, upperbound, username1, username2);
		//after the game, change Gamesplayed and Gameswon information
		for(int i=0;i<index;i++) {
			if(players.get(i).getUsername().equals(username1)) 
				players.get(i).setGamesplayed(players.get(i).getGamesplayed()+1);}
			for(int j=0;j<index;j++) {
				if(players.get(j).getUsername().equals(username2)) 
					players.get(j).setGamesplayed(players.get(j).getGamesplayed()+1);}
			if(condition1 || condition2) {
				for(int c=0;c<index;c++)
					if(players.get(c).getFirstname().equals(NimAIGame.getPlayerwin()))
						players.get(c).setGameswon(players.get(c).getGameswon()+1);
			}
			else
				for(int m=0;m<index;m++) {
					if(players.get(m).getFirstname().equals(NimGame.getPlayerwin()))
						players.get(m).setGameswon(players.get(m).getGameswon()+1);}
			System.out.println();
			}
	
	public static void displayplayer() {
		if(command.length()!=13) {
		String displayplayer = command.substring(14);
		//find the player to display
		for(int i=0;i<index;i++) {
			if(players.get(i).getUsername().equals(displayplayer))
				System.out.println("$ "+players.get(i).getUsername()+", "+players.get(i).getFirstname()+", "+players.get(i).getLastname()+", "+players.get(i).getGamesplayed()+" games, "+players.get(i).getGameswon()+" wins");
		}
		System.out.println();
		}
		else {
			for(int j=0;j<index;j++)
				System.out.println("$ "+players.get(j).getUsername()+", "+players.get(j).getFirstname()+", "+players.get(j).getLastname()+", "+players.get(j).getGamesplayed()+" games, "+players.get(j).getGameswon()+" wins");
		}
		System.out.println();
	}
	
	public static void rankings() {
		Nimplayer change = new Nimplayer("","","",0,0);
		//ranking players by name in Alphabet order
		for(int a=0;a<index-1;a++)
			for(int b=0;b<index-1-a;b++) {
				if(players.get(b).getUsername().compareToIgnoreCase(players.get(b+1).getUsername())>0)
				{
					change.setUsername(players.get(b).getUsername());
					change.setFirstname(players.get(b).getFirstname());
					change.setLastname(players.get(b).getLastname());
					change.setGamesplayed(players.get(b).getGamesplayed());
					change.setGameswon(players.get(b).getGameswon());
					players.get(b).setUsername(players.get(b+1).getUsername());
					players.get(b).setFirstname(players.get(b+1).getFirstname());
					players.get(b).setLastname(players.get(b+1).getLastname());
					players.get(b).setGamesplayed(players.get(b+1).getGamesplayed());
					players.get(b).setGameswon(players.get(b+1).getGameswon());
					players.get(b+1).setUsername(change.getUsername());
					players.get(b+1).setFirstname(change.getFirstname());
					players.get(b+1).setLastname(change.getLastname());
					players.get(b+1).setGamesplayed(change.getGamesplayed());
					players.get(b+1).setGameswon(change.getGameswon());			
				}
			}
		Nimplayer change2 = new Nimplayer("","","",0,0);
		//ranking players by win ratio
		for(int c=0;c<index-1;c++)
			for(int d=0;d<index-1-c;d++) {
				if(((double)players.get(d).getGameswon()/players.get(d).getGamesplayed())<(double)players.get(d+1).getGameswon()/players.get(d+1).getGamesplayed())
				{
					change2.setUsername(players.get(d).getUsername());
					change2.setFirstname(players.get(d).getFirstname());
					change2.setLastname(players.get(d).getLastname());
					change2.setGamesplayed(players.get(d).getGamesplayed());
					change2.setGameswon(players.get(d).getGameswon());
					players.get(d).setUsername(players.get(d+1).getUsername());
					players.get(d).setFirstname(players.get(d+1).getFirstname());
					players.get(d).setLastname(players.get(d+1).getLastname());
					players.get(d).setGamesplayed(players.get(d+1).getGamesplayed());
					players.get(d).setGameswon(players.get(d+1).getGameswon());
					players.get(d+1).setUsername(change2.getUsername());
					players.get(d+1).setFirstname(change2.getFirstname());
					players.get(d+1).setLastname(change2.getLastname());
					players.get(d+1).setGamesplayed(change2.getGamesplayed());
					players.get(d+1).setGameswon(change2.getGameswon());
				}
			}
		//win ratio array
		String[] ratio = new String[index];
		//win ratio asc array
		String[] ratioasc = new String[index];
		for(int x=0;x<index;x++) {
			if(players.get(x).getGamesplayed()!=0)
			ratio[x] = String.valueOf((int) Math.round((double)players.get(x).getGameswon()/players.get(x).getGamesplayed()*100));
			//if player's Gameplayed is 0, win ratio is 0
			else if(players.get(x).getGamesplayed()==0)
				ratio[x] = "0";
		}
		//rankings default and desc
		if(command.length()==8 || command.length()==13) {
		for(int y=0;y<index;y++) {
			System.out.print(ratio[y]+"%");
			for(int z=0;z<(4-ratio[y].length());z++)
				System.out.print(" ");
			System.out.println("|"+" "+"0"+players.get(y).getGamesplayed()+" "+"games"+" "+"|"+" "+players.get(y).getFirstname()+" "+players.get(y).getLastname());
		}
		}
		//rankings asc
		else if(command.length()==12) {
			Collections.reverse(players);
			for(int f=0;f<index;f++) {
				if(players.get(f).getGamesplayed()!=0)
				ratioasc[f] = String.valueOf((int) Math.round((double)players.get(f).getGameswon()/players.get(f).getGamesplayed()*100));
				else if(players.get(f).getGamesplayed()==0)
					ratioasc[f] = "0";
			}
			for(int u=0;u<index;u++) {
				System.out.print(ratioasc[u]+"%");
				for(int o=0;o<(4-ratioasc[u].length());o++)
					System.out.print(" ");
				System.out.println("|"+" "+"0"+players.get(u).getGamesplayed()+" "+"games"+" "+"|"+" "+players.get(u).getFirstname()+" "+players.get(u).getLastname());
			}
		}
		System.out.println();
	}
	
	public static void exit(String filename) {
		ObjectOutputStream outputStream = null;		
		try
		{
			outputStream = new ObjectOutputStream(new FileOutputStream(filename));
			GameState gameState = new GameState(players);
			outputStream.writeObject(gameState);
			outputStream.close();
		}
		catch(IOException e)
		{
			System.out.println("Could not open file: " + filename);
			System.out.println(e);
		}
	}
	
	//getters and setters
	public static ArrayList<Nimplayer> getPlayers() {
		return players;
	}

	public static int getIndex() {
		return index;
	}
	
	public static void loadGame(String filename) {
		ObjectInputStream inputStream = null;
		try 
		{
			inputStream = new ObjectInputStream(new FileInputStream(filename));
			GameState gameState = (GameState) inputStream.readObject();
			players = gameState.players;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not open file: " + filename);
		}
		catch(IOException e)
		{
			System.out.println("Could not read from file.");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class not found.");
		}
	}
	}

class GameState implements Serializable {
	public ArrayList<Nimplayer> players;

	//constructor
	public GameState (ArrayList<Nimplayer> players) {
		this.players = players;
	}
}
