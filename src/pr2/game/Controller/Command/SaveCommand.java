package pr2.game.Controller.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;

public class SaveCommand extends Command {

	protected static final String name = "save";
	protected static final String shortCut = "save";
	private static final String details = "save \"filename\"";
	private static final String help = "Save the game.";

	private String filename;

	public SaveCommand() {
		super(name, shortCut, details, help);
		this.filename="";
	}

	@Override
	public Boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter(filename + ".dat"))) {	//try-with-resources
			outChars.write(game.stringify());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Game successfully saved in file "+filename+".dat. Use the load command to reload it.");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if ((commandWords[0].equalsIgnoreCase(name) || commandWords[0].equalsIgnoreCase(shortCut))) {
			if(commandWords.length == 2) {
				filename = commandWords[1];				
			}else {
				throw new CommandParseException("Usage: " + details);
			}
			return this;
		}
		return null;
	}

}
