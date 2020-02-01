package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.GameObjects.DestroyerShip;
import pr2.game.GameObjects.ExplosiveShip;
import pr2.game.GameObjects.Ovni;
import pr2.game.GameObjects.RegularShip;
import pr2.game.GameObjects.UCMShip;

public class ListCommand extends Command {

	protected static final String name = "list";
	protected static final String shortCut = "l";
	private static final String details = "list";
	private static final String help = "Prints the list of available ships.";

	public ListCommand() {
		super(name, shortCut, details, help);
	}

	public Boolean execute(Game game) {
		System.out.println("  "+RegularShip.getInfo() + "\r\n" + "  " + Ovni.getInfo() + "\r\n" + "  " + DestroyerShip.getInfo() + "\r\n" + "  "
				 + ExplosiveShip.getInfo()+ "\r\n"+ "  " +UCMShip.getInfo());
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut)) {
			return this;
		}
		return null;
	}

}
