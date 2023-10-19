package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			System.out.println("New game server - PORT: " + server.getLocalPort());
			System.out.println("Type 'start' to start the game");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			Game game = new Game();
			ServerThread serverThread = new ServerThread(server, game);
			serverThread.start();
			
			String command = "";
			while(!command.equalsIgnoreCase("start")) {
				command = br.readLine();
			}
			
			serverThread.begin();
			
			System.out.println("End of connection accepting thread");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
