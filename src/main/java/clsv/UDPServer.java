package clsv;

import data.*;
import dbutility.DBWorker;
import util.CollectionManager;
import util.RequestReceiver;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.DatagramChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UDPServer {
    static final int PORT = 5554;
    private static final Logger logger = Logger.getLogger("Server");
    static DatagramSocket datagramSocket;
    static CollectionManager collectionManager = new CollectionManager();
    public static void main(String[] args) {
        logger.info("The server has started working");
        DBWorker.init();
        getCollection();


        try{
            datagramSocket = new DatagramSocket(PORT);

        }catch (SocketException e){
            e.printStackTrace();
        }
        //чтение запроса
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new RequestReceiver(datagramSocket, collectionManager));
    }

    private static void getCollection() {
        try {
            ResultSet data = DBWorker.getCollection();
            while (data.next()) {
                collectionManager.add(new Organization(
                        data.getInt(1), // id
                        data.getString(2), // name
                        new Coordinates(data.getDouble(3), data.getFloat(4)),// Coordinates
                        data.getDate(5).toLocalDate(),
//                        LocalDate.of(data.getDate(5).toLocalDate().getYear(),
//                                data.getDate(6).toLocalDate().getMonth(),
//                                data.getDate(7).toLocalDate().getDayOfMonth()),
                        data.getDouble(6), //annualTurnover
                        data.getString(7), //fullName
                        data.getInt(8), // employeesCount
                        OrganizationType.valueOf(data.getString(9)), //OrganizationType
                        new Address(data.getString(10),//street
                                data.getString(11),//zipCode
                                new Location(data.getLong(12),//townX
                                        data.getDouble(13),//townY
                                        data.getDouble(14)))));//townZ

            }
        } catch (SQLException | NullPointerException e) {
            logger.warning("Collection is empty!");
        }
    }
}
