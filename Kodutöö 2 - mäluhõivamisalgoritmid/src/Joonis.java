import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class Joonis extends JPanel {

	private String muster = "";
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
		
		int tekstX = 15;
		graphics2D.drawString("Algoritm:", tekstX, 25);
		graphics2D.drawString(algoritm, tekstX + 95, 25);
		graphics2D.drawString("Pöördusmuster:", tekstX, 50);
		graphics2D.drawString(muster, tekstX, 70);

		g.setFont(def);
		
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
