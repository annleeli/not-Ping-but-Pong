//Name: Annlee Li
//Date: May 12, 2014
//Description: Pong game assignment
// The "PongGame" class.
import java.awt.*;
import hsa.Console;

public class PongGame
{
    static Console c;           // The output console

    //screen variables
    static final int rows = 25;
    static final int cols = 80;

    static final int SCREEN_X = cols * 8;
    static final int SCREEN_Y = rows * 20;

    //fonts
    static Font title = new Font ("ISOCP", Font.PLAIN, 72);
    static Font heading1 = new Font ("ISOCP", Font.PLAIN, 48);
    static Font heading2 = new Font ("ISOCP", Font.PLAIN, 28);
    static Font heading3 = new Font ("ISOCP", Font.PLAIN, 20);
    static Font normal = new Font ("Times New Roman", Font.PLAIN, 12);

    //custom colours
    public static Color red = new Color (255, 51, 51);
    public static Color green = new Color (128, 255, 0);
    public static Color bluegreen = new Color (0, 153, 76);
    public static Color blue = new Color (0, 102, 204);
    public static Color purple = new Color (153, 51, 255);
    public static Color magenta = new Color (153, 0, 76);

    public static Color turquoise = new Color (0, 153, 153);

    //players
    static Player player1 = new Player ("Player 1", 0, bluegreen);
    static Player player2 = new Player ("Player 2", 0, blue);

    //ball
    static Bubble bubble1 = new Bubble (400, 400, 15, Color.orange);

    //paddles
    static Paddle paddle1 = new Paddle (10, 300, player1.GetColour ());
    static Paddle paddle2 = new Paddle (SCREEN_X - 20, 40, player2.GetColour ());
    static public int paddleSpeed = 20;

    //colours
    static Color unselected = Color.black;
    static Color selected = turquoise;

    //menu selections
    public static int locationMain = 1;
    public static int locationDifficulty = 1;
    public static int locationMode = 1;

    //options
    static public int obScore = 7;  //score to win
    public static boolean beginner = true;
    public static boolean intermediate = false;
    public static boolean expert = false;

    //conditions
    public static boolean scoreUpdate = true;
    public static boolean finish = false;   //game is done
    public static boolean singleplayer = false;
    public static boolean play = false;     //play game
    public static boolean menuOn = true;

    public static void main (String[] args)
    {
	c = new Console (rows, cols, "!Ping, but Pong");

	menu:
	do
	{
	    while (!play)   //goes through the menu unless play is selected
	    {
		Main_Menu ();
	    }

	    c.clear ();

	    //pong game
	    while (play)
	    {
		//sets ball in the middle
		bubble1.SetX (SCREEN_X / 2);
		bubble1.SetY (SCREEN_Y / 2);

		//makes sure players start with 0 points
		player1.SetScore (0);
		player2.SetScore (0);

		//difficulty of game
		if (beginner)
		{
		    setValues (2, 4, 2, 4, 120, 15);
		}
		else if (intermediate)
		{
		    setValues (3, 6, 2, 5, 80, 15);
		}
		else if (expert)
		{
		    setValues (5, 7, 3, 8, 60, 10);
		}

		//prepare players
		countdown (1000);

		//the game
		if (singleplayer)
		    pongSingle ();  //single player mode
		else
		    pong ();

		//congrats
		winner ();

		//delay (500);

		//play again?
		c.setFont (heading2);
		c.setColour (Color.black);
		c.drawString ("Press...", 100, 340);
		c.drawString ("space to play again", 100, 370);
		c.drawString ("q to exit", 100, 400);
		c.drawString ("x to go back to main menu", 100, 430);

		char key;
		do
		{
		    key = c.getChar ();

		    //play again
		    if (key == ' ')
		    {
			//reset game
			player1.SetWin (false);
			player2.SetWin (false);
			finish = false;
			play = true;

			c.clear ();
			break;
		    }

		    //exit game
		    else if (key == 'q')
		    {
			play = false;
			c.clear ();
			c.setFont (heading1);
			c.drawString ("bye bye", 100, 200);
			c.drawString ("thanks for playing!", 100, 300);
			break menu;
		    }

		    //go back to main menu
		    else if (key == 'x')
		    {
			//reset game
			player1.SetWin (false);
			player2.SetWin (false);
			finish = false;

			play = false;
			c.clear ();
			break;
		    }
		}
		while (key != ' ' || key != 'x');   //data validation, prevents accidental exits
	    }
	}
	while (menuOn);
    } // main method


