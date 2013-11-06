import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class Joonis extends JPanel {

	private ArrayList<Integer> muster = new ArrayList<Integer>();
	private String algoritm = "FCFS";
	private Integer number = 0;

	public void paintComponent(Graphics g) {
		int x = 235;
		int y = 60;
		int posX;
		super.paintComponent(g);
		Graphics2D graphics2D = (Graphics2D) g;

		graphics2D.setColor(Color.black);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Font def = g.getFont();

		g.setFont(new Font("", Font.BOLD, 18));

		graphics2D.drawString("Algoritm:", 25, 25);
		graphics2D.drawString(algoritm, 120, 25);
		graphics2D.drawString("Pöördusmuster:", 220, 25);
		graphics2D.drawString(muster.toString(), 365, 25);

		g.setFont(def);

		for (int i = 0; i < 32; i++, number++) {
			graphics2D.drawString(number.toString(), 30 + i * 20, 45);
		}
		number = 0;

		for (int i = 0; i < muster.size() + 2; i++) {
			for (int j = 0; j < 32; j++) {
				graphics2D.drawRect(25 + j * 20, 30 + i * 20, 20, 20);
			}
		}

		int algus = 10;
		graphics2D.fillOval(x - 5, y - 5, 10, 10);
		if (algoritm == "FCFS") {
			for (int i = 0; i < muster.size(); i++) {
				posX = 35 + 20 * muster.get(i);
				graphics2D.drawLine(x, y, posX, y + 20);
				x = posX;
				y += 20;
				graphics2D.fillOval(x - 5, y - 5, 10, 10);
			}
		} else if (algoritm == "SSTF") {
			ArrayList<Integer> koopia = new ArrayList(muster);
			while (koopia.size() > 0) {
				int min = 31;
				int lühim = algus;
				for (int i = 0; i < koopia.size(); i++) {
					if (Math.abs(koopia.get(i) - algus) < min) {
						min = Math.abs(koopia.get(i) - algus);
						lühim = i;
					}
				}
				posX = 35 + 20 * koopia.get(lühim);
				graphics2D.drawLine(x, y, posX, y + 20);
				x = posX;
				y += 20;
				graphics2D.fillOval(x - 5, y - 5, 10, 10);
				algus = koopia.get(lühim);
				koopia.remove(lühim);
			}
		} else if (algoritm == "LOOK") {
			ArrayList<Integer> koopia = new ArrayList(muster);
			Collections.sort(koopia);
			int max = Collections.max(koopia);
			for (int i = 0;;) {
				if (koopia.get(i) == max) {
					break;
				}
				if (koopia.get(i) >= algus) {
					posX = 35 + 20 * koopia.get(i);
					graphics2D.drawLine(x, y, posX, y + 20);
					x = posX;
					y += 20;
					graphics2D.fillOval(x - 5, y - 5, 10, 10);
					algus = koopia.get(i);
					koopia.remove(i);
				} else {
					i++;
				}
			}
			if (koopia.size() > 0) {
				for (int i = koopia.size(); i > 0; i--) {
					posX = 35 + 20 * koopia.get(i - 1);
					graphics2D.drawLine(x, y, posX, y + 20);
					x = posX;
					y += 20;
					graphics2D.fillOval(x - 5, y - 5, 10, 10);
				}
			}
		} else if (algoritm == "SCAN") {
			ArrayList<Integer> koopia = new ArrayList(muster);
			Collections.sort(koopia);
			ArrayList<Integer> väiksemad = new ArrayList();
			ArrayList<Integer> suuremad = new ArrayList();
			for (int i = 0; i < koopia.size(); i++){
				if (koopia.get(i) >= algus) {
					suuremad.add(koopia.get(i));
				} else väiksemad.add(koopia.get(i));
			}
			if (suuremad.size() > 0) {
				for (int i = 0; i < suuremad.size(); i++) {
					posX = 35 + 20 * suuremad.get(i);
					graphics2D.drawLine(x, y, posX, y + 20);
					x = posX;
					y += 20;
					graphics2D.fillOval(x - 5, y - 5, 10, 10);
				}
			}
			graphics2D.drawLine(x, y, 655, y + 10);
			x = 655;
			y += 10;
			if (väiksemad.size() > 0) {
				posX = 35 + 20 * väiksemad.get(väiksemad.size()-1);
				graphics2D.drawLine(x, y, posX, y + 10);
				x = posX;
				y += 10;
				graphics2D.fillOval(x - 5, y - 5, 10, 10);
				väiksemad.remove(väiksemad.size()-1);
			} 
			
			if (väiksemad.size() > 0) {
				for (int i = väiksemad.size(); i > 0; i--) {
					posX = 35 + 20 * väiksemad.get(i-1);
					graphics2D.drawLine(x, y, posX, y + 20);
					x = posX;
					y += 20;
					graphics2D.fillOval(x - 5, y - 5, 10, 10);
				}
			}
			
		}

	}

	public ArrayList getmuster() {
		return muster;
	}

	public void setMuster(ArrayList muster) {
		this.muster = muster;
	}

	public String getAlgoritm() {
		return algoritm;
	}

	public void setAlgoritm(String algoritm) {
		this.algoritm = algoritm;
	}

}
