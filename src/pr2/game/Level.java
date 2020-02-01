package pr2.game;

public enum Level {

	EASY(4, 2, 1, 0.3, 0.2, 3, 0.5, 1), HARD(8, 4, 2, 0.4, 0.3, 2, 0.2, 2), INSANE(12, 4, 3, 0.5, 0.5, 1, 0.1, 3);

	private int numRegularAliens;
	private int numDestroyerAliens;
	private int numSmartBombs;
	private int numCyclesToMoveOneCell;
	private double ovniFrequency;
	private double shootFrequency;
	private int numRowsOfRegularAliens;
	private double turnExplodeFrequency = 0.05; // actualmente no depende del nivel
	private double turnSmartBombFrequency;

	private double numMovesSmartBomb=3;
	
	private Level(int numRegularAliens, int numDestroyerAliens, int numSmartBombs, double turnSmartBombFrequency,
			double shootFrequency, int numCyclesToMoveOneCell, double ovniFrequency, int numRowsOfRegularAliens) {
		this.numRegularAliens = numRegularAliens;
		this.numDestroyerAliens = numDestroyerAliens;
		this.numSmartBombs = numSmartBombs;
		this.turnSmartBombFrequency = turnSmartBombFrequency;
		this.shootFrequency = shootFrequency;
		this.numCyclesToMoveOneCell = numCyclesToMoveOneCell;
		this.ovniFrequency = ovniFrequency;
		this.numRowsOfRegularAliens = numRowsOfRegularAliens;
	}

	public int getNumRegularAliens() {
		return numRegularAliens;
	}

	public int getNumDestroyerAliens() {
		return numDestroyerAliens;
	}

	public Double getShootFrequency() {
		return shootFrequency;
	}

	public int getNumCyclesToMoveOneCell() {
		return numCyclesToMoveOneCell;
	}

	public Double getOvniFrequency() {
		return ovniFrequency;
	}

	public int getNumRowsOfRegularAliens() {
		return numRowsOfRegularAliens;
	}

	public int getNumRegularAliensPerRow() {
		return numRegularAliens / numRowsOfRegularAliens;
	}

	public int getNumDestroyerAliensPerRow() {
		return getNumDestroyerAliens();
	}

	public static Level fromParam(String param) {
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(param))
				return level;
		return EASY;
	}

	public double getTurnExplodeFrequency() {
		return turnExplodeFrequency;
	}

	public static Level parse(String string) {
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(string))
				return level;
		return null;
	}

	public int getNumSmartBombs() {
		return numSmartBombs;
	}

	public double getTurnSmartBombFrequency() {
		return turnSmartBombFrequency;
	}

	public double getNumMovesSmartBomb() {
		double percent=100/numMovesSmartBomb;
		return percent/100;
	}
}
