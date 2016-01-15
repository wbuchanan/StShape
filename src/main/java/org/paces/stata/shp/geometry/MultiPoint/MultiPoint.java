package org.paces.stata.shp.geometry.MultiPoint;

import org.paces.stata.shp.geometry.Point.Point;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 1/12/16.
 */
public class MultiPoint {

	private final Double x0, y0, x1, y1;

	private final Integer nPoints;

	private final List<Point> points = new ArrayList<Point>();

	public MultiPoint(List<List<byte[]>> MultiPoints) {
		this.x0 = ByteBuffer.wrap(MultiPoints.get(0).get(0)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y0 = ByteBuffer.wrap(MultiPoints.get(0).get(1)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.x1 = ByteBuffer.wrap(MultiPoints.get(0).get(2)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y1 = ByteBuffer.wrap(MultiPoints.get(0).get(3)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.nPoints = ByteBuffer.wrap(MultiPoints.get(0).get(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
		this.points.add(new Point(MultiPoints.get(1)));
	}

	public Double[] getBoundingBox() {
		return new Double[] {this.x0, this.y0, this.x1, this.y1};
	}

	public Integer getNumPoints() {
		return this.nPoints;
	}

	public List<Point> getListPoints() {
		return this.points;
	}

	public Double[][] getCoordinates() {
		Double[][] retPoints = new Double[this.nPoints][2];
		for(int i = 0; i < this.nPoints; i++) {
			retPoints[i] = new Double[]{ this.points.get(i).getLat(),
										 this.points.get(i).getLon() };
		}
		return retPoints;
	}

}
