import java.awt.*;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		
		JFrame mainFrame = new JFrame("Simulaator");
		mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setSize(700, 600);
		
		Joonis joonistus = new Joonis();
		joonistus.setBackground(Color.white);
		
		int[] sisend = {200, 400,500,300,500};
		joonistus.setSisend(sisend);
		mainFrame.add(joonistus);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2,
				(screen.height - mainFrame.getHeight()) / 2);

		mainFrame.setVisible(true);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] sisend2 = {200, 100,500,400,250,300};
		joonistus.setSisend(sisend2);
		joonistus.paint(joonistus.getGraphics());

	}

}
