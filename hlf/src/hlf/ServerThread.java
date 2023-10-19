package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	ServerSocket server = null;
	Game game;
	BufferedReader inText;
	
	public ServerThread(ServerSocket server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void run() {
		while(true) {
			Socket client = new Socket();
			try {
				client = server.accept();
				PrintWriter outText = new PrintWriter(client.getOutputStream(), true);
				
				outText.println("Please, type your username: ");
				inText = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String name = inText.readLine();
				
				while(name.isBlank() || name == null) {
					outText.print("Name cannot be empty, please write another: ");
					name = inText.readLine();
				}
				Player player = new Player(name);
				System.out.println(name + " joined the game");
				
				GameThread gameThread = new GameThread(client, game, player);
				gameThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void begin() {
		game.start();
	}
}