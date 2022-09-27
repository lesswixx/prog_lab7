package util;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class RequestReceiver implements Runnable {
    private static final Logger logger = Logger.getLogger("Server");
    static DatagramSocket datagramSocket;
    static ByteBuffer receivingDataBuffer = ByteBuffer.allocate(10240);
    static DatagramPacket inputPacket;
    static ExecutorService executor;
    private static CollectionManager collectionManager;
    public RequestReceiver(DatagramSocket aDatagramSocket, CollectionManager collectionManager){
        datagramSocket = aDatagramSocket;
        this.collectionManager = collectionManager;

    }

    public static void setExecutor(ExecutorService executor) {
        RequestReceiver.executor = executor;
    }

    public void run() {
        while (true) {
            receivingDataBuffer.clear();
            inputPacket = new DatagramPacket(receivingDataBuffer.array(), receivingDataBuffer.capacity());
            try {
                datagramSocket.receive(inputPacket);
                logger.info("Package received");
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(inputPacket.getData()));
                Object receivedObject = objectInputStream.readObject();
                Deliver.setInputPacket(inputPacket);
                Deliver.setDatagramSocket(datagramSocket);
                // обработка полученного запроса
                Thread t = new Thread(new RequestHandler(receivedObject, collectionManager));
                t.start();

            } catch (IOException | ClassNotFoundException e) {
                logger.severe(e.getMessage());
            }
        }
    }
}
