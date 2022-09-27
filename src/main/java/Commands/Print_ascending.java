package Commands;

import data.Organization;
import util.CollectionManager;
import util.Respond;

import java.util.Vector;
import java.util.stream.Stream;


public class Print_ascending extends AbstractCommand {


    public Print_ascending(CollectionManager collection) {
        super(collection);

    }

    @Override
    public synchronized Respond execute(Object arg) {

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
