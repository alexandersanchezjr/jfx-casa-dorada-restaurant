package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Restaurant;

public class WelcomeGUI {
	
	@FXML
    private GridPane mainGridPane;
	
	@FXML
    private AnchorPane imagePane;
	
	@FXML
    private ImageView imgRestaurant;
	
	@FXML
    private ToggleGroup user;

    @FXML
    private RadioButton rdBtOperator;

    @FXML
    private RadioButton rdBtAdmin;

    @FXML
    private TextField adminUserTxt;

    @FXML
    private PasswordField adminPasswordTxt;
    
    @FXML
    private Button logInButton;
    
    @FXML
    private Button firstRegisterButton;

	private Restaurant restaurant;
	private EmployeeGUI employeeGUI;
	private AdminGUI adminGUI;
    
    public WelcomeGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    	employeeGUI = new EmployeeGUI();
    	adminGUI = new AdminGUI();
    }
  
	public void initialize() {
    	//the method (initialize) is called several times by different fxml files loads 
    }
	
	public void resizeImageView() {
		imgRestaurant.fitWidthProperty().bind(imagePane.widthProperty());
		imgRestaurant.fitHeightProperty().bind(imagePane.heightProperty());
	}
	
	public void firstAdmin() {
		if(restaurant.getAdmins().isEmpty()) {
			logInButton.setDisable(false);
			rdBtOperator.setDisable(false);
			rdBtAdmin.setDisable(false);
		}
		else {
			firstRegisterButton.setVisible(false);
			firstRegisterButton.setDisable(false);
		}
	}
	
	//Verification login of an administrator
	public boolean verificationAdminLogin() {
		boolean logged = false;
		for(int i=0; i<restaurant.getAdmins().size() && !logged; i++) {
	        if(restaurant.getAdmins().get(i) != null) {	
    			if(adminUserTxt.getText().equals(restaurant.getAdmins().get(i).getUsername()) && adminPasswordTxt.getText().equals(restaurant.getAdmins().get(i).getPassword())) {
	        		logged = true;
    			}
	        	else {
        			Alert alert = new Alert(AlertType.ERROR);
        	    	alert.setTitle("Log In incorrect");
        	    	alert.setHeaderText(null);
        	    	alert.setContentText("El usuario o la contrase�a son incorrectos");
        	    	alert.showAndWait();
        	    	adminUserTxt.setText(""); 
        	    	adminPasswordTxt.setText("");
	        	}
	        }
		}
		return logged;
	}
	
	//Verification login of an operator user
	public boolean verificationOperatorLogin() {
		boolean logged = false;
		if(restaurant.getOperatorsUsers().size() == 0) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("A�n no hay usuarios registrados en el sistema");
	    	alert.showAndWait();
	    	adminUserTxt.setText(""); 
	    	adminPasswordTxt.setText("");
		}
		else {
			for(int i=0; i<restaurant.getOperatorsUsers().size() && !logged; i++) {
    	        if(restaurant.getOperatorsUsers().get(i) != null) {	
        			if(adminUserTxt.getText().equals(restaurant.getOperatorsUsers().get(i).getUsername()) && adminPasswordTxt.getText().equals(restaurant.getOperatorsUsers().get(i).getPassword())) {
    	        		logged = true;
        			}
    	        	else {
	        			Alert alert = new Alert(AlertType.ERROR);
	        	    	alert.setTitle("Log In incorrect");
	        	    	alert.setHeaderText(null);
	        	    	alert.setContentText("El usuario o la contrase�a son incorrectos");
	        	    	alert.showAndWait();
	        	    	adminUserTxt.setText(""); 
	        	    	adminPasswordTxt.setText("");
    	        	}
            	}
        	}
    	}
		return logged;
	}

    @FXML
    public void logIn(ActionEvent event) throws IOException {
    	
    	if(rdBtOperator.isArmed()) {
    		if(verificationOperatorLogin()) {
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
				
				fxmlLoader.setController(employeeGUI);
				Parent EmployeeWindow = fxmlLoader.load();
		    	
				mainGridPane.getChildren().clear();
				mainGridPane.getChildren().addAll(EmployeeWindow);
    		}       
        }
    	else if(rdBtAdmin.isArmed()) {
    		if(verificationAdminLogin()) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
				
				fxmlLoader.setController(adminGUI);
				Parent AdminWindow = fxmlLoader.load();
		    	
				mainGridPane.getChildren().clear();
				mainGridPane.getChildren().addAll(AdminWindow);
    		}
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No has seleccionado el tipo de usuario");
	    	alert.showAndWait();
	    	adminUserTxt.setText(""); 
	    	adminPasswordTxt.setText("");
    	}
	}
    
    @FXML
    public void registerFirstUser(ActionEvent event) {
    	
    }
}
