import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class backupServer {

    public static void main (String []args){

    DatagramSocket aSocket = null;

        try{
        aSocket = new DatagramSocket(23000);
	    byte[] buffer = new byte[1000];
        System.out.println("Backup Server is ready and accepting clients' requests");
        while(true){
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(request);
            String b = "$";
            String msg = new String(request.getData(),0,request.getLength());
            String newmsg = new String(b + msg);
            newmsg+= " 3";
            DatagramPacket reply = new DatagramPacket(newmsg.getBytes(),newmsg.length(),request.getAddress(),request.getPort());
            aSocket.send(reply);
        }

        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
 	        }catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
	            }finally {
		            if(aSocket != null) aSocket.close();
	}
    }
    
}
