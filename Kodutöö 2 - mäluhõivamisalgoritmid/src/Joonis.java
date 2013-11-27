import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class Joonis extends JPanel {

	private String muster = "";
	private String algoritm = "First-fit";
	private Integer number = 0;
	private int mälu[] = new int[50];

	public void paintComponent(Graphics g) {
		int x = 100;
		int y = 100;
		int posX;
		super.paintComponent(g);
		Graphics2D graafika = (Graphics2D) g;

		graafika.setColor(Color.black);
		graafika.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Font def = g.getFont();

		g.setFont(new Font("", Font.BOLD, 18));

		int tekstX = 15;
		graafika.drawString("Algoritm:", tekstX, 25);
		graafika.drawString(algoritm, tekstX + 95, 25);
		graafika.drawString("Protsessid:", tekstX, 50);
		graafika.drawString(muster, tekstX, 70);
		g.setFont(def);

		for (int i = 0; i < 50; i++) {
			graafika.drawRect(x, y, 150, 10);
			y += 10;
		}
		y = 100;
		mälu[0] = 1;
		mälu[19] = 1;
		for (int i = 0; i < mälu.length; i++) {
			if (mälu[i] == 1) {
				graafika.setColor(Color.red);
				graafika.fillRect(x + 1, y + 1, 149, 9);
			}
			y += 10;
		}
		graafika.setColor(Color.black);

	}

	public String getmuster() {
		return muster;
	}

	public void setMuster(String muster) {
		this.muster = muster;
	}

	public String getAlgoritm() {
		return algoritm;
	}

	public void setAlgoritm(String algoritm) {
		this.algoritm = algoritm;
	}

}
