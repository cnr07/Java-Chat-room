import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
	static String nickNamee="";
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			String host = "localhost";		
			Socket socket = new Socket(host, 6789);
			
			
			
			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			new ClientPrinter(in).start();
			String str;
			while (true) {
				//out.println(getTime.secondsDisplay);
				
				str = inFromUser.readLine();
				out.println(str);
				out.flush();
				
				
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	public static String getNickName() {
		return nickNamee;
	}

}