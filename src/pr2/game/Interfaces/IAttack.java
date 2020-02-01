package pr2.game.Interfaces;

import pr2.game.GameObjects.GameObject;

public interface IAttack {
	default boolean performAttack(GameObject other) {return false;};

	default boolean receiveMissileAttack(int  damage) {return false;};
	default boolean receiveBombAttack(int damage) {return false;};
	default boolean receiveShockWaveAttack(int damage) {return false;};
	default boolean receiveSniperAttack(int  damage) {return false;};
	default boolean receiveSmartBombAttack(int damage) {return false;};
}
