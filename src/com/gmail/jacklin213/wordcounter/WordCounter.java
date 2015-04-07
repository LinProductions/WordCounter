package com.gmail.jacklin213.wordcounter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WordCounter extends Application {

	TextArea input; 
	
	public static void main(String args[]) {
		launch();
	}
	
	@Override
	public void start(Stage window) throws Exception {
		//Items
		input = new TextArea();
		input.setMinHeight(50);
		input.setMinWidth(100);
		Label lblTitle = new Label("Word Counter");
		lblTitle.setFont(Font.font(20));
		Label lblDescription = new Label("Paste words to count and click count");
		Text txtResultWords = new Text("Number of Words: ");
		Text txtResultSentence = new Text("Number of Sentences: ");
		Button btnCount = new Button("Count");
		btnCount.setOnAction(event -> {
			txtResultWords.setText("Number of Words: " + getWordCount());
			txtResultSentence.setText("Number of Sentences: " + getSentenceCount());
			countSentence();
		});
		//Layout
		VBox layout = new VBox();
		GridPane content = new GridPane();
		VBox boxCount = new VBox();
		boxCount.setAlignment(Pos.CENTER_RIGHT);
		boxCount.getChildren().add(btnCount);
		
		content.setPadding(new Insets(10));
		content.setVgap(10);
//		layout.getChildren().addAll(lblTitle, lblDescription, input, btnSubmit);
		content.add(lblTitle, 0, 0, 2, 1);
		content.add(lblDescription, 0, 1, 2, 1);
		content.add(input, 0, 2, 2, 1);
		content.add(txtResultWords, 0, 3); content.add(boxCount, 1, 3);
		content.add(txtResultSentence, 0, 4);
		
		layout.getChildren().addAll(getMenuBar(), content);
		//Scene
		Scene scene = new Scene(layout, 500, 375);
		//Stage
		window.setOnCloseRequest(event -> {
			event.consume();
			onDisable();
		});
		window.setTitle("Word Counter");
		window.setScene(scene);
		window.show();
	}
	
	public MenuBar getMenuBar() {
		MenuBar menuBar = new MenuBar();
		
		Menu menuFile = new Menu("File");
		
		MenuItem itemExit = new MenuItem("Exit");
		itemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		itemExit.setOnAction(event -> onDisable());
		menuFile.getItems().addAll(itemExit);
		
		Menu menuEdit = new Menu("Edit");
		MenuItem itemCut = new MenuItem("Cut");
		itemCut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		//itemCut.setOnAction(event -> );
		MenuItem itemCopy = new MenuItem("Copy");
		itemCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
		//itemCopy.setOnAction(event -> );
		MenuItem itemPaste = new MenuItem("Paste");
		itemPaste.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
		//itemPaste.setOnAction(event -> );
		MenuItem itemSelect = new MenuItem("Select All");
		itemSelect.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		//itemSelect.setOnAction(event -> );
		
		menuEdit.getItems().addAll(itemCut, itemCopy, itemPaste, new SeparatorMenuItem(), itemSelect);
		
		menuBar.getMenus().addAll(menuFile, menuEdit);
		return menuBar;
	}
	
	public void onDisable() {
		ConfirmBox confirmBox = new ConfirmBox("Are you sure you want to exit?");
		if (confirmBox.getConfirm()) {
			//Do stuff
//			window.close();
			Platform.exit();
		}
	}
	
	public int getWordCount() {
		String[] words = input.getText().split(" ");
		return words.length;
	}
	
	public int getSentenceCount() {
		String[] sentences = input.getText().split("\\.");
		return sentences.length;
	}
	
	//Debugging
	
	public void countSentence() {
		String[] sentences = input.getText().split("\\.");
		for (String sentence : sentences)
			System.out.println(sentence);
		System.out.println("Works");
	}
	
	public void countWords() {
		String[] words = input.getText().split(" ");
		System.out.println("Words: " + words.length);
	}
	
}
