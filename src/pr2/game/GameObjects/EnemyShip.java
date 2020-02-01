package pr2.game.GameObjects;

import pr2.game.Game;

public abstract class EnemyShip extends Ship {

	public EnemyShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}

	public EnemyShip() {
		super();
	}
	
	public boolean receiveMissileAttack(int damage) {
		live -= damage;
		return true;
	}

	public boolean receiveShockWaveAttack(int damage) {
		live -= damage;
		return true;
	}
	
	
}
