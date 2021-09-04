import java.io.IOException;
import java.util.ArrayList;

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
	
	@FXML
	private AnchorPane textAreaAnchor;
	
	@FXML
	private ScrollPane scroller;
	
	@FXML
	final private Spinner<Integer> spinner = new Spinner<Integer>();
	
	final int initialValue = 1;
	
	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,initialValue);
	
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
		spinner.setValueFactory(valueFactory);
		System.out.println(spinner.getValue());
		
		documentCluster.setNoOfCluster(spinner.getValue());
		
		for(int i=0;i<index;i++) {
			TextArea textArea1 = (TextArea)textAreaAnchor.getChildren().get(i);
			documentCluster.createNewDocument(textArea1.getText());
		}
		
		clusters = documentCluster.cluster();
		
		for(int i=0;i<index;i++)
			System.out.println(clusters[i]);
		
		setColorToTextArea();
		
	}
	
	@FXML
	private void setColorToTextArea() {
		for(int i=0;i<index;i++) {
			if(clusters[i]==0)
				textAreas.get(i).setStyle("-fx-text-fill: green;");
			else if(clusters[i]==1)
				textAreas.get(i).setStyle("-fx-text-fill: red;");
			else if(clusters[i]==2)
				textAreas.get(i).setStyle("-fx-text-fill: blue;");
		}
	}
	
	@FXML
	public void actionOnExit() {
		System.exit(0);
	}
}
