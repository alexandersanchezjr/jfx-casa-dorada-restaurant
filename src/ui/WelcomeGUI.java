package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class WelcomeGUI {
	
	@FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField userPasswordTxt;

    
	private Restaurant restaurant;
	private EmployeeGUI employeeGUI;
	private AdminGUI adminGUI;
    
    public WelcomeGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    	employeeGUI = new EmployeeGUI();
    	adminGUI = new AdminGUI();
    }
  
	public void initialize() {
    	//the method (initialize) is called several times by diferents fxml file loads 
    }

    @FXML
    public void showAdminWindow(ActionEvent event) throws IOException {
    	/*boolean logged = false;
		
		if(restaurant.getEmployees().size() == 0) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("The username or password given are incorrect");
	    	alert.showAndWait();
	    	employeeUserTxt.setText(""); 
	    	employeeIdTxt.setText("");
    	}
    	else {
    		for(int i=0; i<restaurant.getEmployees().size(); i++) {
        		if(employeeUserTxt.getText().equals(restaurant.getEmployees().get(i).getSurname()) && employeeIdTxt.getText().equals(restaurant.getEmployees().get(i).getId())) {
        			logged = true;
        		}
        		else {
        			Alert alert = new Alert(AlertType.ERROR);
        	    	alert.setTitle("Log In incorrect");
        	    	alert.setHeaderText(null);
        	    	alert.setContentText("The username or password given are incorrect");
        	    	alert.showAndWait();
        	    	employeeUserTxt.setText(""); 
        	    	employeeIdTxt.setText("");
        		}
        	}
    	}
		
		if(logged) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
			
			fxmlLoader.setController(employeeGUI);
			Parent EmployeeWindow = fxmlLoader.load();
	    	
			mainAnchorPane.getChildren().clear();
			mainAnchorPane.getChildren().addAll(EmployeeWindow);
		}*/
    }	

    @FXML
    public void showEmployeeWindow(ActionEvent event) throws IOException {
		
    } 
    
}
