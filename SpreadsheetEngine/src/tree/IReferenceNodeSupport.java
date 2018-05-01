package tree;

import spreadsheet.Position;

interface IReferenceNodeSupport {
	Position getReference();
	void setReference(Position value);
}
