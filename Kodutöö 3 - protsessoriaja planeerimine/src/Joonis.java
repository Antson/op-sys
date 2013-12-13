import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import javax.swing.*;

public class Joonis extends JPanel {

	private ArrayList<Protsess> saabuvadProtsessid = new ArrayList<Protsess>();
	private ArrayList<Protsess> ootelProtsessid = new ArrayList<Protsess>();
	private String algoritm = "FCFS";
	private String hetkeProtsess = "Hetkel töödeldav protsess";
	private String saabuvadTekst = "Saabuvad \nprotsessid: \nSaabumisaeg:";

	public void paintComponent(Graphics g) {
		int x = 100, y = 100, laius = 70, kõrgus = 60;
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
		//graafika.drawString("Töötlemata saabuvadProtsessid:", tekstX, 50);
		StringBuilder tekstProts = new StringBuilder("");
		for (Protsess protsess : saabuvadProtsessid) {
			tekstProts.append(protsess.getSaabumisaeg());
			tekstProts.append("|");
			tekstProts.append(protsess.getAjahulk());
			tekstProts.append(" , ");
		}
		if (!saabuvadProtsessid.isEmpty()) {
			tekstProts.deleteCharAt(tekstProts.length() - 2);
		}
		//graafika.drawString(tekstProts.toString(), tekstX, 70);
		g.setFont(def);
		
		//Protsessoriaega saava protsessi joonistamine
		graafika.drawRect(x, y, 100, 100);
		
		
		//Saabuvate protsesside joonistamine
		x = 100;
		if (!saabuvadProtsessid.isEmpty()) {
			y = 240;
			for (String rida : saabuvadTekst.split("\n")) {
				graafika.drawString(rida, x-85, y += graafika.getFontMetrics().getHeight());
			}
			y = 240;
			graafika.setFont(new Font("", Font.BOLD, 15));
			for (Protsess protsess : saabuvadProtsessid) {
				graafika.drawRect(x, y, laius, kõrgus);
				graafika.setColor(protsess.getVärv());
				graafika.fillRect(x+1, y+1, laius - 1, kõrgus - 1);
				graafika.setColor(new Color(0, 0, 0));
				graafika.drawString(new String(protsess.getNimi()), 
						(x + (x+laius))/2 - 7 , y + 20);
				graafika.drawString(Integer.toString(protsess.getSaabumisaeg())
						, x + 30, y + kõrgus - 10);
				x += laius;			
			}
			x = 100;
			graafika.drawLine(x, y + kõrgus/2, x + 
					(laius*saabuvadProtsessid.size()),  y + kõrgus/2);
			graafika.setFont(def);
		}


	}

	public ArrayList<Protsess> getprotsessid() {
		return saabuvadProtsessid;
	}

	public void setSaabuvad(ArrayList<Protsess> s) {
		this.saabuvadProtsessid = s;
	}

	public String getAlgoritm() {
		return algoritm;
	}

	public void setAlgoritm(String algoritm) {
		this.algoritm = algoritm;
	}

}