    //=================MENU PARTS===================//
    //typography
    public static void Menu_Title ()
    {
	c.setFont (title);
	c.setColor (unselected);
	c.drawString ("PING", 100, 90);
	c.drawLine (100, 60, 240, 60);
	c.setColour (selected);
	c.drawString ("PONG", 260, 90);
    }


    //play
    public static void Menu_Play (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Play", 100, 150);
    }


    public static void Menu_Options (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Options", 100, 200);
    }


    //difficulty menu
    public static void Menu_Difficulty (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Difficulty", 100, 250);
    }


    public static void Menu_Beginner (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Beginner", 100, 150);
    }


    public static void Menu_Int (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Intermediate", 100, 200);
    }


    public static void Menu_Expert (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Expert", 100, 250);
    }


    //mode menu
    public static void Menu_Mode (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Game Mode", 100, 300);
    }


    public static void Menu_Multi (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Multiplayer", 100, 200);
    }


    public static void Menu_Single (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Single Player", 100, 150);
    }


    //instructions
    public static void Menu_Help (Color C)
    {
	c.setFont (heading1);
	c.setColor (C);
	c.drawString ("Help/Instructions", 100, 350);
    }


    public static void Menu_Nav (Color C)
    {
	//navigation
	c.setFont (heading3);
	c.setColor (C);
	c.drawString ("Use [w] & [s] to navigate menus. ", 50, 450);
	c.drawString ("Press [space] to select and [x] to go back to the main menu", 50, 470);
    }


    //use W abd S keys to move up and down a menu
    public static int selector (char key, int min, int max, int location)
    {
	//keeps track of which menu item it is on
	if (key == 'w' && location > min)
	{
	    return -1;      //up
	}
	else if (key == 's' && location < max)
	{
	    return 1;       //down
	}
	else
	    return 0;       //don't move
    }


    //=================MENU PAGES===================//
    //-----------------Main Menu-----------------//
    public static void Main_Menu ()
    {
	//decoration
	paddle1.Draw (c);
	paddle2.Draw (c);
	bubble1.Draw (c);

	//menu
	Menu_Title ();  //title of game

	c.setFont (heading1);
	Menu_Play (selected);       //menu options
	Menu_Options (unselected);
	Menu_Difficulty (unselected);
	Menu_Mode (unselected);
	Menu_Help (unselected);

	//navigation hint
	Menu_Nav (unselected);

	char key;
	int menuCount = 5;  //how big the menu is
	locationMain = 1;

	while (true)
	{
	    key = c.getChar ();

	    //use W and S keys to move through the menu
	    locationMain += selector (key, 1, menuCount, locationMain);

	    //keeps track of location
	    if (locationMain == 1)      //play
	    {
		Menu_Play (selected);   //selected
		Menu_Options (unselected);
		Menu_Difficulty (unselected);
		Menu_Mode (unselected);
		Menu_Help (unselected);

		if (key == ' ')     //space key = enter key
		{
		    play = true;
		    break;
		}
	    }
	    else if (locationMain == 2)     //options
	    {
		Menu_Play (unselected);
		Menu_Options (selected);    //selected
		Menu_Difficulty (unselected);
		Menu_Mode (unselected);
		Menu_Help (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    Options ();     //go to options, once done, exit the loop to go back to main menu
		    break;
		}
	    }

	    else if (locationMain == 3)     //difficulty
	    {
		Menu_Play (unselected);
		Menu_Options (unselected);
		Menu_Difficulty (selected); //selected
		Menu_Mode (unselected);
		Menu_Help (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    Difficulty ();  //go to difficulty selector, once done, exit the loop to go back to main menu
		    break;
		}
	    }

	    else if (locationMain == 4)     //difficulty
	    {
		Menu_Play (unselected);
		Menu_Options (unselected);
		Menu_Difficulty (unselected);
		Menu_Mode (selected);   //selected
		Menu_Help (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    Mode ();  //go to Mode selector, once done, exit the loop to go back to main menu
		    break;
		}
	    }

	    else if (locationMain == 5)     //difficulty
	    {
		Menu_Play (unselected);
		Menu_Options (unselected);
		Menu_Difficulty (unselected);
		Menu_Mode (unselected);   //selected
		Menu_Help (selected);

		if (key == ' ')
		{
		    c.clear ();
		    Instructions ();  //go to Mode selector, once done, exit the loop to go back to main menu
		    break;
		}
	    }

	}
    }


