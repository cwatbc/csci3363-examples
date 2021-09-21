package csci3363.echo1;

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
            /* get one connection from a client */
            var client = listener.accept();    

            /* setup input and output streams for the client */
            var sock_in = new Scanner(client.getInputStream());
            var sock_out = new PrintWriter(client.getOutputStream(), true);

            /* read one line of plain text from the client */
            var message = sock_in.nextLine();

            /* send the same line of text back to the client */
            sock_out.println(message);

        } catch(IOException e) {
            /* Have to catch IOexceptions for most socket calls */
            System.out.println("Network error!");
        }
    }
}
