package formatage;

import donnees.Line;

public class Ligne implements Cloneable, Comparable<Ligne> {
	private FeatureEnum type;
	private double[][] coordonnees;
	private int z_order;
	private LineEnum value;
	
	public Ligne(Line.Features feat) {
		this.type = feat.getType();
		this.coordonnees = feat.getCoordinates();
		this.z_order = feat.getZ_order();
		this.value = feat.getProperties();
	}
	
	public Ligne(FeatureEnum type, double[][] coordonnees, int z_order, LineEnum value) {
		this.type = type;
		this.coordonnees = coordonnees;
		this.z_order = z_order;
		this.value = value;
	}
	
	public Ligne clone() {
		Ligne o = null;
		try {
			o = (Ligne) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		double[][] d = new double[coordonnees.length][];
		for (int i = 0; i < d.length; i++) {
			d[i] = new double[coordonnees[i].length];
			for (int j = 0; j < d[i].length; j++) {
				d[i][j] = coordonnees[i][j];
			}
		}
		o.setCoordonnees(d);
		return o;
	}

	public void setCoordonnees(double[][] coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public FeatureEnum getType() {
		return type;
	}

	public double[][] getCoordonnees() {
		return coordonnees;
	}

	public int getZ_order() {
		return z_order;
	}

	public LineEnum getValue() {
		return value;
	}

	public int compareTo(Ligne o) {
		if (z_order < o.z_order) {
			return -1;
		}
		else if (z_order > o.z_order) {
			return 1;
		}
		return 0;
	}
}
