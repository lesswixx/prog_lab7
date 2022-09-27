package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"street" , "zipCode" , "town"})
public class Address implements Serializable {
    private String street; //Поле может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле не может быть null

    /**
     *
     * @param street
     * @param zipCode
     * @param town
     */
    public Address(String street, String zipCode, Location town){
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }
    public Address(){

    }

    public Location getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setTown(Location town) {
        this.town = town;
    }
}