/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static server.Server.logger;

/**
 *
 * @author hudik1
 */
public class ConnectionHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        logger.log(Level.INFO, String.valueOf(clientSocket.getPort()));

        String newLine;
        try {
            while ((newLine = in.readLine()) != null) {
                System.out.println(newLine);
                out.println("Echo:" + newLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
