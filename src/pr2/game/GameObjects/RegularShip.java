package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Interfaces.IExecuteRandomActions;
import pr2.game.util.FileContentsVerifier;

public class RegularShip extends AlienShip {

	private static final int POINTS = 5;
	private static final int DAMAGE = 0;
	private static final int LIFE = 2;
	private static final String SERIAL = "R";

	public RegularShip(Game game, int y, int x, int live) {
		super(game, y, x, live);
	}

	public RegularShip() {
		super();
	}

	public String toString() {
		return "C[" + live + "]";
	}

	public static int getPoints() {
		return POINTS;
	}

	public static String getInfo() {
		return "[C]ommon ship: Points: " + POINTS + " - Harm: " + DAMAGE + " - Shield: " + LIFE;
	}

	public static int getLife() {
		return LIFE;
	}

	public static int getDamage() {
		return DAMAGE;
	}

	public void computerAction() {
		super.computerAction();
		if (IExecuteRandomActions.canGenerateExplosiveShip(game)) {
			ExplosiveShip other = new ExplosiveShip(game, fila, columna, live);
			other.setCyclesNextAlienMove(cyclesNextAlienMove);
			other.setDir(dir);
			game.swap(this, other);
			REMAINIG_ALIENS--;
		}
	}

	@Override
	public void onDelete() {
		super.onDelete();
		game.receivePoints(POINTS);
	}

	@Override
	public String stringify() {
		String info = "";
		info = "R;" + fila + "," + columna + ";" + live + ";" + getCyclesNextAlienMove() + ";" + getDir() + "\n";
		return info;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO R;1,3;2;2;-1
		 */
		try {
			
		String[] regular = stringFromFile.split(";");

		if (regular[0].equals(SERIAL) && verifier.verifyAlienShipString(stringFromFile, game, LIFE)) {
			int x = Integer.parseInt(regular[1].substring(0, 1));
			int y = Integer.parseInt(regular[1].substring(2, 3));
			int life = Integer.parseInt(regular[2]);
			int cyclesMove = Integer.parseInt(regular[3]);
			int dir = Integer.parseInt(regular[4]);

			RegularShip ship = new RegularShip(game, x, y, life);
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
