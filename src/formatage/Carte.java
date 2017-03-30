package formatage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import donnees.Line;
import donnees.Polygon;

public class Carte {
	private int zoom;
	private double minX;
	private double maxX;
	private double ecartX;
	private double minY;
	private double maxY;
	private double ecartY;
	private double ratio;
	private double origineX;
	private double origineY;
	private List<Ligne> lignes;
	private List<Polygone> polygones;
	private List<Ligne> lignesTmp;
	private List<Polygone> polygonesTmp;
	
	public Carte (File folder) {
		List<String> paths = new ArrayList<String>();
		for (File fileTmp : folder.listFiles()){
			System.out.println(fileTmp.getAbsolutePath());
			paths.add(fileTmp.getAbsolutePath());
		}
		lignes = new ArrayList<Ligne>();
		polygones = new ArrayList<Polygone>();
		lignesTmp = new ArrayList<Ligne>();
		polygonesTmp = new ArrayList<Polygone>();
		// parcours les strings
		for (String path : paths) {
			// si fichier de lignes
			if (path.contains("line")) {
				Gson gson = new Gson();
				try {
					Line lineTmp = gson.fromJson(new FileReader(path), Line.class);
					for (int i = 0; i < lineTmp.getFeatures().length; i++) {
						String type = path.substring(path.lastIndexOf('\\')+1, path.lastIndexOf('_'));
						lineTmp.getFeatures()[i].setType(FeatureEnum.valueOf(type));
						lignes.add(new Ligne(lineTmp.getFeatures()[i]));
					}
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// si fichier de polygones
			else if (path.contains("polygon")) {
				Gson gson = new Gson();
				try {
					Polygon polygonTmp = gson.fromJson(new FileReader(path), Polygon.class);
					for (int i = 0; i < polygonTmp.getFeatures().length; i++) {
						String type = path.substring(path.lastIndexOf('\\')+1, path.lastIndexOf('_'));
						polygonTmp.getFeatures()[i].setType(FeatureEnum.valueOf(type));
						polygones.add(new Polygone(polygonTmp.getFeatures()[i]));
					}
				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		lignes.sort(null);
		polygones.sort(null);
		calculeValeurs();
	}
	
	private void calculeValeurs () {
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		for (int i = 0; i < lignes.size(); i++) {
			for (int j = 0; j < lignes.get(i).getCoordonnees().length; j++) {
				x.add(lignes.get(i).getCoordonnees()[j][0]);
				y.add(lignes.get(i).getCoordonnees()[j][1]);
			}
		}
		for (int i = 0; i < polygones.size(); i++) {
			for (int j = 0; j < polygones.get(i).getCoordonnees().length; j++) {
				for (int k = 0; k < polygones.get(i).getCoordonnees()[j].length; k++) {
					x.add(polygones.get(i).getCoordonnees()[j][k][0]);
					y.add(polygones.get(i).getCoordonnees()[j][k][1]);
				}
			}
		}
		x.sort(null);
		y.sort(null);
		int size = x.size();
		for (int i = 0; i <size/10; i++) {
			try{
			x.remove(0);
			x.remove(x.size()-1);
			y.remove(0);
			y.remove(y.size()-1);
			} catch (Exception e) {
			}
		}
		maxX = x.get(x.size()-1);
		minX = x.get(0);
		ecartX = maxX-minX;
		maxY = y.get(y.size()-1);
		minY = y.get(0);
		ecartY = maxY-minY;
		zoom = 500;
	}
	
	public void adapteCarte() {
		ratio = Math.max((ecartX+ecartY)/(zoom*2), (ecartX+ecartY)/(zoom*2));
		origineX = (ecartX / (ratio*2)) - (1900/2);
		origineY = (ecartY / (ratio*2)) - (1000/2);
		adapteLignes();
		adaptePolygones();
	}
	
	private void adapteLignes() {
		lignesTmp.clear();
		for (Ligne ligne : lignes) {
			lignesTmp.add(ligne.clone());
		}
		for (Ligne ligne : lignesTmp) {
			for (int i = 0; i < ligne.getCoordonnees().length; i++) {
				ligne.getCoordonnees()[i][0] -= minX;
				ligne.getCoordonnees()[i][0] /= ratio;
				ligne.getCoordonnees()[i][0] -= origineX;
				ligne.getCoordonnees()[i][1] -= minY;
				ligne.getCoordonnees()[i][1] = ecartY - ligne.getCoordonnees()[i][1];
				ligne.getCoordonnees()[i][1] /= ratio;
				ligne.getCoordonnees()[i][1] -= origineY;
			}
			
		}
	}
	private void adaptePolygones() {
		polygonesTmp.clear();
		for (Polygone polygone : polygones) {
			polygonesTmp.add(polygone.clone());
		}
		for (Polygone polygone : polygonesTmp) {
			for (int i = 0; i < polygone.getCoordonnees().length; i++) {
				for (int j = 0; j < polygone.getCoordonnees()[i].length; j++) {
					polygone.getCoordonnees()[i][j][0] -= minX;
					polygone.getCoordonnees()[i][j][0] /= ratio;
					polygone.getCoordonnees()[i][j][0] -= origineX;
					polygone.getCoordonnees()[i][j][1] -= minY;
					polygone.getCoordonnees()[i][j][1] = ecartY - polygone.getCoordonnees()[i][j][1];
					polygone.getCoordonnees()[i][j][1] /= ratio;
					polygone.getCoordonnees()[i][j][1] -= origineY;
				}
			}
		}
	}
	
	public void addZoom(int zoom) {
		this.zoom += zoom;
	}
	public int getZoom() {
		return zoom;
	}

	public List<Ligne> getLignesTmp() {
		return lignesTmp;
	}

	public List<Polygone> getPolygonesTmp() {
		return polygonesTmp;
	}
}
