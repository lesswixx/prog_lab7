package data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Coordinates implements Serializable ,Comparable<Coordinates>{
    private Double x; //Максимальное значение поля: 570, Поле не может быть null
    private Float y; //Поле не может быть null


    /**
     * Class constructor
     *
     * @param x - first coordinate
     * @param y - second coordinate
     */
    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Class constructor for Xml parser
     */
    public Coordinates(){
        x = null;
        y = null;
    }

    public Double getX() {
        return x;
    }

    @XmlElement(name = "CoordinateX")
    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    @XmlElement(name = "CoordinateY")
    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }


    private double dist0(Coordinates aCoordinates) {
        return Math.sqrt(Math.pow(aCoordinates.getX(),2) + Math.pow(aCoordinates.getY(),2));
    }


    @Override
    public int compareTo(Coordinates o) {
        double dist = dist0(this) - dist0(o);
        if (dist > 0) return 1;
        else if (dist ==0) return 0;
        else return -1;
    }
}
