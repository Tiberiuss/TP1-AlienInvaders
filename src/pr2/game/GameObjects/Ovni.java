package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Interfaces.IExecuteRandomActions;
import pr2.game.util.FileContentsVerifier;

public class Ovni extends EnemyShip {

	private static final int POINTS = 25;
	private static final int DAMAGE = 0;
	private static final int LIFE = 1;
	private static final String SERIAL = "O";

	public Ovni(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public Ovni() {
		super();
	}

	public String toString() {
		if (game.isOvni())
			return "O[" + live + "]";
		else
			return "";
	}

	public static int getPoints() {
		return POINTS;
	}

	public static String getInfo() {
		return "[O]vni: Points: " + POINTS + " - Harm: " + DAMAGE + " - Shield: " + LIFE;
	}

	public static int getDamage() {
		return DAMAGE;
	}

	public static int getLife() {
		return LIFE;
	}

	@Override
	public void computerAction() {
		if (IExecuteRandomActions.canGenerateRandomOvni(game)) {
			game.setOvni(true);
		}
	}

	@Override
	public void onDelete() {
		game.receivePoints(POINTS);
		// game.addObject(new ShockWave(game, 0, 0, 1));
		game.enableShockWave();
	}

	@Override
	public void move() {
		if (game.isOvni()) {
			if (columna - 1 >= 0) {
				columna--;
			} else {
				columna = Game.DIM_COLUMNA;
				game.setOvni(false);
			}
		}
	}

	public boolean isOut() {
		return false;
	}

	public boolean receiveShockWaveAttack(int damage) {
		return false;
	}

	@Override
	public String stringify() {
		String info = "";
		if (game.isOvni())
			info = "O;" + fila + "," + columna + ";" + live + "\n";
		return info;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO O;0,3;1
		 */
		try {

			String[] ovni = stringFromFile.split(";");

			if (ovni[0].equals(SERIAL) && verifier.verifyOvniString(stringFromFile, game, LIFE)) {
				int x = Integer.parseInt(ovni[1].substring(0, 1));
				int y = Integer.parseInt(ovni[1].substring(2, 3));
				int life = Integer.parseInt(ovni[2]);

				Ovni ship = new Ovni(game, x, y, life);
				if (y < Game.DIM_COLUMNA) {
					game.setOvni(true);
				}

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
