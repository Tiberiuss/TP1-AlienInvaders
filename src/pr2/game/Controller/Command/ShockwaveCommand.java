package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;

public class ShockwaveCommand extends Command {

	protected static final String name = "shockWave";
	protected static final String shortCut = "w";
	private static final String details = "shockWave";
	private static final String help = "UCM-Ship releases a shock wave.";

	public ShockwaveCommand() {
		super(name, shortCut, details, help);
	}

	public Boolean execute(Game game) throws CommandExecuteException{
		game.shockWave();		
		return true;
	}

	public Command parse(String[] commandWords) {
		if (commandWords[0].equals(name) || commandWords[0].equals(shortCut)) {
			return this;
		} else {
			return null;
		}
	}

}
