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
		mainFrame.setLayout(new GridLayout(2, 1));
		mainFrame.setSize(700, 600);

		joonistus = new Joonis();
		joonistus.setBackground(Color.white);
		mainFrame.add(joonistus);

		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(3, 1));

		JPanel mustrid = new JPanel(new FlowLayout());
		JButton näide1 = new JButton("1 10 5 20");
		JButton näide2 = new JButton("18 30 15 0 23 7 16");
		JButton näide3 = new JButton("8 24 11 2 19 13 32 15 4 12");

		näide1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster("1 10 5 20");
			}
		});

		näide2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster("18 30 15 0 23 7 16");
			}
		});

		näide3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMuster("8 24 11 2 19 13 31 15 4 12");
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
		
		JButton alg1 = new JButton("FCFS");
		JButton alg2 = new JButton("SSTF");
		JButton alg3 = new JButton("SCAN");
		JButton alg4 = new JButton("LOOK");
		JButton alg5 = new JButton("C-SCAN");
		JButton alg6 = new JButton("C-LOOK");
		JButton alg7 = new JButton("NOOP");
		
		algoritmid.add(alg1);
		algoritmid.add(alg2);
		algoritmid.add(alg3);
		algoritmid.add(alg4);
		algoritmid.add(alg5);
		algoritmid.add(alg6);
		algoritmid.add(alg7);
		
		alg1.addActionListener(new Nupukuular());
		alg2.addActionListener(new Nupukuular());
		alg3.addActionListener(new Nupukuular());
		alg4.addActionListener(new Nupukuular());
		alg5.addActionListener(new Nupukuular());
		alg6.addActionListener(new Nupukuular());
		alg7.addActionListener(new Nupukuular());
		
		mainMenu.add(algoritmid);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2,
				(screen.height - mainFrame.getHeight()) / 2);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}

	private static GridBagConstraints mustridConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.SOUTH;
		// gc.weightx = 0.2;
		// gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.fill = GridBagConstraints.VERTICAL;
		return gc;
	}

	private GridBagConstraints nupudConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.RELATIVE;

		return gc;
	}

	private static void setMuster(String s) {
		ArrayList<Integer> muster = new ArrayList<Integer>();
		String[] arvud = s.split(" ");
		for (String arv : arvud) {
			muster.add(Integer.parseInt(arv));
		}
		joonistus.setMuster(muster);
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
