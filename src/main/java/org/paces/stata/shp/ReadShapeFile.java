package org.paces.stata.shp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 1/12/16.
 */
public class ReadShapeFile {

	private final Map<Integer, String> shapeTypes = new HashMap<Integer, String>();

	public ReadShapeFile(String[] args) {
		setTypes();
	}

	void setTypes() {
		this.shapeTypes.put(0, "Null Shape");
		this.shapeTypes.put(1, "org.paces.stata.shp.geometry.Point.Point");
		this.shapeTypes.put(3, "org.paces.stata.shp.geometry.PolyLine.PolyLine");
		this.shapeTypes.put(5, "org.paces.stata.shp.geometry.Polygon.Polygon");
		this.shapeTypes.put(8, "org.paces.stata.shp.geometry.MultiPoint.MultiPoint");
		this.shapeTypes.put(11, "org.paces.stata.shp.geometry.Point.PointZ");
		this.shapeTypes.put(13, "org.paces.stata.shp.geometry.PolyLine.PolyLineZ");
		this.shapeTypes.put(15, "org.paces.stata.shp.geometry.Polygon.PolygonZ");
		this.shapeTypes.put(18, "org.paces.stata.shp.geometry.MultiPoint.MultiPointZ");
		this.shapeTypes.put(21, "org.paces.stata.shp.geometry.Point.PointM");
		this.shapeTypes.put(23, "org.paces.stata.shp.geometry.PolyLine.PolyLineM");
		this.shapeTypes.put(25, "org.paces.stata.shp.geometry.Polygon.PolygonM");
		this.shapeTypes.put(28, "org.paces.stata.shp.geometry.MultiPoint.MultiPointM");
		this.shapeTypes.put(31, "org.paces.stata.shp.geometry.MultiPatch");
	}


}
