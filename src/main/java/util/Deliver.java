package util;



import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.abbreviateMiddle;

public class Deliver implements Runnable{
    private static final Logger logger = Logger.getLogger("Server");
    static DatagramSocket datagramSocket;
    static ByteBuffer sendingDataBuffer = ByteBuffer.allocate(65000);
    static DatagramPacket inputPacket;
    Respond respond;

    public static void setDatagramSocket(DatagramSocket datagramSocket) {
        Deliver.datagramSocket = datagramSocket;
    }

    public void setRespond(Respond respond) {
        this.respond = respond;
    }

    public static void setInputPacket(DatagramPacket inputPacket) {
        Deliver.inputPacket = inputPacket;
    }

    private static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try (ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(obj);
            } catch (Exception e) {
                logger.severe("Serialization error");
            }
            return b.toByteArray();
        }
    }

    public void run() {
        sendingDataBuffer.clear();
        try {
            sendingDataBuffer.put(serialize(respond));
            if(serialize(respond).length >65000){
                logger.severe("ты даун");
            }
            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();
            DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer.array(), sendingDataBuffer.limit(), senderAddress, senderPort);
            datagramSocket.send(outputPacket);
            logger.info("The package has been sent");
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
