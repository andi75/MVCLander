import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LanderView extends JPanel {
	double height;
	double visibleHeight;
	
	public LanderView(double visibleHeight)
	{
		this.visibleHeight = visibleHeight;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.black);
		g.drawLine(
				(int)(w * 0.2), (int)(h * 0.8),
				(int)(w * 0.8), (int)(h * 0.8)
				);
		
		g.setColor(Color.red);
		double y = height / visibleHeight;
		g.fillRect( (int) (w * 0.3), (int) ( h * 0.8 - y * h * 0.6 - h * 0.1 ),
				(int) (w * 0.4), (int) (h * 0.1)
				);
	}
}
