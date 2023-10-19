package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	Socket client = null;
	BufferedReader br = null;
	Client c;

	public ClientThread(Socket client) {
		super();
		this.client = client;
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				String text = br.readLine();
				if (text == null || text.equalsIgnoreCase("null")) {
					break;
				}
				System.out.println(text);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Lost connection with host, exiting program...");
				System.exit(0);
			}
		}
		Client.stop();
	}
}
