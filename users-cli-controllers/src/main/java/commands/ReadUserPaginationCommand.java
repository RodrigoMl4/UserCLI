package commands;


import models.User;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@CommandLine.Command(name = "-readP", description = "Read Users")

public class ReadUserPaginationCommand extends Command implements Callable<Integer> {
    @CommandLine.Option(names = {"-p", "--page"}, description = "The page number to read", defaultValue = "1")
    private int page;

    @CommandLine.Option(names = {"-s", "--size"}, description = "The number of users per page", defaultValue = "5")
    private int size;

    @Override
    public Integer call() throws Exception {
        try {
            var users = getUsersService().getUsersPaginated(page, size);
            System.out.printf("Users on page %d:\n", page);
            AtomicInteger count = new AtomicInteger(0);
            for (User user : users) {
                System.out.printf("[%d] %s\n", count.addAndGet(1), user);
            }
            return 0;
        } catch (Exception ex) {
            System.out.printf("Cannot read users. %s", ex.getMessage());
            return 1;
        }
    }
}
