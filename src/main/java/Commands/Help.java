package Commands;

import Commands.AbstractCommand;
import util.CollectionManager;
import util.Respond;

import java.util.ArrayList;
import java.util.List;

/**
 * The command class for help.
 */
public class Help extends AbstractCommand {
    /**
     * Class constructor
     *
     * @param collection class for work with collection
     */
    public Help(CollectionManager collection) {
        super(collection);
    }

    /**
     * Print all available commands.
     * @param argument empty list
     * @return
     */
    @Override
    public synchronized Respond execute(Object argument) {
        List<Object> help = new ArrayList<>();
        help.add("help : output help for available commands");
        help.add("info: output information about the collection (type, initialization date, number of items, etc.) to the standard output stream.)");
        help.add("show : output to the standard output stream all the elements of the collection in a string representation");
        help.add("add {element} : add a new element to the collection");
        help.add("remove_by_id id : remove an item from the collection by its id");
        help.add("clear : clear the collection");
        help.add("execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.");
        help.add("exit : terminate the program (without saving to a file)");
        help.add("add_if_min {element} : add a new element to the collection if its value exceeds the value of the largest element of this collection");
        StringBuilder ans = new StringBuilder();
        for (Object string : help) {
            ans.append(string).append("\n");
        }

        return new Respond(ans.toString());
    }
}
