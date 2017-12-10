/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortcp;

import java.io.*;
import java.net.*;

/**
 *
 * @author alexander
 */
public class ServidorTCP {

    private static int PORT = 9090;
	
     /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        
    	ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server listening on port "+PORT);
        int resp;
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    //Clase InputStream para leer los datos del mensaje
                    InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());

		//Lee los datos del mensaje
		BufferedReader input = new BufferedReader(inputStream);
		int a= Integer.parseInt(input.readLine());
                int b= Integer.parseInt(input.readLine());
                System.out.println("Se ha recibido los datos "+a+" "+b);
                resp=sumarNumeros(a,b);// se realiza la suma
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Sumando "+a+"+"+b+" la respuesta es "+resp);// se envia la repuesta
                    
                } finally {
                    socket.close();
                }
            }
        }
        finally {
        	serverSocket.close();// cierra el socket del servidor
        }
}
    //funcion que suma dos numeros
    public static int sumarNumeros(int a, int b){
    int c= a+b;
        System.out.println("Sumando "+a+"+"+b+" la respuesta es "+c );
        return a+b;
    }
}

