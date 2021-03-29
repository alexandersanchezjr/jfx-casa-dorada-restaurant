package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DetailProduct;
import model.Restaurant;
import model.User;

public class UsersGUI {
	
	//admins_pane.fxml attributes
	
	@FXML
    private AnchorPane adminPane;

    @FXML
    private TableView<User> tvAdmins;

    @FXML
    private TableColumn<User, String> tcAdminName;

    @FXML
    private TableColumn<User, String> tcAdminSurname;

    @FXML
    private TableColumn<User, String> tcAdminId;

    @FXML
    private TableColumn<User, String> tcAdminUsername;

    @FXML
    private TextField adminNameTxt;

    @FXML
    private TextField adminSurnameTxt;

    @FXML
    private TextField adminIdTxt;

    @FXML
    private ToggleButton tbAdminAvailability;

    @FXML
    private TextField adminUsernameTxt;

    @FXML
    private PasswordField adminHiddenPasswordTxt;

    @FXML
    private TextField adminPasswordTxt;

    @FXML
    private CheckBox chkbAdminShowPassword;

    @FXML
    private Label labEmployeeId2;

    @FXML
    private TextField newAdminNameTxt;

    @FXML
    private TextField newAdminSurnameTxt;

    @FXML
    private TextField newAdminIdTxt;

    @FXML
    private TextField newAdminUsernameTxt;
    
    @FXML
    private PasswordField newHiddenPasswordTxt;

    @FXML
    private PasswordField newPasswordTxt;

    @FXML
    private CheckBox chkbNewAdminShowPassword;

    @FXML
    private TextField adminImportSeparatorTxt;

    @FXML
    private TextField adminExportSeparatorTxt;
    
    //
    
    //clients_pane.fxml
    
    @FXML
    private AnchorPane orderPaner;

    @FXML
    private TableView<?> tvProducts;

    @FXML
    private TableColumn<?, ?> tcOrderProductName;

    @FXML
    private TableColumn<?, ?> tcOrderProductType;

    @FXML
    private TableColumn<?, ?> tcOrderProductSize;

    @FXML
    private TableColumn<?, ?> tcOrderProductPrice;

    @FXML
    private TableColumn<?, ?> tcOrderProductPrice1;

    @FXML
    private TableColumn<?, ?> tcOrderProductPrice11;

    @FXML
    private Label labOrderId;

    @FXML
    private Label labClientComments;

    @FXML
    private TextField importSeparatorTxt;

    @FXML
    private TextField exportSeparatorTxt;
    
    
    private Restaurant restaurant;
    
    public UsersGUI() {
    	initializeAdminTableView ();
    }
    
     public void injectAdminGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    
    
    //admin_pane.fxml methods
    public void initializeAdminTableView () {
    	
    	ObservableList<User> observableList = FXCollections.observableArrayList(restaurant.getAdmins());
    	tvAdmins.setItems(observableList);
    	tcAdminName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    	tcAdminSurname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
    	tcAdminId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
    	tcAdminUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    }
    
    @FXML
    void changeAdminAvailability(ActionEvent event) {
    	
    	User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();
    	
    	if (tbAdminAvailability.isSelected()) {
    		tbAdminAvailability.setText("HABILITADO");
    		thisAdmin.setAvailability(true);
    	}else {
    		tbAdminAvailability.setText("DESHABILITADO");
    		thisAdmin.setAvailability(false);
    	}
    		
    }
    
