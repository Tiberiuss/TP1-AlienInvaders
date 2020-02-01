package pr2.game;

public enum Move {
	LEFT(-1), RIGHT(1);

	private double maxSquaresMove = 2;
	private double minSquaresMove = 2;
	private int direction;

	private Move(int direction) {
		this.direction = direction;
	}

	public static Move parse(String dir) {
		try {
			for (Move move : Move.values())
				if (Integer.parseInt(dir) == move.direction)
					return move;
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage() + "%n %n");
		}
		return null;
	}

	public double getMaxSquaresMove() {
		return maxSquaresMove;
	}

	public void setMaxSquaresMove(double maxSquaresMove) {
		this.maxSquaresMove = maxSquaresMove;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getMinSquaresMove() {
		return minSquaresMove;
	}

	public void setMinSquaresMove(double minSquaresMove) {
		this.minSquaresMove = minSquaresMove;
	}

}
