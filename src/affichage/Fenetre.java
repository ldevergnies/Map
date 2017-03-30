package affichage;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseWheelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panneau pan;
	
	public Fenetre () {
		this.setTitle("Map");
	    this.setSize(1900, 1000);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pan = new Panneau();
	    pan.addMouseWheelListener(this);
	    
	    this.setContentPane(pan);
	    
	    this.setVisible(true);
	}
	
	public Fenetre (String path) {
		this.setTitle("Map");
	    this.setSize(1900, 1000);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pan = new Panneau(path);
	    pan.addMouseWheelListener(this);
	    
	    this.setContentPane(pan);
	    
	    this.setVisible(true);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
        if (notches < 0) {
        	
            pan.getCarte().addZoom(10);
            pan.getCarte().adapteCarte();
            pan.repaint();
        	
        } else {
        	if (pan.getCarte().getZoom()>10){
        	pan.getCarte().addZoom(-10);
        	pan.getCarte().adapteCarte();
        	pan.repaint();
        	}
        }
	}
}
