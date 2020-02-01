package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.util.FileContentsVerifier;

public class ShockWave extends Weapon {

	private static int DAMAGE=1;
	
	public ShockWave(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public ShockWave() {
		super();
	}

	@Override
	public void computerAction() {

	}

	@Override
	public void onDelete() {
		game.disableShockWave();
	}

	@Override
	public void move() {

	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public String stringify() {
		return null;
	}

	public static int getDAMAGE() {
		return DAMAGE;
	}

	public static void setDAMAGE(int dAMAGE) {
		DAMAGE = dAMAGE;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		return null;
	}

}
