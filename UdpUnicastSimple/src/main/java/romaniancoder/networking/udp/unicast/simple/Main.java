package romaniancoder.networking.udp.unicast.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int port = 50001;
        int port2 = 50002;
        UdpUnicastClient client = new UdpUnicastClient(AddressConfig.NODE0_ADDRESS, AddressConfig.CLIENT_LISTEN_PORT);
        UdpUnicastClient client2 = new UdpUnicastClient(AddressConfig.NODE0_ADDRESS, AddressConfig.CLIENT_LISTEN_PORT2);
        UdpUnicastServer server = new UdpUnicastServer(AddressConfig.CLIENT_LISTEN_PORT);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(client);
        executorService.submit(client2);
        executorService.submit(server);
    }
}
