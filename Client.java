import java.net.*;
import java.io.*;

public class Client {

    public static void main(String [] args){

        DatagramSocket aSocket = null;

        try{
            aSocket=new DatagramSocket();
            byte [] m = args[0].getBytes();
            int serverPort = 22000;
            InetAddress servAddress = InetAddress.getByName(args[1]);
            DatagramPacket request = new DatagramPacket(m,args[0].length(),servAddress,serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Received Reply: " + new String(reply.getData(), 0, reply.getLength()));

        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
            }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
                }finally {
                    if(aSocket != null) aSocket.close();
    }
    }
    
}
