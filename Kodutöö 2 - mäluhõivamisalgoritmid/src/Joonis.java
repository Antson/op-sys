import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import javax.swing.*;

public class Joonis extends JPanel {

	private ArrayList<Protsess> protsessid = new ArrayList<Protsess>();
	private ArrayList<Protsess> m�lusProtsessid = new ArrayList<Protsess>();
	private String protsessTekst = "";
	private String algoritm = "First-fit";
	private Integer number = 0;
	private int m�lu[] = new int[50];

	public void paintComponent(Graphics g) {
		int x = 100;
		int y = 590;
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
		graafika.drawString("T��tlemata protsessid:", tekstX, 50);
		StringBuilder tekstProts = new StringBuilder("");
		for (Protsess protsess : protsessid) {
			tekstProts.append(protsess.getMaht());
			tekstProts.append("|");
			tekstProts.append(protsess.getEluiga());
			tekstProts.append(" , ");
		}
		if (!protsessid.isEmpty()) {
			tekstProts.deleteCharAt(tekstProts.length() - 2);
		}
		graafika.drawString(tekstProts.toString(), tekstX, 70);
		g.setFont(def);

		for (int i = 0; i < 50; i++) {
			graafika.drawRect(x, y, 150, 10);
			y -= 10;
		}
		y = 590;

		Color plokiv�rv = new Color(50, 150, 190);

		if (!m�lusProtsessid.isEmpty()) {
			for (Protsess protsess : m�lusProtsessid) {
				graafika.setColor(protsess.getV�rv());
				y -= protsess.getAlgus() * 10;
				for (int i = protsess.getAlgus(); i < protsess.getMaht()
						+ protsess.getAlgus(); i++) {
					graafika.fillRect(x + 1, y + 1, 149, 9);
					y -= 10;
				}
				y = 590;
			}
		}
		graafika.setColor(Color.black);
		for (int i = 0; i < m�lu.length; i++) {
			Integer num = new Integer(i);
			graafika.drawString(num.toString(), x - 15, y + 10);
			y -= 10;
		}
		y = 590;

		graafika.setColor(Color.black);
		for (Protsess protsess : m�lusProtsessid) {
			graafika.drawLine(x + 160, 600 - protsess.getAlgus() * 10, x + 170,
					600 - protsess.getAlgus() * 10);
			graafika.drawLine(x + 170, 600 - protsess.getAlgus() * 10, x + 170,
					600 - protsess.getAlgus() * 10 - protsess.getMaht() * 10);
			graafika.drawLine(x + 160, 600 - protsess.getAlgus() * 10
					- protsess.getMaht() * 10, x + 170,
					600 - protsess.getAlgus() * 10 - protsess.getMaht() * 10);
			graafika.drawString(
					protsess.toString(),
					x + 180,
					((600 - protsess.getAlgus() * 10) + (600 - protsess
							.getAlgus() * 10 - protsess.getMaht() * 10)) / 2 + 5);
		}

	}

	public void lisaM�llu() {
		if (algoritm == "First-fit") {

			protsessidM�llu();

		} else if (algoritm == "Best-fit") {

			ArrayList<Protsess> eemaldamisele = new ArrayList<Protsess>();
			for (Protsess protsess : protsessid) {
				ArrayList<int[]> vabadKohad = leiaKohad();
				if (!vabadKohad.isEmpty()) {
					int[] parimVabakoht = null;
					for (int[] vabakoht : vabadKohad) {
						if (vabakoht[1] - vabakoht[0] + 1 >= protsess.getMaht()) {
							if (parimVabakoht == null) {
								parimVabakoht = vabakoht;
							} else if (parimVabakoht[1] - parimVabakoht[0] + 1 > vabakoht[1]
									- vabakoht[0] + 1) {
								parimVabakoht = vabakoht;
							}
						}
					}
					if (parimVabakoht != null) {
						m�lusProtsessid.add(protsess);
						eemaldamisele.add(protsess);
						protsess.setAlgus(parimVabakoht[0]);
						for (int alusta = parimVabakoht[0]; alusta < parimVabakoht[0]
								+ protsess.getMaht(); alusta++) {
							m�lu[alusta] = 1;
						}
					}
				}
			}
			if (!eemaldamisele.isEmpty()) {
				for (Protsess protsess : eemaldamisele) {
					protsessid.remove(protsess);
				}
			}

		} else if (algoritm == "Worst-fit") {

			ArrayList<Protsess> eemaldamisele = new ArrayList<Protsess>();
			for (Protsess protsess : protsessid) {
				ArrayList<int[]> vabadKohad = leiaKohad();
				if (!vabadKohad.isEmpty()) {
					int[] parimVabakoht = null;
					for (int[] vabakoht : vabadKohad) {
						if (vabakoht[1] - vabakoht[0] + 1 >= protsess.getMaht()) {
							if (parimVabakoht == null) {
								parimVabakoht = vabakoht;
							} else if (parimVabakoht[1] - parimVabakoht[0] + 1 < vabakoht[1]
									- vabakoht[0] + 1) {
								parimVabakoht = vabakoht;
							}
						}
					}
					if (parimVabakoht != null) {
						m�lusProtsessid.add(protsess);
						eemaldamisele.add(protsess);
						protsess.setAlgus(parimVabakoht[0]);
						for (int alusta = parimVabakoht[0]; alusta < parimVabakoht[0]
								+ protsess.getMaht(); alusta++) {
							m�lu[alusta] = 1;
						}
					}
				}
			}
			if (!eemaldamisele.isEmpty()) {
				for (Protsess protsess : eemaldamisele) {
					protsessid.remove(protsess);
				}
			}

		} else if (algoritm == "Random-fit") {

			ArrayList<Protsess> eemaldamisele = new ArrayList<Protsess>();
			for (Protsess protsess : protsessid) {
				ArrayList<int[]> vabadKohad = leiaKohad();
				if (!vabadKohad.isEmpty()) {
					ArrayList<int[]> sobivadKohad = new ArrayList<int[]>();
					for (int[] vabakoht : vabadKohad) {
						if (vabakoht[1] - vabakoht[0] + 1 >= protsess.getMaht()) {
							sobivadKohad.add(vabakoht);
						}
					}
					if (!sobivadKohad.isEmpty()) {
						int index = (int) (Math.random() * sobivadKohad.size());
						int[] juhuslikKoht = sobivadKohad.get(index);
						m�lusProtsessid.add(protsess);
						eemaldamisele.add(protsess);
						protsess.setAlgus(juhuslikKoht[0]);
						for (int alusta = juhuslikKoht[0]; alusta < juhuslikKoht[0]
								+ protsess.getMaht(); alusta++) {
							m�lu[alusta] = 1;
						}
					}
				}
			}
			if (!eemaldamisele.isEmpty()) {
				for (Protsess protsess : eemaldamisele) {
					protsessid.remove(protsess);
				}
			}

		}
	}

