package controller.command.factory;

import controller.command.Command;
import controller.command.impl.ErrorCommand;
import controller.command.impl.SearchCarsCommand;
import controller.command.impl.ShowLoginCommand;
import controller.command.impl.ShowSignUpCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("search_cars", new SearchCarsCommand());
        commands.put("show_login", new ShowLoginCommand());
        commands.put("show_sign_up", new ShowSignUpCommand());
    }

    public static Command getCommand(String url){
        // Поменять логику в плане синтаксиса ниже
        Command command = commands.getOrDefault(url, (r,response) -> "index.jsp");
        return command;
    }

}
