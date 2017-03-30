package formatage;

import donnees.Polygon;

public class Polygone implements Cloneable, Comparable<Polygone> {
	private FeatureEnum type;
	private double[][][] coordonnees;
	private int z_order;
	
	public Polygone (Polygon.Features feat) {
		this.type = feat.getType();
		this.coordonnees = feat.getCoordinates();
		this.z_order = feat.getZ_order();
	}
	
	public Polygone(FeatureEnum type, double[][][] coordonnees, int z_order) {
		this.type = type;
		this.coordonnees = coordonnees;
		this.z_order = z_order;
	}
	
	public Polygone clone() {
		Polygone o = null;
		try {
			o = (Polygone) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		double[][][] d = new double[coordonnees.length][][];
		for (int i = 0; i < d.length; i++) {
			d[i] = new double[coordonnees[i].length][];
			for (int j = 0; j < d[i].length; j++) {
				d[i][j] = new double[coordonnees[i][j].length];
				for (int k = 0; k < d[i][j].length; k++) {
					d[i][j][k] = coordonnees[i][j][k];
				}
			}
		}
		o.setCoordonnees(d);
		return o;
	}

	public double[][][] getCoordonnees() {
		return coordonnees;
	}
	
	public void setCoordonnees(double[][][] coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public FeatureEnum getType() {
		return type;
	}
	
	public int getZ_order() {
		return z_order;
	}

	public int compareTo(Polygone o) {
		if (z_order < o.z_order) {
			return -1;
		}
		else if (z_order > o.z_order) {
			return 1;
		}
		return 0;
	}
}
