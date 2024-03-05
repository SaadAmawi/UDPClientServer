

import java.net.*;
import java.util.Hashtable;
import java.io.*;

public class UDPServer{
   public static void main(String args[]) throws InterruptedException {
    DatagramSocket aSocket = null;
    try{

        //Setting Port Number and Buffer
        aSocket = new DatagramSocket(20000);
	    byte[] buffer = new byte[1000];
	   	System.out.println("Server is ready and accepting clients' requests ... ");

        //Instantiating Sum and Hashtable values
        Integer Sum=0;
        Hashtable<String,Integer> clients = new Hashtable<>();


		while(true){
            //Receiving request from client
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);

            //Creating Client IP address and Port String and Creating msg string from Client Request
            String clientId = request.getAddress().toString()+"/"+request.getPort();
            System.out.println("clientID"+clientId);
            String msg = new String(request.getData(),0,request.getLength());

            //If Client ID has no Sum value, make sum value 0
            if(Sum==null){
                Sum=0;
                clients.put(clientId,Sum);
            }

            //get Sum from the Client Request and add it to the hashtable
            Sum += Integer.parseInt(msg);
            clients.put(clientId,Sum);
            msg = Sum + "";

            //Creating the message string the server will reply with and building reply datagram
            String message = new String("Your IP Address Is: "+clientId + ", Sum is: " + msg);
            DatagramPacket reply = new DatagramPacket(message.getBytes(), message.length(),request.getAddress(),request.getPort());

            //Send reply and add sleep timer of 2s
            aSocket.send(reply);
            Thread.sleep(2000);
		}

        //Exception Handeling
 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
	}finally {
		if(aSocket != null) aSocket.close();
	}
   }
}

