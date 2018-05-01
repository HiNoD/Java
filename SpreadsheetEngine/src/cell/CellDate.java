package cell;

import spreadsheet.Position;

import java.text.SimpleDateFormat;

public class CellDate extends Cell {
	public CellDate(long value, Position position) {
		super(CellType.DATE, position);
		this.value = value;
	}

	private final long value;
	public long getValue() {
		return value;
	}

	public String getStringValue(SimpleDateFormat dateFormat) {
		return dateFormat.format(value);
	}
}
