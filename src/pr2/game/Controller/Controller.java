package pr2.game.Controller;

import java.util.Scanner;

import pr2.game.Game;
import pr2.game.Controller.Command.Command;
import pr2.game.Controller.Command.CommandGenerator;
import pr2.game.Exceptions.CommandExecuteException;
import pr2.game.Exceptions.CommandParseException;
import pr2.view.FormattedPrinter;

public class Controller {
	private Game game;
	private Scanner in;
	private FormattedPrinter printer;
	private final static String PROMPT = "Command > ";
	private final static String unknownCommandMsg = "Comando desconocido.";

	public Controller(Game ge) {
		game = ge;
		in = new Scanner(System.in);
		printer = new FormattedPrinter(game, Game.DIM_FILA, Game.DIM_COLUMNA); // Instancia de game, columnas, filas
	}

	public void run() {
		printUpdatedGame();
		while (!game.isFinished()) {
			System.out.print(PROMPT);
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			try {
				Command command = CommandGenerator.parseCommand(words);
				if (command != null) {
					Boolean update = command.execute(game);
					if (update != null && update) {
						printUpdatedGame();
					} else if (update == null) {
						printGame();
					}
				} else {
					System.out.println("  " + unknownCommandMsg);
				}
			} catch (CommandParseException | CommandExecuteException ex) {
				System.out.println("Failed to " + words[0] + "\nCause of Exception:\n  " + ex.toString());
			}
		}
		System.out.println(game.getWinnerMessage());
	}

	private void printGame() {
		System.out.println(printer.toString());
	}

	private void printUpdatedGame() {
		game.update();
		System.out.println(printer.toString());
	}
}
