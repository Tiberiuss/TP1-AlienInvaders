package pr2.game.GameObjects.Lists;

import pr2.game.GameObjects.GameObject;
import pr2.game.GameObjects.ShockWave;

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;

	public GameObjectBoard(int width, int height) {
		currentObjects = 0;
		objects = new GameObject[width * height];
	}

	public void add(GameObject object) {
		objects[currentObjects] = object;
		currentObjects++;
	}

	public GameObject getObjectInPosition(int x, int y) {
		for (int i = 0; i < currentObjects; i++) {
			if (objects[i].isOnPosition(x, y)) {
				return objects[i];
			}
		}
		return null;
	}

	private int getIndex(int x, int y) {
		for (int i = 0; i < currentObjects; i++) {
			if (objects[i].isOnPosition(x, y)) {
				return i;
			}
		}
		return -1; // Not Found
	}

	private void remove(int i) {
		for (int x = i; x < currentObjects - 1; x++) {
			objects[x] = objects[x + 1];
		}
		objects[currentObjects - 1] = null;
		currentObjects--;
	}

	public void update() {
		for (int i = 0; i < currentObjects; i++) {
			checkAttacks(objects[i], i);
			if (objects[i].isAlive())
				objects[i].move();
			checkAttacks(objects[i], i);
		}
		removeDead();
		removeOutOfBoundsBombs();
	}

	
	public void refresh() {
		removeDead();
	}
	
	private void checkAttacks(GameObject object, int index) {
		int attackObject = getSameCellObject(object.getFila(), object.getColumna(), index);

		if (attackObject != -1 && objects[attackObject].isAlive()) {
			object.performAttack(objects[attackObject]);
		}

	}

	private int getSameCellObject(int x, int y, int index) {
		int same = -1;
		for (int i = index + 1; i < currentObjects; i++) {
			if (objects[i].isOnPosition(x, y)) {
				same = i;
			}
		}
		if (same == -1) {
			for (int i = index - 1; i >= 0; i--) {
				if (objects[i].isOnPosition(x, y)) {
					same = i;
				}
			}
		}
		return same;
	}

	public void computerAction() {
		for (int i = 0; i < currentObjects; i++) {
			objects[i].computerAction();
		}
	}

	private void removeDead() { // Se comprueba siempre si queda algunos objetos sin vida por si explota alguna
								// nave
		boolean allAlive = false;

		while (!allAlive) {
			allAlive = true;
			for (int i = 0; i < currentObjects; i++) {
				if (!objects[i].isAlive()) {
					allAlive = false;
					objects[i].onDelete();
					remove(i);
				}
			}
		}

	}

	private void removeOutOfBoundsBombs() {
		boolean oneOut = true;

		while (oneOut) {
			oneOut = false;
			for (int i = 0; i < currentObjects; i++) {
				if (objects[i].isOut()) {
					oneOut = true;
					objects[i].onDelete();
					remove(i);
				}
			}

		}
	}

	public String toString(int x, int y) {
		if (getObjectInPosition(x, y) != null)
			return getObjectInPosition(x, y).toString();
		else
			return "";
	}

	public String stringify(StringBuilder str) {
		for (int x = 0; x < currentObjects; x++) {
			str.append(objects[x].stringify());
		}
		str.append("\n");
		return str.toString();
	}

	public void swap(GameObject obj1, GameObject obj2) {
		//TODO ESTA MAL SI HAY VARIOS OBJETOS EN LA MISMA CASILLA
		int i = getIndex(obj1.getFila(), obj1.getColumna());
		objects[i] = obj2;
	}

	public void explodeShipOn(int fila, int columna, int dmg) {
		if (getObjectInPosition(fila, columna) != null)
			getObjectInPosition(fila, columna).receiveMissileAttack(dmg);

	}

	public void shockWave() {
		for (int i = 0; i < currentObjects; i++) {
			objects[i].receiveShockWaveAttack(ShockWave.getDAMAGE());
			// Es realmente necesario crear una instancia de ShockWave?
		}
	}

}
