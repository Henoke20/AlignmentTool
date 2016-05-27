package Alignment;
public class GlobalAlign extends AbstractAlignment {
	private static GlobalAlign aligner;
	
	private GlobalAlign() {}
	
	/**
	 * Force only one instance of the GlobalAlignment class
	 * @return a GlobalAlign instance
	 */
	public static GlobalAlign GetGlobalAlignInstance() {
		if (aligner == null) {
			aligner = new GlobalAlign();
		}
		return aligner;
	}
	
	@Override
	/**
	 * Fill the rest of the table using the classic dynamic programming 
	 * algorithm for a global alignment. Remember x,y in the table refers
	 * to x-1, y-1 in the strings because the table is larger by 1 (gap
	 * penalty row, cols). 
	 */
	protected void FillRestOfTable(int[][] table) {
		// TODO Auto-generated method stub
		for (int y = 1; y< table[0].length; y++) {
			for (int x = 1; x<table.length; x++) {
				table[x][y] = getMaxValue(getHorizontalString().charAt(x-1), 
						getVerticalString().charAt(y-1), table[x-1][y-1],
						table[x][y-1],
						table[x-1][y]);
			}
		}
		
	}

	@Override
	/***
	 * In global alignment, Gap penalties are used when preparing the
	 * table.
	 */
	protected void PrepareTable(int[][] table) {
		// TODO Auto-generated method stub
		for (int x = 0; x< table.length; x++) {
			table[x][0] = x*this.getGapPenalty()*-1;
		}
		for (int y = 0; y<table[0].length; y++) {
			table[0][y] = y*this.getGapPenalty()*-1;
		}
		
	}

	@Override
	/**
	 * Global Alignment retraces until you reach the ends of one of the
	 * tables (therefore when x and y) reach 0. If the retrace ends the 
	 * end of the table (x = 0 or y = 0), just trace straight to 0,0. 
	 * End gap penalty. 
	 */
	protected void retrace(int[][] table) {
		// trace the alignment
		int x = table.length-1;
		int y = table[0].length-1;

		
		while (x != 0 && y != 0) {
			char direction = TraceStep(table,x,y);
			if (direction == 'd') {
				setAlignedVerticalString(getVerticalString().charAt(y-1));
				setAlignedHorizontalString(getHorizontalString().charAt(x-1));
				x -=1;
				y -=1;
			} else if (direction == 'u') {
				setAlignedHorizontalString('-');
				setAlignedVerticalString(getVerticalString().charAt(y-1));
				y-=1;
			} else if (direction == 'l') {
				setAlignedHorizontalString(getHorizontalString().charAt(x-1));
				setAlignedVerticalString('-');
				x-=1;
			}
		}
		
		
		// fill edge gaps if the retrace hits the end early
		// end at x=0,y=0
		if (x == 0) {
			while(y>=1) {
				setAlignedHorizontalString('-');
				setAlignedVerticalString(getVerticalString().charAt(y-1));
				y-=1;
			}
		} else if (y == 0) {
			while(x >=1) {
				setAlignedHorizontalString(getHorizontalString().charAt(x-1));
				setAlignedVerticalString('-');
				x-=1;
			}
		}
		System.out.println(getAlignedHorizontalString());
		System.out.println(getAlignedVerticalString());
		
	}
}
