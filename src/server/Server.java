/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hudik1
 */
public class Server {

    static Logger logger = Logger.getLogger("Server");
    private static int portNum = 100;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        logger.log(Level.INFO, "Start server");
        try {
            ServerSocket serverSocket = new ServerSocket(portNum);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ConnectionHandler(clientSocket)).start();
            }

//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//            
//            logger.log(Level.INFO, String.valueOf(clientSocket.getPort()));
//            
//            String newLine;
//            while ((newLine = in.readLine()) != null) {
//                System.out.println(newLine);
//                out.println("Echo:" + newLine);
//            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
