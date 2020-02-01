package pr2.game.Exceptions;

import pr2.game.GameObjects.GameObject;

@SuppressWarnings("serial")
public class CanNotShootObjectException extends CommandExecuteException {

	public CanNotShootObjectException(GameObject ally) {
		super(ally+" is an invalid target.");
	}

	public CanNotShootObjectException(String error) {
		super(error);
	}

}
