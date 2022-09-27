package Commands;

import data.Organization;
import dbutility.DBWorker;
import util.CollectionManager;
import util.Respond;

import java.util.stream.Stream;

/**
 * The command class for remove_by_id.
 */
public class Remove_by_ID extends AbstractCommand {
    final int countOfArguments = 1;
    /**
     * Class constructor
     *
     * @param collection class for remove elements from collection
     */
    public Remove_by_ID(CollectionManager collection) {
        super(collection);
    }


    /**
     * Removes an item from the collection if its id the input value.
     * @param argument id of elements which we need to remove
     * @return
     */
    @Override
    public synchronized Respond execute(Object argument) {

        Stream<Organization> stream = collectionManager.getAllElements().stream();
        Stream<Organization> test = stream.filter(x->x.getId() == Integer.parseInt((String) argument));
        Object[] arr =  test.toArray();
        for(Object obj:arr){
            if(DBWorker.remove(getUsername(),(Organization) obj)){
                collectionManager.remove((Organization) obj);
            }else return new Respond("");
        }

        return new Respond("Objects have been deleted");
    }
}