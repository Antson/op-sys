import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Main {

	private static Joonis joonistus;
	private static JFrame mainFrame;
	private static JTextField omaprotsess;
	private static  JButton sammuNupp;

	public static void main(String[] args) {

		mainFrame = new JFrame("Simulaator");
		mainFrame.setLayout(new GridLayout(1,2));
		mainFrame.setSize(700, 650);
		
		joonistus = new Joonis();
		joonistus.setBackground(Color.white);
		mainFrame.add(joonistus);

		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(5, 1));

		JPanel mustrid = new JPanel(new FlowLayout());
		final JButton näide1 = new JButton("10,5;20,7;1,3;2,2;12,3");
		final JButton näide2 = new JButton("4,2;8,4;16,1;2,5;18,3;12,2;14,4");
		final JButton näide3 = new JButton("25,5;18,2;5,4;8,3;16,4;5,1;16,2");

		näide1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 alusta(näide1.getText());
			}
		});

		näide2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 alusta(näide2.getText());
			}
		});

		näide3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 alusta(näide3.getText());
			}
		});

		mustrid.add(näide1);
		mustrid.add(näide2);
		mustrid.add(näide3);
		mainMenu.add(mustrid);

		omaprotsess = new JTextField();
		JButton simuleeriNupp = new JButton("Simuleeri oma \n"
				+ "päringute järjekord");
		JPanel omaSisend = new JPanel();
		omaSisend.setLayout(new FlowLayout());
		omaSisend.add(simuleeriNupp, BorderLayout.WEST);
		omaprotsess.setColumns(20);
		omaSisend.add(omaprotsess, BorderLayout.CENTER);


		simuleeriNupp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String omaProtsessid = omaprotsess.getText();
				if(!(omaProtsessid.equals(""))) {
					String[] protsessid = omaProtsessid.split(";");
					try {
						for (String protsess: protsessid) {
							String[] andmed = protsess.split(",");
							if (andmed.length > 2) {
								throw new ArrayIndexOutOfBoundsException();
							}
							Integer.parseInt(andmed[0]);
							if (Integer.parseInt(andmed[0]) > 50 || Integer.parseInt(andmed[0]) < 1) {
								throw new ArithmeticException();
							}
							Integer.parseInt(andmed[1]);
							alusta(omaProtsessid);
						}
					} catch(ArrayIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(null,
								"Liiga palju või vähe andmeid ühe protsessi kohta.\n"
								+ "Sisend anda kujul: maht1,eluiga1;maht2,eluiga2...", "Vale sisend",
								JOptionPane.WARNING_MESSAGE);
					} catch(ParseException ex) {
						JOptionPane.showMessageDialog(null,
								"Andmed peavad olema täisarvud.\n"
								+ "Sisend anda kujul: maht1,eluiga1;maht2,eluiga2...", "Vale sisend",
								JOptionPane.WARNING_MESSAGE);
					} catch(NumberFormatException ex) {
						
					} catch( ArithmeticException ex) {
						JOptionPane.showMessageDialog(null,
								"Protsessi maht peab olema 1 kuni 50.\n"
								+ "Sisend anda kujul: maht1,eluiga1;maht2,eluiga2...", "Vale sisend",
								JOptionPane.WARNING_MESSAGE);
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
		
		sammuNupp = new JButton("Järgmine samm");
		sammuNupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws ConcurrentModificationException {
				joonistus.vähendaSamme();
				joonistus.lisaMällu();
				joonistus.repaint();
				System.out.println(joonistus.getprotsessid());
			}
		});
		mainMenu.add(sammuNupp);	
		
		JPanel juhendPaneel = new JPanel();
		JLabel juhend = new JLabel();
		juhend.setText("Juhendid \n kasutamiseksa");
		juhendPaneel.add(juhend);
		mainMenu.add(juhendPaneel);
		
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation((screen.width - mainFrame.getWidth()) / 2,
				(screen.height - mainFrame.getHeight()) / 2);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}

	private static void setprotsess(String s) {
		String protsessid[] = s.split(";");
		ArrayList<Protsess> protsid = new ArrayList<Protsess>();
		for (String protsess: protsessid) {
			String andmed[] = protsess.split(",");
			Protsess pro = new Protsess(Integer.parseInt(andmed[0]), Integer.parseInt(andmed[1]));
			protsid.add(pro);
		}
		joonistus.setprotsessid(protsid);
		joonistus.setProtsessTekst(s);
		joonistus.protsessidMällu();
		joonistus.repaint();
	}
	
	private static void alusta(String protsess) {
		int mälu[] = new int[50];
		joonistus.tühjendaMälu();
		joonistus.setMälu(mälu);
		setprotsess(protsess);
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
