// Name: Annlee Li
// Date: May 12, 2014
//Description: Ball Class
// The "Ball" class.
import java.awt.*;
import hsa.Console;

public class Ball //I am creating an object called Ball
{
    //All the characteristics of the object Ball

    protected int x, y, radius; //protected means only approved methods can alter the value of these following variables
    protected Color clr, background;
    protected Console c;

    public Ball ()  // Constructor method is used to create (memory) and where values are being initialized
    {
	x = 320;
	y = 240;
	radius = 50;
	clr = Color.black;
	background = Color.white;
    } // A Constructor method is vital for any object


    public Ball (int x1, int y1, int rad1, Color clr1)
    {
	this.x = x1;
	this.y = y1;
	this.radius = rad1;
	this.clr = clr1;
	background = Color.white;
    } // Another Constructor method - Method overloading


    /*
    Method overloading: Two or three methods that have the same name, but differ
    by either, the parameters that are being passed

    */
    //Behaviours

    public void SetX (int x1)  // Sets the characteristics
    {
	x = x1;
    }


    public int GetX ()  // Returns the characteristic value of x
    {
	return x;
    } // Returns a characteristic value of x - It is a function method that returns a value


    public void SetY (int y1)
    {
	y = y1;
    }


    public int GetY ()
    {
	return y;
    }


    public void SetRadius (int rad1)
    {
	radius = rad1;
    }


    public int GetRadius ()
    {
	return radius;
    }


    public void SetColour (Color clr1)
    {
	clr = clr1;
    }


    public Color GetColour ()
    {
	return clr;
    }


    public void SetBackground (Color background1)
    {
	background = background1;
    }


    public Color GetBackground ()
    {
	return background;
    }


    public int GetLeftX ()
    {
	return x - radius;
    }


    public int GetLeftY ()
    {
	return y;
    }


    public int GetRightX ()
    {
	return x + radius;
    }


    public int GetRightY ()
    {
	return y;
    }


    //Actions

    public void Draw (Console c1)
    {
	c = c1;
	int x0 = x - radius;
	int y0 = y - radius;
	c.setColor (clr);
	c.fillOval (x0, y0, 2 * radius, 2 * radius);
    }


    public void Erase (Console c1)
    {
	c = c1;
	int x0 = x - radius;
	int y0 = y - radius;
	c.setColor (background);
	c.fillOval (x0, y0, 2 * radius, 2 * radius);
    }
} // Ball class

