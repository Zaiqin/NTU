package mypackage;
import java.io.IOException;
import java.net.*;

public class Rfc865UdpServer { 
	
	public static void main(String[] argv) { 
		
		String QUOTE = "This is my Quote of the Day";
		//
		// 1. Open UDP socket at well-known port 
		//
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(17); // QOTD Port is 17
			System.out.println("QOTD Socket is opened on server");
		} catch (SocketException e) {
			e.printStackTrace();
            System.exit(-1);
		}
		
		while (true) {
			try {
				//
				// 2. Listen for UDP request from client 
				//
				byte[] buf = new byte[512];
				DatagramPacket request = new DatagramPacket(buf, buf.length);
				System.out.println("Server is waiting for request...");
				socket.receive(request);
				
				// Server has received request
				String requestContent = new String(buf, 0, request.getLength(), "UTF-8");
                System.out.println("Received request from client: " + requestContent);
                
                // Get client's address and port to reply back to
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                
				//
				// 3. Send UDP reply to client 
				//
                String replyContent = QUOTE;
                byte[] replyBuf = replyContent.getBytes("UTF-8");
                System.out.println("Server will reply with this content: " + replyContent);
				DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length, clientAddress, clientPort);
				
				System.out.println("Server is sending reply");
				socket.send(reply);
				System.out.println("Server has sent reply");
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
	}
}