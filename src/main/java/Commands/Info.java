package Commands;

import Commands.AbstractCommand;
import util.CollectionManager;
import util.Respond;

/**
 * The command class for info.
 */
public class Info extends AbstractCommand {
    /**
     * Class constructor
     *
     * @param collection class for work with collection
     */
    public Info(CollectionManager collection) {
        super(collection);
    }


    /**
     * Print information about collection(Type, data of init and count of objects).
     * @param argument empty list
     * @return
     */
    @Override
    public synchronized Respond execute(Object argument) {

        String s = "Type of Collection - Vector" + "\n" +
                "Date of init - " + collectionManager.getInitTime() + "\n" +
                "Count of object in Collection - " + collectionManager.size() + "\n";

        return new Respond(s);
    }
}
