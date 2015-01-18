// The "BubbleClass" class.
import java.awt.*;
import hsa.Console;

public class Bubble extends Ball
{
    protected int iHorizontal, iVertical; //add other fields (characteristics)

    protected Console c;

    public Bubble ()  //Constructor method
    {
	super (); //First call the constructor of the base class
	iHorizontal = 1;
	iVertical = (int) (Math.random () * 2) - 1;

    }


    public Bubble (int ix, int iy, int radius, Color clr1, int iHor, int iVert)
    {
	super (ix, iy, radius, clr1); //call the constructor method first
	iHorizontal = iHor;
	iVertical = iVert;

    }


    public Bubble (int ix, int iy, int radius, Color clr1)
    {
	super (ix, iy, radius, clr1); //call the constructor method first
	iHorizontal = (int) (Math.random () * 2) - 1;
	iVertical = (int) (Math.random () * 2) - 1;

    }


    public void SetHorizontal (int iH)  // Sets the characteristics
    {
	iHorizontal = iH;
    }


    public int GetHorizontal ()  // Returns the characteristic
    {
	return iHorizontal;
    }


    public void SetVertical (int iV)  // Sets the characteristics
    {
	iVertical = iV;
    }


    public int GetVertical ()  // Returns the characteristic
    {
	return iVertical;
    }


    public void Move (Console c1)
    {
	Erase (c1);
	SetX (GetX () + GetHorizontal ());
	SetY (GetY () + GetVertical ());
	Draw (c1);

    }
} // BubbleClass class
