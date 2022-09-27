package Commands;

import data.Organization;
import util.CollectionManager;
import util.Respond;


import java.util.Iterator;

public class Min_by_creation_date extends AbstractCommand {


    public Min_by_creation_date(CollectionManager collection) {
        super(collection);



    }

    @Override
    public synchronized Respond execute(Object arg) throws ClassCastException {
        if(!arg.equals("")){
            Iterator<Organization> iterator = collectionManager.iterator();
            Organization min = iterator.next();
            while (iterator.hasNext()){
                Organization organizationToCompare = iterator.next();
                if (organizationToCompare.getCreationDate().compareTo(min.getCreationDate()) < 0){
                    return new Respond(String.valueOf(true));
                }

            }
            return new Respond("Minimum date: " + min.getCreationDate());
        }
        return new Respond("The argument is incorrect");
    }
}
