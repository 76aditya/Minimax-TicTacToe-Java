# Minimax-TicTacToe-Java
An unbeatable Tic-Tac-Toe game built in Java. Implements the Minimax algorithm (Game Theory) to simulate Artificial Intelligence without neural networks to determine the optimal move in a zero-sum game. Demonstrates recursive decision making, state management, and Swing GUI design.

## Features
* **Unbeatable AI:** The computer plays perfectly and will never lose. The best outcome a human can achieve is a draw.
* **Graphical Interface:** Clean, wooden-themed UI built with Java Swing.
* **Game Logic:** Full implementation of win/loss/draw detection.
* **Reset Functionality:** Automatic board reset upon game completion.

## Technical Details
* **Algorithm:** Minimax (Recursive Backtracking)
* **Time Complexity:** $O(b^d)$ — effectively $O(n!)$ for Tic-Tac-Toe.
* **Space Complexity:** $O(d)$ where $d$ is the depth of the game tree.

## Getting Started

Follow the below instructions to set up and run the game on your local machine.

### Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher.
* **An IDE (Optional):** IntelliJ IDEA, Eclipse, or VS Code.

### Installation

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/76aditya/Minimax-TicTacToe-Java.git](https://github.com/76aditya/Minimax-TicTacToe-Java.git)
    ```

2.  **Navigate to the Directory:**
    Open the project folder in your terminal or IDE.

3.  **⚠️ Important Configuration (Image Path):**
    * This game uses a background image (`wood-texture-wallpapers.jpg`).
    * Open `Game.java`.
    * Locate the line inside `main()` and `Background` class where the image path is defined:
        ```java
        // Look for lines similar to:
        new ImageIcon("C:/Aditya/DSA in JAVA/...");
        ```
    * **Update this path** to match the location of the image file on *your* specific computer.

### Running the Game

**Option 1: Using Command Line**
Compile and run the Java file:
```bash
javac Game.java
java Game
```
**Option 2: Using IDE**
Compile and run the Java file on the IDE you are using.
