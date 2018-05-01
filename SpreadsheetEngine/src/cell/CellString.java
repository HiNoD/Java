package cell;

import spreadsheet.Position;

public class CellString extends Cell {
	public CellString(String value, Position position) {
		super(CellType.STRING, position);
		this.value = value;
	}

	private final String value;
	public String getValue() {
		return value;
	}
}