    //-----------------Options-----------------//
    public static void Options ()
    {
	OptionsText ();

	char key;

	while (true)
	{
	    if (c.isCharAvail ())
	    {
		key = c.getChar ();

		//player 1 name
		if (key == '1')
		{
		    c.println ("Player 1, please enter your name:");
		    player1.SetName (c.readLine ());
		    c.println ("Hello, " + player1.GetName ());

		    delay (500);
		}

		//player 2 name
		else if (key == '2')
		{
		    c.println ("Player 2, please enter your name:");
		    player2.SetName (c.readLine ());

		    c.println ("Hello, " + player2.GetName ());

		    delay (500);
		}

		//player 1 colours
		else if (key == '3')
		{
		    c.println (player1.GetName () + ", please select the colour of your paddle");
		    player1.SetColour (pickColour (c.readInt ()));
		    paddle1.SetColour (player1.GetColour ());

		}

		//player 2 colours
		else if (key == '4')
		{
		    c.println (player1.GetName () + ", please select the colour of your paddle");
		    player2.SetColour (pickColour (c.readInt ()));
		    paddle2.SetColour (player2.GetColour ());
		}

		//ball colour
		else if (key == '5')
		{
		    c.println ("Pick colour of the ball");
		    bubble1.SetColour (pickColour (c.readInt ()));
		}

		else if (key == '6')
		{
		    c.println ("Enter how many points it takes to win a game");
		    c.println ("Suggestions: Short - 5 pts       Medium - 10 pts       Long - 20 pts");
		    obScore = c.readInt ();
		}
		//exit
		else if (key == 'x')
		{
		    break;
		}

		c.clear ();
		OptionsText ();
	    }
	}
	c.clear ();
    }


    //customization submenu
    public static void OptionsText ()
    {
	c.println ("Press the number to change the setting. \nPress 'x' to go back.");
	c.println ();
	c.println ("(1) - Player 1 name: " + player1.GetName ());
	c.println ("(2) - Player 2 name: " + player2.GetName ());

	c.println ("(3) - Player 1 Colours");
	c.println ("(4) - Player 2 Colours");

	c.println ("(5) - Colour of ball");

	c.println ("(6) - Objective Score (# of points to win game): " + obScore);

	c.println ();

	coloursDisplay (SCREEN_Y - 20);
    }


    //display the options for colours
    public static void coloursDisplay (int y)
    {
	c.setFont (normal);
	c.setColor (red);
	c.drawString ("1 - RED", 20, y);

	c.setColor (Color.orange);
	c.drawString ("2 - ORANGE", 80, y);

	c.setColor (green);
	c.drawString ("3 - GREEN", 160, y);

	c.setColor (bluegreen);
	c.drawString ("4 - GREEN-BLUE", 230, y);

	c.setColor (blue);
	c.drawString ("5 - BLUE", 340, y);

	c.setColor (purple);
	c.drawString ("6 - PURPLE", 400, y);

	c.setColor (magenta);
	c.drawString ("7 - MAGENTA", 470, y);

	c.setColor (Color.darkGray);
	c.drawString ("8 - GRAY", 560, y);
    }


