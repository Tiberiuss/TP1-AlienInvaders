package pr2.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import pr2.game.Exceptions.CanNotShootObjectException;
import pr2.game.Exceptions.FileContentsException;
import pr2.game.Exceptions.MissileInFlightException;
import pr2.game.Exceptions.MoveException;
import pr2.game.Exceptions.NoShockwaveException;
import pr2.game.Exceptions.NoSuperMissileException;
import pr2.game.Exceptions.NotEnoughPointsException;
import pr2.game.Exceptions.NothingToKillException;
import pr2.game.GameObjects.AlienShip;
import pr2.game.GameObjects.GameObject;
import pr2.game.GameObjects.UCMShip;
import pr2.game.GameObjects.Lists.GameObjectBoard;
import pr2.game.Interfaces.IPlayerController;
import pr2.game.util.FileContentsVerifier;

public class Game implements IPlayerController {
	public final static int DIM_FILA = 8;
	public final static int DIM_COLUMNA = 9;

	private int currentCycle;
	private Random rand;
	private Level level;
	private boolean ovni;
	private int seed;

	GameObjectBoard board;

	private UCMShip player;

	private boolean doExit;
	private BoardInitializer initializer;

	public Game(Level level, int seed) {
		this.rand = new Random(seed);
		this.seed = seed;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}

	public void initGame() {
		currentCycle = 0;
		ovni = false;
		rand = new Random(seed);
		board = initializer.initialize(this, level);
		player = new UCMShip(this, DIM_FILA - 1, DIM_COLUMNA / 2);
		board.add(player);
	}

	public Random getRandom() {
		return rand;
	}

	public Level getLevel() {
		return level;
	}

	public void reset() {
		AlienShip.reset();
		initGame();
	}

	public void addObject(GameObject object) {
		board.add(object);
	}

	public String positionToString(int x, int y) {
		return board.toString(x, y);
	}

