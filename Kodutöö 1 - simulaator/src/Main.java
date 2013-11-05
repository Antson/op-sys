import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;



public class Main {
	
	private static Joonis joonistus;
	private static JFrame mainFrame;
	
	public static void main(String[] args) {
		
		mainFrame = new JFrame("Simulaator");
		mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setSize(700, 600);
		
		joonistus = new Joonis();
		joonistus.setBackground(Color.white);
		mainFrame.add(joonistus);
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridBagLayout());
		
		JPanel mustrid = new JPanel(new GridLayout(3,1));
		JButton näide1 = new JButton("Näide");
		JButton näide2 = new JButton("Näide");
		JButton näide3 = new JButton("Näide");
		
		näide1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster("1 10 5 20");
			}
		});
		
		mustrid.add(näide1);
		mustrid.add(näide2);
		mustrid.add(näide3);
		
		mainMenu.add(mustrid, mustridConstraints());
		
		mainFrame.add(mainMenu);

		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2,
				(screen.height - mainFrame.getHeight()) / 2);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
	}
	
	private static GridBagConstraints mustridConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTH;
		gc.weightx = 0.2;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;
		return gc;
	}
	
	private GridBagConstraints nupudConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.RELATIVE;

		return gc;
	}
	
	private static void setMuster(String s){
		ArrayList<Integer> muster = new ArrayList<Integer>();
		String[] arvud = s.split(" ");
		for (String arv:arvud) {
			muster.add(Integer.parseInt(arv));
		}
		joonistus.setMuster(muster);

	}

}
