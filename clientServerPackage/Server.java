//Vikel Cunningham

package clientServerPackage;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    Server(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started...");

        Socket socket = serverSocket.accept();
        System.out.println("Client Connected!");

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

        Scanner reader = new Scanner(inputStreamReader);

        String str = reader.nextLine();
        System.out.println("Client: " + str + " open sesame!");

        PrintWriter servPrintWriter = new PrintWriter(socket.getOutputStream());
        File inputFile = new File(str);
        Scanner sc = new Scanner(inputFile);
        if (inputFile.exists()) {
            while (sc.hasNextLine()) {
                servPrintWriter.println(sc.nextLine());
                servPrintWriter.flush();
            }
        }

        sc.close();
        servPrintWriter.close();
        reader.close();
        serverSocket.close();

    }

    public static void main(String[] args) throws IOException {
        new Server(999);
    }
}