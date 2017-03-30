package affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.io.File;

import javax.swing.JPanel;

import formatage.Carte;
import formatage.FeatureEnum;

public class Panneau extends JPanel implements MouseListener, MouseMotionListener {
	private Point startPoint;
	private Point offset = new Point(0, 0);
	Carte carte;
	Graphics2D g2;
	public Carte getCarte() {
		return carte;
	}
	
	public Panneau () {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.carte = new Carte(new File("C:\\Users\\admin\\workspace\\Map\\weogeo_j230828\\data"));
	}

	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2 = (Graphics2D) g;
		g2.translate(offset.getX(), offset.getY());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		carte.adapteCarte();
		
		afficherPolygone(g2, carte);
		afficherLigne(g2, carte);
	}
	
	private void afficherLigne(Graphics2D g2, Carte carte) {
		for (int i = 0; i < carte.getLignesTmp().size(); i++) {
			try {
			float niveau = carte.getLignesTmp().get(i).getValue().getNiveau();
			Stroke s = new BasicStroke(niveau, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			g2.setStroke(s);
			}
			catch (Exception e){
				Stroke s = new BasicStroke(0, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
				g2.setStroke(s);
			}
			g2.setColor(carte.getLignesTmp().get(i).getType().getColor());
			for (int j = 0; j < carte.getLignesTmp().get(i).getCoordonnees().length-1; j++) {
				Line2D lin = new Line2D.Double(
						carte.getLignesTmp().get(i).getCoordonnees()[j][0],
						carte.getLignesTmp().get(i).getCoordonnees()[j][1],
						carte.getLignesTmp().get(i).getCoordonnees()[j+1][0],
						carte.getLignesTmp().get(i).getCoordonnees()[j+1][1]);
				g2.draw(lin);
			}
		}
	}
	
	private void afficherPolygone(Graphics2D g2, Carte carte) {
		for (int i = 0; i < carte.getPolygonesTmp().size(); i++) {
			for (int j = 0; j < carte.getPolygonesTmp().get(i).getCoordonnees().length; j++) {
				g2.setColor(carte.getPolygonesTmp().get(i).getType().getColor());
				Path2D shape = new Path2D.Double();
				shape.moveTo(carte.getPolygonesTmp().get(i).getCoordonnees()[j][0][0],
							 carte.getPolygonesTmp().get(i).getCoordonnees()[j][0][1]);
					for (int k = 1; k < carte.getPolygonesTmp().get(i).getCoordonnees()[j].length; k++) {	
						shape.lineTo(carte.getPolygonesTmp().get(i).getCoordonnees()[j][k][0],
									 carte.getPolygonesTmp().get(i).getCoordonnees()[j][k][1]);
					}
				shape.closePath();
				g2.fill(shape);
			}
		}
	}
	
	 @Override
     public void mousePressed(MouseEvent e) {
         startPoint = e.getPoint();
         startPoint.x -= offset.x;
         startPoint.y -= offset.y;
     }

     @Override
     public void mouseReleased(MouseEvent e) {
         startPoint = null;
     }
	
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
        int x = p.x - startPoint.x;
        int y = p.y - startPoint.y;
        offset = new Point(x, y);
        repaint();
	}

	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
