package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class NoShockwaveException extends CommandExecuteException {
	public NoShockwaveException() {
		super("Cannot release shockwave: no shockwave available");
	}
	
	public NoShockwaveException(String error) {
		super(error);
	}
}
