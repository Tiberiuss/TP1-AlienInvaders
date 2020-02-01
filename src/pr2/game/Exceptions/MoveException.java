package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class MoveException extends CommandExecuteException {
	public MoveException() {
		super("Cannot perform move: ship too near to border");
	}
	
	public MoveException(String error) {
		super(error);
	}
}
