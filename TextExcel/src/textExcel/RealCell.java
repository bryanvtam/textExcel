package textExcel;
//========================================
//Bryan Tam
//2nd Period
//========================================
public class RealCell implements Cell{
	
	private String contents;
	private double value;
	
	public RealCell(String userinput) { //real cell constructor for value cell
		 
		contents = userinput; //sets what ever the user inputed as the string contents
		
		if(userinput.contains("(") && userinput.contains(")")) {
			String temp = userinput.substring(1, userinput.indexOf(")"));
			String[] r = temp.split(" ");
			for(int c = 0; c < r.length; c+=2) {
				System.out.print (c +" " + (c+1));
				System.out.println();
				value = doMath((Integer.parseInt(r[c])), r[c+1]);
			}
		}
		else if( userinput.contains("%")) {
			value = (Double.parseDouble(userinput.substring(0, userinput.indexOf("%"))))/100;
		}
		else {
			value = Double.parseDouble(userinput);
		}
	}
	
	public String abbreviatedCellText() { // text for spreadsheet cell display, must be exactly length 10
		String tempcontents = (Double.parseDouble(contents)) + "          "; //adds 10 spaces to a temporary string 
		return (tempcontents.substring(0, 10)); //returns the first 10 characters in the temporary string, including spaces
	}

	public String fullCellText() {// text for individual cell inspection, not truncated or padded
		return contents;
	}
	public String getContents() { //returns contents in string "contents"
		return contents;
	}
	public double getValue() { //returns value in double for "value"
		return value;
	}
	
	public double doMath(int number, String operation) {
		double answer = 0;
		if(operation == "+") {
			answer += number;
		}
		else if(operation == "-") {
			answer -= number;
		}
		else if(operation == "*") {
			answer *= number;
		}
		else if (operation =="/") {
			answer /= number;
		}
		
		return answer;
	}
}
