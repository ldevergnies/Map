package formatage;

import java.awt.Color;

public enum FeatureEnum {
	aerialway(new Color(205, 201, 196)),
	aeroway(new Color(189, 184, 177)),
	amenity(new Color(173, 165, 157)),
	barrier(new Color(152, 143, 134)),
	boundary(new Color(124, 115, 105)),
	building(new Color(100, 90, 80)),
	craft(new Color(202, 196, 194)),
	emergency(new Color(165, 153, 151)),
	geological(new Color(148, 134, 131)),
	highway(new Color(123, 110, 106)),
	historic(new Color(98, 82, 72)),
	landuse(new Color(55, 43, 39)),
	leisure(new Color(200, 201, 195)),
	man_made(new Color(181, 182, 176)),
	military(new Color(157, 157, 150)),
	natural(new Color(135, 136, 127)),
	office(new Color(110, 111, 100)),
	place(new Color(90, 91, 81)),
	power(new Color(31, 33, 28)),
	public_transport(new Color(204, 204, 204)),
	railway(new Color(186, 187, 188)),
	route(new Color(169, 170, 171)),
	shop(new Color(147, 148, 149)),
	sport(new Color(118, 122, 125)),
	tourism(new Color(86, 89, 92)),
	waterway(new Color(165, 184, 201));
	
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	private FeatureEnum(Color c) {
		this.color = c;
	}
}
