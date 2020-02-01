package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;

public class SniperCommand extends Command {

	protected static final String name = "sniper";
	protected static final String shortCut = "sn";
	private static final String details = "sniper <x> <y> <z>";
	private static final String help = "Shoot a position of the game.";

	private int x, y,z;

	public SniperCommand() {
		super(name, shortCut, details, help);
	}

	public SniperCommand(int x, int y,int z) {
		super(name, shortCut, details, help);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Boolean execute(Game game) throws CommandExecuteException {
		Boolean update = null;
		game.snipe(x,y,z);
		return update;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		if (commandWords[0].equals(name) || commandWords[0].equals(shortCut)) {
			if (commandWords.length == 4) {
				try {
					int columna = Integer.parseInt(commandWords[1]);
					int fila = Integer.parseInt(commandWords[2]);
					int z = Integer.parseInt(commandWords[3]);
					if(x>=0 && x<Game.DIM_FILA && y>=0 && y<Game.DIM_COLUMNA) {
						command = new SniperCommand(fila, columna,z);						
					}else {
						throw new CommandParseException("Usage: " + details);
					}

				} catch (NumberFormatException e) {
					throw new CommandParseException("Usage: " + details);
				}
			} else {
				throw new CommandParseException("Usage: " + details);
			}
		}
		return command;
	}
}
