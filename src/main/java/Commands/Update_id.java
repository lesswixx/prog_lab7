package Commands;


import com.sun.org.apache.xpath.internal.operations.Or;
import data.Organization;

import dbutility.DBWorker;
import util.CollectionManager;
import util.Command;
import util.Respond;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class for command update_id
 */
public class Update_id extends AbstractCommand {
    /**
     * Class constructor
     *
     * @param collection class for update elements from collection
     */
    public Update_id(CollectionManager collection) {
        super(collection);
    }

    /**
     * Update the id of the object that based on input values.
     * @param argument id of element which we need to update
     * @return Respond(String answer)
     */
    @Override
    public synchronized Respond execute(Object argument)  throws  ClassCastException{
        Organization organization = getOrganization();
        Organization organizationToUpdate = collectionManager.getById( Integer.parseInt((String)argument));
        if(organizationToUpdate == null) return new Respond("Object hasn't been updated!");
        if(DBWorker.updateOrganization(getUsername(),getOrganization(),Integer.parseInt((String)argument))){
            organization.setId(Integer.parseInt((String)argument));
            collectionManager.remove(organizationToUpdate);
            collectionManager.add(organization);
            return new Respond("Object has been updated!");

        }else {
            return new Respond("Object hasn't been updated!");
        }
    }

}