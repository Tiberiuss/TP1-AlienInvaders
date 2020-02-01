package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class MissileInFlightException extends CommandExecuteException {

	public MissileInFlightException() {
		super("Cannot fire missile: missile already exists on board");
	}

	public MissileInFlightException(String error) {
		super(error);
	}

}
