package com.emmett.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.emmett.game.parser.ListingGetter;
import com.emmett.game.state.GameStateManager;
import com.emmett.game.state.IntroState;
import com.emmett.game.state.RoomMateState;

public class SecretSanta extends ApplicationAdapter {
	SpriteBatch batch;
	GameStateManager gsm = new GameStateManager();

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm.setState(new IntroState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gsm.update();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gsm.dispose();
	}
}
