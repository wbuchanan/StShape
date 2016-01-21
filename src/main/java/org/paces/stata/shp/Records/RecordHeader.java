package org.paces.stata.shp.Records;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class RecordHeader {

	private static final ByteOrder BO = ByteOrder.BIG_ENDIAN;
	private Integer recordNumber;
	private Integer record16bitWords;
	private Integer recordLength;
	private Integer soffset, eoffset;

	public RecordHeader(ByteBuffer x, Integer soffset) throws IOException {
		this.soffset = soffset;
		x.position(soffset);
		x.order(BO);
		this.recordNumber = x.getInt();
		this.record16bitWords = x.getInt();
		this.recordLength = this.record16bitWords * 2;
		this.eoffset = x.position();
	}

	public Integer getRecordNumber() {
		return this.recordNumber;
	}

	public Integer getRecordLength() {
		return this.recordLength;
	}

	public Integer getStartOffset() {
		return this.soffset;
	}

	public Integer getEndOffset() {
		return this.eoffset;
	}

 }
