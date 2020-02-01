package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.view.PrinterTypes;

public class ListPrintersCommand extends Command{
	protected static final String name = "listPrinters";
	protected static final String shortCut = null;
	private static final String details = "listPrinters";
	private static final String help = "Prints the list of available printers.";

	public ListPrintersCommand() {
		super(name, shortCut, details, help);
	}

	public Boolean execute(Game game) {
		System.out.println(PrinterTypes.printerHelp(game));
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut)) {
			return this;
		}
		return null;
	}
}
