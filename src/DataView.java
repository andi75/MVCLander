import java.awt.GridLayout;

import javax.swing.JPanel;

public class DataView extends JPanel {
	String labels[];
	String data[];
	
	DataView(int nElements)
	{
		labels = new String[nElements];
		data = new String[nElements];
		
		setLayout(new GridLayout(nElements, 2));
	}
}
