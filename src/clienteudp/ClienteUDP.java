/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteudp;

import java.io.IOException;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class ClienteUDP {
    //indicamos el puerto al que debemos conectarnos con el servidor
    private static int SERVER_PORT=9091;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Aqui guardamos la dirección IP del servidor que se ingresa por teclado
        String sAdress = JOptionPane.showInputDialog("Enter IP Adress connect on port "+SERVER_PORT+":");
        
        //Creamos un socket para datagrama
        DatagramSocket clientSock=new DatagramSocket();
        //Transformamos en Bytes la dirección Ip ingresada
        byte bufferSend[]=sAdress.getBytes();
        //Creamos el datagrama para enviar la informacion, se crea con el mensaje, la longitud del mensaje, la dirección IP del servidor y el puerto
        DatagramPacket sendPack= new DatagramPacket(bufferSend,bufferSend.length,InetAddress.getByName(sAdress),SERVER_PORT);
        //Se envía el mensaje por el socket creado anteriormente
        clientSock.send(sendPack);
        //Se inicializa una variable para almacenar bytes
        byte bufferReceive[]=new byte[128];
        //Creamos un datagrama para recivir la respuesta del servidor
            DatagramPacket receivePack=new DatagramPacket(bufferReceive,bufferReceive.length);
           //Recibimos el datagrama mediante el socket del cliente y lo guardamos en el datagrama creado anteriormente
            clientSock.receive(receivePack);
    
         //Las siguientes instrucciones transforman los bytes que llegaron en un mensaje que el usuario pueda leer
            InputStream myInputStream = new ByteArrayInputStream(receivePack.getData());
            BufferedReader input =  new BufferedReader(new InputStreamReader(myInputStream));
            String answer= input.readLine();
         //Despliega mensaje en un cuadro de dialogo
            JOptionPane.showMessageDialog(null, answer);
           
            // El socket del cliente se finaliza para evitar dejar puertos abiertos
            clientSock.close();
            // El programa termina su ejecucion
            System.exit(0);
    }
    
    
}