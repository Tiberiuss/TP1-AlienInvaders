package pr2.game.Controller.Command;

import pr2.game.Game;

public class HelpCommand extends Command {

	protected static final String name="help";
	protected static final String shortCut="h";
	private static final String details="help";
	private static final String help="Prints this help message";

	public HelpCommand() {
		super(name,shortCut,details,help);
	}



	public Boolean execute(Game game) {
		System.out.print(CommandGenerator.commandHelp());
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut)) {
			return this;
		}
		return null;
	}

}
