package pr2.game.GameObjects;

import pr2.game.Game;

public abstract class Ship extends GameObject {

	public Ship(Game game, int fila, int columna, int live) {
		super(game, fila, columna, live);
	}

	public Ship() {
		super();
	}
}
