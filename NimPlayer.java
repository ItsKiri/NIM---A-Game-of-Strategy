// Delong Huang 1125981 delongh
import java.io.Serializable;
import java.util.ArrayList;

public class Nimplayer implements Serializable {
private String username;
private String firstname;
private String lastname;
private int gamesplayed;
private int gameswon;

//getters and setters
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public int getGamesplayed() {
	return gamesplayed;
}
public void setGamesplayed(int gamesplayed) {
	this.gamesplayed = gamesplayed;
}
public int getGameswon() {
	return gameswon;
}
public void setGameswon(int gameswon) {
	this.gameswon = gameswon;
}
//constructor
public Nimplayer(String username, String firstname, String lastname, int gamesplayed, int gameswon) {
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.gamesplayed = gamesplayed;
	this.gameswon = gameswon;
}
//copy constructor
public Nimplayer(Nimplayer otherplayer) {
	this.username = otherplayer.getUsername();
	this.firstname = otherplayer.getFirstname();
	this.lastname = otherplayer.getLastname();
	this.gamesplayed = otherplayer.getGamesplayed();
	this.gameswon = otherplayer.getGameswon();
}
	
}
