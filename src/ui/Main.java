package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;

public class Main extends Application {
	private WelcomeGUI welcomeGUI;
	private Restaurant restaurant;

	public Main(){
		restaurant = new Restaurant();
		welcomeGUI = new WelcomeGUI(restaurant);
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));

		fxmlLoader.setController(welcomeGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Casa Dorada Restaurant");
		primaryStage.show();
		welcomeGUI.resizeImageView();
		welcomeGUI.firstAdmin();
	}
}
