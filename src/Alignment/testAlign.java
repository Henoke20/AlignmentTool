package Alignment;

public class testAlign {

	public static void main(String[] args) {
		String A = "soften"; // goes on the left of table
		String B = "bestoftimes"; // goes on top of table		
		
		// fill the table & calculate values
		LocalAlign lAligner = LocalAlign.GetLocalAlignInstance();
		lAligner.FillTable(7,10,5, B, A);
		
		//GlobalAlign gAligner = GlobalAlign.GetGlobalAlignInstance();
		//gAligner.FillTable(1,3,1, B, A);
	}

}
