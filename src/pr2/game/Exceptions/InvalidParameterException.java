package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class InvalidParameterException extends Exception {
	public InvalidParameterException() {
		super("Usage: Main <EASY|HARD|INSANE> [seed]");
	}

	public InvalidParameterException(String error) {
		super(error);
	}
}
