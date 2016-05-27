package Alignment;

/*
 * The following abstract class defines the shared characteristics of
 * the Smith-Waterman and Needleman-wunch 
 */
public abstract class AbstractAlignment {
	private int misMatchScore;
	private int matchScore;
	private int[][] table;
	private String verticalString;
	private String horizontalString;
	private int gapPenalty;
	StringBuilder alignedVerticalString;

	StringBuilder getAlignedVerticalString() {
		
		return alignedVerticalString;
	}

	 void setAlignedVerticalString(char ch) {
		if (alignedVerticalString != null) {
			alignedVerticalString.insert(0, Character.toString(ch));			
		} else {
			alignedVerticalString = new StringBuilder(Character.toString(ch));
			
		}
	}

	 StringBuilder getAlignedHorizontalString() {
		return alignedHorizontalString;
	}

	void setAlignedHorizontalString(char ch) {
		if (alignedHorizontalString!=null) {
			alignedHorizontalString.insert(0, Character.toString(ch));
		
		} else {
			alignedHorizontalString = new StringBuilder(Character.toString(ch));
		}
	}

	 StringBuilder alignedHorizontalString;
	
	public int getGapPenalty() {
		return gapPenalty;
	}

	public int getMisMatchScore() {
		return misMatchScore;
	}

	public int getMatchScore() {
		return matchScore;
	}

	public int[][] getTable() {
		return table;
	}

	public String getVerticalString() {
		return verticalString;
	}

	public String getHorizontalString() {
		return horizontalString;
	}
	
	public void FillTable(int gapPenalty,int matchScore,
			int misMatchScore, String A, String B) {
		this.horizontalString = A;
		this.verticalString = B;
		this.gapPenalty = gapPenalty;
		this.matchScore = matchScore;
		this.misMatchScore = misMatchScore;
		this.table = new int[A.length()+1][B.length()+1];

		PrepareTable(table);
		FillRestOfTable(table);
		
		// prevent appending to any previous alignments
		if (alignedVerticalString != null) {
			alignedVerticalString = null;
		}
		if (alignedHorizontalString != null) {
			alignedHorizontalString=null;
		}
		
		retrace(table);

		printTable(table);
	}
	
	protected int getMaxValue(char A, char B, int upLeft, int up, int left) {
		
		if (A == B) {
			upLeft += matchScore;
		} else {
			upLeft -= misMatchScore;
		}
		up -= gapPenalty;
		left -= gapPenalty;
		return Math.max(left,Math.max(upLeft, up));
	}
	
	protected void printTable(int[][] table) {
		// print top nucleotides
		System.out.printf("%8s","-");
		System.out.printf("%8s","-");
		for(int i = 0; i < getHorizontalString().length(); i++) {
			System.out.printf("%8s",getHorizontalString().charAt(i));
		}

		// new line to allow for printing of rest of table
		System.out.println("\n");
		System.out.printf("%8s","-");
		for (int row = 0; row < table[0].length; row++) {
			// print out table
			if (row != 0 ) {
				// first print out nucleotide on left
				System.out.printf("%8s",getVerticalString().charAt(row-1));
			}
			for (int col = 0; col < table.length; col++) {
				System.out.printf("%8s",table[col][row]);
			}
			System.out.println("\n");
		}
	}
	
	protected char TraceStep(int[][]table, int x, int y) {
		// refers to the values on the outside locations in the table with
		// respect to location x,y
		int diag = table[x-1][y-1];
		int up = table[x][y-1];
		int left = table[x-1][y];
		int val = table[x][y];
		
		if (getVerticalString().charAt(y-1) == getHorizontalString().charAt(x-1) || 
				(diag == (val+this.getMisMatchScore()))) {
			return 'd';
		} else if (up == (val+this.getGapPenalty())) {
			return 'u';
		} else {
			return 'l';
		}
		
	}
	protected abstract void FillRestOfTable(int[][] table);

	protected abstract void PrepareTable(int[][] table);
	
	protected abstract void retrace(int[][] table);
	
}
