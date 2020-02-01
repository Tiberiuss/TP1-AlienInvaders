package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public class UCMMissile extends Weapon {

	private static final int DAMAGE = 1;
	private static final int LIFE = 1;
	private static final String SERIAL = "M";

	public UCMMissile(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public UCMMissile() {
		super();
	}

	public String toString() {
		if (game.isMissile())
			return "oo";
		else
			return "";
	}

	@Override
	public String stringify() {
		String info = "";
		info = "M:" + fila + "," + columna + "\n";
		return info;
	}

	@Override
	public void computerAction() {
	}

	@Override
	public void onDelete() {
		game.disableMissile();
	}

	public boolean performAttack(GameObject other) {
		if (other.receiveMissileAttack(DAMAGE)) {
			live = 0;
			game.disableMissile();
		}
		return true;
	}

	public boolean receiveBombAttack(int damage) {
		live -= damage;
		return true;
	}

	@Override
	public void move() {
		if (game.isMissile()) {
			if (fila - 1 >= 0) {
				fila--;
			} else {
				fila--; // Sale del tablero y se elimina
				game.disableMissile();
			}
		}
	}

	public static int getDamage() {
		return DAMAGE;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO M;5,0
		 */
		try {
			String[] regular = stringFromFile.split(";");

			if (regular[0].equals(SERIAL) && super.verifyWeapon(stringFromFile, game, verifier)) {
				int x = Integer.parseInt(regular[1].substring(0, 1));
				int y = Integer.parseInt(regular[1].substring(2, 3));

				UCMMissile missile = new UCMMissile(game, x, y, LIFE);
				return new GameObject[] { missile };
			}
		} catch (StringIndexOutOfBoundsException ex) {
			return null;
		}

		return null;
	}

}
