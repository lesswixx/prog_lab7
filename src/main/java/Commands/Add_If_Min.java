package Commands;
import data.Organization;
import dbutility.DBWorker;
import util.CollectionManager;
import util.Respond;


public class Add_If_Min extends AbstractCommand {
    public Add_If_Min(CollectionManager collection){
        super(collection);
    }

    @Override
    public synchronized Respond execute(Object arg){
        Respond respond;
        Organization organization = (Organization) arg;
        if (collectionManager.first().compareTo(organization) > 0){
            if(DBWorker.addOrganization(getUsername(),organization)){
              Add add = new Add(collectionManager);
              add.setUsername(getUsername());
              respond = add.execute(organization);
              respond = new Respond("Object has been added");
            }
            respond = new Respond("Problems with sql");

        }else {

            respond = new Respond("The object was not added");
        }
        return respond;
    }

}
