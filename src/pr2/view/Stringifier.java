package pr2.view;

import pr2.game.Game;

public class Stringifier extends GamePrinter{

	public static String stringify(Game game) {
		return game.stringify();
	}

}
