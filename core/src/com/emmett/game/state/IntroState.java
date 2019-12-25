package com.emmett.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class IntroState implements GameState {

	private Stage stage;

	private Table table;

	private Label descriptionLabel;

	private Label titleLabel;

	public IntroState(final GameStateManager gameStateManager) {
		table = new Table();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Label.LabelStyle descriptionStyle = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
		Label.LabelStyle titleStyle = new Label.LabelStyle(new BitmapFont(), Color.PURPLE);

		titleLabel = new Label("Welcome to the Fabulous Flatmate Finder!", titleStyle);
		descriptionLabel = new Label("Click anywhere to get a Flatmate Suggestion.", descriptionStyle);

		titleLabel.setFontScale(3);
		descriptionLabel.setFontScale(2);
		titleLabel.setWrap(true);
		descriptionLabel.setWrap(true);

		table.add(titleLabel).width(500).center();
		table.row();
		table.add(descriptionLabel).width(500).center();

		table.setFillParent(true);
		stage.addActor(table);

		stage.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
                final RoomMateState nextState = new RoomMateState(gameStateManager);
                final Runnable newState = new Runnable() {
					@Override
					public void run() {
                        nextState.renderListing();
					}
				};
                gameStateManager.setState(new LoadingState(newState, gameStateManager, nextState));
			}
		});
	}

	@Override
	public void update() {
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
