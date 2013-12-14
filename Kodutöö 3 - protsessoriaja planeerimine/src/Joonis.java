import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import javax.swing.*;

public class Joonis extends JPanel {

	private ArrayList<Protsess> saabuvadProtsessid = new ArrayList<Protsess>();
	private ArrayList<Protsess> ootelProtsessid = new ArrayList<Protsess>();
	private String algoritm = "FCFS";
	private String hetkeProtsess = "Hetkel t��deldav protsess";
	private String saabuvadTekst = "Saabuvad \nprotsessid: \nSaabumisaeg:";
	private Protsess t��tavProtsess;

	public void paintComponent(Graphics g) {
		int x = 100, y = 100, laius = 70, k�rgus = 50;
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
		g.setFont(def);
		
		//Protsessoriaega saava protsessi joonistamine
		x = 50;
		graafika.drawRect(x, y, 100, 100);
		graafika.setFont(new Font("", Font.BOLD, 15));
		if (t��tavProtsess != null) {
			graafika.setColor(t��tavProtsess.getV�rv());
			graafika.fillRect(x+1, y+1, 99, 99);
			graafika.setColor(new Color(0,0,0));
			graafika.drawLine(x, y + 50, x + 100, y + 50);
			graafika.drawString(new String(t��tavProtsess.getNimi()), 
					x + 30, y + 20);
			graafika.drawString(Integer.toString(t��tavProtsess.getAjahulk())
					, x + 30, y + 70);
		}
		graafika.setFont(def);
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
				graafika.drawRect(x, y, laius, k�rgus);
				graafika.setColor(protsess.getV�rv());
				graafika.fillRect(x+1, y+1, laius - 1, k�rgus - 1);
				graafika.setColor(new Color(0, 0, 0));
				graafika.drawString(new String(protsess.getNimi()), 
						(x + (x+laius))/2 - 7 , y + 20);
				graafika.drawString(Integer.toString(protsess.getSaabumisaeg())
						, x + 30, y + k�rgus - 10);
				x += laius;			
			}
			x = 100;
			graafika.drawLine(x, y + k�rgus/2, x + 
					(laius*saabuvadProtsessid.size()),  y + k�rgus/2);
			graafika.setFont(def);
		}
		
		//Ootej�rjekorras protsesside joonistamine
		x = 150;
		if (!ootelProtsessid.isEmpty()) {
			y = 150;
			graafika.setFont(new Font("", Font.BOLD, 15));
			for (Protsess protsess : ootelProtsessid) {
				graafika.drawRect(x, y, laius, k�rgus);
				graafika.setColor(protsess.getV�rv());
				graafika.fillRect(x+1, y+1, laius - 1, k�rgus - 1);
				graafika.setColor(new Color(0, 0, 0));
				graafika.drawString(new String(protsess.getNimi()), 
						(x + (x+laius))/2 - 7 , y + 20);
				graafika.drawString(Integer.toString(protsess.getAjahulk())
						, x + 30, y + k�rgus - 10);
				x += laius;			
			}
			graafika.drawLine(x, y + k�rgus/2, x + 
					(laius*ootelProtsessid.size()),  y + k�rgus/2);
			graafika.setFont(def);
		}
	}
	
	public void t��tleProtsessi() {
		if (t��tavProtsess != null) {
			t��tavProtsess.setAjahulk(t��tavProtsess.getAjahulk() - 1);
			if (t��tavProtsess.getAjahulk() == 0) {
				t��tavProtsess = null;
			}
		}
		if (t��tavProtsess == null && (!ootelProtsessid.isEmpty())) {
			if (algoritm == "FCFS") {
				t��tavProtsess = ootelProtsessid.get(0);
				ootelProtsessid.remove(0);
			}
		}
		
	}

	public void v�hendaAegu() {
		if (!saabuvadProtsessid.isEmpty()) {
			ArrayList<Protsess> eemaldatavad = new ArrayList<Protsess>();
			for (Protsess p : saabuvadProtsessid) {
				if (p.getSaabumisaeg() == 0) {
					eemaldatavad.add(p);
					ootelProtsessid.add(p);
				}
				p.setSaabumisaeg(p.getSaabumisaeg() - 1);
				if (p.getSaabumisaeg() == 0) {
					eemaldatavad.add(p);
					ootelProtsessid.add(p);
				}
			}
			if (!eemaldatavad.isEmpty()) {
				for (Protsess p : eemaldatavad) {
					saabuvadProtsessid.remove(p);
				}
				eemaldatavad.clear();
			}
		}
	}

	public void setOotelProtsessid(ArrayList<Protsess> ootelProtsessid) {
		this.ootelProtsessid = ootelProtsessid;
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
	
	public void setT��tavProtsess(Protsess p) {
		this.t��tavProtsess = p;
	}

}
