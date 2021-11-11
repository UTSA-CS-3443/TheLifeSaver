package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Main.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			stage = primaryStage;
			stage.getIcons().add(new Image("https://raw.githubusercontent.com/UTSA-CS-3443/TheLifeSaver/main/images/life%20perserver%20icon.png"));
			stage.setTitle("LIFE SAVER GAAAAANG");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
