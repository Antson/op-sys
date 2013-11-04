import java.awt.*;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		
		JFrame mainFrame = new JFrame("Simulaator");
		mainFrame.setSize(700, 600);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2, (screen.height - mainFrame.getHeight()) / 2);

		mainFrame.setVisible(true);

	}

}
