package mypackage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Rfc865UdpClient { 
	
    //private static final String SERVER_NAME = "hwlab1.scse.ntu.edu.sg";
	private static final String SERVER_NAME = "localhost";
	
	public static void main(String[] argv) { 
		
		//
		// 1. Open UDP socket 
		//
		try {
			DatagramSocket socket = null;
			InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
			socket = new DatagramSocket(); // QOTD Port is 17
			socket.connect(serverAddress, 17);
			System.out.println("UDP Client connected on port " + 17 + " to server: " + serverAddress.getCanonicalHostName());
		
			//
			// 2. Send UDP request to server 
			//
			String content = "Client's content";
			byte[] buf = content.getBytes("UTF-8");
			System.out.println("Client is going to send this: " + content);
			
			DatagramPacket request = new DatagramPacket(buf, buf.length);
			System.out.println("Client is sending this");
			socket.send(request);
			System.out.println("Client has sent message to server");
			//
			// 3. Receive UDP reply from server 
			//
			byte[] replyBuf = new byte[512];
			DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
			System.out.println("Client is waiting for reply");
			socket.receive(reply);
			
			System.out.println("Client has received reply");
			String replyContent = new String(replyBuf, 0, reply.getLength(), "UTF-8");
            System.out.println("Received reply: " + replyContent);
		
		} catch (SocketException e) {
			e.printStackTrace();
            System.exit(-1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}