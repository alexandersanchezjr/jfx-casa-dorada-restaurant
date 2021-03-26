package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import model.Restaurant;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdminGUI {
	
	@FXML
    private GridPane adminGridPane;
	
	@FXML
    private Label labTime;

    @FXML
    private AnchorPane mainPane;
    
    private Restaurant restaurant;
    private WelcomeGUI welcomeGUI;
    
    public AdminGUI() {
    	welcomeGUI = new WelcomeGUI(restaurant);
    	restaurant = new Restaurant();
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
		
		fxmlLoader.setController(welcomeGUI);
		Parent EmployeeWindow = fxmlLoader.load();
    	
		adminGridPane.getChildren().clear();
		adminGridPane.getChildren().addAll(EmployeeWindow);
    }

    @FXML
    public void showAdminPane(ActionEvent event) {

    }
    
    @FXML
    public void showOperatorPane(ActionEvent event) {

    }

    @FXML
    public void showClientsPane(ActionEvent event) {

    }

    @FXML
    public void showEmployeePane(ActionEvent event) {

    }

    @FXML
    public void showIngredientsPane(ActionEvent event) {

    }

    @FXML
    public void showOrdersPane(ActionEvent event) {

    }

    @FXML
    public void showProductsPane(ActionEvent event) {

    }

    @FXML
    public void showTypesPane(ActionEvent event) {

    }
}
