# Battleship Game in Java

## Project Description
This repository contains a Battleship game implemented in Java with a client-server architecture. The game allows two players to engage in an exciting naval battle through the Windows terminal.

## Project Structure
The project is divided into two main parts: the client and the server. Here is a brief description of each of the key classes:

### Client (Client.java)
The `Client` class is responsible for running the game for each of the two players involved in the match. When executed, it creates a socket that connects to the server through port 8888 and initializes a `ClientThread`. The `ClientThread` is responsible for displaying messages and the game's state in the player's terminal.

### Server (Server.java)
The `Server` class manages the server and accepts connection requests from clients. It initiates the `ServerThread`, which displays relevant information about the server's state and clients. Additionally, it receives keyboard input to start the `GameThread` once it receives the word "start." The `GameThread` houses and manages all the game logic at the thread management level, allowing each player to initially position their fleet and managing turns to ensure the game's proper operation.

## Game Logic
All game logic is located in the `Game` class, which is initialized by the `Server` class. Some of the key actions performed by this class include:
- Assigning the initial position of ships, ensuring it is valid and collision-free.
- Launching and receiving attacks, calculating the state of the ships.
- Managing the winner of the match.

## Getting Started
1. Clone this repository to your local machine.
2. Run the server using `java -jar Server.jar`.
3. Next, run two instances of the client, each using `java -jar Client.jar`.
4. Follow the instructions in the terminal to play the match. I tried to make it the easiest and friendliest way possible for the player to understand.

## Contributions
Contributions are welcome! If you want to enhance this project, feel free to fork it and submit pull requests.

## License
This project is under the GNU General Public License v3.0. Check the [LICENSE file](https://github.com/Adripoblado/HLF/blob/main/LICENSE) for more details.

## Author
[Adrián Cervera]

### See Also
- [Java Socket Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.net/java/net/Socket.html) - Information about Java's Socket class.
- [Git Tutorial](https://www.atlassian.com/git) - Learn more about Git and GitHub.
