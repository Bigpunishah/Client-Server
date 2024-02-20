//Vikel Cunningham
//! For future referance if forgotten: START SERVER FIRST

package clientServerPackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    Server(int port) throws IOException {                           //Initializer
        ServerSocket serverSocket = new ServerSocket(port);         //Server starts using the port number
        System.out.println("Server started...");

        Socket socket = serverSocket.accept();                      //Accepting the incoming client 
        System.out.println("Client Connected!");

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());   //Opens input stream for information from client

        Scanner reader = new Scanner(inputStreamReader);            //scanner as reader

        String str = reader.nextLine();                             //incoming string named str
        System.out.println("Client: " + str + " open sesame!");     

        PrintWriter servPrintWriter = new PrintWriter(socket.getOutputStream());                //Opening output stream to send information to client
        File inputFile = new File(str);                             //Creating new file from the incoming name the client sends
        Scanner sc = new Scanner(inputFile);                        //Scanner for the open File
        if (inputFile.exists()) {                                   //Condition statement if the file exists - continue
            while (sc.hasNextLine()) {                              //While the file has another line, we do...
                servPrintWriter.println(sc.nextLine());             //this! (Sending the contents of .txt file to the Client)
                servPrintWriter.flush();
            }
        }

        sc.close();                                                 //Closing everything that is open
        servPrintWriter.close();
        reader.close();
        serverSocket.close();

    }

    public static void main(String[] args) throws IOException {
        new Server(999);                                      //Creating a new Server with that port value.
    }
}