	public String stringify() {
		StringBuilder str = new StringBuilder();
		str.append("— Space Invaders v2.0 —");
		str.append("\n\n");
		str.append("G;" + getCurrentCycle() + ";" + getSeed());
		str.append("\n");
		str.append("L;" + getLevel().name());
		str.append("\n");
		return board.stringify(str);
	}

	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}

	public boolean aliensWin() {

		return player.getLive() == 0 || AlienShip.haveLanded();
	}

	private boolean playerWin() {
		return AlienShip.allDead();
	}

	public void update() {
		board.computerAction();
		board.update();
		currentCycle += 1;
	}

	public void refresh() {
		board.refresh();
	}
	
	public boolean isOnBoard(int fila, int columna) {
		return fila >= 0 && columna >= 0 && fila < DIM_FILA && columna < DIM_COLUMNA;
	}

	public void exit() {
		doExit = true;
	}

	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" + player.stateToString() + "\n" + "Points: " + player.getPoints() + "\n"
				+ AlienShip.getRemainingAliens() + "\n" + "ShockWave: " + (player.isShockWave() ? "YES" : "NO") + "\n"
				+ "SuperMissiles: " + player.getSupermissiles();
	}

	public String getWinnerMessage() {
		if (playerWin())
			return "Player win!";
		else if (aliensWin())
			return "Aliens win!";
		else if (doExit)
			return "Player exits the game";
		else
			return "This should not happen";
	}

	public boolean move(int numCells) throws MoveException {
		return player.move(numCells);
	}

	public void Load(BufferedReader inStream) throws IOException, FileContentsException {
		FileContentsVerifier verifier = new FileContentsVerifier();
		String line = inStream.readLine().trim();
		String[] words = line.split(";");
		// RESET
		Level newLevel;
		int newSeed;
		int newCurrentCycle;
		boolean oldOvni = ovni;
		UCMShip oldPlayer = player;
		GameObjectBoard newBoard = new GameObjectBoard(Game.DIM_FILA, Game.DIM_COLUMNA);
		//

		if (verifier.verifyCycleString(line)) {
			newCurrentCycle = Integer.parseInt(words[1]);
			newSeed = Integer.parseInt(words[2]);
			line = inStream.readLine().trim();
			words = line.split(";");
			if (verifier.verifyLevelString(line)) {
				newLevel = Level.parse(words[1]);
				line = inStream.readLine().trim();
			} else {
				throw new FileContentsException("invalid file, " + "unrecognised line prefix");
			}
		} else {
			throw new FileContentsException("invalid file, " + "unrecognised line prefix");
		}

		boolean a = AlienShip.isALIENS_JUMP();
		boolean b = AlienShip.isIS_IN_FINAL_ROW();
		int c = AlienShip.getREMAINIG_ALIENS();
		int d = AlienShip.getALIENS_ON_BORDER();
		int e = AlienShip.getLEFT_BEHIND();

		AlienShip.reset();
		while (line != null && !line.isEmpty()) {
			GameObject[] gameObjects = GameObjectGenerator.parse(line, this, verifier); // Una linea puede tener dos
																						// Objetos

			for (GameObject gameObject : gameObjects) {
				if (gameObject == null) {
					AlienShip.set(a, b, c, d, e);
					ovni = oldOvni;
					player = oldPlayer;
					throw new FileContentsException("invalid file, " + "unrecognised line prefix");
				}
				newBoard.add(gameObject);
			}
			line = inStream.readLine().trim();
		}

		rand = new Random(newSeed);
		level = newLevel;
		seed = newSeed;
		currentCycle = newCurrentCycle;
		board = newBoard;
	}

	@Override
	public boolean shootLaser() throws MissileInFlightException {
		return player.shoot();
	}

	public boolean shootSuperMissile() throws MissileInFlightException, NoSuperMissileException {
		return player.shootSuperMissile();
	}

	@Override
	public boolean shockWave() throws NoShockwaveException {
		if (player.isShockWave()) {
			board.shockWave();
			player.setShockWave(false);
			return true;
		} else
			throw new NoShockwaveException();
	}

	@Override
	public void receivePoints(int points) {
		player.setPoints(player.getPoints() + points);
	}

	public int getPoints() {
		return player.getPoints();
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(int currentCycle) {
		this.currentCycle = currentCycle;
	}

	public boolean isOvni() {
		return ovni;
	}

	public void setOvni(boolean ovni) {
		this.ovni = ovni;
	}

	// Por ahora cambia regular por explosive
	public void swap(GameObject obj1, GameObject obj2) {
		board.swap(obj1, obj2);
	}

	public void explodeShipOn(int fila, int columna, int dmg) {
		board.explodeShipOn(fila, columna, dmg);
	}

	@Override
	public void enableShockWave() {
		player.setShockWave(true);
	}

	public void disableShockWave() {
		player.setShockWave(false);
	}

	@Override
	public void enableMissile() {
		player.setMissile(true);
	}

	public void disableMissile() {
		player.setMissile(false);
	}

	public boolean isMissile() {
		return player.isMissile();
	}

	public void buySuperMissile() throws NotEnoughPointsException {
		int points = player.getPoints();
		if (points >= 20) {
			player.setPoints(points - 20);
			player.setSupermissiles(player.getSupermissiles() + 1);//Mejor Buysupermissiles y cambio puntos dentro
			System.out.println("Bought SuperMissile for 20 points.");
		} else {
			throw new NotEnoughPointsException("Cannot buy supermissile: not enough points available (20 points needed)");
		}
	}

	public int getSuperMissile() {
		return player.getSupermissiles();
	}

	public void setSuperMissile(int i) {
		player.setSupermissiles(i);
	}

	public void setPlayer(UCMShip player) {
		this.player = player;
	}

	public void snipe(int x, int y,int z) throws NothingToKillException,CanNotShootObjectException,NotEnoughPointsException {
		int points = player.getPoints();
		int cost=5*z;
		if(points>=cost) {
			GameObject object=board.getObjectInPosition(x, y);
			if(object!=null) {
				player.setPoints(points - cost);
				player.snipe(object,z);
			}else {
				throw new NothingToKillException();
			}
			
		}else {
			throw new NotEnoughPointsException("Cannot shoot sniper: not enough points available ("+cost+" points needed)");
		}
	}
}
