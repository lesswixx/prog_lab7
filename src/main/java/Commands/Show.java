package Commands;

import Commands.AbstractCommand;
import data.Organization;
import sun.security.krb5.internal.Ticket;
import util.CollectionManager;
import util.Respond;

import java.util.stream.Stream;

/**
 * The command class for save.
 */
public class Show extends AbstractCommand {
    /**
     * Class constructor
     *
     * @param collection class for show elements from collection
     */
    public Show(CollectionManager collection) {
        super(collection);
    }

    /**
     * print all elements of collection.
     * @param argument empty list
     * @return
     */
    @Override
    public synchronized Respond execute(Object argument) {

        StringBuilder ans = new StringBuilder();
        for (Organization organization : collectionManager.getAllElements()) {
            ans.append(organization.toString()).append("\n");
        }
        if(collectionManager.size()==0){
            ans = new StringBuilder("At the moment the collection is empty");
            ans.append("\n");
        }
        Stream<Organization> stream = collectionManager.getAllElements().stream();

        return new Respond(ans.toString());
    }
}
