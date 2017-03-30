package donnees;

import java.lang.reflect.Field;

import formatage.FeatureEnum;
import formatage.LineEnum;

public class Line implements Cloneable {
	private Features[] features;
	
	public Line clone() {
		Line o = null;
		try {
			o = (Line) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public Line () {
	}
	
	public Line (Features[] features) {
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

		public LineEnum getProperties() {
			Field[] fields = properties.getClass().getDeclaredFields();
			for (Field field : fields){
				String standard = "";
				try {
					standard = "";
					if (field.get(properties) instanceof String) {
						standard = (String) field.get(properties);
					}
					if (standard != null && !standard.equals("")) {
						return LineEnum.valueOf(standard);
					}
				} catch (Exception e) {
					System.out.println("Error at LineEnum : " +standard+ " does not exist!");
				}
			}
			return LineEnum.defaut;
		}
		
		public double[][] getCoordinates() {
			return geometry.coordinates;
		}

		public FeatureEnum getType() {
			return type;
		}

		public void setType(FeatureEnum type) {
			this.type = type;
		}
		
		public int getZ_order () {
			return properties.z_order;
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
		public double[][] coordinates;
	}

	/* Classe interne Properties */
	public class Properties {
		public int z_order;
		public String aerialway;
		public String aeroway;
		public String amenity;
		public String barrier;
		public String boundary;
		public String building;
		public String craft;
		public String emergency;
		public String geological;
		public String highway;
		public String historic;
		public String landuse;
		public String leisure;
		public String man_made;
		public String military;
		public String natural;
		public String office;
		public String place;
		public String power;
		public String public_transport;
		public String railway;
		public String route;
		public String shop;
		public String sport;
		public String tourism;
		public String waterway;
	}
}



