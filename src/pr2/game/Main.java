package pr2.game;

import java.util.Random;

import pr2.game.Controller.Controller;
import pr2.game.Exceptions.InvalidParameterException;
import pr2.game.Exceptions.LevelParseException;

public class Main {

	// Practica realiazada por
	// Vicentiu Tiberius Roman

	// Para que sirve width y height en gameObjectBoard
	// Como se mueven las naves
	// Cuando se inicializa el ovni
	// Nave explosiva y comprar supermisil
	// Para que sirve BoardPrinter y FormattedPrinter

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		int seed = 0;
		try {
			if (args.length == 2) {
				seed = Integer.parseInt(args[1]);
			} else if (args.length == 1) {
				seed = new Random().nextInt(1000);
			} else {
				throw new InvalidParameterException();
			}
			if(Level.parse(args[0])==null) {
				throw new LevelParseException();
			}
			Game game = new Game(Level.parse(args[0]), Integer.parseInt(args[1]));
			Controller controller = new Controller(game);
			controller.run();

		} catch (InvalidParameterException | LevelParseException ex) {
			System.out.format(ex.getMessage() + " %n %n");
		} catch (NumberFormatException ex) {
			System.out.println("Usage: Main <EASY|HARD|INSANE> [seed]: the seed must be a number");
		}
	}
}
