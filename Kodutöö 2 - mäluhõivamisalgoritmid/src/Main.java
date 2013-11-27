import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main {

	private static Joonis joonistus;
	private static JFrame mainFrame;
	private static JTextField omaMuster;

	public static void main(String[] args) {

		mainFrame = new JFrame("Simulaator");
		mainFrame.setLayout(new GridLayout(1,2));
		mainFrame.setSize(700, 650);

		joonistus = new Joonis();
		joonistus.setBackground(Color.white);
		mainFrame.add(joonistus);

		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(3, 1));

		JPanel mustrid = new JPanel(new FlowLayout());
		final JButton näide1 = new JButton("10,5;20,7;1,3;2,2;12,3");
		final JButton näide2 = new JButton("4,2;8,4;16,1;2,5;18,3;12,2;14,4");
		final JButton näide3 = new JButton("25,5;18,2;5,4;8,3;16,4;5,1;16,2");

		näide1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster(näide1.getText());
			}
		});

		näide2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster(näide2.getText());
			}
		});

		näide3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster(näide3.getText());
			}
		});

		mustrid.add(näide1);
		mustrid.add(näide2);
		mustrid.add(näide3);

		mainMenu.add(mustrid);

		omaMuster = new JTextField();
		JButton simuleeriNupp = new JButton("Simuleeri oma \n"
				+ "päringute järjekord");
		JPanel omaSisend = new JPanel();
		omaSisend.setLayout(new FlowLayout());
		omaSisend.add(simuleeriNupp, BorderLayout.WEST);
		omaMuster.setColumns(20);
		omaSisend.add(omaMuster, BorderLayout.CENTER);


		simuleeriNupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = omaMuster.getText();
				if (!(s.equals(""))) {
					String[] arvud = s.split(" ");
					if (arvud.length > 10) {
						JOptionPane.showMessageDialog(null,
								"Sisend liiga pikk.\n Maksimaalselt 10 täisarvu vahemikus 0 kuni 31.", 
								"Vale sisend", JOptionPane.WARNING_MESSAGE);
					} else {
						try {
							for (String sArv: arvud) {
								int arv = Integer.parseInt(sArv);
								if (arv > 31 || arv < 0) {
									throw new IndexOutOfBoundsException();
								}
							}
							setMuster(s);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null,
									"Ei sisestatud täisarve või leidub üleliigseid tühikuid."
									+ "\nNumbrite vahel peab olema üks tühik.",
									"Vale sisend", JOptionPane.WARNING_MESSAGE);
						} catch (IndexOutOfBoundsException ex) {
							JOptionPane.showMessageDialog(null,
									"Sisestatud täisarvud peavad olema vahemikus 0 kuni 31.", "Vale sisend",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					
				}
			}
		});
		mainMenu.add(omaSisend);
		mainFrame.add(mainMenu);
		
		//Algoritmide nupud
		JPanel algoritmid = new JPanel(new FlowLayout());
		
		JButton alg1 = new JButton("First-fit");
		JButton alg2 = new JButton("Best-fit");
		JButton alg3 = new JButton("Worst-fit");
		JButton alg4 = new JButton("Random-fit");

		
		algoritmid.add(alg1);
		algoritmid.add(alg2);
		algoritmid.add(alg3);
		algoritmid.add(alg4);

		alg1.addActionListener(new Nupukuular());
		alg2.addActionListener(new Nupukuular());
		alg3.addActionListener(new Nupukuular());
		alg4.addActionListener(new Nupukuular());
		
		mainMenu.add(algoritmid);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2,
				(screen.height - mainFrame.getHeight()) / 2);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}

	private static void setMuster(String s) {
		joonistus.setMuster(s);
		joonistus.repaint();
	}
	
	public static Joonis getJoonistus() {
		return joonistus;
	}


}

class Nupukuular implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Main.getJoonistus().setAlgoritm(((JButton)e.getSource()).getText());
		Main.getJoonistus().repaint();
	}
	
}
