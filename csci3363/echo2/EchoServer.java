package csci3363.echo2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class EchoServer {
    
    public static void main(String[] args) {

        try (
            /* Accept connections on port 3363 */        
            var listener = new ServerSocket(3363);
        ) {
            /* process one client connection at a time, forever */
            while(true) {
                /* get one connection from a client */
                var client = listener.accept();    
                System.out.println(client);

                /* setup input and output streams for the client */
                var sock_in = new Scanner(client.getInputStream());
                var sock_out = new PrintWriter(client.getOutputStream(), true);

                /* read one line of plain text from the client */
                var message = sock_in.nextLine();

                /* send the same line of text back to the client */
                sock_out.println(message);

                /* close the client connection */
                client.close();
            }
        } catch(IOException e) {
            /* Have to catch IOexceptions for most socket calls */
            System.out.println("Network error!");
        }
    }
}
