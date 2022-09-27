package data;

import java.io.Serializable;
import java.util.Arrays;

public enum OrganizationType implements Serializable {
    COMMERCIAL("COMMERCIAL"),
    PUBLIC("PUBLIC"),
    GOVERNMENT("GOVERNMENT"),
    TRUST("TRUST"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY");
    final String type;

    OrganizationType(String s) {
        type=s;

    }

    public String getType(){
        return type;
    }

}