	public ArrayList<int[]> leiaKohad() {
		ArrayList<int[]> vabadKohad = new ArrayList<int[]>();
		for (int i = 0; i < m�lu.length; i++) {
			if (m�lu[i] == 0) {
				int algus = i;
				for (int j = i; j < m�lu.length; j++) {
					if (m�lu[j] == 1) {
						int[] kohad = { i, j - 1 };
						vabadKohad.add(kohad);
						i = j;
						break;
					}
					if (j == m�lu.length - 1) {
						int[] kohad = { i, j };
						vabadKohad.add(kohad);
						i = j;
						break;
					}
				}
			}
		}
		return vabadKohad;
	}

	public void protsessidM�llu() {
		ArrayList<Protsess> eemaldamisele = new ArrayList<Protsess>();
		for (Protsess protsess : protsessid) {
			boolean leitiKoht = false;
			for (int i = 0; i < m�lu.length; i++) {
				if (m�lu[i] == 0) {
					for (int algus = i, pikkus = 1; algus < m�lu.length; algus++, pikkus++) {
						if (m�lu[algus] != 0)
							break;
						if (pikkus == protsess.getMaht()) {
							leitiKoht = true;
							m�lusProtsessid.add(protsess);
							eemaldamisele.add(protsess);
							protsess.setAlgus(i);
							for (int alusta = i; alusta < i + pikkus; alusta++) {
								m�lu[alusta] = 1;
							}
						}
					}
				}
				if (leitiKoht == true) {
					break;
				}
			}
		}
		if (!eemaldamisele.isEmpty()) {
			for (Protsess protsess : eemaldamisele) {
				protsessid.remove(protsess);
			}
		}
	}

	public void v�hendaSamme() {
		ArrayList<Protsess> l�ppenud = new ArrayList<Protsess>();
		for (Protsess protsess : m�lusProtsessid) {
			protsess.setEluiga(protsess.getEluiga() - 1);
			if (protsess.getEluiga() == 0) {
				l�ppenud.add(protsess);
			}
		}
		if (!l�ppenud.isEmpty()) {
			eemaldaM�lust(l�ppenud);
		}
	}

	public void eemaldaM�lust(ArrayList<Protsess> protsessid) {
		for (Protsess protsess : protsessid) {
			for (int i = protsess.getAlgus(); i < protsess.getMaht()
					+ protsess.getAlgus(); i++) {
				m�lu[i] = 0;
			}
			m�lusProtsessid.remove(protsess);
		}
	}

	public String getProtsessTekst() {
		return protsessTekst;
	}

	public void setProtsessTekst(String protsessTekst) {
		this.protsessTekst = protsessTekst;
	}

	public ArrayList<Protsess> getprotsessid() {
		return protsessid;
	}

	public void setprotsessid(ArrayList<Protsess> s) {
		this.protsessid = s;
	}

	public String getAlgoritm() {
		return algoritm;
	}

	public void setAlgoritm(String algoritm) {
		this.algoritm = algoritm;
	}

	public void setM�lu(int[] m�lu) {
		this.m�lu = m�lu;
	}

	public void t�hjendaM�lu() {
		m�lusProtsessid.clear();
	}
}
