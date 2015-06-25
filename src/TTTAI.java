import java.awt.Point;

public class TTTAI {
	private final int board[][];
	Point bestPoint = new Point();

	public TTTAI(int[][] board) {
		this.board = board;

	}

	public Point doing() {
		minmax(2, true);
		return bestPoint;
	}

	public int numOfWin(int what) {
		int total = 0;
		int enamy = what == 1 ? 2 : 1;

		int check[] = new int[4];

		for (int i = 0; i < 3; i++) {
			if (board[i][i] != enamy)
				check[2]++;
			if (board[2 - i][i] != enamy)
				check[3]++;

			for (int j = 0; j < 3; j++) {
				if (board[i][j] != enamy)
					check[0]++;

				if (board[j][i] != enamy)
					check[1]++;
			}

			if (check[0] == 0 || check[1] == 0)
				return Integer.MIN_VALUE;
			if (check[0] == 3)
				total++;
			if (check[1] == 3)
				total++;

			check[0] = check[1] = 0;
		}

		if (check[2] == 0 || check[3] == 0)
			return Integer.MIN_VALUE;

		if (check[2] == 3)
			total++;
		if (check[3] == 3)
			total++;

		return total;
	}

	public int minmax(int depth, boolean isMax) {
		int x = 0, y = 0;

		if (depth == 0) {
			int a = numOfWin(2);
			int b = numOfWin(1);
			if (b == Integer.MIN_VALUE)
				return Integer.MAX_VALUE;
			else if (a == Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
			else
				return a - b;
		}

		if (isMax) {
			int bestValue = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0) {
						board[i][j] = 2;
						int val = minmax(depth - 1, false);

						if (bestValue < val) {
							bestValue = val;
							x = j;
							y = i;
						}
						board[i][j] = 0;
					}
				}
			}

			bestPoint = new Point(x,y);
			return bestValue;
		} else {
			int bestValue = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0) {
						board[i][j] = 1;
						int val = minmax(depth - 1, true);
						if (bestValue > val) {
							bestValue = val;
						}
						board[i][j] = 0;
					}
				}
			}
			
			return bestValue;
		}

	}

}
