# Breakout

A classic Breakout game built in Java using Swing.

## Features

- 5 rows of bricks across the screen
- Paddle angle affects ball direction — hitting the edge of the paddle sends the ball at a steeper angle
- Left/right arrow keys to move the paddle
- P to pause and unpause

## Getting Started

**Prerequisites:** JDK installed — see [adoptium.net](https://adoptium.net) if you need it.

**Compile and run:**
```bash
javac *.java
java Main
```

## Controls

| Key | Action |
|---|---|
| `←` / `→` | Move paddle |
| `P` | Pause / unpause |

## Project Structure

| File | Description |
|---|---|
| `Main.java` | Entry point |
| `BreakoutGame.java` | Main JFrame window |
| `Screen.java` | Game loop, rendering, and input handling |
| `Ball.java` | Ball movement and collision logic |
| `Paddle.java` | Paddle movement |
| `Brick.java` | Brick state and rendering |