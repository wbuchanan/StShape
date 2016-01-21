package org.paces.stata.shp.geometry;

import org.paces.stata.shp.BoundingBox;
import org.paces.stata.shp.Records.RecordHeader;
import org.paces.stata.shp.ShapeFileReader;

import java.io.IOException;
import java.io.RandomAccessFile;
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

	public Polygon(ShapeFileReader x) throws IOException {
		RandomAccessFile shapeFile = x.getShapeFile();

		RecordHeader record = new RecordHeader(shapeFile, (long) 100);
		this.recordNumber = record.getRecordNumber();
		this.recordLength = record.getRecordLength();
		readRecordMeta(shapeFile, record.getEndOffset());
		setIndices(shapeFile, this.numberParts);
	}

	public void setIndices(RandomAccessFile shapeFile, Integer nparts) throws IOException {
		for (int i = 0; i < nparts; i++) {
			byte[] idx = new byte[4];
			shapeFile.read(idx);
			this.parts.add(i, ByteBuffer.wrap(idx).order(LSF).getInt());
		}
	}

	@Override
	public void readRecordMeta(RandomAccessFile shapeFile, Long offset) throws IOException {
		shapeFile.seek(offset);
		byte[] xmin = new byte[8];
		byte[] ymin = new byte[8];
		byte[] xmax = new byte[8];
		byte[] ymax = new byte[8];
		byte[] numparts = new byte[4];
		byte[] numpoints = new byte[4];
		shapeFile.read(xmin);
		shapeFile.read(ymin);
		shapeFile.read(xmax);
		shapeFile.read(ymax);
		bbox = new BoundingBox(ByteBuffer.wrap(xmin).order(LSF).getDouble(),
				ByteBuffer.wrap(ymin).order(LSF).getDouble(),
				ByteBuffer.wrap(xmax).order(LSF).getDouble(),
				ByteBuffer.wrap(ymax).order(LSF).getDouble());
		shapeFile.read(numparts);
		shapeFile.read(numpoints);
		this.numberParts = ByteBuffer.wrap(numparts).order(LSF).getInt();
		this.numberPoints = ByteBuffer.wrap(numpoints).order(LSF).getInt();

	}

}
