package cell;

import spreadsheet.Position;

public class Cell implements IPositioned {
	Cell(CellType type, Position position) {
		this.type = type;
		this.position = position;
	}

	private final CellType type;
	public CellType getType() {
		return type;
	}

	private final Position position;
	public Position getPosition() {
		return position;
	}
}
