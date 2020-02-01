package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;

public class MoveCommand extends Command {

	protected static final String name = "move";
	protected static final String shortCut = "m";
	private static final String details = "move <left|right> <1|2>";
	private static final String help = "Moves UCM-Ship to the indicated direction.";

	private int positions;

	public MoveCommand() {
		super(name, shortCut, details, help);
	}

	public MoveCommand(int positions) {
		super(name, shortCut, details, help);
		this.positions = positions;
	}

	public Boolean execute(Game game) throws CommandExecuteException {
		return game.move(positions);
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;

		if (commandWords.length == 3
				&& (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut))) {
			if ((commandWords[1].equalsIgnoreCase("left") || commandWords[1].equalsIgnoreCase("right"))
					&& (commandWords[2].equalsIgnoreCase("1") || commandWords[2].equalsIgnoreCase("2"))) {
				int direction = 0;
				int squaresMove = 0;
				if (commandWords[1].equalsIgnoreCase("left")) {
					direction = -1;
				} else if (commandWords[1].equalsIgnoreCase("right")) {
					direction = 1;
				}
				squaresMove = Integer.parseInt(commandWords[2]);
				command = new MoveCommand(direction * squaresMove);
			} else {
				throw new CommandParseException("Usage: " + details);
			}
		}
		return command;
	}

}
