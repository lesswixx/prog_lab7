package Commands;


import data.Organization;
import dbutility.DBWorker;
import util.CollectionManager;
import util.Respond;

public class Clear extends AbstractCommand {

    public Clear(CollectionManager collection){
        super(collection);
    }
    public int getCountOfArguments(){
        return 0;
    }
    @Override
    public synchronized Respond execute(Object arg){

        for(Organization t:collectionManager.getAllElements()){
            if(DBWorker.remove(getUsername(),t)){
                collectionManager.remove(t);
            }
        }

        return new Respond("The collection has been cleared");
    }
}
