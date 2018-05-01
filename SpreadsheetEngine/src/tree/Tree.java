package tree;

import cell.CellType;
import spreadsheet.Column;
import spreadsheet.Position;
import spreadsheet.Row;
import spreadsheet.Spreadsheet;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Tree implements IBinaryTree, ITypedTree, INumberNodeSupport, ITimestampNodeSupport, IOperationNodeSupport, IReferenceNodeSupport {
	private Tree left;
	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	private Tree right;
	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	private NodeType type;
	public NodeType getType() {
		return type;
	}

	private CellType valueType;
	public CellType getValueType() {
		return valueType;
	}

	public void setValueType(CellType value) {
		this.valueType = value;
	}

	private BigDecimal numberValue;
	public BigDecimal getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(BigDecimal value) {
		this.numberValue = value;
		this.type = NodeType.VALUE;
	}

	private Long timestampValue;
	public Long getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(Long value) {
		this.timestampValue = value;
		this.type = NodeType.VALUE;
	}

	private Operation operation;
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation value) {
		this.operation = value;
		this.type = NodeType.OPERATION;
	}

	private Position reference;
	public Position getReference() {
		return reference;
	}

	public void setReference(Position value) {
		this.reference = value;
		this.type = NodeType.REFERENCE;
	}

	public static Tree createFromScanner(Scanner scanner, NodeType nodeType) throws RuntimeException {
		Tree result = new Tree();
		if (!scanner.hasNext()) {
			return result;
		}
		String part = scanner.next();

		if ((nodeType == NodeType.ANY || nodeType == NodeType.OPERATION) && part.length() == 1) {
			Operation operation = Operation.createFromCharacter(part.charAt(0));
			if (operation != null) {
				result.setOperation(operation);
				result.left = createFromScanner(scanner, NodeType.ANY);
				result.right = createFromScanner(scanner, NodeType.ANY);

				return result;
			}
		}

		if ((nodeType == NodeType.ANY || nodeType == NodeType.REFERENCE) && part.length() == 2) {
			Column column = Column.createFromCharacter(part.charAt(0));
			Row row = Row.createFromCharacter(part.charAt(1));
			if (column != null && row != null) {
				Position reference = new Position(column, row);
				result.setReference(reference);

				return result;
			}
		}

		if (nodeType == NodeType.ANY || nodeType == NodeType.VALUE) {
			try {
				DecimalFormat decimalFormat = new DecimalFormat();
				decimalFormat.setParseBigDecimal(true);
				BigDecimal doubleValue = new BigDecimal(part);
				result.setNumberValue(doubleValue);
			} catch(NumberFormatException numberFormatException) {
				try {
					Date daveValue = Spreadsheet.dateFormat.parse(part);
					result.setTimestampValue(daveValue.getTime());
				} catch (ParseException parseException) {
					throw new RuntimeException("A string literal is found in the formula: " + part);
				}
			}
		}

		return result;
	}
}
