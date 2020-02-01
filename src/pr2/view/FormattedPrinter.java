package pr2.view;


import pr2.game.Game;
import pr2.game.util.MyStringUtils;

public class FormattedPrinter extends GamePrinter {

	Game game;
	int numRows;
	int numCols;
	String[][] board;
	final String space = " ";

	public FormattedPrinter(Game game, int fila, int columna) {
		this.game = game;
		numCols = columna;
		numRows = fila;
	}

	public FormattedPrinter() {
	}

	private void encodeGame() {
		board = new String[numRows][numCols];

		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				board[x][y] = game.positionToString(x, y);
			}
		}
	}

	public String toString() {
		encodeGame();

		int cellSize = 6;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";

		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (numCols * (cellSize + 1) + 1));
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(game.infoToString());
		str.append(lineDelimiter);

		for (int i = 0; i < numRows; i++) {
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < numCols; j++) {
				if (board[i][j] == null) {
					str.append(MyStringUtils.centre("", cellSize)).append(vDelimiter);
				} else {
					str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}
}
