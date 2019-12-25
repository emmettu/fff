package com.emmett.game.state;

public class GameStateManager {

	private GameState currentState;

	public void update() {
		currentState.update();
	}

	public void dispose() {
		currentState.dispose();
	}

	public void setState(GameState currentState) {
		this.currentState = currentState;
	}

	public void resize (int width, int height) {
	}

}
