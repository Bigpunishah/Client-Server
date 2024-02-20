//Vikel Cunningham

package clientServerPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Scanner;

public class Client {
    Client(String Ip, int port) throws UnknownHostException, IOException {
        Socket socket = new Socket(Ip, port);
        System.out.println("Connected to Server.");

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("input.txt");
        printWriter.flush();

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

        File myFile = new File("output.txt");
        if (myFile.exists()) {
            System.out.println("File already exists.");
        } else {
            System.out.println("Creating new file: " + myFile);
        }
        FileWriter writeFile = new FileWriter(myFile);

        Scanner reader = new Scanner(inputStreamReader);
        System.out.println("Server info loading...");
        while (reader.hasNextLine()) {
            writeFile.write(reader.nextLine());
            writeFile.write("\n");

        }

        reader.close();
        writeFile.close();
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("localhost", 999);

    }
}