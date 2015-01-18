// Name: Annlee Li
// Date: May 12, 2014
//Description: Paddle Class
// The "Paddle" class.
import java.awt.*;
import hsa.Console;

public class Paddle
{
    protected Console c;           // The output console
    protected int x, y, height, width, speed;
    protected Color clr, background;


    public Paddle ()    //default paddle
    {
	x = 10;
	y = 10;
	width = 10;
	height = 70;
	clr = Color.black;
	background = Color.white;
    }


    public Paddle (int x1, int y1, Color clr1)
    {
	this.x = x1;
	this.y = y1;
	width = 10;
	height = 90;
	clr = clr1;
	background = Color.white;
    }


    //overloaded paddle
    public Paddle (int x1, int y1, int width1, int height1, Color clr1)
    {
	this.x = x1;
	this.y = y1;
	this.width = width1;
	this.height = height1;
	this.clr = clr1;
	background = Color.white;

    }


    //behaviours 
    public void SetX (int x1)
    {
	x = x1;
    }


    public int GetX ()
    {
	return x;
    }


    public void SetY (int y1)
    {
	y = y1;
    }


    public int GetY ()
    {
	return y;
    }


   
    public void SetWidth (int width1)
    {
	width = width1;
    }


    public int GetWidth ()
    {
	return width;
    }


    public void SetHeight (int height1)
    {
	height = height1;
    }


    public int GetHeight ()
    {
	return height;
    }


    public void SetColour (Color clr1)
    {
	clr = clr1;
    }


    public Color GetColour ()
    {
	return clr;
    }


    public void SetSpeed (int speed1)
    {
	speed = speed1;
    }


    public int GetSpeed ()
    {
	return speed;
    }

 //actions
    public void Draw (Console c1)
    {
	c = c1;
	int x0 = x;
	int y0 = y;
	c.setColor (clr);
	c.fillRect (x0, y0, width, height);

    }


    public void Erase (Console c1)
    {
	c = c1;
	int x0 = x;
	int y0 = y;
	c.setColor (background);
	c.fillRect (x0, y0, width, height);
    }


    //move
    public void Move (Console c1, int dir)
    {
	Erase (c1);

	switch (dir)
	{
	    case 1:                             //up
		{
		    SetY (GetY () - speed);
		    break;
		}
	    case 2:                             //down
		{
		    SetY (GetY () + speed);
		    break;
		}

	    case 3:                             //left
		{
		    SetX (GetX () - speed);
		    break;
		}
	    case 4:                             //right
		{
		    SetX (GetX () + speed);
		    break;
		}
	}

	Draw (c1);
    }


    //is (x,y) within the paddle area
    public boolean isTouching (int x1, int y1)
    {
	if (x1 <= x + width && x1 >= x && y1 <= y + height && y1 >= y)
	    return true;

	else
	    return false;
    }
} // Paddle class
