package pr2.game.Controller.Command;

import pr2.game.Game;

public class StringifyCommand extends Command {
	protected static final String name = "stringify";
	protected static final String shortCut = null;
	private static final String details = "stringify";
	private static final String help = "Show the serialized game.";

	public StringifyCommand() {
		super(name,shortCut,details,help);
	}

	@Override
	public Boolean execute(Game game) {
		System.out.println(game.stringify());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equals(name)) {
			return this;
		} else {
			return null;
		}
	}
}
