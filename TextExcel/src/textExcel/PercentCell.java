package textExcel;

public class PercentCell extends RealCell implements Cell {
	
	public PercentCell(String userinput) {
		super(userinput);
		//passes in the user input, which for example is "8.999%" including the percent sign
	}
	@Override
	public String abbreviatedCellText() {
		String temp = (super.getContents());
		if(temp.contains(".")) { //if my percentage has a decimal
			return(temp.substring(0, temp.indexOf("."))+ "%"+ "          ").substring(0, 10) ;
		}
		else { //no decimal for percentage
			return(temp + "          ").substring(0, 9) + "%";
		}
		
	}
	@Override
	public String fullCellText() {
		return Double.toString(super.getValue());
		
	}

}
