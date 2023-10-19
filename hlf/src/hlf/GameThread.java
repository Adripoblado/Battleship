package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameThread extends Thread {

	Socket client;
	Game game;
	Player player;

	public GameThread(Socket client, Game game, Player player) {
		this.client = client;
		this.game = game;
		this.player = player;
	}

	@Override
	public void run() {
		try {
			BufferedReader inText = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter outText = new PrintWriter(client.getOutputStream(), true);

			game.enlistPlayer(player);

			outText.println(
					"CHOOSE YOUR FLEET DISPLAY BY WRITING THE INITIAL POSITION (A0 to J9) AND DIRECTION (V FOR VERTICAL (UP TO DOWN) AND H FOR HORIZONTAL (LEFT TO RIGHT))");
			for (int i = 0; i < 5; i++) {
				switch (i) {
				case 0: {
					game.showDisplay(player, outText);
					outText.println("4 box ship:");
					game.settleFleet(player, inText, outText, i);
					game.showDisplay(player, outText);
					break;
				}
				case 1: {
					outText.println("First 3 box ship:");
					game.settleFleet(player, inText, outText, i);
					game.showDisplay(player, outText);
					break;
				}
				case 2: {
					outText.println("Seccond 3 box ship:");
					game.settleFleet(player, inText, outText, i);
					game.showDisplay(player, outText);
					break;
				}
				case 3: {
					outText.println("First 2 box ship:");
					game.settleFleet(player, inText, outText, i);
					game.showDisplay(player, outText);
					break;
				}
				case 4: {
					outText.println("Seccond 2 box ship:");
					game.settleFleet(player, inText, outText, i);
					game.showDisplay(player, outText);
					break;
				}
				}
			}
			game.calculateReadyPlayers(player);
			outText.println("Waiting for the host to start the game...");
			System.out.println(player.getName() + " is ready and waiting for the game to begin");
			System.out.println(game.readyPlayers + " out of 2 players ready");
			game.playerWait();

			while (game.active) {
				if (player.id == 1) {
					game.getPlayer(2).setEnemy(player);
					if (game.turn % 2 != 0) {
						playTurn(inText, outText, player, game.getPlayer(2));
					} else {
						waitTurn(outText);
					}
				} else {
					game.getPlayer(1).setEnemy(player);
					if (game.turn % 2 == 0) {
						playTurn(inText, outText, player, game.getPlayer(1));
					} else {
						waitTurn(outText);
					}
				}

				if (!game.isAlive(player)) {
					game.active = false;
				}
			}
			if (game.isAlive(player)) {
				outText.println("YOU WON!!");
			} else {
				outText.println("GAME OVER, YOU LOST!!");
			}
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void playTurn(BufferedReader inText, PrintWriter outText, Player player, Player enemy) throws IOException {
		outText.println("It´s your turn, please make your move");
		String shot = inText.readLine();
		while (!game.validatePosition(player, shot, outText)) {
			shot = inText.readLine();
		}
		game.calculateShot(player, enemy, shot);
		game.showDisplay(player, outText);
		game.manageTurn();
		game.playerNotify();
	}

	private void waitTurn(PrintWriter outText) {
		outText.println("Opponent's turn, please wait...");
		game.playerWait();
		game.showDisplay(player, outText);
		game.showFleetStatus(player, player.getEnemy(), outText);
	}
}
