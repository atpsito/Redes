/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetcp;

import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author root
 */
public class ClienteTCP {

    private static int SERVER_PORT = 9090;
	
	/**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the server, then connects to it and displays the message that
     * it serves.
     */
	
	public static void main(String[] args) throws IOException {
        
		String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n" +
            							   "running the date service on port "+SERVER_PORT+":");
                int a= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el primer numero ha sumar"));
                int b= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el segundo numero ha sumar"));
		//Establece la conexión con el servidor mediante un socket
		Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
                
                PrintWriter cout= new PrintWriter(clientSocket.getOutputStream(),true);
                cout.println(a);
                cout.println(b);
                
		//Obtiene el mensaje enviado por el servidor a través del socket
		InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());

		//Lee los datos del mensaje
		BufferedReader input = new BufferedReader(inputStream);
		String answer = input.readLine();

		//Imprime los datos del mensaje
		JOptionPane.showMessageDialog(null, answer);
		System.exit(0);
    }

}
