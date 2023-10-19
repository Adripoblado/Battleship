package hlf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {

	int turn, readyPlayers = 0;
	boolean active;
	ArrayList<Player> players = new ArrayList<>();

	private String[] setShipCoordinates(Player player, String direction, int shipSize, int x, int y) {
		String[] cases = new String[shipSize];
		if (direction.equalsIgnoreCase("V")) {
			for (int i = 0; i < shipSize; i++) {
				cases[i] = String.valueOf(x).concat(String.valueOf(y));
				x = x + 1;
			}
		} else {
			for (int i = 0; i < shipSize; i++) {
				cases[i] = String.valueOf(x).concat(String.valueOf(y));
				y = y + 1;
			}
		}
		return cases;
	}

	public boolean asignBoatPossition(Player player, String initialPosition, String direction, int shipN,
			PrintWriter outText) {
		int x = calculateXAxis(initialPosition), y = Integer.parseInt(String.valueOf(initialPosition.charAt(1)));

		switch (shipN) {
		case 0: {
			if (checkCollisions(player, setShipCoordinates(player, direction, 4, x, y))) {
				player.setS4(setShipCoordinates(player, direction, 4, x, y));
				player.fleet.add(setShipCoordinates(player, direction, 4, x, y));
				return true;
			} else {
				outText.println("Collision detected!");
				return false;
			}
		}
		case 1: {
			if (checkCollisions(player, setShipCoordinates(player, direction, 3, x, y))) {
				player.setS31(setShipCoordinates(player, direction, 3, x, y));
				player.fleet.add(setShipCoordinates(player, direction, 3, x, y));
				return true;
			} else {
				outText.println("Collision detected!");
				return false;
			}
		}
		case 2: {
			if (checkCollisions(player, setShipCoordinates(player, direction, 3, x, y))) {
				player.setS32(setShipCoordinates(player, direction, 3, x, y));
				player.fleet.add(setShipCoordinates(player, direction, 3, x, y));
				return true;
			} else {
				outText.println("Collision detected!");
				return false;
			}
		}
		case 3: {
			if (checkCollisions(player, setShipCoordinates(player, direction, 2, x, y))) {
				player.setS21(setShipCoordinates(player, direction, 2, x, y));
				player.fleet.add(setShipCoordinates(player, direction, 2, x, y));
				return true;
			} else {
				outText.println("Collision detected!");
				return false;
			}
		}
		case 4: {
			if (checkCollisions(player, setShipCoordinates(player, direction, 2, x, y))) {
				player.setS22(setShipCoordinates(player, direction, 2, x, y));
				player.fleet.add(setShipCoordinates(player, direction, 2, x, y));
				return true;
			} else {
				outText.println("Collision detected!");
				return false;
			}
		}
		}
		return false;
	}

	private boolean checkCollisions(Player player, String[] ship) {
		boolean clear = true;

		for (String coord : ship) {
			if (player.display[Integer.parseInt(coord.substring(0, 1))][Integer.parseInt(coord.substring(1))]
					.contains("#")) {
				clear = false;
			}
		}

		if (clear) {
			for (String coord : ship) {
				player.display[Integer.parseInt(coord.substring(0, 1))][Integer
						.parseInt(coord.substring(1))] = " \u001B[32m# ";
			}
		}

		return clear;
	}

	public synchronized void calculateShot(Player sender, Player receiver, String shot) {
		int x = calculateXAxis(shot), y = Integer.parseInt(String.valueOf(shot.charAt(1)));

		if (checkShot(receiver, shot)) {
			sender.enemyDisplay[x][y] = " \u001B[31mX ";
			receiver.display[x][y] = " \u001B[31mX ";
		} else {
			sender.enemyDisplay[x][y] = " \u001B[34mO ";
			receiver.display[x][y] = " \u001B[34mO ";
		}
	}

	private synchronized boolean checkShot(Player player, String shot) {
		int x = calculateXAxis(shot), y = Integer.parseInt(String.valueOf(shot.charAt(1)));

		for (int i = 0; i < player.getS4().length; i++) {
			if (player.s4[i].equalsIgnoreCase(String.valueOf(x) + String.valueOf(y))) {
				player.bs4[i] = true;
				return true;
			}
		}

		for (int i = 0; i < player.getS31().length; i++) {
			if (player.s31[i].equalsIgnoreCase(String.valueOf(x) + String.valueOf(y))) {
				player.bs31[i] = true;
				return true;
			}
			if (player.s32[i].equalsIgnoreCase(String.valueOf(x) + String.valueOf(y))) {
				player.bs32[i] = true;
				return true;
			}
		}

		for (int i = 0; i < player.getS21().length; i++) {

			if (player.s21[i].equalsIgnoreCase(String.valueOf(x) + String.valueOf(y))) {
				player.bs21[i] = true;
				return true;
			}
			if (player.s22[i].equalsIgnoreCase(String.valueOf(x) + String.valueOf(y))) {
				player.bs22[i] = true;
				return true;
			}
		}
		return false;
	}

	public void populateDisplay(Player player) {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				player.display[x][y] = " \u001B[0m- ";
				player.enemyDisplay[x][y] = " \u001B[0m- ";
			}
		}
	}

	public void showDisplay(Player player, PrintWriter outText) {
		String[] letters = { " A ", " B ", " C ", " D ", " E ", " F ", " G ", " H ", " I ", " J " };

		outText.print("      A L L Y    D I S P L A Y     \t      E N E M Y  D I S P L A Y    \n");
		outText.print("    0  1  2  3  4  5  6  7  8  9   \t    0  1  2  3  4  5  6  7  8  9  \n");
		for (int x = 0; x < 10; x++) {
			outText.print(letters[x]);
			for (int y = 0; y < 10; y++) {
				outText.print(player.display[x][y]);
			}
			outText.print("\t");
			outText.print(letters[x]);
			for (int y = 0; y < 10; y++) {
				outText.print(player.enemyDisplay[x][y]);
			}
			outText.println();
		}
	}

	public void showFleetStatus(Player player, Player enemy, PrintWriter outText) {
		outText.println(" Ally fleet status: \t\t\t  Enemy fleet status: ");
		outText.println(" Ship 4: " + calculateShipStatus(player.bs4) + "\t\t\t  Ship 4: " + calculateShipStatus(enemy.bs4));
		outText.println(" Ship 31: " + calculateShipStatus(player.bs31) + "\t\t\t  Ship 31: " + calculateShipStatus(enemy.bs31));
		outText.println(" Ship 32: " + calculateShipStatus(player.bs32) + "\t\t\t  Ship 32: " + calculateShipStatus(enemy.bs32));
		outText.println(" Ship 21: " + calculateShipStatus(player.bs21) + "\t\t\t  Ship 21: " + calculateShipStatus(enemy.bs21));
		outText.println(" Ship 22: " + calculateShipStatus(player.bs22) + "\t\t\t  Ship 22: " + calculateShipStatus(enemy.bs22));
	}

	private int calculateXAxis(String shot) {

		int shotLetter = shot.charAt(0);

		if(shotLetter >= 97 && shotLetter <= 106) {
			return shotLetter - 97;
		}
		
		if(shotLetter >= 65 && shotLetter <= 74) {
			return shotLetter - 65;
		}
		
		return -1;
	}

	public String calculateShipStatus(boolean[] ship) {
		int hp = ship.length;
		for (int i = 0; i < ship.length; i++) {
			if (ship[i]) {
				hp = hp - 1;
			}
		}
		return "Ship HP: " + hp + "/" + ship.length;
	}

	public void enlistPlayer(Player player) {
		players.add(player);
		player.id = players.indexOf(player) + 1;
		populateDisplay(player);
		player.fleet = new ArrayList<String[]>();
		player.alive = true;
	}

	public void settleFleet(Player player, BufferedReader inText, PrintWriter outText, int i) {
		String initialPosition = null;
		String direction = null;

		try {
			outText.println("Initial position (format > A0 to J9): ");
			initialPosition = inText.readLine();
			while (!validatePosition(player, initialPosition, outText)) {
				initialPosition = inText.readLine();
			}
			outText.println("Direction (format > H or V): ");
			direction = inText.readLine();
			while (!direction.equalsIgnoreCase("H") && !direction.equalsIgnoreCase("V")) {
				outText.println(
						"DIRECTION ENTERED IS NOT VALID, PLEASE CHOOSE \"V\" FOR VERTICAL (UP TO DOWN) OR \"H\" FOR HORIZONTAL (LEFT TO RIGHT)");
				direction = inText.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!asignBoatPossition(player, initialPosition, direction, i, outText)) {
			outText.println("Indicated position is not valid, collision detected!");
			settleFleet(player, inText, outText, i);
		}
	}

	public boolean validatePosition(Player player, String position, PrintWriter outText) {
		if (position != null && position.length() == 2 && Character.isLetter(position.charAt(0))
				&& Character.isDigit(position.charAt(1))) {
			if (position.substring(0, 1).equalsIgnoreCase("A") || position.substring(0, 1).equalsIgnoreCase("B")
					|| position.substring(0, 1).equalsIgnoreCase("C") || position.substring(0, 1).equalsIgnoreCase("D")
					|| position.substring(0, 1).equalsIgnoreCase("E") || position.substring(0, 1).equalsIgnoreCase("F")
					|| position.substring(0, 1).equalsIgnoreCase("G") || position.substring(0, 1).equalsIgnoreCase("H")
					|| position.substring(0, 1).equalsIgnoreCase("I")
					|| position.substring(0, 1).equalsIgnoreCase("J")) {
				return true;
			} else {
				outText.println("POSITION ENTERED IS NOT VALID, PLEASE WRITE IT AGAIN");
			}
		} else {
			outText.println("POSITION ENTERED IS NOT VALID, PLEASE WRITE IT AGAIN");
		}
		return false;
	}

	public synchronized void manageTurn() {
		turn = turn + 1;
	}

	public synchronized void playerWait() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void playerNotify() {
		notifyAll();
	}

	public Player getPlayer(int id) {
		return players.get(id - 1);
	}

	public void start() {
		active = true;
		playerNotify();
		turn = 1;
		if (players.size() == 2) {
			System.out.println("THE GAME BEGINS...\n");
			playerNotify();
		} else {
			System.out.println("COULD NOT START THE GAME (NOT ENOUGH PLAYERS)");
		}
	}

	public synchronized int calculateReadyPlayers(Player player) {
		player.ready = true;
		readyPlayers = readyPlayers + 1;
		return readyPlayers;
	}

	public synchronized boolean isAlive(Player player) {

		int totalHP = 0;

		for (int i = 0; i < player.bs4.length; i++) {
			if (!player.bs4[i]) {
				totalHP++;
			}
		}

		for (int i = 0; i < player.bs31.length; i++) {
			if (!player.bs32[i]) {
				totalHP++;
			}
			if (!player.bs31[i]) {
				totalHP++;
			}
		}

		for (int i = 0; i < player.bs21.length; i++) {
			if (!player.bs21[i]) {
				totalHP++;
			}
			if (!player.bs22[i]) {
				totalHP++;
			}
		}

		if (totalHP == 0) {
			player.alive = false;
		}

		return player.alive;
	}
}
