package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class WelcomeGUI {
	
	@FXML
    private AnchorPane adminPane;

    @FXML
    private TextField adminUserTxt;

    @FXML
    private PasswordField adminPasswordTxt;

    @FXML
    private AnchorPane employeePane;

    @FXML
    private TextField employeeUserTxt;

    @FXML
    private TextField employeeIdTxt;
	
	private Restaurant restaurant;
    
    public WelcomeGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
  
	public void initialize() {
    	//the method (initialize) is called several times by diferents fxml file loads 
    }
    
    @FXML
    public void loadLogIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent login = fxmlLoader.load();
 
    }
    
    @FXML
    void enableAdminPane(ActionEvent event) {

    }

    @FXML
    void enableEmployeePane(ActionEvent event) {

    }

    @FXML
    void showAdminWindow(ActionEvent event) {

    }

    @FXML
    void showEmployeeWindow(ActionEvent event) {

    } 
    
}
