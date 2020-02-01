package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Interfaces.IExecuteRandomActions;
import pr2.game.util.FileContentsVerifier;

public class DestroyerShip extends AlienShip {
	private boolean shoot;
	private static final int DAMAGE = 1;
	private static final int POINTS = 10;
	private static final int LIFE = 1;
	private static final String SERIAL = "D";

	public DestroyerShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
		shoot = false;
	}

	public DestroyerShip() {
		super();
	}

	public String toString() {
		return "D[" + live + "]";
	}

	public static int getDamage() {
		return DAMAGE;
	}

	public static int getPoints() {
		return POINTS;
	}

	public static String getInfo() {
		return "[D]estroyer ship: Points: " + POINTS + " - Harm: " + DAMAGE + " - Shield: " + LIFE;
	}

	public static int getLife() {
		return LIFE;
	}

	public void computerAction() {
		super.computerAction();
		if (!shoot && IExecuteRandomActions.canGenerateRandomBomb(game)) {
			game.addObject(new Bomb(game, fila, columna, Bomb.getLife(), this));
			shoot = true;
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

		if (!shoot)
			info = SERIAL + ";" + fila + "," + columna + ";" + live + ";" + getCyclesNextAlienMove() + ";" + (getDir())
					+ "\n";

		return info;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO D;7,6;1;2;-1
		 */
		try {
			String[] destroyer = stringFromFile.split(";");
			DestroyerShip ship = new DestroyerShip();

			if (destroyer[0].equals(DestroyerShip.getSerial())
					&& verifier.verifyAlienShipString(stringFromFile.substring(6), game, DestroyerShip.getLife())) {

				ship = new DestroyerShip(game, Integer.parseInt(destroyer[1].substring(0, 1)),
						Integer.parseInt(destroyer[1].substring(2, 3)), Integer.parseInt(destroyer[2]));

				ship.setCyclesNextAlienMove(Integer.parseInt(destroyer[3]));
				ship.setDir(Integer.parseInt(destroyer[4]));
				ship.setShoot(true);

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
