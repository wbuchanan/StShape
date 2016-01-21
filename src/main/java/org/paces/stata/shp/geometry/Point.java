package org.paces.stata.shp.geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * Created by billy on 1/12/16.
 */
public class Point {

	/**
	 * Private members used to store the latitude and longitude values
	 */
	private final Double x, y;

	private Double m = null;
	private Double z = null;

	public Point(List<byte[]> PointBytes) {

		this.x = ByteBuffer.wrap(PointBytes.get(0)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y = ByteBuffer.wrap(PointBytes.get(1)).order(ByteOrder.LITTLE_ENDIAN).getDouble();

	}

	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getLat() {
		return this.y;
	}

	public Double getLon() {
		return this.x;
	}

	public Double[] getCoordinates() {
		Double[] coord = new Double[2];
		coord[0] = this.x;
		coord[1] = this.y;
		return coord;
	}

	/**
	 * Method that allows user to specify whether the values are returned as
	 * latitude/longitude or longitude/latitude
	 * @param latlon Boolean if true will return the pair as latitude/longitude
	 * @return A two element array of doubles
	 */
	public Double[] getCoordinates(Boolean latlon) {
		Double[] coord = new Double[2];
		if (latlon) {
			coord[0] = this.y;
			coord[1] = this.x;
		} else {
			coord = getCoordinates();
		}
		return coord;
	}

	public String getPrintCoordinates() {
		return String.valueOf(this.x) + ", " + String.valueOf(this.y);
	}

	public String getPrintCoordinates(Boolean latlon) {
		if (latlon) return String.valueOf(this.y) + ", " + String.valueOf(this.x);
		else return getPrintCoordinates();
	}

}
