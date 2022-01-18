import java.io.*;
import java.net.Socket;
import java.util.HashMap;
public class getTime extends Thread{
	public static long secondsDisplay;
	public getTime() {
		
	}
	public void run() {
		try {
			PrintWriter out;
			long elapsedTime,elapsedSeconds;
			long startTime = System.currentTimeMillis();
			
			int i=0;
			while(true) {
				
				elapsedTime = System.currentTimeMillis() - startTime;
				elapsedSeconds = elapsedTime / 1000;
				secondsDisplay = elapsedSeconds % 31;
				if(getTime.secondsDisplay==30) {
					//System.out.println("SANIYELERRRR: "+secondsDisplay);
					HashMap<Socket, String> hashMap=TCPMutliServer.getHashMap();
					for(Socket s: TCPMutliServer.getHashMap().keySet()) {
						out = new PrintWriter(new OutputStreamWriter(
								s.getOutputStream()));
						out.printf("Chatroom participants:\n");
						for(Socket s1: TCPMutliServer.getHashMap().keySet()) {
							if(!hashMap.get(s1).equals("")) {
								out.println(hashMap.get(s1));
								out.flush();
							}
							
						}
						
					}
					
					
					//System.out.println("COUNTERRRRRRRRRRR: "+i);
					//out.flush();
					Thread.sleep(1000);
				}
				else {
					///System.out.println("SANIYELERRRR: "+secondsDisplay);
				}
				i++;
			}
			
			
			
			
			
		}catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
