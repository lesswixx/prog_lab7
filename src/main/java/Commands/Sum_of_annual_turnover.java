package Commands;

import data.Organization;
import util.CollectionManager;
import util.Respond;


import java.util.Iterator;

public class Sum_of_annual_turnover extends AbstractCommand {

    public Sum_of_annual_turnover(CollectionManager collection) {
        super(collection);

    }

    @Override
    public synchronized Respond execute(Object arg)  {
            try {
                if (collectionManager.size()!=0){
                    Iterator<Organization> iterator = collectionManager.iterator();
                    double sum = 0;

                    while (iterator.hasNext()){
                        Organization organization = iterator.next();
                        sum += organization.getAnnualTurnover();
                    }
                    return new Respond(Double.toString(sum));
                }
            }catch (ClassCastException e){
                e.printStackTrace();
            }
            return new Respond("The collection is empty");

    }


}
