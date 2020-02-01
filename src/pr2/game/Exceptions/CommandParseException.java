package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends Exception {
	public CommandParseException() {
		super("Invalid Command.");
	}
	
	public CommandParseException(String error) {
		super(error);
	}
}
