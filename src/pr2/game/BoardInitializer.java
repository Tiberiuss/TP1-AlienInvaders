package pr2.game;

import pr2.game.GameObjects.DestroyerShip;
import pr2.game.GameObjects.Ovni;
import pr2.game.GameObjects.RegularShip;
import pr2.game.GameObjects.SmartBomb;
import pr2.game.GameObjects.Lists.GameObjectBoard;

public class BoardInitializer {

	private Level level;
	private GameObjectBoard board;
	private Game game;

	public GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_FILA, Game.DIM_COLUMNA);

		initializeOvni();
		initializeSmartBombs();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}

	private void initializeOvni() {
		board.add(new Ovni(game, 0, Game.DIM_COLUMNA, Ovni.getLife()));
	}

	private void initializeSmartBombs() {
		int numBombsPerRow = level.getNumSmartBombs();
		// 1 4 7 si son 3
		// 2 6 si son 2
		// 4 si es 1

		// Seguro que hay una mejor manera pero no me da tiempo a pensarlo :)
		switch (numBombsPerRow) {
		case 1:
			board.add(new SmartBomb(game, 0, 4, SmartBomb.getLife()));
			break;
		case 2:
			board.add(new SmartBomb(game, 0, 2, SmartBomb.getLife()));
			board.add(new SmartBomb(game, 0, 6, SmartBomb.getLife()));
			break;
		case 3:
			board.add(new SmartBomb(game, 0, 1, SmartBomb.getLife()));
			board.add(new SmartBomb(game, 0, 4, SmartBomb.getLife()));
			board.add(new SmartBomb(game, 0, 7, SmartBomb.getLife()));
			break;
		}

	}

	private void initializeRegularAliens() {
		int fila = 1; // Fila comienzo regular
		int numRegularAliensPerRow = level.getNumRegularAliensPerRow();
		int numRowsOfRegularAliens = level.getNumRowsOfRegularAliens();
		int inicio = Math.round((float) Game.DIM_COLUMNA / 2) - numRegularAliensPerRow / 2;

		int cont = 0;
		while (cont < numRowsOfRegularAliens) {
			for (int columna = inicio; columna < inicio + numRegularAliensPerRow; columna++) {
				board.add(new RegularShip(game, fila, columna, RegularShip.getLife()));
			}
			fila++;
			cont++;
		}
	}

	private void initializeDestroyerAliens() {
		int fila = level.getNumRowsOfRegularAliens() + 1; // Regular + 1 porque empieza en la 2 fila
		int numRegularAliensPerRow = level.getNumDestroyerAliensPerRow();
		int inicio = Math.round((float) Game.DIM_COLUMNA / 2) - numRegularAliensPerRow / 2;
		for (int columna = inicio; columna < inicio + numRegularAliensPerRow; columna++) {
			board.add(new DestroyerShip(game, fila, columna, DestroyerShip.getLife()));
		}

	}
}
