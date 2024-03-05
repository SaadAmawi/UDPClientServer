
import java.net.*;
import java.io.*;

public class UDPClient{

    public static void main(String args[]) {
    // args[0] = message to be sent to the server;
     //new branch comment
    DatagramSocket aSocket = null;

        try {
            aSocket= new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(args[0]);
            int serverPort = 20000;
            for(int i=1;i<=10;i++){
                String msg = i+ "";
            DatagramPacket request = new DatagramPacket(msg.getBytes(),msg.length(),serverAddress, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Received Reply: " + new String(reply.getData(), 0, reply.getLength()));	}
        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
        }finally {
            if(aSocket != null) aSocket.close();
        }
    }
}

