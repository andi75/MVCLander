import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LanderControl implements MouseListener, Runnable, ActionListener {
	LanderView landerView;
	DataView dataView;
	LanderModel model;
	
	boolean isRunning;
	long starttime = 0;
	long lasttime = 0;

	public static void main(String[] args) {
		new LanderControl();
	}
	
	public LanderControl()
	{
		model = new LanderModel();
		
		landerView = new LanderView(200);
		landerView.setPreferredSize(new Dimension(400, 400));
		
		dataView = new DataView(3);
		dataView.setPreferredSize(new Dimension(160, 400));
		dataView.labels[0] = "Height: ";
		dataView.labels[1] = "Velocity: ";
		dataView.labels[2] = "Time: ";

		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new FlowLayout());
		viewPanel.add(landerView);
		viewPanel.add(dataView);
		
		JButton resetButton = new JButton("reset");
		resetButton.addActionListener(this);
		
		JFrame frame = new JFrame("Lander");
		frame.setLayout(new BorderLayout());

		frame.add(viewPanel, BorderLayout.CENTER);
		frame.add(resetButton, BorderLayout.SOUTH);
		frame.pack();
		
		frame.addMouseListener(this);
		frame.setVisible(true);
			
		resetModel();
		isRunning = true;
		

		Thread t = new Thread(this);
		t.start();
	}

	private void resetModel() {
		model.reset(160);
		starttime = System.currentTimeMillis();
		lasttime = System.currentTimeMillis();
		updateDataView(0.0);
		updateLanderView();
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
		while (true) 
		{
			if(isRunning)
			{
				if(lasttime != 0)
				{
					double dt = (System.currentTimeMillis() - lasttime) / 1000.0;
					isRunning = model.tick(dt);
	
					updateLanderView();
					updateDataView((lasttime - starttime) / 1000.0 );
				}
				lasttime = System.currentTimeMillis();
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				/* ignore exception */ }
		}
		// System.out.println("Geschwindigkeit: " + model.velocity);
		// System.out.println("Zeit: " + (lasttime - starttime));
	}

	private void updateLanderView() {
		landerView.height = model.height;
		landerView.velocity = model.velocity;
		landerView.repaint();		
	}

	private void updateDataView(double elapsedTime) {
		dataView.data[0] = "" + model.height;
		dataView.data[1] = "" + model.velocity;
		dataView.data[2] = "" + elapsedTime;
		dataView.updateView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.reset(160);
		starttime = System.currentTimeMillis();
		isRunning = true;
	}
}
