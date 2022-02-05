package controller.command.factory;

import controller.command.Command;
import controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static controller.Path.INDEX_VIEW;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("", new MainPageCommand());
        commands.put("login", new LoginCommand());
        commands.put("process_login", new ProcessLoginCommand());
        commands.put("sign_up", new SignUpCommand());
        commands.put("process_sign_up", new ProcessSignUpCommand());
        commands.put("log_out", new LogOutCommand());
        commands.put("search_cars", new SearchCarsCommand());
        commands.put("view_deal", new ViewDealCommand());
        commands.put("book", new BookCommand());
        commands.put("process_booking", new ProcessBookingCommand());
        commands.put("my_booking", new MyBookingCommand());
        commands.put("sortByPrice", new SortCarsByPriceCommand());
        commands.put("sortByName", new SortCarsByNameCommand());
        commands.put("manager_page", new ManagerPageCommand());
        commands.put("admin_page", new AdminPageCommand());
    }

    public static Command getCommand(String url){
        // Поменять логику в плане синтаксиса ниже
        System.out.println("CURRENT COMMAND -" + url);
        Command command = commands.getOrDefault(url, (r,response) -> INDEX_VIEW);
        return command;
    }

}
