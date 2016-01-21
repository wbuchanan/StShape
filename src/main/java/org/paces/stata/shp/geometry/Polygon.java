package org.paces.stata.shp.geometry;

import org.paces.stata.shp.BoundingBox;
import org.paces.stata.shp.ShapeFileReader;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by billy on 1/12/16.
 */
public class Polygon {

	private Integer recordNumber;
	private Integer recordLength;
	private BoundingBox bbox;
	private Integer numberParts;
	private Integer numberPoints;
	private List<Integer> parts;
	private List<Point> points;

	public Polygon(ByteBuffer x) {
		x.order(ShapeFileReader.LSF);
		this.bbox = new BoundingBox(x, 4);
		this.numberParts = x.getInt();
		this.numberPoints = x.getInt();
		for(int i = 0; i < this.numberParts; i++) {
			parts.add(i, x.getInt());
		}
	}

	public void setPolygonPoints(ByteBuffer polyBuff, Integer makePoints) {
		for(int i = 0; i < makePoints; i++) {
			points.add(i, new Point(polyBuff.getDouble(), polyBuff.getDouble()));
		}
	}


}
