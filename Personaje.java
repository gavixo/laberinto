import javax.swing.*;
import java.awt.event.*;

public class Personaje extends JLabel
{
	private int x, y;

	public Personaje( int x, int y)
	{
		
		this.x = x;
		this.y = y;
		setBounds(x*50, y*50+40, 45, 50);
	}

	public void setXPos(int xPos)
	{
		x = (xPos+10)*50;
	}

	public void setYPos(int yPos)
	{
		y = (yPos+10)*50;
	}

	public int getXPos()
	{
		return x;
	}

	public int getYPos()
	{
		return y;
	}

}
