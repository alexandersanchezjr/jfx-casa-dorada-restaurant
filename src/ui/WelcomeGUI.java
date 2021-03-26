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
import model.User;

public class WelcomeGUI {
	
	@FXML
    private AnchorPane mainWelcomePane;
	
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
    
    //Attributes of First Admin's Register (first_register_pane.fxml)
    
    @FXML
    private AnchorPane mainFirstRegisterPane;
    
    @FXML
    private TextField registerNameTxt;

    @FXML
    private TextField registerSurnameTxt;

    @FXML
    private TextField registerIdTxt;

    @FXML
    private TextField registerUsernameTxt;

    @FXML
    private TextField registerPasswordTxt;

    @FXML
    private TextField registerConfirmPasswordTxt;

	private Restaurant restaurant;
	private EmployeeGUI employeeGUI;
	private AdminGUI adminGUI;
    
    public WelcomeGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    	employeeGUI = new EmployeeGUI();
    	adminGUI = new AdminGUI();
    	initialize();
    }
  
	public void initialize() {
    	//the method (initialize) is called several times by different fxml files loads
		employeeGUI.injectWelcomeGUI(this, restaurant);
		adminGUI.injectWelcomeGUI(this, restaurant);
    }
	
	//Methods of welcome.fxml
	
	public void resizeImageView() {
		imgRestaurant.fitWidthProperty().bind(imagePane.widthProperty());
		imgRestaurant.fitHeightProperty().bind(imagePane.heightProperty());
	}
	
	public void firstAdmin() {
		if(restaurant.getAdmins().size() == 0) {
			logInButton.setDisable(true);
			rdBtOperator.setDisable(true);
			rdBtAdmin.setDisable(true);
			adminUserTxt.setDisable(true);
			adminPasswordTxt.setDisable(true);
		}
		else {
			firstRegisterButton.setVisible(false);
			firstRegisterButton.setDisable(true);
		}
	}
	
	//Verification login of an administrator
	private boolean verificationAdminLogin() {
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
        	    	alert.setContentText("El usuario o la contraseña son incorrectos");
        	    	alert.showAndWait();
        	    	adminUserTxt.setText(""); 
        	    	adminPasswordTxt.setText("");
	        	}
	        }
		}
		return logged;
	}
	
	//Verification login of an operator user
	private boolean verificationOperatorLogin() {
		boolean logged = false;
		if(restaurant.getOperatorsUsers().size() == 0) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Aún no hay usuarios registrados en el sistema");
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
	        	    	alert.setContentText("El usuario o la contraseña son incorrectos");
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
    	
    	if(rdBtOperator.isSelected()) {
    		if(verificationOperatorLogin()) {
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
				
				fxmlLoader.setController(employeeGUI);
				Parent EmployeeWindow = fxmlLoader.load();
		    	
				mainWelcomePane.getChildren().clear();
				mainWelcomePane.getChildren().addAll(EmployeeWindow);
    		}       
        }
    	else if(rdBtAdmin.isSelected()) {
    		if(verificationAdminLogin()) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
				
				fxmlLoader.setController(adminGUI);
				Parent AdminWindow = fxmlLoader.load();
		    	
				mainWelcomePane.getChildren().clear();
				mainWelcomePane.getChildren().addAll(AdminWindow);
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
    public void registerFirstUser(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("first_register_pane.fxml"));
		
		fxmlLoader.setController(this);
		Parent FirstAdminRegister = fxmlLoader.load();
    	
		mainWelcomePane.getChildren().clear();
		mainWelcomePane.setStyle("-fx-background-color:white; -fx-opacity:1;");
		mainWelcomePane.getChildren().addAll(FirstAdminRegister);
    }
    
    //Methods of first_register_pane.fxml
    
    @FXML
    public void registerFirstAdmin(ActionEvent event) throws IOException {
    	if(registerNameTxt.getText().isEmpty() || registerSurnameTxt.getText().isEmpty() || registerIdTxt.getText().isEmpty() || registerUsernameTxt.getText().isEmpty() || registerPasswordTxt.getText().isEmpty() || registerConfirmPasswordTxt.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Error Validación");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¡Ups! debes llenar todos los campos en el registro");
	    	alert.showAndWait();
    	}
    	else if(!registerPasswordTxt.getText().equals(registerConfirmPasswordTxt.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Error Validación");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¡Ups! La contraseña que ingresaste no coincide con la confirmación");
	    	alert.showAndWait();
    	}
    	else {
    		String name = registerNameTxt.getText();
    		String surname = registerSurnameTxt.getText();
    		String id = registerIdTxt.getText();
    		String username = registerUsernameTxt.getText();
    		String password = registerPasswordTxt.getText();
    		if(restaurant.addAdminUser(name, surname, id, null, username, password, null)) {
    			Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Administrador creado");
		    	alert.setHeaderText(null);
		    	alert.setContentText("El primer administrador ha sido registrado exitosamente");
		    	alert.showAndWait();
		    	
		    	User firstAdmin = restaurant.getAdmins().get(0);
		    	restaurant.getAdmins().get(0).setCreator(firstAdmin);
		    	restaurant.getAdmins().get(0).setModifier(firstAdmin);
		    	restaurant.setLoggedUser(firstAdmin);
    		}
    	}
    }
    
    @FXML
    public void goBackFromRegister(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		
		fxmlLoader.setController(this);
		Parent WelcomeWindow = fxmlLoader.load();
    	
		mainFirstRegisterPane.getChildren().clear();
		mainFirstRegisterPane.setStyle("-fx-background-color:black; -fx-opacity:1;");
		mainFirstRegisterPane.getChildren().addAll(WelcomeWindow);
    }
}
