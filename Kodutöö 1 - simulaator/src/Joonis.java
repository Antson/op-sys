import java.awt.*;
import javax.swing.*;

public class Joonis extends JPanel {
	
	private int[] sisend = new int[5];
	
	public int[] getSisend() {
		return sisend;
	}

	public void setSisend(int[] sisend) {
		this.sisend = sisend;
	}

	public void paintComponent(Graphics g) {
		int x = 300;
		int y = 40;
		super.paintComponent(g);
	    Graphics2D graphics2D = (Graphics2D) g;

	    //Set  anti-alias!
	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON); 
	    
	    for (int i = 0; i < 30; i++){
	    	graphics2D.drawRect((i+1)*20, 10, 20, 20);
	    }
	    
		for (int arv: sisend){
			graphics2D.drawLine(x, y, arv, y+20);
			x = arv;
			y += 20;
		}
	}

}
