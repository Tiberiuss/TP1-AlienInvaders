package pr2.game;

import pr2.game.Exceptions.FileContentsException;
import pr2.game.GameObjects.Bomb;
import pr2.game.GameObjects.DestroyerShip;
import pr2.game.GameObjects.ExplosiveShip;
import pr2.game.GameObjects.GameObject;
import pr2.game.GameObjects.Ovni;
import pr2.game.GameObjects.RegularShip;
import pr2.game.GameObjects.ShockWave;
import pr2.game.GameObjects.SmartBomb;
import pr2.game.GameObjects.SuperMissile;
import pr2.game.GameObjects.UCMMissile;
import pr2.game.GameObjects.UCMShip;
import pr2.game.util.FileContentsVerifier;

public class GameObjectGenerator {
	private static GameObject[] availableGameObjects = {
			new UCMShip(),
			new Ovni(),
			new RegularShip(),
			new DestroyerShip(),
			new ExplosiveShip(),
			new ShockWave(),
			new Bomb(),
			new UCMMissile(),
			new SuperMissile(),
			new SmartBomb()
		};

	public static GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier)
					throws FileContentsException {		
		GameObject[] gameObject = null;
		for (GameObject go: availableGameObjects) {
			gameObject = go.parse(stringFromFile, game, verifier);
			if (gameObject != null) break;
		}
		return gameObject;
	}
}
