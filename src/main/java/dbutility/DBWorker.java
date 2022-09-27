package dbutility;



import data.Organization;
import data.OrganizationType;
import sun.security.krb5.internal.Ticket;
import util.Session;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DBWorker  {

    public static final Logger logger = Logger.getLogger("Database");
    private final static Connection connection = new DBConnection().connect();

    public static void init(){
        try {
            new DBInit(connection).init();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            logger.severe("Can't create tables");
        }
    }
    public static ResultSet getCollection() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Statements.takeAll.getStatement());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.warning("Problems with SQL!");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static boolean remove(String username ,Organization organization) {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet;
            resultSet = st.executeQuery("SELECT count(*) from s334341Organizations");
            resultSet.next();
            int count = resultSet.getInt("count");
            PreparedStatement prst = connection.prepareStatement(Statements.remove.getStatement());
            setOrganizationStatement(prst, username, organization,"remove");
            prst.execute();
            resultSet = st.executeQuery("SELECT count(*) from s334341Organizations");
            resultSet.next();
            int countAfterDelete = resultSet.getInt("count");
            return count != countAfterDelete;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with SQL");
            return false;
        }
    }
//    public static boolean remove_at(int index, Organization organization){
//        try {
//            Statement st = connection.createStatement();
//            ResultSet resultSet;
//            resultSet = st.executeQuery("SELECT count(*) from organizations");
//            resultSet.next();
//            int count = resultSet.getInt("count");
//            PreparedStatement prst = connection.prepareStatement(Statements.remove.getStatement());
//            setOrganizationStatement(prst, String.valueOf(index),organization,"remove_at");
//            prst.execute();
//            resultSet = st.executeQuery("SELECT count(*) from organizations");
//            resultSet.next();
//            int countAfterDelete = resultSet.getInt("count");
//            return count != countAfterDelete;
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//            logger.severe("Problems with SQL");
//            return false;
//        }
//    }
    public static boolean addOrganization(String username, Organization organization) {
        String addStatement = Statements.addOrganization.getStatement();
        try {
            PreparedStatement st = connection.prepareStatement(addStatement);
            setOrganizationStatement(st, username, organization,"add");
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with SQL");
        }
        return false;
    }
    public static boolean updateOrganization(String username, Organization organization, int id){
        if (!getByID(username, id)) return false;
        String updateStatement = Statements.updateOrganizations.getStatement();
        try{
            PreparedStatement st = connection.prepareStatement(updateStatement);
            setOrganizationStatement(st, username, organization,"update_id" );
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warning("Problems with SQL!");
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean getByID(String username, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Statements.getById.getStatement())) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) return false;
            if (!resultSet.getString("username").equals(username)) return false;
            return true;
        } catch (SQLException e) {
            logger.warning("Problems with SQL!");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean clear(String username) {
        String clearStatement = Statements.clear.getStatement();
        try {
            PreparedStatement st = connection.prepareStatement(clearStatement);
            st.setString(1, username);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with SQL");
            return false;
        }
    }

    public static boolean removeById(String username, String id) {
        String removeByIdStatement = Statements.removeById.getStatement();
        try {
            PreparedStatement st = connection.prepareStatement(removeByIdStatement);
            st.setString(1, username);
            st.setString(2, id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with SQL");
            return false;
        }
        return true;
    }


    public static void addUser(Session session) {
        String addUserStatement = Statements.addUser.getStatement();
        try {
            PreparedStatement st = connection.prepareStatement(addUserStatement);
            st.setString(1, session.getUsername());
            st.setBytes(2, session.getHashPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with sql");
        }
    }

    public static boolean checkUser(Session session){
        ResultSet resultSet;
        try {
            PreparedStatement st2 = connection.prepareStatement(Statements.checkUsername.getStatement());
            st2.setString(1,session.getUsername());
            resultSet = st2.executeQuery();
            return resultSet.next();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            logger.severe("Problems with sql");
            return false;
        }
    }

    public static boolean checkPassword(Session session){
        try{
            ResultSet resultSet;
            PreparedStatement st;
            if(session.getHashPassword() == null){
                st = connection.prepareStatement(Statements.checkNullPassword.getStatement());
            }else{
                st = connection.prepareStatement(Statements.checkPassword.getStatement());
                st.setBytes(2, session.getHashPassword());
            }
            st.setString(1, session.getUsername());
            resultSet = st.executeQuery();
            return resultSet.next();
        }catch (SQLException e) {
            logger.severe("Problems with sql");
            return false;
        }
    }

    private static Integer generateId() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Statements.generateID.getStatement());
            if (resultSet.next()) {
                return resultSet.getInt("nextval");
            }
        } catch (SQLException throwable) {
            logger.severe("SQL problem with generating id!");
        }
        return null;
    }

    private static void setOrganizationStatement(PreparedStatement st, String username, Organization organization, String commandName)
            throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(Statements.addOrganization.getStatement());

        if(commandName.equals("add") || commandName.equals("update_id")) {
            organization.setId(generateId());
            st.setLong(1, organization.getId());
        }else{
            st.setLong(1,organization.getId());
        }
        st.setString(2, organization.getName()); //
        st.setDouble(3, organization.getCoordinates().getX()); //
        st.setFloat(4, organization.getCoordinates().getY());
//        st.setDate(5, Date.valueOf(String.valueOf(organization.getCreationDate().getYear())));//
//        st.setDate(6, Date.valueOf(String.valueOf(organization.getCreationDate().getMonth())));
//        st.setDate(7,Date.valueOf(String.valueOf(organization.getCreationDate().getDayOfMonth())));
        st.setDate(5,Date.valueOf(organization.getCreationDate()));
        st.setDouble(6, organization.getAnnualTurnover());
        st.setString(7, organization.getFullName());
        st.setInt(8, organization.getEmployeesCount());
        if(organization.getType() == null){
            st.setNull(9, Types.VARCHAR);
        }else {
            st.setString(9, organization.getType().toString());
        }
        st.setString(10, organization.getPostalAddress().getStreet());
        st.setString(11, organization.getPostalAddress().getZipCode());
        st.setLong(12, organization.getPostalAddress().getTown().getX());
        st.setDouble(13, organization.getPostalAddress().getTown().getY());
        st.setDouble(14, organization.getPostalAddress().getTown().getZ());
        st.setString(15,username);
    }
}
