package org.paces.stata.shp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by billy on 1/21/16.
 */
public class ShapeFileHeader {

	private Integer fileCode;
	private Integer fileLength;
	private Integer version;
	private Integer shapeType;
	private String shapeTypeString;
	private BoundingBox boundingBox;
	private Boolean normalizePoints;
	private final Map<Integer, String> shapeTypes = new HashMap<>();

	ShapeFileHeader(ByteBuffer shapeFileByteBuffer) {
		this.shapeTypes.put(0, "Null");
		this.shapeTypes.put(1, "Point");
		this.shapeTypes.put(3, "PolyLine");
		this.shapeTypes.put(5, "Polygon");
		this.shapeTypes.put(8, "MultiPoint");
		this.shapeTypes.put(11, "PointZ");
		this.shapeTypes.put(13, "PolyLineZ");
		this.shapeTypes.put(15, "PolygonZ");
		this.shapeTypes.put(18, "MultiPointZ");
		this.shapeTypes.put(21, "PointM");
		this.shapeTypes.put(23, "PolyLineM");
		this.shapeTypes.put(25, "PolygonM");
		this.shapeTypes.put(28, "MultiPointM");
		this.shapeTypes.put(31, "MultiPatch");

		Integer pos = shapeFileByteBuffer.position();
		shapeFileByteBuffer.order(ByteOrder.BIG_ENDIAN);
		this.fileCode = shapeFileByteBuffer.getInt();
		shapeFileByteBuffer.position(shapeFileByteBuffer.position() + 20);
		this.fileLength = shapeFileByteBuffer.getInt();
		shapeFileByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		this.version = shapeFileByteBuffer.getInt();
		this.shapeType = shapeFileByteBuffer.getInt();
		this.boundingBox = new BoundingBox(shapeFileByteBuffer, 8);
	}

	public Integer getFileCode() {
		return this.fileCode;
	}

	public Integer getFileLength() {
		return this.fileLength;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Integer getShapeType() {
		return this.shapeType;
	}

	public String getShapeTypeString() {
		return this.shapeTypes.get(getShapeType());
	}

	public BoundingBox getBoundingBox() {
		return this.boundingBox;
	}


}
