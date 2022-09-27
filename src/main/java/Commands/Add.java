package Commands;


import data.Organization;
import dbutility.DBWorker;
import util.CollectionManager;
import util.Respond;


public class Add extends AbstractCommand {

    public Add(CollectionManager collection) {
        super(collection);
    }




    @Override
    public synchronized Respond execute(Object arg) {
        Respond respond;

        Organization organization = (Organization) arg;
        if (DBWorker.addOrganization(getUsername(),organization)){
            collectionManager.add(organization);
            respond = new Respond("Object has been added");
        }else {
            respond = new Respond("Object hasn't been add object");
        }

        return respond;
    }
}
