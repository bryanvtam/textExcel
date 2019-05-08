package textExcel;

import java.util.ArrayList;
import java.util.List;

// Update this file with your own code.
//===================================
//Bryan Tam 
//2nd Period
//===================================
public class Spreadsheet implements Grid
{
	//fields
	private Cell[][] Arrayspreadsheet;
	private int row;
	private int col;
	private Location loc;

// create a constructor for Spreadsheet
	public Spreadsheet(){
		row = 20; //sets field 'row' = 20
		col = 12; //sets field 'col' = 12
		//initializes a 2D array of 20 rows & 12 columns
		Arrayspreadsheet = new Cell[row][col]; //12 Alphabets and 20 #'s
		for(int rownum = 0; rownum < row; rownum++) { //turns all of the 2D array into empty cells
			for(int colnum = 0; colnum < col; colnum++) {
				Arrayspreadsheet[rownum][colnum] = new EmptyCell(); //sets inputed row and col to empty cell
			}
		}
	}
	
	@Override
	public String processCommand(String command)
	{
		if (command.contains("=") ==true){ //check if cell assignment
			loc = new SpreadsheetLocation(command.split(" ")[0]); // location by taking the first string in an assignment command
			//EX: 'a1 = 20', takes 'a1' and inputs it into the SpreadsheetLocation
			return cellAssignment(command); //calls the method for cell assignment & inputs full command
		}
		else { // if not cell assignment
			if (command.toLowerCase().contains("clear") == true) { //checks if there it is a clear method
				if(command.length() == 5) { // if only 'clear' call ClearGrid method
					loc = new SpreadsheetLocation(); 
					return clearGrid();
				}
				else { //if not clear grid then goes to clear cell method
					loc = new SpreadsheetLocation(command.split(" ")[1]); 
					return clearCell(command.split(" ")[1]);
				}
			}
			else{ //if not clear or cell assignment, must be cell inspection
				loc = new SpreadsheetLocation (command);
				return cellInspection(command);
			}
		}
	}
	
	public String clearCell(String inputedcell) {
		SpreadsheetLocation cellLocation = new SpreadsheetLocation(inputedcell); //creates a temporary cell location
		Arrayspreadsheet[cellLocation.getRow()][cellLocation.getCol()] = new EmptyCell(); //sets the inputed cell location contents to an empty cell clearing it
		return getGridText(); //reprints the grid with updated cells
	}
	
	
	public String clearGrid() { //clearing grid method
		for(int rownum = 0; rownum < row; rownum++) { //turns all of the 2D array into empty cells
			for(int colnum = 0; colnum < col; colnum++) {
				Arrayspreadsheet[rownum][colnum] = new EmptyCell(); //sets all to empty cells that have blank contents
			}
		}
		return getGridText(); //reprints the grid with updated cells
	}

	@Override
	public int getRows()
	{
		return row;
	}

	@Override
	public int getCols()
	{
		return col;
	}

	@Override
	public Cell getCell(Location loc) {
		return Arrayspreadsheet[loc.getRow()][loc.getCol()]; //returns the cell at the given/inputed location
	}
	
	public String getGridText()
	{
		String output = "";
		output += "   |";
		for(char c = 'A'; c < 'M'; c++) { //print out the column labels at the top
			output+= c + "         |"; //add each letter to the header
		}
		output+= "\n"; // next line after column header
		
		for (int rows = 0; rows < 20; rows++ ){
			String temp = (rows + 1) + "   "; //sets the name for the rows
			output+= temp.substring(0, 3) + "|";
			for(int cols = 0; cols < 12 ; cols++) {
				output += (Arrayspreadsheet[rows][cols].abbreviatedCellText()) + "|"; //adds the abbreviated text for what ever type of cell it is
				
			}
			output+= "\n"; //next row
		}
		return output;
	}
	
	public String cellInspection(String cellinput) { //returns the full text of what is in the cell at the inputed location/cell
		SpreadsheetLocation cellLoc = new SpreadsheetLocation(cellinput); //creates a new instance for the cellinput
		return getCell(cellLoc).fullCellText(); //calls fullText method of that instance of the cell
	}
	
	public String cellAssignment(String userinput) {
		//a1 = 20 ; a1 = "hi" ; a1 = 8.9%
		String value = ""; //holds the user-inputed assignment
		String cellLoc = userinput.split(" ")[0]; //cell location for all cell assignments
		//example "A1 = *****", Cell loc = A1, all locations come before the equal sign
		SpreadsheetLocation cellLocation = new SpreadsheetLocation(cellLoc); 
		//creates a temporary cell location 
		
		if(userinput.contains("\"") && userinput.contains("\"")) {//checks if it is a text cell by checking if there are quotation marks
			value = userinput.substring(userinput.indexOf("\"")+1, userinput.lastIndexOf("\""));
			//sets 'value' to the contents between the two quotations, not including the quotations
			Arrayspreadsheet[cellLocation.getRow()][cellLocation.getCol()] = new TextCell(value); 
			//sets the inputed cell location contents to a text cell with the inputed string value
		}
		else if(userinput.contains("(") && userinput.contains(")")){ //checks if it is a formula cell by checking if there are parenthesis 
			value = userinput.split(" ",3)[2];
			//sets 'value' to the formula including the parentheses  
			Arrayspreadsheet[cellLocation.getRow()][cellLocation.getCol()] = new ForumlaCell(value, Arrayspreadsheet); 		
			//sets the inputed cell location to a formula cell and passes in the formula set in 'value'
		}
		else { //if not a formula or a text cell
			if(userinput.contains("%")) { //checking if there is a '%', if it is a percent cell
				value = userinput.split(" ")[2];
				//sets 'value' to the contents as a string including the '%'
				Arrayspreadsheet[cellLocation.getRow()][cellLocation.getCol()] = new PercentCell(value); 		
				//sets the inputed cell location to a percent cell and passes in the string of the percent set in 'value'
			}
			else {
				value = userinput.split(" ")[2];
				Arrayspreadsheet[cellLocation.getRow()][cellLocation.getCol()] = new ValueCell(value); 		
				//sets the inputed cell location to a value cell and passes in the string of the number set in 'value'
			}
		}
		return getGridText(); // prints the grid with updated cell
	}

}
