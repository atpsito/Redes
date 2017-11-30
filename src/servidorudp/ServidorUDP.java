/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author alexander
 */
public class ServidorUDP {
    //Creamos una variable puerto con el numero de puerto que vamos a abrir
    private static int PORT=9091;
    
    public static void main(String[] args )throws IOException{
        //Se crea un socket para las comunicaciones del servidor que tiene el puerto de escucha
        DatagramSocket serverSock = new DatagramSocket(PORT);
       //Se escribe el puerto de escucha en consola para control
        System.err.println("Server listening on PORT "+PORT+" UDP conection");
        
        
        try{
            //Para escucha permanente se crea un while
        while(true){
            //Receive Packet
         byte bufferReceive[]=new byte[128];
            //Crea un datagrama que recibira el paquete del cliente con el mensaje y la longitud del mensaje
            DatagramPacket receivePack=new DatagramPacket(bufferReceive,bufferReceive.length);
            //Se recive el datagrama por el puerto especificado y se guarda el datagrama entrante en el datagrama que creamos
            serverSock.receive(receivePack);
            //Extrae la direccion completa del cliente
            InetAddress clientAdd=receivePack.getAddress();
            //Guarda el puerto del cliente en una variable int
            int clientPort=receivePack.getPort();
            //Imprimimos el puerto del cliente y al direccion Ip en consola para control
            System.out.println("Client port: "+clientPort+" ip: "+clientAdd.getHostAddress());
            
            //Crea el mensaje que vamos a enviar
            String msg="Servidor de Alexander";
            //Transforma el mensaje a Bytes
            byte bufferSend[]=msg.getBytes();
            //Crea el datagrama con el mensaje, la longitud del mensaje, la Ip del Cliente y El puerto del cliente
            DatagramPacket sendPack=new DatagramPacket(bufferSend,bufferSend.length,clientAdd,clientPort);
            //envia el datagrama por el Socket Creado
            serverSock.send(sendPack);
            
        }
       }finally{
            // Si se finaliza 
        serverSock.close();
        }
    
    }
    
}