    @FXML
    void cleanAdminTableView(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Confirme borrado de datos");
    	dialog.setHeaderText("Por favor, escriba 'SI' si desea limpiar la lista de administradores ");

    	// Traditional way to get the response value.
    	String input = "";
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	   input = result.get();
    	}
    	if (input.equals("SI")) {
    		restaurant.getAdmins().clear();
    	}
    }

    @FXML
    void confirmPassword(ActionEvent event) {
    	
    	if(!chkbAdminShowPassword.isSelected()) {
    		User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();
	    	
	    	TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("Contraseña");
	    	dialog.setHeaderText("Por favor, confirme su contraseña");
	
	    	// Traditional way to get the response value.
	    	String inputPassword = "";
	    	Optional<String> result = dialog.showAndWait();
	    	if (result.isPresent()){
	    	   inputPassword = result.get();
	    	}
	    	if (inputPassword.equals(thisAdmin.getPassword())) {
	    		adminPasswordTxt.setText(inputPassword);
	    		adminPasswordTxt.setVisible(true);
	    		adminHiddenPasswordTxt.setVisible(false);
	    	}
    	}else {
    		adminHiddenPasswordTxt.setText(adminPasswordTxt.getText());
    		adminHiddenPasswordTxt.setVisible(true);
    		adminPasswordTxt.setVisible(false);
    	}
    		
	    	
    		
    }

    @FXML
    void createNewAdmin(ActionEvent event) {
    	
    	if(newAdminNameTxt.getText().isEmpty() || newAdminSurnameTxt.getText().isEmpty() || newAdminIdTxt.getText().isEmpty() || newAdminUsernameTxt.getText().isEmpty() || (newPasswordTxt.getText().isEmpty() && newHiddenPasswordTxt.getText().isEmpty())) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Campos Vacíos");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Por favor, rellene todos los campos para poder crear un administrador");
	    	alert.showAndWait();
    	}else {
    		if (newPasswordTxt.getText().equals(newHiddenPasswordTxt.getText())) {
    			try {
    				restaurant.addAdminUser(newAdminNameTxt.getText(), newAdminSurnameTxt.getText(), newAdminIdTxt.getText(), null, newAdminUsernameTxt.getText(), newPasswordTxt.getText(), null);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
    	}
    	
    }
    
    @FXML
    void deleteAdmin(ActionEvent event) {
    	User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();
    	
    	try {
			restaurant.deletedAdminUser(thisAdmin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void exportAdminsList(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Reporte-Administradores.csv");


        //Show save file dialog
        
        File file = fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());

        if (file != null) {
            //TODO make a exportAdmins() method in restaurant
        }
    }

    @FXML
    void importAdminsList(ActionEvent event) {
    	
    }

    @FXML
    void showAdminInfo(MouseEvent event) {
    	chkbAdminShowPassword.setSelected(false);
    	
    	User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();
    	
    	if(thisAdmin.isAvailability())
    		tbAdminAvailability.setText("HABILITADO");
    	else
    		tbAdminAvailability.setText("DESHABILITADO");
    	adminNameTxt.setText(thisAdmin.getName());
    	adminSurnameTxt.setText(thisAdmin.getSurname());
    	adminIdTxt.setText(thisAdmin.getId());
    	adminUsernameTxt.setText(thisAdmin.getUsername());
    	adminHiddenPasswordTxt.setText(thisAdmin.getPassword());
    	
    }

    @FXML
    void showNewAdminPassword(ActionEvent event) {
    	if(!chkbNewAdminShowPassword.isSelected()) {
	    	
    
    		newPasswordTxt.setText(newHiddenPasswordTxt.getText());
    		newPasswordTxt.setVisible(true);
    		newHiddenPasswordTxt.setVisible(false);
	    
    	}else {
    		newHiddenPasswordTxt.setText(newPasswordTxt.getText());
    		newHiddenPasswordTxt.setVisible(true);
    		newPasswordTxt.setVisible(false);
    	}
    }

    @FXML
    void updateAdmin(ActionEvent event) {
    	User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();

    		
    	if(adminNameTxt.getText().isEmpty() || adminSurnameTxt.getText().isEmpty() || adminIdTxt.getText().isEmpty() || adminUsernameTxt.getText().isEmpty() || (adminHiddenPasswordTxt.getText().isEmpty() && adminPasswordTxt.getText().isEmpty())) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Campos Vacíos");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Por favor, rellene todos los campos para poder actualizar un administrador");
	    	alert.showAndWait();
    	}else {
    		if (adminHiddenPasswordTxt.getText().equals(adminPasswordTxt.getText())) {
    			
    			thisAdmin.setName(adminNameTxt.getText());
    			thisAdmin.setSurname(adminSurnameTxt.getText());
    			thisAdmin.setId(adminIdTxt.getText());
    			thisAdmin.setUsername(adminUsernameTxt.getText());
    			thisAdmin.setPassword(adminHiddenPasswordTxt.getText());
    		}
    			
    	}
    }
    
    //CLIENTS METHODS
    

    
    @FXML
    void cleanList(ActionEvent event) {

    }

    @FXML
    void deleteClient(ActionEvent event) {

    }

    @FXML
    void exportClientList(ActionEvent event) {

    }

    @FXML
    void importClientList(ActionEvent event) {

    }
   
     @FXML
    void updateClient(ActionEvent event) {

    }
    
}
