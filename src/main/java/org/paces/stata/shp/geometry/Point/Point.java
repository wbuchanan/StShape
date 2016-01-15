package org.paces.stata.shp.geometry.Point;

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

	public Point(List<byte[]> PointBytes) {

		this.x = ByteBuffer.wrap(PointBytes.get(0)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y = ByteBuffer.wrap(PointBytes.get(1)).order(ByteOrder.LITTLE_ENDIAN).getDouble();

	}

	public Double getLat() {
		return this.x;
	}

	public Double getLon() {
		return this.y;
	}
}
