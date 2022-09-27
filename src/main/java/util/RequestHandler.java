package util;



import dbutility.DBWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {

    private final static Logger logger = Logger.getLogger("Server");
    private static CollectionManager collectionManager;
    private static Invoker invoker;
    Object inputObject;

    public RequestHandler(Object inputObject, CollectionManager collectionManager) {
        this.inputObject = inputObject;
        this.collectionManager = collectionManager;
        invoker = new Invoker(collectionManager);

    }
    @Override
    public void run() {
        Respond respond;
        Command command;
        try {
            command = (Command) inputObject;
            logger.info("Transmitted command: " + command.getCommandName());
            respond = invoker.execute(command);
        } catch (Exception e) {
            Session session = (Session) inputObject;
            if (DBWorker.checkUser(session)) {
                if (DBWorker.checkPassword(session)) {
                    logger.info("user:" + session.getUsername() + " is exists");
                    respond = new Respond("true");
                } else {
                    logger.info("wrong password for " + session.getUsername());
                    respond = new Respond("false");
                }
            } else {
                logger.info("user:" + session.getUsername() + " isn't exists");
                DBWorker.addUser(session);
                respond = new Respond("true");
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Deliver deliver = new Deliver();
        deliver.setRespond(respond);
        // отправка ответа
        executorService.submit(deliver);
    }
}
