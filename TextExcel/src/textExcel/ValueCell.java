package textExcel;
//========================================
//Bryan Tam
//2nd Period
//========================================
public class ValueCell extends RealCell implements Cell{

	
	public ValueCell(String userinput) {
		//super(userinput.split(" ")[2]); //splits the command EX. "A1 = 213", sends "213" into the real cell constructor
		super(userinput);
	}
}
