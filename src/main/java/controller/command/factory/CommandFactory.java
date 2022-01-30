package controller.command.factory;

import controller.command.Command;
import controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("process_login", new ProcessLoginCommand());
        commands.put("sign_up", new SignUpCommand());
        commands.put("process_sign_up", new ProcessSignUpCommand());
        commands.put("search_cars", new SearchCarsCommand());
        commands.put("car_info", new CarInfoCommand());
    }

    public static Command getCommand(String url){
        // Поменять логику в плане синтаксиса ниже
        Command command = commands.getOrDefault(url, (r,response) -> "index.jsp");
        return command;
    }

}
