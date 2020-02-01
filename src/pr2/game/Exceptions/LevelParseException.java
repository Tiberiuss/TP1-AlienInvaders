package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class LevelParseException extends Exception {
	public LevelParseException() {
		super("Usage: Main <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
	}
	
	public LevelParseException(String error) {
		super(error);
	}
}
