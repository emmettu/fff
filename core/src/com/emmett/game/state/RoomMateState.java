package com.emmett.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.emmett.game.parser.Listing;
import com.emmett.game.parser.ListingGetter;
import com.emmett.game.parser.ListingParser;

public class RoomMateState implements GameState {

	private Stage stage;

	private Table table;

	private Label descriptionLabel;

	private Label nameLabel;

	private Label locationLabel;

	private Label flatMateLabel;

	private ListingGetter listingGetter;

	private ListingParser parser;

	private Label yesLabel;

	private Label noLabel;

	String url;

	private GameStateManager gsm;

	public RoomMateState(final GameStateManager gsm) {
		this.gsm = gsm;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();

		Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
		Label.LabelStyle yesStyle = new Label.LabelStyle(new BitmapFont(), Color.GREEN);
		Label.LabelStyle noStyle = new Label.LabelStyle(new BitmapFont(), Color.RED);
		Label.LabelStyle nameStyle = new Label.LabelStyle(new BitmapFont(), Color.PURPLE);

		descriptionLabel = new Label("", style);
		nameLabel = new Label("", nameStyle);
		locationLabel = new Label("", style);
		flatMateLabel = new Label("", style);

		table.setFillParent(true);
		stage.addActor(table);

		descriptionLabel.setFontScale(1f);
		locationLabel.setFontScale(1.5f);
		nameLabel.setFontScale(1.8f);
		flatMateLabel.setFontScale(1.5f);

		descriptionLabel.setWrap(true);
		flatMateLabel.setWrap(true);
		locationLabel.setWrap(true);
		nameLabel.setWrap(true);

		noLabel = new Label("No, find someone else", noStyle);

		yesLabel = new Label("Sure, they sound nice", yesStyle);

		noLabel.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				renderListing();
			}
		});

		yesLabel.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				Gdx.net.openURI(url);
				gsm.setState(new OutroState());
			}
		});

		table.add(nameLabel).width(500).center();
		table.row();
		table.add(locationLabel).width(500).center();
		table.row();
		table.add(flatMateLabel).width(500).center();
		table.row();
		table.add(descriptionLabel).width(500).center();
		table.row();
		table.add(yesLabel).center();
		table.row();
		table.add(noLabel).center();

		listingGetter = new ListingGetter();
		parser = new ListingParser();

		renderListing();
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

	private void renderListing() {
		url = listingGetter.randomListing();
		Listing listing = parser.parseListing(url);
		nameLabel.setText("Why not rent from: " + listing.getName() + " for " + listing.getPrice() + "?");
		locationLabel.setText("\nThey're located in beautiful: " + listing.getDetail("Location") + "!");
		flatMateLabel.setText(getFlatMateText(listing));
		descriptionLabel.setText("\n" + listing.getDetail("Description") + "\n");
	}

	private String getFlatMateText(Listing listing) {
		String flatMateDetail = listing.getDetail("Ideal flatmate");
		return flatMateDetail == null
				? ""
				: "\nThey're looking for someone who is " + flatMateDetail;
	}

}
