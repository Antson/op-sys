import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class Joonis extends JPanel {

	private ArrayList<Integer> muster = new ArrayList<Integer>();
	private String algoritm = "First-fit";
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
		graphics2D.fillRect(x - 5, y - 5, 10, 10);
		
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