    //returns the colour the player picks
    public static Color pickColour (int key)
    {
	if (key == 1)
	{
	    return red;
	}
	else if (key == 2)
	{
	    return Color.orange;
	}
	else if (key == 3)
	{
	    return green;
	}
	else if (key == 4)
	{
	    return bluegreen;
	}
	else if (key == 5)
	{
	    return blue;
	}
	else if (key == 6)
	{
	    return purple;
	}
	else if (key == 7)
	{
	    return magenta;
	}
	else if (key == 8)
	{
	    return Color.darkGray;
	}
	else
	{
	    return Color.black;
	}
    }


    //----------------Difficulty-----------------//
    public static void Difficulty ()
    {
	//difficulty menu
	c.setFont (heading1);
	Menu_Beginner (selected);
	Menu_Int (unselected);
	Menu_Expert (unselected);

	//navigation hint
	Menu_Nav (unselected);

	char key;
	int locationDifficulty = 1;     //first option selected by default
	int menuCount = 3;

	//current difficulty level
	c.setColour (selected);
	c.setFont (heading1);
	if (beginner)
	{
	    c.drawString ("+", 70, 150);
	}
	else if (intermediate)
	{
	    c.drawString ("+", 70, 200);
	}
	else if (expert)
	{
	    c.drawString ("+", 70, 250);
	}


	while (true)
	{
	    key = c.getChar ();

	    //use W and S keys to move through the menu
	    locationDifficulty += selector (key, 1, menuCount, locationDifficulty);

	    //beginnner
	    if (locationDifficulty == 1)
	    {
		Menu_Beginner (selected);
		Menu_Int (unselected);
		Menu_Expert (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    beginner = true;
		    intermediate = false;
		    expert = false;
		    break;
		}
	    }

	    //intermediate
	    else if (locationDifficulty == 2)
	    {
		Menu_Beginner (unselected);
		Menu_Int (selected);
		Menu_Expert (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    beginner = false;
		    intermediate = true;
		    expert = false;
		    break;
		}
	    }

	    //expert
	    else if (locationDifficulty == 3)
	    {
		Menu_Beginner (unselected);
		Menu_Int (unselected);
		Menu_Expert (selected);

		if (key == ' ')
		{
		    c.clear ();
		    beginner = false;
		    intermediate = false;
		    expert = true;
		    break;
		}
	    }

	    //exit menu
	    if (key == 'x')
	    {
		c.clear ();
		break;
	    }
	}
    }


    //-----------------Mode-----------------//
    public static void Mode ()
    {
	//menu
	c.setFont (heading1);
	Menu_Multi (unselected);       //menu options
	Menu_Single (selected);

	//navigation hint
	Menu_Nav (unselected);

	//current mode
	c.setFont (heading1);
	c.setColor (selected);
	if (singleplayer)
	{
	    c.drawString ("+", 70, 150);
	}
	else
	{
	    c.drawString ("+", 70, 200);
	}

	char key;
	int menuCount = 2;  //how big the menu is
	locationMain = 1;

	while (true)
	{
	    key = c.getChar ();

	    //use W and S keys to move through the menu
	    locationMode += selector (key, 1, menuCount, locationMode);

	    //keeps track of location
	    if (locationMode == 1)      //single
	    {
		Menu_Multi (unselected);
		Menu_Single (selected);             //selected

		if (key == ' ')
		{
		    c.clear ();
		    singleplayer = true;
		    break;
		}
	    }
	    else if (locationMode == 2)      //multi
	    {
		Menu_Multi (selected);       //selected
		Menu_Single (unselected);

		if (key == ' ')
		{
		    c.clear ();
		    singleplayer = false;
		    break;
		}
	    }

	    //exit
	    if (key == 'x')
	    {
		c.clear ();
		break;
	    }
	}
    }


