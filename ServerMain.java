import java.net.*;
import java.util.Hashtable;
import java.io.*;
 
 
public class ServerMain {
 
    public static void main(String args[]) throws InterruptedException {
        //args[0] = backupIP
        //args[1] = IP address of backup
 
 
    DatagramSocket aSocket = null;
    try{
       
     
        aSocket = new DatagramSocket(22000);
        byte[] buffer = new byte[1000];
        InetAddress backupIP = InetAddress.getByName(args[0]);
        int backupPort = Integer.parseInt(args[1]);
        System.out.println("Primary Server is ready and accepting clients' requests");
 
        while(true){
            //Receiving request from client
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);
            InetAddress clientIP = request.getAddress();
            int clientPort = request.getPort();
            String msg = new String(request.getData(),0,request.getLength());
            // String type[] = StringUtils.substringBefore(filename, ".");;
            // System.out.println(type[.0]);
            msg += " 2";
            DatagramPacket reply = new DatagramPacket(msg.getBytes(), msg.length(),backupIP,backupPort);
            aSocket.send(reply);
           
           
            request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);
            msg = new String(request.getData(),0,request.getLength());
            msg += " 4";
             reply = new DatagramPacket(msg.getBytes(), msg.length(),clientIP,clientPort);
            aSocket.send(reply);
 
           
     
 
        }
 
        //Exception Handeling
    }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
    }catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
    }finally {
        if(aSocket != null) aSocket.close();
    }
   }
}