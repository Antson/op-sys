import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Joonis extends JPanel {
	
	private ArrayList<Integer> muster = new ArrayList<Integer>();
	private String algoritm;
	private Integer number = 0;

	public void paintComponent(Graphics g) {
		int x = 35;
		int y = 60;
		super.paintComponent(g);
	    Graphics2D graphics2D = (Graphics2D) g;

	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON); 

	    for (int i = 0; i < 32; i++, number++) {
	    	graphics2D.drawString(number.toString(), 30+i*20, 45);
	    }
	    number = 0;
	    
	    for (int i = 0; i < muster.size() + 2; i++){
	    	for (int j = 0; j < 32; j++){
	    		graphics2D.drawRect(25+j*20, 30 + i*20, 20, 20);
	    	}
	    }
	    
	    int posX;
		for (int i = 0; i < muster.size(); i++){
			graphics2D.setColor(Color.black);
			posX = 35 + 20*muster.get(i);
			graphics2D.drawLine(x, y, posX, y+20);
			x = posX;
			y += 20;
			graphics2D.fillOval(x-5, y-5, 10, 10);
			try {
				wait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

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
