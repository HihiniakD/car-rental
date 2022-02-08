package controller.command.factory;

import controller.command.Command;
import controller.command.impl.*;
import controller.command.impl.admin.*;
import controller.command.impl.manager.*;

import java.util.HashMap;
import java.util.Map;

import static controller.Path.INDEX_VIEW;


/**
 * Factory of commands.
 * Return command class
 */
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
        commands.put("approveBooking", new ApproveBookingCommand());
        commands.put("declineBooking", new DeclineBookingCommand());
        commands.put("finishBooking", new FinishBookingCommand());
        commands.put("processDeclineBooking", new ProccessDeclineBookingCommand());
        commands.put("processFinishBooking", new ProcessFinishBookingCommand());
        commands.put("users", new UsersCommand());
        commands.put("blocking", new ProcessBlockCommand());
        commands.put("managers", new ManagersCommand());
        commands.put("addManager", new AddManagerCommand());
        commands.put("cars", new CarsCommand());
        commands.put("processAddManager", new ProcessAddManagerCommand());
        commands.put("deleteCar", new DeleteCarCommand());
        commands.put("editCar", new EditCarCommand());
        commands.put("processEditCar", new ProcessEditCarCommand());
        commands.put("addCar", new AddCarCommand());
        commands.put("processAddCar", new ProcessAddCarCommand());
    }

    /**
     * @param url representing a command as a string
     * @return Command class
     */
    public static Command getCommand(String url){
        Command command = commands.getOrDefault(url, (r,response) -> INDEX_VIEW);
        return command;
    }

}
