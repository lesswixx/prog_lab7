package util;

import data.Organization;

import java.util.*;
import java.util.function.Predicate;

public class CollectionManager {
    public static String initTime;
    private final Vector<Organization> collection;
    private Organization organization;
    private Command command;
    public CollectionManager(){
        collection = new Vector<>();
        initTime = new Date().toString();
    }
    public void add(Organization organization){
        collection.add(organization);
    }
    public void clear() {
        collection.clear();
    }
    /**
     * return size of collection
     * @return int
     */
    public int size() {
        return collection.size();
    }
    public Organization getById(int id) {
        for (Organization organization : collection) {
            if (organization.getId().equals(id)) return organization;
        }
        return null;
    }
    public String getUsername(){
        return command.getUsername();
    }
    public void remove(Organization obj){
        collection.remove(obj);
    }
    public int getUsedId(){
        return organization.getId();
    }

    /**
     * Return initialization time of collection.
     * @return String
     */
    public String getInitTime() {
        return initTime;
    }
    public Organization getIndex(int arg){
        return collection.get(arg);
    }
    /**
     * Return last element of collection.
     * @return Ticket
     */

    public Organization first(){
        return collection.firstElement();
    }
    /**
     * Return all elements of collection into List.
     * @return List<Ticket>
     */
    public List<Organization> getAllElements(){
        return new ArrayList<>(collection);
    }

    /**
     * Return iterator of collection.
     * @return Iterator<Ticket>
     */
    public Iterator<Organization> iterator(){
        return collection.iterator();
    }
    public void numberAdd(int arg ,Organization organization){
        collection.add(arg, organization);
    }
    /**
     * Remove element from collection if it is matches the filter.
     */
    public void removeIf(Predicate<Organization> filter) {
        Objects.requireNonNull(filter);
        collection.removeIf(filter);
    }
}
