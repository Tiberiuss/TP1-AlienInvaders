package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public class SuperMissile extends Weapon {

	private static final int DAMAGE = 2;
	private static final int LIFE = 1;
	private static final String SERIAL = "X";

	public SuperMissile(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public SuperMissile() {
		super();
	}

	public String toString() {
		return "oooo";
	}

	@Override
	public void computerAction() {

	}

	@Override
	public void onDelete() {
		game.setSuperMissile(game.getSuperMissile()-1);
		game.disableMissile();
	}

	@Override
	public void move() {
		if (game.isMissile()) {
			if (fila - 1 >= 0) {
				fila--;
			} else {
				fila--; // Sale del tablero y se elimina
				game.enableMissile();
			}
		}
	}

	public boolean performAttack(GameObject other) {
		if (other.receiveMissileAttack(DAMAGE)) {
			live = 0;
			game.setSuperMissile(game.getSuperMissile() - 1);
			game.disableMissile();
		}
		return true;
	}

	public boolean receiveBombAttack(int damage) {
		live -= damage;
		return true;
	}

	public static int getDamage() {
		return DAMAGE;
	}

	@Override
	public String stringify() {
		String info = "";
		info = "X;" + fila + "," + columna;
		return info;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO X;5,0
		 */
		try {
		String[] weapon = stringFromFile.split(";");

		if (weapon[0].equals(SERIAL) && super.verifyWeapon(stringFromFile, game, verifier)) {
			int x = Integer.parseInt(weapon[1].substring(0, 1));
			int y = Integer.parseInt(weapon[1].substring(2, 3));

			SuperMissile missile = new SuperMissile(game, x, y, LIFE);
			return new GameObject[] { missile };
		}
		}catch(StringIndexOutOfBoundsException ex) {
			return null;
		}
		return null;
	}

	public static String getSerial() {
		return SERIAL;
	}

}
