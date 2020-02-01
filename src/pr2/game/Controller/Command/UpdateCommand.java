package pr2.game.Controller.Command;

import pr2.game.Game;

public class UpdateCommand extends Command {

	protected static final String name="none";
	protected static final String shortCut="n";
	private static final String details="[none]";
	private static final String help="Skips one cycle.";

	public UpdateCommand() {
		super(name,shortCut,details,help);
	}

	@Override
	public Boolean execute(Game game) {
		return true; 
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equals(name) || commandWords[0].equals(shortCut) || commandWords[0].equals("") ) {
			return this;
		}else {
			return null;			
		}
	}

}
