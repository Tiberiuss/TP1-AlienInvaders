package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public abstract class Weapon extends GameObject {
	
	public Weapon(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public Weapon() {
		super();
	}

	public boolean verifyWeapon(String stringFromFile, Game game, FileContentsVerifier verifier) {
		if (verifier.verifyWeaponString(stringFromFile, game)) {
			return true;
		}
		return false;
	}

}
