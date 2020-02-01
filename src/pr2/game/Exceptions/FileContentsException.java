package pr2.game.Exceptions;

@SuppressWarnings("serial")
public class FileContentsException extends CommandExecuteException {
	public FileContentsException() {
		super();
	}

	public FileContentsException(String error) {
		super(error);
	}
}
