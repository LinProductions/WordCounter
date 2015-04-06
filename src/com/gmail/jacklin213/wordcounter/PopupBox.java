package com.gmail.jacklin213.wordcounter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupBox extends Stage {
	
	Stage window;
	
	static final String DEFAULT_TITLE = "Alert: Default Title";
	static final String DEFAULT_MESSAGE = "Alert triggered: Default message";
	
	public PopupBox() {
		this(DEFAULT_TITLE);
	}
	
	public PopupBox(String title) {
		this(title, DEFAULT_MESSAGE);
	}
	
	public PopupBox(String title, String message) {
		window = this;

		Scene scene = getDefaultScene(message);
		this.setTitle(title);
		this.setMinWidth(250);
		this.setScene(scene);
		//AlertBox style bellow
		this.initModality(Modality.APPLICATION_MODAL);
		this.showAndWait();
	}
	
	private Scene getDefaultScene(String message) {
		//Item
		Label text = new Label(message);
		Button btnClose = new Button("Click to close");
		btnClose.setOnAction(event -> window.close());
		//Layout
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(text, btnClose);
		//Scene
		Scene scene = new Scene(layout);
		return scene;
	}
}
