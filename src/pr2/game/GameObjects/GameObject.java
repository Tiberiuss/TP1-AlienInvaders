package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Exceptions.FileContentsException;
import pr2.game.Interfaces.IAttack;
import pr2.game.util.FileContentsVerifier;

public abstract class GameObject implements IAttack {
	protected int fila;
	protected int columna;
	protected int live;
	protected Game game;

	public GameObject(Game game, int fila, int columna, int live) {
		this.fila = fila;
		this.columna = columna;
		this.game = game;
		this.live = live;
	}

	public GameObject() {
		this.fila = 0;
		this.columna = 0;
		this.game = null;
		this.live = 0;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public boolean isAlive() {
		return this.live > 0;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public int getLive() {
		return this.live;
	}

	public boolean isOnPosition(int fila, int columna) {
		return this.fila == fila && this.columna == columna;
	}

	public void getDamage(int damage) {
		this.live = damage >= this.live ? 0 : this.live - damage;
	}

	public boolean isOut() {
		return !game.isOnBoard(fila, columna);
	}

	public abstract void computerAction();

	public abstract void onDelete();

	public abstract void move();

	public abstract String toString();

	public abstract String stringify();

	public abstract GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws FileContentsException;
}
