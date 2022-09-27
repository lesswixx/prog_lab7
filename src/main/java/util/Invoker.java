package util;




import Commands.*;

import java.util.HashMap;
import java.util.Map;


public class Invoker {
    private final CollectionManager collectionManager;
    private Command command;
    private final Map<String, AbstractCommand> commands;

    public Invoker(CollectionManager aCollectionManager){
        collectionManager = aCollectionManager;
        commands = new HashMap<>();
        initMap();
    }

    public Respond execute(Command newCommand){
        AbstractCommand command = commands.get(newCommand.getCommandName());
        command.setUsername(newCommand.getUsername());
        return command.execute(newCommand.getArgument());
    }


    public void initMap(){
        commands.put("add", new Add(collectionManager));
        commands.put("add_if_min", new Add_If_Min(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("help", new Help(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("remove_by_id", new Remove_by_ID(collectionManager));
        commands.put("update_id", new Update_id(collectionManager));
        commands.put("min_by_creation_date", new Min_by_creation_date(collectionManager));
        commands.put("sum_of" , new Sum_of_annual_turnover(collectionManager));
        commands.put("print_ascending" , new Print_ascending(collectionManager));
    }
}
