package pr2.game.Controller.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pr2.game.Game;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;
import pr2.game.Exceptions.FileContentsException;

public class LoadCommand extends Command{
	protected static final String name = "load";
	protected static final String shortCut = "load";
	private static final String details = "load";
	private static final String help = "Load the game.";

	private String filename;
	public LoadCommand() {
		super(name, shortCut, details, help);
		filename="";
	}

	@Override
	public Boolean execute(Game game) throws CommandExecuteException {
		try (BufferedReader inChars = new BufferedReader(new FileReader(filename + ".dat"))) {	//try-with-resources
			inChars.readLine();
			inChars.readLine();
			game.Load(inChars);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch(NumberFormatException e) {	//Si hay un error de parseo el archivo esta mal y se lanza excepcion
			throw new FileContentsException();
		}
		System.out.println("Game successfully loaded from file "+filename+".");
		return null;
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
