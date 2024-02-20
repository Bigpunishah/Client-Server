//Vikel Cunningham
//! For future referance if forgotten: START SERVER FIRST

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
        Socket socket = new Socket(Ip, port);                                   //Creating a new Socket for the connection to a Server with the same port
        System.out.println("Connected to Server.");

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());    //Creating an output stream for the Sockets connection
        printWriter.println("input.txt");                                     //Sending this String (The file name we need to open)
        printWriter.flush();

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());   //Creating an output stream to receive information from Server

        File myFile = new File("output.txt");                          //Creating a new file named.. well you see what it is
        if (myFile.exists()) {                                                  //If it already exists then...
            System.out.println("File already exists.");
        } else {
            System.out.println("Creating new file: " + myFile);                 //If not, then create one
        }
        FileWriter writeFile = new FileWriter(myFile);                          //Creating a FileWriter, to write to the file

        Scanner reader = new Scanner(inputStreamReader);                        //A reader for the file (Scanner)
        System.out.println("Server info loading...");
        while (reader.hasNextLine()) {                                          //While theres another line of text
            writeFile.write(reader.nextLine());                                 //We are going to write to the file "..."
            writeFile.write("\n");                                          //For the next line being read. Adds new line for the next line of info

        }

        reader.close();                                                         //Close everything open
        writeFile.close();
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("localhost", 999);                                  //New client with that IP & port number

    }
}