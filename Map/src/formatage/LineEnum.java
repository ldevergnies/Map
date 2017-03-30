package formatage;

public enum LineEnum {
	// Highway
	motorway(4),
	motorway_link(4),
	trunk(3.5f),
	trunk_link(3.5f),
	primary(3),
	primary_link(3),
	secondary(2.5f),
	secondary_link(2.5f),
	tertiary(2),
	tertiary_link(2),
	unclassified(1.5f),
	residential(1),
	living_street(1),
	pedestrian(1),
	service(0.5f),
	raceway(0.5f),
	road(0.5f),
	construction(0.5f),
	virtual(0.5f),
	track(0),
	bus_guideway(0),
	escape(0),
	footway(0),
	bridleway(0),
	steps(0),
	path(0),
	cycleway(0),
	// Waterway
	river(2),
	canal(1.5f),
	stream(1),
	drain(0.5f),
	dam(0.5f),
	ditch(0f),
	fairway(0f),
	weir(0),
	// Defaut
	defaut(0);
	
	
	private float niveau;

	public float getNiveau() {
		return niveau;
	}

	private LineEnum(float i) {
		this.niveau = i;
	}
	
}