    //-----------------Mode-----------------//
    public static void Instructions ()
    {
	//navigation hint
	Menu_Nav (unselected);

	c.println ("=========================HOW TO PLAY=========================");
	c.println (player1.GetName () + " uses the [W] & [S] keys to move their paddle up and down. \n" + player2.GetName () + " uses the [5] & [2] keys (numpad) to move their paddle up and down \n");
	c.println ("Players move their paddle to where the ball is to hit it and bounce it back \ntowards their opponent\n");

	c.println ("==========================OBJECTIVE==========================");
	c.println ("To be the first player to score [" + obScore + "] points (# can be changed in options)\n");

	c.println ("========================MULTIPLAYER MODE=====================");
	c.println ("When a player misses the ball, the other player gains a point\n");

	c.println ("=========================SINGLE MODE=========================");
	c.println ("Each time the player catches the ball, they gain a point. \nLose the game when they drop the ball.");
	c.println ("Careful, the ball bahaves in unexpected ways when it hits the other wall.\n");

	c.println ("============================HINT=============================");
	c.println ("Press [z] to quit the game.\n");

	char key;

	while (true)
	{
	    if (c.isCharAvail ())
	    {
		key = c.getChar ();

		//go back to menu
		if (key == 'x')
		{
		    c.clear ();
		    break;
		}
	    }
	}
    }


    //=================PONG BITS===================//
    public static void setValues (int minH, int maxH, int minV, int maxV, int padH, int ballR)
    {
	//ball
	bubble1.SetRadius (ballR);
	bubble1.SetHorizontal (dirMultiplier () * random (minH, maxH));
	bubble1.SetVertical (dirMultiplier () * random (minV, maxV));

	//paddle1
	paddle1.SetWidth (10);
	paddle1.SetHeight (padH);
	paddle1.SetColour (player1.GetColour ());
	paddle1.Draw (c);
	paddle1.SetSpeed (paddleSpeed);

	//paddle 2
	if (!singleplayer)
	{
	    paddle2.SetWidth (10);
	    paddle2.SetHeight (padH);
	    paddle2.SetColour (player2.GetColour ());
	    paddle2.Draw (c);
	    paddle2.SetSpeed (paddleSpeed);
	}
    }


    //random starting directions
    public static int dirMultiplier ()
    {
	int dirMultiplier = random (0, 1);

	if (dirMultiplier == 0)
	    return -1;
	else
	    return 1;
    }


