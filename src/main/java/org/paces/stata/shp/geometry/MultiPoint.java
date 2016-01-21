package org.paces.stata.shp.geometry;

import org.paces.stata.shp.BoundingBox;
import org.paces.stata.shp.ShapeFileReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 1/12/16.
 */
public class MultiPoint {

	private BoundingBox bbox;
	private Integer nPoints;
	private List<Point> points = new ArrayList<Point>();

	private Double z = null;
	private Double m = null;




	public MultiPoint(ByteBuffer multiPoints) {
		multiPoints.order(ShapeFileReader.LSF);
		this.bbox = new BoundingBox(multiPoints, 4);
		this.nPoints = multiPoints.getInt();
	}

	public void setPoints(ByteBuffer bufferPoints, Integer numberOfPoints) {
		for(int i = 0; i < numberOfPoints; i++) {
			points.add(i, new Point(bufferPoints));
		}
	}


	public BoundingBox getBoundingBox() {
		return this.bbox;
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
