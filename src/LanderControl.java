import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LanderControl implements MouseListener, Runnable {
	LanderView landerView;
	DataView dataView;
	LanderModel model;
	
	public static void main(String[] args) {
		new LanderControl();
	}
	
	public LanderControl()
	{
		model = new LanderModel(160);
		
		landerView = new LanderView(200);
		landerView.setPreferredSize(new Dimension(400, 400));
		
		dataView = new DataView(3);
		dataView.setPreferredSize(new Dimension(160, 400));
		dataView.labels[0] = "Height: ";
		dataView.labels[1] = "Velocity: ";
		dataView.labels[2] = "Time: ";
		
		JFrame frame = new JFrame("Lander");
		frame.setLayout(new FlowLayout());
		frame.add(landerView);
		frame.add(dataView);
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
		model.thrust = 2.5 * LanderModel.g;
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
				landerView.height = model.height;
				landerView.repaint();
				
				updateDataView((lasttime - starttime) / 1000.0 );
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
		// System.out.println("Geschwindigkeit: " + model.velocity);
		// System.out.println("Zeit: " + (lasttime - starttime));
	}

	private void updateDataView(double elapsedTime) {
		dataView.data[0] = "" + model.height;
		dataView.data[1] = "" + model.velocity;
		dataView.data[2] = "" + elapsedTime;
		dataView.updateView();
		// TODO Auto-generated method stub
		
	}
}
