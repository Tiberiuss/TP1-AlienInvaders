package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class NothingToKillException extends CommandExecuteException {

	public NothingToKillException() {
		super("Nothing to shoot in that position.");
	}

	public NothingToKillException(String error) {
		super(error);
	}

}
