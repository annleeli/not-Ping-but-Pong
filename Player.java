// Name: Annlee Li
// Date: May 12, 2014
//Description: Player Class
// The "Player" class.
import java.awt.*;
import hsa.Console;

public class Player
{
    protected Console c;
    protected String name;
    protected int score;
    protected boolean win;
    protected Color clr;


    //default
    public Player ()
    {
	name = "Player";
	score = 0;
	win = false;
	clr = Color.black;
    }


    //overloaded
    public Player (String name1, int score1, Color clr1)
    {
	this.name = name1;
	this.score = score1;
	this.win = false;
	this.clr = clr1;
    }


    //name
    public void SetName (String name1)
    {
	name = name1;
    }


    //score
    public void SetScore (int score1)
    {
	score = score1;
    }


    //make winner
    public void SetWin (boolean win1)
    {
	win = win1;
    }


    //colour
    public void SetColour (Color clr1)
    {
	clr = clr1;
    }


    public String GetName ()
    {
	return name;
    }


    public int GetScore ()
    {
	return score;
    }


    public boolean isWinner ()
    {
	return win;
    }


    public Color GetColour ()
    {
	return clr;
    }
} // Player class
