package tw.org.sekainohane.common.progresser;

import tw.org.sekainohane.common.logger.Log;

public class Progress {
	private final int total;
	private int left;
	private double precentage;
	
	public Progress(int total) {
		this.total = total;
		this.left = total;
		this.precentage = 100;
		Log.info("Process running...");
		System.out.println("0   10   20   30   40   50   60   70   80   90   00");
		System.out.println("|----|----|----|----|----|----|----|----|----|----|");
		System.out.print("|");
	}
	
	public void treated(final int treated) {
		if (treated > 0) {
			left -= treated;
			final double leftPercentage = ((double) left / total) * 100;
			if (precentage - leftPercentage >= 2) { // 每次進度前進2%時就log出來
				System.out.print("|");
				precentage = leftPercentage;
			}
		}
	}
	
	public void done() {
		for (int i = 0; i < (precentage / 2); i++) {
			System.out.print("|");
		}
		System.out.println();
		Log.info("Process done.");
	}
}
