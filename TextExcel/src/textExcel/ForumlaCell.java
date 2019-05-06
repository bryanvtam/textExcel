package textExcel;

import java.util.ArrayList;

//========================================
//Bryan Tam
//2nd Period
//========================================
public class ForumlaCell extends RealCell implements Cell{

	private String contents;
	private double value;
	private Spreadsheet sheet;
	
	
	public ForumlaCell(String userinput) {
		super(userinput);
		this.contents = super.getContents();
		//sheet = sheetcopy;
	}
	//
	public String abbreviatedCellText() {
		return (getValue() + "          ").substring(0, 10) ;
	}
	
	public String fullCellText() {
		return contents;
	}
	
}
