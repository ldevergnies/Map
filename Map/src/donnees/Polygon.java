package donnees;

import formatage.FeatureEnum;

public class Polygon implements Cloneable {
	
	public Polygon clone() {
		Polygon o = null;
		try {
			o = (Polygon) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	private Features[] features;

	public Polygon(Features[] features) {
		this.features = features;
	}

	public Features[] getFeatures() {
		return features;
	}
	
	/* Classe interne Features */
	public class Features implements Comparable<Features> {
		private FeatureEnum type;
		private Geometry geometry;
		private Properties properties;
		
		public double[][][] getCoordinates() {
			return geometry.coordinates;
		}
		
		public int getZ_order() {
			return properties.z_order;
		}
		
		public FeatureEnum getType() {
			return type;
		}

		public void setType(FeatureEnum type) {
			this.type = type;
		}
		
		public int compareTo(Features o) {
			if (properties.z_order < o.properties.z_order) {
				return -1;
			}
			else if (properties.z_order > o.properties.z_order) {
				return 1;
			}
			return 0;
		}
	}
	
	/* Classe interne Geometry */
	public class Geometry {
		public double[][][] coordinates;
	}
	
	/* Classe interne Properties */
	public class Properties {
		public int z_order;
	}
}
