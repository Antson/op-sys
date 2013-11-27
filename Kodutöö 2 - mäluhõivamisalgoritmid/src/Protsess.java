
public class Protsess {

	private int eluiga;
	private int maht;
	private int algus;
	
	Protsess(int maht, int eluiga) {
		this.eluiga = eluiga;
		this.maht = maht;
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
}
