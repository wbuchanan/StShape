package org.paces.stata.shp;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by billy on 1/12/16.
 */
public class ShapeFileReader {

	public static final ByteOrder MSF = ByteOrder.BIG_ENDIAN;
	public static final ByteOrder LSF = ByteOrder.LITTLE_ENDIAN;
	private MappedByteBuffer shapeFile;
	private ShapeFileHeader fileHeader;
	private List<Integer> intelems = new ArrayList<>();
	private List<Double> boundingBoxes = new ArrayList<>();
	private Map<Integer, List<?>> byteMap = new HashMap<>();

	public ShapeFileReader(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile(new File(args[0]), "rw");
			FileChannel fc = raf.getChannel();
			this.shapeFile = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());
			parseFileHeader(this.shapeFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ByteBuffer getShapeFile() {
		return this.shapeFile;
	}

	public void parseFileHeader(ByteBuffer shapeFileByteBuffer) {
		this.fileHeader = new ShapeFileHeader(shapeFileByteBuffer);
	}


	protected void setByteMapping() {

	}



}
