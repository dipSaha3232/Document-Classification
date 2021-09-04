import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestClass extends Application{
	
	public void start(Stage primaryStage) throws Exception
	{
		Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene=new Scene(root);
		primaryStage.setTitle("Document Classification");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}

/*DocumentCluster documentCluster = new DocumentCluster();

Scanner input = new Scanner(System.in);

System.out.println("Enter number of cluster (By default, it is 3)");
int noOfCluster = input.nextInt();
documentCluster.setNoOfCluster(noOfCluster);

int i=1;

while(true) {
	System.out.println("1. Add new text  2. Cluster");
	int choice = input.nextInt();
	
	if(choice == 1) {
		System.out.println("Enter text no "+Integer.toString(i));
		String text = input.nextLine();
		text = input.nextLine();
		documentCluster.createNewDocument(text);
		i++;
	}
	else if(choice == 2) {
		int [] cluster=documentCluster.cluster();
		for(int j=0;j<cluster.length;j++) {
			System.out.println("Text no "+Integer.toString(j+1)+"; Cluster No : "+Integer.toString(cluster[j]));
		}
		break;
	}
	else break;
}*/

