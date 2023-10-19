package hlf;

import java.util.ArrayList;

public class Player {

	int id;
	String name;
	boolean ready, alive;
	boolean[][] receivedShotsRecord = new boolean[10][10],
			sentShotsRecord = new boolean[10][10];
	boolean[] bs4 = new boolean[4],
			bs31 = new boolean[3], 
			bs32 = new boolean[3],
			bs21 = new boolean[2],
			bs22 = new boolean[2];
	String[] s4, s31, s32, s21, s22;
	String[][] display = new String[10][10];
	String[][] enemyDisplay = new String[10][10];
	String lastHit;
	ArrayList<String[]> fleet = new ArrayList<>();
	Player enemy;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean[][] getReceivedShotsRecord() {
		return receivedShotsRecord;
	}

	public void setReceivedShotsRecord(boolean[][] receivedShotsRecord) {
		this.receivedShotsRecord = receivedShotsRecord;
	}

	public boolean[][] getSentShotsRecord() {
		return sentShotsRecord;
	}

	public void setSentShotsRecord(boolean[][] sentShotsRecord) {
		this.sentShotsRecord = sentShotsRecord;
	}

	public boolean[] getBs4() {
		return bs4;
	}

	public void setBs4(boolean[] bs4) {
		this.bs4 = bs4;
	}

	public boolean[] getBs31() {
		return bs31;
	}

	public void setBs31(boolean[] bs31) {
		this.bs31 = bs31;
	}

	public boolean[] getBs32() {
		return bs32;
	}

	public void setBs32(boolean[] bs32) {
		this.bs32 = bs32;
	}

	public boolean[] getBs21() {
		return bs21;
	}

	public void setBs21(boolean[] bs21) {
		this.bs21 = bs21;
	}

	public boolean[] getBs22() {
		return bs22;
	}

	public void setBs22(boolean[] bs22) {
		this.bs22 = bs22;
	}

	public String[] getS4() {
		return s4;
	}

	public void setS4(String[] s4) {
		this.s4 = s4;
	}

	public String[] getS31() {
		return s31;
	}

	public void setS31(String[] s31) {
		this.s31 = s31;
	}

	public String[] getS32() {
		return s32;
	}

	public void setS32(String[] s32) {
		this.s32 = s32;
	}

	public String[] getS21() {
		return s21;
	}

	public void setS21(String[] s21) {
		this.s21 = s21;
	}

	public String[] getS22() {
		return s22;
	}

	public void setS22(String[] s22) {
		this.s22 = s22;
	}

	public String[][] getDisplay() {
		return display;
	}

	public void setDisplay(String[][] display) {
		this.display = display;
	}

	public String getLastHit() {
		return lastHit;
	}

	public void setLastHit(String lastHit) {
		this.lastHit = lastHit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String[][] getEnemyDisplay() {
		return enemyDisplay;
	}

	public void setEnemyDisplay(String[][] enemyDisplay) {
		this.enemyDisplay = enemyDisplay;
	}

	public ArrayList<String[]> getFleet() {
		return fleet;
	}

	public void setFleet(ArrayList<String[]> fleet) {
		this.fleet = fleet;
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}
	
}
