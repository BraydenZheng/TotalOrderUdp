package romaniancoder.networking.udp.unicast.simple;

import java.io.IOException;
import java.net.*;

/**
 * Created by dan.geabunea on 6/3/2016.
 */
public class UdpUnicastClient implements Runnable {
    private final int port;
    private InetAddress address;
    private OutputPacket<String> outputPacket;

    public UdpUnicastClient(String add, int port) {
        this.port = port;
        {
            try {
                this.address = InetAddress.getByName(add);
                this.outputPacket = new OutputPacket(address.toString(), port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        /**
         * Bind the client socket to the port on which you expect to
         * read incoming messages
         */
        try (DatagramSocket clientSocket = new DatagramSocket(port, address)) {
            /**
             * Create a byte array buffer to store incoming data. If the message length
             * exceeds the length of your buffer, then the message will be truncated. To avoid this,
             * you can simply instantiate the buffer with the maximum UDP packet size, which
             * is 65506
             */

            byte[] buffer = new byte[65507];

            // Set a timeout of 3000 ms for the client.
//            clientSocket.setSoTimeout(3000);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);

                /**
                 * The receive method will wait for 3000 ms for data.
                 * After that, the client will throw a timeout exception.
                 */
                clientSocket.receive(datagramPacket);


                String receivedMessage = new String(datagramPacket.getData());
                outputPacket.setContent(receivedMessage);
//                System.out.println(receivedMessage);
                System.out.print("Node IP: " + outputPacket.ip + ", ");
                System.out.print("Port: " + outputPacket.port + ", ");
                System.out.println("Content: " + outputPacket.content + ".");

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client is closing.");
        }
    }
}
