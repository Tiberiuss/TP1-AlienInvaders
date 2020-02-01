package pr2.game.GameObjects;

import pr2.game.Game;

public abstract class AlienShip extends EnemyShip {

	protected int cyclesNextAlienMove;
	protected int dir;

	protected static int REMAINIG_ALIENS = 0;
	protected static boolean ALIENS_JUMP = false;
	protected static int ALIENS_ON_BORDER = 0;
	protected static int LEFT_BEHIND = 0; // Naves anteriores a la que tiene que saltar la fila
	protected static boolean IS_IN_FINAL_ROW = false;

	public AlienShip(Game game, int y, int x, int live) {
		super(game, y, x, live);
		cyclesNextAlienMove = game.getLevel().getNumCyclesToMoveOneCell();
		dir = -1; // left
		REMAINIG_ALIENS++;
	}

	public AlienShip() {
		super();
	}

	public static boolean allDead() {
		return (REMAINIG_ALIENS == 0) ? true : false;
	}

	public static void reset() {
		ALIENS_JUMP = false;
		IS_IN_FINAL_ROW = false;
		REMAINIG_ALIENS = 0;
		ALIENS_ON_BORDER = 0;
		LEFT_BEHIND = 0;
	}

	public static void set(boolean a, boolean b, int c, int d, int e) {
		ALIENS_JUMP = a;
		IS_IN_FINAL_ROW = b;
		REMAINIG_ALIENS = c;
		ALIENS_ON_BORDER = d;
		LEFT_BEHIND = e;
	}

	public static boolean haveLanded() {
		return IS_IN_FINAL_ROW;
	}

	public static String getRemainingAliens() {
		return "Remaining aliens: " + REMAINIG_ALIENS;
	}

	public void computerAction() {
		if (game.getCurrentCycle() != 0) {
			if (getCyclesNextAlienMove() == 0) {
				setCyclesNextAlienMove(game.getLevel().getNumCyclesToMoveOneCell());
			} else {
				setCyclesNextAlienMove(getCyclesNextAlienMove() - 1);
			}
		}
	}

	@Override
	public void move() {
		if (cyclesNextAlienMove == game.getLevel().getNumCyclesToMoveOneCell()) {
			if (ALIENS_JUMP) {
				ALIENS_ON_BORDER++;
			} else if (columna + dir > Game.DIM_COLUMNA - 1 || columna + dir < 0) {
				ALIENS_JUMP = true;
				ALIENS_ON_BORDER++;
			} else {
				ALIENS_ON_BORDER++; // Cuenta todos por si alguno esta en el borde
			}
		} else if (cyclesNextAlienMove == 0 && ALIENS_JUMP && ALIENS_ON_BORDER > 0) {
			fila++;
			if (fila == Game.DIM_FILA - 1) {
				IS_IN_FINAL_ROW = true;
			}
			dir = -dir;
			ALIENS_ON_BORDER--;
			if (cyclesNextAlienMove == 0 && ALIENS_ON_BORDER == 0)
				ALIENS_JUMP = false;
		} else if (cyclesNextAlienMove == 0) {
			columna = columna + dir;
			ALIENS_ON_BORDER = 0;
		}

	}

	public void onDelete() {
		REMAINIG_ALIENS--;
		if (ALIENS_ON_BORDER > 0)
			ALIENS_ON_BORDER--;
	}

	public static int getREMAINIG_ALIENS() {
		return REMAINIG_ALIENS;
	}

	public static boolean isALIENS_JUMP() {
		return ALIENS_JUMP;
	}

	public static int getALIENS_ON_BORDER() {
		return ALIENS_ON_BORDER;
	}

	public static int getLEFT_BEHIND() {
		return LEFT_BEHIND;
	}

	public static boolean isIS_IN_FINAL_ROW() {
		return IS_IN_FINAL_ROW;
	}

	public int getCyclesNextAlienMove() {
		return cyclesNextAlienMove;
	}

	public void setCyclesNextAlienMove(int cyclesNextMove) {
		cyclesNextAlienMove = cyclesNextMove;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int d) {
		dir = d;
	}

	public boolean receiveSniperAttack(int  damage) {
		live-=damage;
		ExplosiveShip other = new ExplosiveShip(game, fila, columna, live);
		other.setCyclesNextAlienMove(cyclesNextAlienMove);
		other.setDir(dir);
		game.swap(this, other);
		REMAINIG_ALIENS--;
		return true;
		}
}
