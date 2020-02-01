package pr2.game.Controller.Command;

import pr2.game.Game;

public class ExitCommand extends Command {
	protected static final String name = "exit";
	protected static final String shortCut = "e";
	private static final String details = "exit";
	private static final String help = "Terminates the program.";

	public ExitCommand() {
		super(name, shortCut, details, help);
	}

	public Boolean execute(Game game) {
		game.exit();
		return true;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1
				&& (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut))) {
			return this;
		}
		return null;
	}

}
