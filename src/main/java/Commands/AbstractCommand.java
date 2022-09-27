/**
 * Abstract command for all another commands.
 */
package Commands;



import Commands.interfacess.CommandInterface;
import data.Organization;
import util.CollectionManager;
import util.Respond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class AbstractCommand implements CommandInterface {
    final CollectionManager collectionManager;
    final int countOfArguments = 0;
    private String username;
    private int index;
    protected Lock locker = new ReentrantLock();
    private Organization organization;
    /**
     * @param collection collection
     */

    public AbstractCommand(CollectionManager collection) {
        collectionManager = collection;
    }

    /**
     * Executing the command
     *
     * @param argument arguments for our command
     */
    @Override
    public abstract Respond execute(Object argument);

    public int getCountOfArguments() {
        return countOfArguments;
    }
    public Organization getOrganization(){
        return organization;
    }
    public String getUsername() {
        return username;
    }

    public int getIndex(){
        return index;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }
}
