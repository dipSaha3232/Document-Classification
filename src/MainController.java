import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController {
	
	DocumentCluster documentCluster = new DocumentCluster();
	
	int [] clusters ;
	int noOfCluster=3;
	String [] style = new String [20];
	
	@FXML
	private AnchorPane textAreaAnchor;
	
	@FXML
	private ScrollPane scroller;
	
	@FXML
	private Spinner<Integer> spinner;
	
	private ArrayList<TextArea> textAreas = new ArrayList<>();
	
	int index=0;
	
	private TextArea createNewArea() {
		TextArea textArea = new TextArea();
		textArea.setPromptText("Write your text here");
		textArea.setPrefHeight(100);
		textArea.setPrefWidth(textAreaAnchor.getWidth());
		textArea.setLayoutX(0);
		textArea.setLayoutY(index*100 + 20);
		textArea.setId("ta"+Integer.toString(index));
		textAreas.add(textArea);
		index++;
		return textArea;
	}
	
	@FXML
	public void actionOnAddNewText() throws Exception {
		scroller.setFitToWidth(true);
		Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));
		textAreaAnchor.getChildren().add(createNewArea());
	}
	
	@FXML
	public void actionOnCluster() {
		
		documentCluster.setNoOfCluster(spinner.getValue());
		noOfCluster = spinner.getValue();
		
		for(int i=0;i<index;i++) {
			TextArea textArea1 = (TextArea)textAreaAnchor.getChildren().get(i);
			documentCluster.createNewDocument(textArea1.getText());
		}
		
		clusters = documentCluster.cluster();
		
		createStyleForCluster();
		
		setColorToTextArea();
		
	}
	
	private void createStyleForCluster() {
		
		style[0] = "-fx-text-fill: rgb(0,0,204)";
		style[1] = "-fx-text-fill: rgb(255,0,0)";
		style[2] = "-fx-text-fill: rgb(0,102,0)";
		style[3] = "-fx-text-fill: rgb(255,0,127)";
		style[4] = "-fx-text-fill: rgb(102,0,102)";
		style[5] = "-fx-text-fill: rgb(102,0,0)";
		style[6] = "-fx-text-fill: rgb(255,0,127)";
		style[7] = "-fx-text-fill: rgb(102,51,0)";
		style[8] = "-fx-text-fill: rgb(51,0,0)";
		style[9] = "-fx-text-fill: rgb(102,102,255)";
		style[10] = "-fx-text-fill: rgb(102,102,0)";
		style[11] = "-fx-text-fill: rgb(0,128,255)";
		style[12] = "-fx-text-fill: rgb(0,204,0)";
		style[13] = "-fx-text-fill: rgb(128,0,128)";
		style[14] = "-fx-text-fill: rgb(255,69,0)";
		style[15] = "-fx-text-fill: rgb(47,79,79)";
		style[16] = "-fx-text-fill: rgb(139,0,139)";
		style[17] = "-fx-text-fill: rgb(0,255,0)";
		style[18] = "-fx-text-fill: rgb(0,0,255)";
		style[19] = "-fx-text-fill: rgb(138,43,226)";
	}
	
	@FXML
	private void setColorToTextArea() {
		for(int i=0;i<index;i++) {
			textAreas.get(i).setStyle(style[clusters[i]]);
		}
	}
	
	@FXML
	public void actionOnExit() {
		System.exit(0);
	}
}
