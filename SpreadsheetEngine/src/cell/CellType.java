package cell;

public enum CellType {
	NUMBER("number"),
	STRING("string"),
	DATE("data"),
	FORMULA("formula");

	private final String string;

	CellType(final String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}
