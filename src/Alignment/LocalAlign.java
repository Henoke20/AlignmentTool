package Alignment;
public class LocalAlign extends AbstractAlignment {
	private static LocalAlign aligner;
	private int maxX;
	private int maxY;
	
	private LocalAlign() {}
	
	public static LocalAlign GetLocalAlignInstance() {
		if (aligner == null) {
			aligner = new LocalAlign();
		}
		return aligner;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AbstractAlignment#FillRestOfTable(int[][])
	 */
	protected void FillRestOfTable(int[][] table) {
		maxX = 0;
		maxY = 0;
		int maxVal = 0;
		for (int y = 1; y< table[0].length; y++) {
			for (int x = 1; x<table.length; x++) {
				table[x][y] = Math.max(0, getMaxValue(getHorizontalString().charAt(x-1), 
						getVerticalString().charAt(y-1), table[x-1][y-1],
						table[x][y-1],
						table[x-1][y]));
				if (table[x][y] >= maxVal) {
					maxVal = table[x][y];
					maxX = x;
					maxY = y;
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see AbstractAlignment#PrepareTable(int[][])
	 */
	protected void PrepareTable(int table[][]) {
		for (int x = 0; x< table.length; x++) {
			table[x][0] = 0;
		}
		for (int y = 0; y<table[0].length; y++) {
			table[0][y] = 0;
		}
	}

	@Override
	protected void retrace(int[][] table) {
		int x = maxX;
		int y = maxY;
		
		// trace the alignment
		
		char direction; // points to direction where the current value
						// in retrace came from
		int val = table[maxX][maxY];
		while (val != 0) {
			direction = TraceStep(table,x,y);
			
			if (direction == 'd') {
				if (table[x][y]==0) break;
				setAlignedVerticalString(getVerticalString().charAt(y-1));
				setAlignedHorizontalString(getHorizontalString().charAt(x-1));
				x -=1;
				y -=1;
			} else if (direction == 'u') {
				if (table[x][y]==0) break;
				setAlignedHorizontalString('-');
				setAlignedVerticalString(getVerticalString().charAt(y-1));
				y-=1;
			} else if (direction == 'l') {
				if (table[x][y]==0) break;
				setAlignedHorizontalString(getHorizontalString().charAt(x-1));
				setAlignedVerticalString('-');
				x-=1;
			}
			val = table[x][y];
			System.out.println(val);
		}
		System.out.println(getAlignedVerticalString().toString());
		System.out.println(getAlignedHorizontalString().toString());
		
	}
	
	

}
