package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;

public class BuyCommand extends Command{
	protected static final String name = "buy supermissile";
	protected static final String shortCut = "buy";
	private static final String details = "buy";
	private static final String help = "Buy supermissile with 20 points.";

	
	public BuyCommand() {
		super(name, shortCut, details, help);
	}

	@Override
	public Boolean execute(Game game) throws CommandExecuteException {
		game.buySuperMissile();
		return null;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut)) {
			return this;
		}
		return null;
	}
}
