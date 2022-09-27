package data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Location implements Serializable {
    private long x;
    private double y;
    private double z; //Поле не может быть null

    public Location(long x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Class constructor for Xml parser
     */
    public Location(){
        x = 0;
        y = 0;
        z = 0;
    }
    public long getX() {
        return x;
    }
    public void setX(long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }
    public void setZ(Double z) {
        this.z = z;
    }
}
