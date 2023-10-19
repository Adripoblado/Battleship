package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client = null;
		PrintWriter exit = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while(client == null) {
				client = createClient(client, br);
			}
			exit = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ClientThread clientThread = new ClientThread(client);
		clientThread.start();
		
		while (true) {
			String message = "";
			try {
				message = br.readLine();
				exit.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Socket createClient(Socket client, BufferedReader br) {
		try {
			System.out.println("Introduce server IP address (if you are playing on local it will be \"localhost\"): ");
			client = new Socket(br.readLine(), 8888);
		} catch (IOException e) {
			System.out.println("IP addres is not valid, please try again");
			System.out.println("ERROR: " + e + "\n");
		}
		return client;
	}
	
	public static void stop() {
		System.exit(1);
	}
}
