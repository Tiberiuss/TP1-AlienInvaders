package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception {
	
	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String error) {
		super(error);
	}
}
