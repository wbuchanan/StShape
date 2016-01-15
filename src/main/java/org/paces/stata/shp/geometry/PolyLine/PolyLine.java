package org.paces.stata.shp.geometry.PolyLine;

import org.paces.stata.shp.geometry.Point.Point;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 1/12/16.
 */
public class PolyLine {

	private final Double x0, y0, x1, y1;

	private final Integer nPoints, nParts;

	private final List<Point> points = new ArrayList<Point>();
	private final List<Integer> parts = new ArrayList<Integer>();

	public PolyLine(List<List<byte[]>> pline) {
		this.x0 = ByteBuffer.wrap(pline.get(0).get(0)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y0 = ByteBuffer.wrap(pline.get(0).get(1)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.x1 = ByteBuffer.wrap(pline.get(0).get(2)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.y1 = ByteBuffer.wrap(pline.get(0).get(3)).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		this.nParts = ByteBuffer.wrap(pline.get(0).get(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
		this.nPoints = ByteBuffer.wrap(pline.get(0).get(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();


	}

	public void setParts(List<byte[]> part) {
		for (int i = 0; i < part.size(); i++) {
			this.parts.add(i,
					ByteBuffer.wrap(part.get(i)).order(ByteOrder.LITTLE_ENDIAN).getInt());
		}
	}



}
