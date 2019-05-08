package textExcel;

import java.util.ArrayList;

//========================================
//Bryan Tam
//2nd Period
//========================================
public class ForumlaCell extends RealCell implements Cell{

	private String contents;
	private double value;
	private Cell[][] Spreadsheet;
	
	
	public ForumlaCell(String userinput, Cell[][] sheet) {
		super(userinput);
		Spreadsheet = sheet;
	}

	public String abbreviatedCellText() {
		return (getValue() + "          ").substring(0, 10) ;
	}
	
	public String fullCellText() {
		return contents;
	}
	
	
	public double doMath(String number, String operation) {
		if((number.charAt(0)) >= 'A') {
			String temp = (Spreadsheet[number.charAt(0)][Integer.parseInt(number.substring(1))]).getValue();
		}
		
		
		return 0.0;
	}
}
