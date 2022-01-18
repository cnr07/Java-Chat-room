import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ServerHelper extends Thread {

	private Socket incoming;

	public ServerHelper(Socket incoming) {
		this.incoming = incoming;
	}

	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					incoming.getInputStream()));
			PrintWriter out;
			while (true) {
				
				String str = in.readLine();
				System.out.println("Server received : " + str);
				//if(str.equals("null")) {}
				if (str.equals("null")) {
					String ss=TCPMutliServer.getHashMap().get(incoming);
					//HashMap<Socket, String> hashMap=TCPMutliServer.getHashMap();
					TCPMutliServer.getHashMap().replace(incoming, ss, "");
					
					HashMap<Socket, String> hashMap=TCPMutliServer.getHashMap();
					for(Socket s: TCPMutliServer.getHashMap().keySet()) {
						out = new PrintWriter(new OutputStreamWriter(
								s.getOutputStream()));
						out.printf("Chatroom participants:\n");
						for(Socket s1: TCPMutliServer.getHashMap().keySet()) {
							if(!hashMap.get(s1).equals("") && s.getPort() != incoming.getPort()) {
								out.println(hashMap.get(s1));
								out.flush();
							}
							
						}
						
					}
					break;
					//Thread.sleep(3000);
					//getTime.secondsDisplay=29;
					///System.out.println("OUTTTTTTTTTTTTTTTTTTTT "+ss);
					//break;
				} else {
					for(Socket s: TCPMutliServer.getHashMap().keySet()) {
						
						if(s.getPort() != incoming.getPort() && str.startsWith("PM ")) {
							String afterPM=str.substring(3);
							int indxOfName=afterPM.indexOf(" ");
							
							StringTokenizer strTok = new StringTokenizer(afterPM, " ");
							   String[] def = new String[strTok.countTokens()];
							   int count = 0;
							   while(strTok.hasMoreTokens())
							   {
							       def[count++] = strTok.nextToken();
							   }
							
							//System.out.println("QQQQQQ "+afterPM);
							if(TCPMutliServer.getHashMap().containsValue(def[0])) {
								//System.out.println("BURADADADADA "+def[0]);
								if(TCPMutliServer.getHashMap().get(s).equals(def[0])) {
									out = new PrintWriter(new OutputStreamWriter(
											s.getOutputStream()));
									out.println(TCPMutliServer.getHashMap().get(incoming)
											+ " : " + str);
									out.flush();break;
								}
								
							}
							
						}
						if(s.getPort() != incoming.getPort() && !str.startsWith("PM ")) {
							out = new PrintWriter(new OutputStreamWriter(
									s.getOutputStream()));
							out.println(TCPMutliServer.getHashMap().get(incoming)
									+ " : " + str);
							out.flush();
						}
					}
				}
			}
			//incoming.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}