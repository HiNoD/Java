package cell;

import spreadsheet.Position;
import tree.Tree;

public class CellFormula extends Cell {
	public CellFormula(Tree tree, Position position) {
		super(CellType.FORMULA, position);
		this.tree = tree;
	}

	private final Tree tree;
	public Tree getTree() {
		return tree;
	}
}
