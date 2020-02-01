package pr2.game.Controller.Command;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;

public class ShootCommand extends Command {

	protected static final String name = "shoot";
	protected static final String shortCut = "s";
	private static final String details = "shoot <supermissile>";
	private static final String help = "UCM-Ship launches a missile.";

	private int shoot;

	public ShootCommand() {
		super(name, shortCut, details, help);
		this.shoot = 0;
	}

	public ShootCommand(int shoot) {
		super(name, shortCut, details, help);
		this.shoot = shoot;
	}

	public Boolean execute(Game game) throws CommandExecuteException {
		boolean update = false;
		if (shoot == 0) {
			return game.shootLaser();
		} else if (shoot == 1) {
			return game.shootSuperMissile();
		}
		return update;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		if (commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut)) {
			if (commandWords.length == 1) {
				command = this;
			} else if (commandWords.length == 2 && commandWords[1].equals("supermissile")) {
				command = new ShootCommand(1);
			}else {
				throw new CommandParseException("Usage: " + details);				
			}
		}
		return command;
	}
}