    //multiplayer game
    public static void pong ()
    {
	char key;


	while (!finish)     //while no one has won
	{
	    bubble1.Move (c);   //move the ball

	    //key pressing
	    if (c.isCharAvail ())
	    {
		key = c.getChar ();
		//player 1
		if (bubble1.GetHorizontal () < 0 && key == 'w' && paddle1.GetY () > 0)
		{
		    paddle1.Move (c, 1);
		}

		if (bubble1.GetHorizontal () < 0 && key == 's' && paddle1.GetY () + paddle1.GetHeight () < SCREEN_Y)
		{
		    paddle1.Move (c, 2);

		}
		//player 2
		if (bubble1.GetHorizontal () > 0 && key == '5' && paddle2.GetY () > 0)
		{
		    paddle2.Move (c, 1);
		}

		if (bubble1.GetHorizontal () > 0 && key == '2' && paddle2.GetY () + paddle1.GetHeight () < SCREEN_Y)
		{
		    paddle2.Move (c, 2);
		}
		//exit game
		if (key == 'x')
		{
		    finish = true;
		}
	    }

	    //bounce off the top and bottom edges
	    if (bubble1.GetY () + bubble1.GetRadius () > SCREEN_Y || bubble1.GetY () < bubble1.GetRadius ())
	    {
		bubble1.SetVertical (bubble1.GetVertical () * -1);
	    }

	    //right paddle
	    else if (bubble1.GetHorizontal () < 0 && (paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY ()) ||      //going in right direction and within the paddle limits
			paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY () - bubble1.GetRadius ()) ||
			paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY () + bubble1.GetRadius ())))
	    {
		bubble1.SetHorizontal (bubble1.GetHorizontal () * -1);
	    }

	    //left paddle
	    else if (bubble1.GetHorizontal () > 0 && (paddle2.isTouching (bubble1.GetRightX (), bubble1.GetY ()) ||     //going in right direction and within the paddle limits
			paddle2.isTouching (bubble1.GetRightX (), bubble1.GetY () - bubble1.GetRadius ()) ||
			paddle2.isTouching (bubble1.GetRightX (), bubble1.GetY () + bubble1.GetRadius ())))
	    {
		bubble1.SetHorizontal (bubble1.GetHorizontal () * -1);
	    }

	    //SCORING
	    else if (bubble1.GetX () + bubble1.GetRadius () > SCREEN_X)     //leaves the right side of screen, player 1 gets point
	    {
		player1.SetScore (player1.GetScore () + 1);     //+1 point
		scoreUpdate = true;
		displayScore ();    //update the score

		if (player1.GetScore () == obScore)     //if player 1 gets the right amount of points, they win
		{
		    finish = true;
		    player1.SetWin (true);      //declared winner
		    player2.SetWin (false);
		    break;
		}

		reset ();       //reset ball
	    }

	    else if (bubble1.GetX () < bubble1.GetRadius ())                //leaves the left side of screen, player 1 gets point
	    {
		player2.SetScore (player2.GetScore () + 1);     //+1 point
		scoreUpdate = true;
		displayScore ();    //update the score

		if (player2.GetScore () == obScore)     //if player 2 gets the right amount of points, they win
		{
		    finish = true;
		    player2.SetWin (true);      //declaired winner
		    player1.SetWin (false);
		    break;
		}

		reset ();       //reset ball
	    }

	    //redraw score incase it was erased
	    displayScore ();

	    paddle1.Draw (c);
	    paddle2.Draw (c);
	    delay (20);
	}
    }


    //single player game
    public static void pongSingle ()
    {

	c.setColor (turquoise);
	c.fillRect (SCREEN_X - 150, 0, SCREEN_X, SCREEN_Y);

	char key;
	do     //while you havent lost
	{
	    //colour block to bounce off of
	    c.setColor (turquoise);
	    c.fillRect (SCREEN_X - 150, 0, SCREEN_X, SCREEN_Y);

	    c.setColor (bubble1.GetColour ());
	    bubble1.Move (c);   //move the ball

	    //key pressing
	    if (c.isCharAvail ())
	    {
		key = c.getChar ();
		//player 1
		if (key == 'w' && paddle1.GetY () > 0)
		{
		    paddle1.Move (c, 1);
		}

		if (key == 's' && paddle1.GetY () + paddle1.GetHeight () < SCREEN_Y)
		{
		    paddle1.Move (c, 2);
		}

		//exit game
		if (key == 'x')
		{
		    finish = true;
		}
	    }

	    //bounce off the top and bottom edges
	    if (bubble1.GetY () + bubble1.GetRadius () > SCREEN_Y || bubble1.GetY () < bubble1.GetRadius ())
	    {
		bubble1.SetVertical (bubble1.GetVertical () * -1);
	    }

	    //bounce off right edge, random direction back to make more intersting
	    else if (bubble1.GetX () + bubble1.GetRadius () > SCREEN_X - 150)
	    {
		bubble1.SetHorizontal (-1 * random (3, 5));
		bubble1.SetVertical (dirMultiplier () * random (1, 3));
	    }

	    //if touching paddle
	    else if (paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY ()) || paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY () - bubble1.GetRadius ()) || paddle1.isTouching (bubble1.GetLeftX (), bubble1.GetY () + bubble1.GetRadius ()))
	    {
		bubble1.SetHorizontal (bubble1.GetHorizontal () * -1);  //reverse

		player1.SetScore (player1.GetScore () + 1);     //+point
		scoreUpdate = true;
		displayScore ();    //update the score

		if (player1.GetScore () == obScore)     //if player 1 gets the right amount of points, they win
		{
		    finish = true;
		    player1.SetWin (true);      //declared winner
		    break;
		}
	    }

	    else if (bubble1.GetX () < bubble1.GetRadius ())                //leaves the left side of screen, player 1 gets point
	    {
		finish = true;
		player1.SetWin (false);
	    }

	    //redraw score incase it was erased
	    displayScore ();

	    paddle1.Draw (c);
	    delay (20);
	}
	while (bubble1.GetX () > bubble1.GetRadius () && !finish);
    }


    public static void reset ()
    {
	//relocate ball in middle
	bubble1.Erase (c);
	bubble1.SetX (SCREEN_X / 2);
	bubble1.SetY (SCREEN_Y / 2);

	countdown (1000);

	//new direction
	if (beginner)
	{
	    setValues (2, 4, 2, 4, 120, 15);
	}
	else if (intermediate)
	{
	    setValues (3, 6, 2, 5, 80, 15);
	}
	else if (expert)
	{
	    setValues (5, 7, 3, 8, 60, 10);
	}
    }


    //scoreboard
    public static void displayScore ()
    {
	c.setFont (heading1);

	if (scoreUpdate)    //if changed, erase old
	{
	    c.setColor (Color.white);
	    c.fillRect (260, 0, 200, 100);
	}

	//write scores
	c.setColor (Color.black);
	c.drawString ("" + player1.GetScore (), 275, 60);

	if (!singleplayer)  //multiplayer mode
	{
	    c.drawString ("|", 320, 60);
	    c.drawString ("" + player2.GetScore (), 350, 60);
	}

	scoreUpdate = false;    //prevents erasing every frame
    }


    //congrats
    public static void winner ()
    {
	//player 1 wins (single or multi)
	if (player1.isWinner ())
	{
	    c.setFont (heading1);
	    c.setColour (player1.GetColour ());
	    c.drawString ("Congratulations ", 150, 200);
	    c.drawString (player1.GetName (), 150, 250);
	}

	//player 2 wins
	else if (player1.isWinner ())
	{
	    c.setFont (heading1);
	    c.setColour (player2.GetColour ());
	    c.drawString ("Congratulations ", 150, 200);
	    c.drawString (player2.GetName (), 150, 250);
	}

	//single player, player 1 loses
	else if (singleplayer && !player1.isWinner ())
	{
	    c.setFont (heading1);
	    c.setColour (player1.GetColour ());
	    c.drawString ("Sorry, you lost", 150, 200);
	}
    }


    //=================UTILITIES===================//
    //count down
    public static void countdown (int t)
    {
	c.setFont (heading1);

	//3
	c.setColor (Color.black);
	c.drawString ("3", 310, 250);
	delay (t);
	c.setColor (Color.white);
	c.fillRect (290, 200, 320, 270);

	//2
	c.setColor (Color.black);
	c.drawString ("2", 310, 250);
	delay (t);
	c.setColor (Color.white);
	c.fillRect (290, 200, 320, 270);

	//1
	c.setColor (Color.black);
	c.drawString ("1", 310, 250);
	delay (t);
	c.setColor (Color.white);
	c.fillRect (290, 200, 320, 270);
    }


    //random generator
    public static int random (int min, int max)
    {
	return (int) (min + (int) (Math.random () * ((max - min) + 1)));
    }


    //delay
    public static void delay (int t)
    {
	try
	{
	    Thread.sleep (t);
	}
	catch (InterruptedException e)
	{
	}
    }
} // PongGame class


