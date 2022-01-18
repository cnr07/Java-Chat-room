import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class TCPMutliServer {
	
	
	private static HashMap<Socket, String> hashMap = new HashMap<Socket, String>();
	
	
	public static void main(String[] args) {
		new getTime().start();
		Scanner scanner = new Scanner(System.in);
		int i = 0;
		
		try {
			
			System.out.println("Server is working...");
			ServerSocket serverSocket = new ServerSocket(6789);
			while (true) {
				//System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
				Socket incoming = serverSocket.accept();
				//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				
				DataInputStream in = new DataInputStream(new BufferedInputStream(incoming.getInputStream()));
				OutputStream os = incoming.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.println("Type your nick");
				String as=in.readLine();
				pw.println("Start chatting");
				
				i++;
				
				
				getHashMap().put(incoming, as);
				new ServerHelper(incoming).start();
				
				
				
				
				
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	

	public static synchronized HashMap<Socket, String> getHashMap() {
		return hashMap;
	}

	public static synchronized void setHashMap(HashMap<Socket, String> hashMap) {
		TCPMutliServer.hashMap = hashMap;
	}
	
}