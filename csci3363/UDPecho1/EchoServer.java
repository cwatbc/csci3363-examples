package csci3363.UDPecho1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    
    public static void main(String[] args) {

        try (
            /* Use port 3363 */        
            var sock = new DatagramSocket(3363);
        ) {
            /* use a buffer to hold the incoming bytes */
            var in_buf = new byte[1600];

            /* need a packet to hold the incoming data, stored in in_buf */
            var in_packet = new DatagramPacket(in_buf, in_buf.length);

            /* read one packet in from the socket */
            sock.receive(in_packet);

            /* convert the byte array to a string */
            var in_msg = new String(in_buf, StandardCharsets.UTF_8);

            System.out.println(in_msg);

            /* create a buffer with the response message (same as received message) */
            var out_buf = in_msg.getBytes();

            /* create a response packet, with destination the same as the original sender */
            var out_packet = new DatagramPacket(out_buf, out_buf.length, in_packet.getSocketAddress());

            /* send the response packet */
            sock.send(out_packet);

        } catch(IOException e) {
            /* Have to catch IOexceptions for most socket calls */
            System.out.println("Network error!");
        }
    }
}
