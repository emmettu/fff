package com.emmett.game.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class LoadingState implements GameState {

	private Stage stage;

	private Table table;

	private Label titleLabel;
	private GameStateManager manager;
	private RoomMateState state;
	RunnableFuture future;

	public LoadingState(Runnable runnable, GameStateManager manager, RoomMateState state) {
		this.manager = manager;
		this.state = state;

		table = new Table();
		stage = new Stage();

		Label.LabelStyle titleStyle = new Label.LabelStyle(new BitmapFont(), Color.PURPLE);
		titleLabel = new Label("Scouring the internet for Flatmates...", titleStyle);
		titleLabel.setFontScale(3);

		table.setFillParent(true);
		stage.addActor(table);

		table.add(titleLabel).width(500).center();

		future = new FutureTask<>(runnable, null);
		new Thread(future).start();
	}


	@Override
	public void update() {
		stage.act();
		stage.draw();
		if (future.isDone()) {
			manager.setState(state);
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
