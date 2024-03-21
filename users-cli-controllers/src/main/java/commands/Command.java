package commands;


import services.ServicesFacade;
import services.UsersService;

public class Command {
    protected UsersService getUsersService() throws Exception {
        return ServicesFacade.getInstance().getUsersService();
    }
}
