package romaniancoder.networking.udp.unicast.simple;

import lombok.Data;

/**
 * @author Brayden
 * @create 4/6/22 11:08 AM
 * @Description packet used to output ip, port format in console
 */
@Data
public class OutputPacket<T> {
    public String ip;
    public int port;
    public T content;

    public OutputPacket(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
