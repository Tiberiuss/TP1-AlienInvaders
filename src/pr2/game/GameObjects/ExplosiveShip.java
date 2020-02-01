package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public class ExplosiveShip extends AlienShip {

	private static final int POINTS = 5;
	private static final int DAMAGE = 1;
	private static final int LIFE = 2;
	private static final String SERIAL = "E";

	public ExplosiveShip(Game game, int y, int x, int live) {
		super(game, y, x, live);
	}

	public ExplosiveShip() {
		super();
	}

	@Override
	public void onDelete() {
		super.onDelete();
		explodeShip();
		game.receivePoints(POINTS);
	}

	@Override
	public String toString() {
		return "E[" + live + "]";
	}

	@Override
	public String stringify() {
		String info = "";
		info = SERIAL + ";" + fila + "," + columna + ";" + live + ";" + getCyclesNextAlienMove() + ";" + getDir()
				+ "\n";
		return info;
	}

	public static int getDamage() {
		return DAMAGE;
	}

	public static int getPoints() {
		return POINTS;
	}

	private void explodeShip() {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				game.explodeShipOn(fila + i, columna + j, DAMAGE);
			}
		}
	}

	public static String getInfo() {
		return "[E]xplosive ship: Points: " + POINTS + " - Harm: " + DAMAGE + " - Shield: " + LIFE;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO E;1,3;2;2;-1
		 */
		try {
			String[] explosive = stringFromFile.split(";");

			if (explosive[0].equals(SERIAL) && verifier.verifyAlienShipString(stringFromFile, game, LIFE)) {
				int x = Integer.parseInt(explosive[1].substring(0, 1));
				int y = Integer.parseInt(explosive[1].substring(2, 3));
				int life = Integer.parseInt(explosive[2]);
				int cyclesMove = Integer.parseInt(explosive[3]);
				int dir = Integer.parseInt(explosive[4]);

				ExplosiveShip ship = new ExplosiveShip(game, x, y, life);
				ship.setCyclesNextAlienMove(cyclesMove);
				ship.setDir(dir);
				return new GameObject[] { ship };
			}
		} catch (StringIndexOutOfBoundsException ex) {
			return null;
		}
		return null;
	}

	public static String getSerial() {
		return SERIAL;
	}
}
