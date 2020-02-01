package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class NotEnoughPointsException extends CommandExecuteException {

	public NotEnoughPointsException() {
		super();
	}

	public NotEnoughPointsException(String error) {
		super(error);
	}

}
