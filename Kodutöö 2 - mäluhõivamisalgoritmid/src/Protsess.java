import java.awt.Color;

public class Protsess {

	private int eluiga;
	private int maht;
	private int algus;
	private Color v�rv;
	
	Protsess(int maht, int eluiga, Color v�rv) {
		this.eluiga = eluiga;
		this.maht = maht;
		this.v�rv = v�rv;
	}
	
	public int getEluiga() {
		return eluiga;
	}

	public void setEluiga(int eluiga) {
		this.eluiga = eluiga;
	}

	public int getMaht() {
		return maht;
	}

	public void setMaht(int maht) {
		this.maht = maht;
	}

	public void setAlgus(int algus) {
		this.algus = algus;
	}
	
	public int getAlgus() {
		return algus;
	}
	
	public String toString() {
		return this.maht + " | " + this.eluiga;
	}
	
	public Color getV�rv() {
		return v�rv;
	}
}
