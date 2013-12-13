import java.awt.Color;

public class Protsess {
	
	private String nimi;
	private int ajahulk;
	private int saabumisaeg;
	private Color v�rv;
	
	Protsess(String nimi, int saabumisaeg, int ajahulk, Color v�rv) {
		this.nimi = nimi;
		this.ajahulk = ajahulk;
		this.saabumisaeg = saabumisaeg;
		this.v�rv = v�rv;
	}
	
	public int getAjahulk() {
		return ajahulk;
	}

	public void setAjahulk(int ajahulk) {
		this.ajahulk = ajahulk;
	}

	public int getSaabumisaeg() {
		return saabumisaeg;
	}

	public void setSaabumisaeg(int saabumisaeg) {
		this.saabumisaeg = saabumisaeg;
	}

	public String toString() {
		return this.nimi + " | " + this.saabumisaeg + " | " + this.ajahulk;
	}
	
	public String getNimi() {
		return nimi;
	}

	public Color getV�rv() {
		return v�rv;
	}
}
