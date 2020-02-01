package pr2.game.Controller.Command;

import pr2.game.Game;

public class ResetCommand extends Command {

	protected static final String name = "reset";
	protected static final String shortCut = "r";
	private static final String details = "reset";
	private static final String help = "Starts a new game.";

	public ResetCommand() {
		super(name, shortCut, details, help);
	}

	@Override
	public Boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equals(name) || commandWords[0].equals(shortCut)) {
			return this;
		} else {
			return null;
		}
	}

}
