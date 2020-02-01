package pr2.view;

import pr2.game.Game;

public abstract class GamePrinter {
//	String toString(Game game) {
//		return "";
//	};
//	public GamePrinter parse(String name) {
//		return null;
//	};
//	public String helpText() {
//		return "";
//	};
	
	Game game;
	
	public void setGame(Game game) {
		this.game=game;
	}
}
