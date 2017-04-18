import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LanderControl implements MouseListener, Runnable {
	LanderView view;
	LanderModel model;
	
	public static void main(String[] args) {
		new LanderControl();
	}
	
	public LanderControl()
	{
		view = new LanderView(200);
		model = new LanderModel(160);
		
		view.setPreferredSize(new Dimension(400, 400));
		
		JFrame frame = new JFrame("Lander");
		frame.add(view);
		frame.pack();
		
		frame.addMouseListener(this);
		frame.setVisible(true);
		
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		model.thrust = 4 * LanderModel.g;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		model.thrust = 0;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		model.thrust = 0;
	}

	public void run()
	{
		long lasttime = 0;
		long starttime = 0;
		boolean done = false;
		while (!done) {
			if(lasttime != 0)
			{
				double dt = (System.currentTimeMillis() - lasttime) / 1000.0;
				done = model.tick(dt);
				view.height = model.height;
				view.repaint();
			}
			else
			{
				starttime = System.currentTimeMillis();
			}
			lasttime = System.currentTimeMillis();
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				/* ignore exception */ }
		}
		System.out.println("Geschwindigkeit: " + model.velocity);
		System.out.println("Zeit: " + (lasttime - starttime));
	}
}
