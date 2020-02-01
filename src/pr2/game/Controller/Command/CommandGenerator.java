package pr2.game.Controller.Command;

import pr2.game.Exceptions.CommandParseException;

public class CommandGenerator {
	private static Command[] availableCommands = { new MoveCommand(), new ShootCommand(),new SniperCommand(), new ShockwaveCommand(),
			new ListCommand(), new ResetCommand(), new BuyCommand(), new ListPrintersCommand(), new SaveCommand(),new LoadCommand(),
			new StringifyCommand(), new UpdateCommand(), new HelpCommand(), new ExitCommand()};

	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command command = null;
		for (Command com : availableCommands) {
			command = com.parse(commandWords);
			if (command != null) {
				return command;
			}
		}
		return command;
	}

	public static String commandHelp() {
		String help = "";
		for (Command com : availableCommands) {
			help += "  " + com.helpText();
		}

		return help;
	}
}
