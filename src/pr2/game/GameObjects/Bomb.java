package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public class Bomb extends Weapon {

	private DestroyerShip ship;
	private static final int DAMAGE = 1;
	private static final int LIFE = 1;
	private static final String SERIAL = "B";

	public Bomb(Game game, int x, int y, int live, DestroyerShip ship) {
		super(game, x, y, live);
		this.ship = ship;
	}

	public Bomb() {
		super();
	}

	public String toString() {
		return ".";
	}

	@Override
	public String stringify() {
		String info = "";
		try {
			info = SERIAL + ";" + fila + "," + columna;
			if (ship.live > 0) {
				info += ";"+DestroyerShip.getSerial() + ";" + ship.fila + "," + ship.columna + ";" + ship.live + ";"
						+ ship.getCyclesNextAlienMove() + ";" + (ship.getDir()) + "\n";
			} else {
				info += "\n";
				ship = null;
			}
		} catch (NullPointerException e) {// Destroyer destruida antes que su bomba
			info = SERIAL + ";" + fila + "," + columna + "\n";
		}

		return info;
	}

	@Override
	public void computerAction() {

	}

	@Override
	public void onDelete() {
		ship.setShoot(false);
	}

	public boolean performAttack(GameObject other) {
		if (other.receiveBombAttack(DAMAGE)) {
			live = 0;
		}
		return true;
	}

	public boolean receiveMissileAttack(int damage) {
		live -= damage;
		return true;
	}
	
	@Override
	public void move() {
		fila++;
	}

	public static int getDamage() {
		return DAMAGE;
	}

	public DestroyerShip getShip() {
		return ship;
	}

	public void setShip(DestroyerShip ship) {
		this.ship = ship;
	}

	public static int getLife() {
		return LIFE;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO B;7,6;D;7,6;1;2;LEFT EJEMPLO B;7,6;
		 */
		try {

			String[] bomb = stringFromFile.substring(0, 5).split(";");
			String[] destroyer = stringFromFile.substring(6).split(";");
			DestroyerShip ship = new DestroyerShip();

			if (bomb[0].equals(SERIAL) && verifier.verifyWeaponString(stringFromFile.substring(0, 5), game)) {

				if (destroyer[0].equals(DestroyerShip.getSerial())
						&& verifier.verifyAlienShipString(stringFromFile.substring(6), game, DestroyerShip.getLife())) {

					ship = new DestroyerShip(game, Integer.parseInt(destroyer[1].substring(0, 1)),
							Integer.parseInt(destroyer[1].substring(2, 3)), Integer.parseInt(destroyer[2]));

					ship.setCyclesNextAlienMove(Integer.parseInt(destroyer[3]));
					ship.setDir(Integer.parseInt(destroyer[4]));
					ship.setShoot(true);

					return new GameObject[] { new Bomb(game, Integer.parseInt(bomb[1].substring(0, 1)),
							Integer.parseInt(bomb[1].substring(2, 3)), LIFE, ship), ship };
				} else {
					return new GameObject[] { new Bomb(game, Integer.parseInt(bomb[1].substring(0, 1)),
							Integer.parseInt(bomb[1].substring(2, 3)), LIFE, null) };
				}
			}

		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
		return null;
	}

}
