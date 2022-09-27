package dbutility;

public enum Statements {
    addOrganization("INSERT INTO s334341Organizations " +
            "(id, name, CoordinateX, CoordinateY, creationDate, annualTurnover, fullName, " +
            "employeesCount, type, street, zipCode, TownX, TownY, TownZ, username) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),

    generateId("SELECT nextval('ids')"),

    addUserWithPassword("INSERT INTO s334341Users (username, hashPassword) VALUES(?, ?)"),

    checkUser("SELECT * FROM 334341Users WHERE username=? AND hashPassword=?"),
    getSalt("SELECT * FROM s335153users WHERE login=?"),
    clear("DELETE FROM s334341Organizations " +
            "WHERE user = ?"),
    removeById("DELETE FROM s334341Organizations " +
            "WHERE user = ? AND id = ?"),
    updateOrganizations("UPDATE s334341Organizations SET " +
            "(name = ?, CoordinateX = ?, CoordinateY=?, creationDate=?, annualTurnover=?, fullName=?, " +
            "employeesCount=?, type=? , street=?, zipCode=?, TownX=?, TownY=?, TownZ=? WHERE id = ?)" ),

    getById("SELECT * FROM s334341Organizations WHERE id = ?"),
    checkUsername("SELECT * FROM s334341users where username = ?"),
    deleteById("DELETE FROM s334341Organizations WHERE id = ?"),

    clearAllByUser("DELETE FROM ss334341Organizations WHERE author = ?"),
    checkPassword("SELECT * FROM s334341users WHERE username = ? AND hashpassword = ?"),
    generateID("SELECT nextval('sequence')"),

    addUser("INSERT INTO s334341users " +
            "(username,hashpassword) " +
            "VALUES(?,?)"),
    checkNullPassword("SELECT * FROM s334341users where username = ? AND hashpassword is NULL"),

    remove("DELETE FROM s334341Organizations where id = ? " +
            "AND name = ? " +
            "AND CoordinateX = ? " +
            "AND CoordinateY = ? " +
            "AND creationDate = ? " +
            "AND annualTurnover = ? " +
            "AND fullName = ? " +
            "AND employeesCount = ? " +
            "AND  type = ? " +
            "AND street = ? " +
            "AND zipCode = ? " +
            "AND TownX = ? " +
            "AND TownY = ? " +
            "AND TownZ = ?" +
            "AND username = ?"),


    takeAll("SELECT * FROM s334341Organizations");

    private final String statement;
    Statements(String aStatement) {
        statement = aStatement;
    }
    public String getStatement() {
        return statement;
    }
}
