import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataView extends JPanel {
	String labels[];
	String data[];
	
	JLabel labelItems[];
	
	DataView(int nElements)
	{
		labels = new String[nElements];
		data = new String[nElements];
		
		setLayout(new GridLayout(nElements, 2));
		labelItems = new JLabel[2 * nElements];
		for(int i = 0; i < nElements; i++)
		{
			labelItems[2 * i + 0] = new JLabel("");
			labelItems[2 * i + 1] = new JLabel("");
			add(labelItems[2 * i + 0]);
			add(labelItems[2 * i + 1]);
		}
	}
	
	public void updateView()
	{
		for(int i = 0; i < labels.length; i++)
		{
			labelItems[2 * i + 0].setText(labels[i]);
			labelItems[2 * i + 1].setText(data[i]);
		}
	}
}
