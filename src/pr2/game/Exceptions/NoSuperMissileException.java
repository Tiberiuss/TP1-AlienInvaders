package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class NoSuperMissileException extends CommandExecuteException {

	public NoSuperMissileException() {
		super("Cannot release superMissile: no superMissiles available");
	}

	public NoSuperMissileException(String error) {
		super(error);
	}

}
