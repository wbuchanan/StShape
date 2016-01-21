package org.paces.stata.shp;

import org.paces.stata.shp.geometry.Point;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class BoundingBox {

	private List<Double> box;

	public BoundingBox(Double xmin, Double ymin, Double xmax, Double ymax) {
		this.box.add(0, xmin);
		this.box.add(1, ymin);
		this.box.add(2, xmax);
		this.box.add(3, ymax);
	}

	BoundingBox(ByteBuffer bytes, Integer pts) {
		bytes.order(ByteOrder.LITTLE_ENDIAN);
		for(int i = 0; i < pts; i++) {
			this.box.add(i, bytes.getDouble());
		}
	}

	BoundingBox(List<Point> points) {
		this.box.add(0, points.get(0).getLon());
		this.box.add(1, points.get(0).getLat());
		this.box.add(2, points.get(1).getLon());
		this.box.add(3, points.get(1).getLat());
	}

	public List<Double> getBox() {
		return this.box;
	}

	public List<Double> getMin() {
		List<Double> retval = new ArrayList<Double>();
		retval.add(0, this.box.get(0));
		retval.add(1, this.box.get(1));
		return retval;
	}

	public List<Double> getMax() {
		List<Double> retval = new ArrayList<Double>();
		retval.add(0, this.box.get(2));
		retval.add(1, this.box.get(3));
		return retval;

	}

	public Double[] getBoxArray() {
		return new Double[]{ this.box.get(0), this.box.get(1), this.box.get(2),
				this.box.get(3) };
	}

	public Double[] getMinArray() {
		return new Double[]{ this.box.get(0), this.box.get(1) };
	}

	public Double[] getMaxArray() {
		return new Double[] { this.box.get(2), this.box.get(3) };
	}

	public List<Point> getPoints() {
		List<Point> retval = new ArrayList<>();
		retval.add(0, new Point(this.box.get(0), this.box.get(1)));
		retval.add(1, new Point(this.box.get(0), this.box.get(1)));
		return retval;
	}

}
