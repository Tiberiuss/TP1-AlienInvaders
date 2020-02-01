package pr2.game.GameObjects;

import pr2.game.Game;
import pr2.game.Exceptions.MoveException;
import pr2.game.Exceptions.CanNotShootObjectException;
import pr2.game.Exceptions.MissileInFlightException;
import pr2.game.Exceptions.NoSuperMissileException;
import pr2.game.GameObjects.UCMMissile;
import pr2.game.util.FileContentsVerifier;

public class UCMShip extends Ship {

	private static final int LIFE = 3;
	private static final String SERIAL = "P";
	private int points;
	private int supermissiles;
	private boolean missile;
	private boolean shockWave;

	public UCMShip(Game game, int fila, int columna) {
		super(game, fila, columna, LIFE);
		missile = false;
		shockWave = false;
		supermissiles = 0;
		points = 0;
	}

	public UCMShip() {
		super();
		missile = false;
		shockWave = true;
		supermissiles = 0;
		points = 0;
	}

	public boolean shoot() throws MissileInFlightException {
		if (!missile) {
			game.addObject(new UCMMissile(game, fila, columna, 1));
			missile = true;
			return true;
		} else {
			throw new MissileInFlightException();
		}
	}

	public boolean shootSuperMissile() throws MissileInFlightException, NoSuperMissileException {
		if (!missile) {
			if (supermissiles > 0) {
				game.addObject(new SuperMissile(game, fila, columna, 1));
				missile = true;
				return true;
			} else {
				throw new NoSuperMissileException();
			}
		} else {
			throw new MissileInFlightException();
		}
	}

	public String toString() {
		if (live == 0)
			return "!xx!";
		else
			return "^__^";
	}

	public static String getInfo() {
		return "^__^: Harm: " + UCMMissile.getDamage() + " - Shield: " + LIFE;
	}

	public void computerAction() {
	}

	public void onDelete() {

	}

	public void move() {
	}

	public boolean receiveBombAttack(int damage) {
		live -= damage;
		return true;
	}

	public boolean receiveSmartBombAttack(int damage) {
		live -= damage;
		return true;
	}
	
	public boolean isAlive() {
		// return live > 0;
		return true;
	}

	public boolean move(int numCells) throws MoveException {
		if (game.isOnBoard(fila, columna + numCells)) {
			columna += numCells;
			return true;
		} else {
			throw new MoveException();
		}
	}

	public String stateToString() {
		return "Life: " + live;
	}

	public boolean isMissile() {
		return missile;
	}

	public void setMissile(boolean missile) {
		this.missile = missile;
	}

	public int getSupermissiles() {
		return supermissiles;
	}

	public void setSupermissiles(int supermissiles) {
		this.supermissiles = supermissiles;
	}

	public boolean isShockWave() {
		return shockWave;
	}

	public void setShockWave(boolean shockWave) {
		this.shockWave = shockWave;
	}

	@Override
	public String stringify() {
		String info = "";
		info = "P;" + fila + "," + columna + ";" + live + ";" + game.getPoints() + ";" + +supermissiles + ";"
				+ shockWave + ";" + missile + "\n";
		return info;
	}

	@Override
	public GameObject[] parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
		/*
		 * EJEMPLO P;7,0;3;0;0;true;true
		 */
		try {
			String[] ship = stringFromFile.split(";");

			if (ship[0].equals(SERIAL) && verifier.verifyPlayerString(stringFromFile, game, LIFE)) {
				int x = Integer.parseInt(ship[1].substring(0, 1));
				int y = Integer.parseInt(ship[1].substring(2, 3));
				int life = Integer.parseInt(ship[2]);
				int points = Integer.parseInt(ship[3]);
				int supermissiles = Integer.parseInt(ship[4]);
				boolean shockWave = Boolean.getBoolean(ship[5]);
				boolean missile = Boolean.getBoolean(ship[6]);

				UCMShip player = new UCMShip(game, x, y);
				player.setLive(life);
				player.setMissile(missile);
				player.setShockWave(shockWave);
				player.setSupermissiles(supermissiles);
				player.setPoints(points);
				game.setPlayer(player);
				return new GameObject[] { player };
			}
		} catch (StringIndexOutOfBoundsException ex) {
			return null;
		}
		return null;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public static String getSerial() {
		return SERIAL;
	}

	public void snipe(GameObject obj, int z) throws CanNotShootObjectException {
		if (!obj.receiveSniperAttack(z)) {
			throw new CanNotShootObjectException(obj);
		} else {
			game.refresh();

		}
	}
}
