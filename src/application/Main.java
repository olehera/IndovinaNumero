package application;
	
import application.model.NumeroModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Numero.fxml"));         // creo la scena 
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			
			NumeroModel model = new NumeroModel();        // creo il modello
			NumeroController controller = loader.getController();   // creo il controller
			controller.setModel(model);    // setto il modello
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
