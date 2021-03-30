package ui;

import java.io.File;
import java.io.FileNotFoundException;
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
import model.Customer;
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
    
    //clients_pane.fxml attributes
    
    @FXML
    private AnchorPane orderPaner;

    @FXML
    private TableView<Customer> tvClients;

    @FXML
    private TableColumn<Customer, String> tcClientName;

    @FXML
    private TableColumn<Customer, String> tcClientSurname;

    @FXML
    private TableColumn<Customer, String> tcClientId;

    @FXML
    private TableColumn<Customer, String> tcClientAddress;

    @FXML
    private TableColumn<Customer, String> tcClientPhone;

    @FXML
    private TableColumn<Customer, String> tcClientComments;

    @FXML
    private TextField clientNameTxt;

    @FXML
    private TextField clientSurnameTxt;

    @FXML
    private TextField clientIdTxt;

    @FXML
    private TextField clientAddressTxt;

    @FXML
    private TextField clientPhoneTxt;

    @FXML
    private Label labClientComments;

    @FXML
    private TextField importClientSeparatorTxt;

    @FXML
    private TextField exportClientSeparatorTxt;
    
    //
    
    
    private Restaurant restaurant;
    
    public UsersGUI() {
    	initializeAdminTableView ();
    }
    
     public void injectAdminGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
    
    
    
    //ADMIN METHODS
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
    	initializeAdminTableView ();
    	
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
    
    public void initializeClientTableView () {
    	
    	ObservableList<Customer> observableList = FXCollections.observableArrayList(restaurant.getCustomers());
    	tvClients.setItems(observableList);
    	tcClientName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
    	tcClientSurname.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
    	tcClientId.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
    	tcClientAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
    	tcClientPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
    	tcClientComments.setCellValueFactory(new PropertyValueFactory<Customer, String>("comments"));
    	
    }

    
    @FXML
    void cleanList(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Confirme borrado de datos");
    	dialog.setHeaderText("Por favor, escriba 'SI' si desea limpiar la lista de clientes ");

    	// Traditional way to get the response value.
    	String input = "";
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	   input = result.get();
    	}
    	if (input.equals("SI")) {
    		restaurant.getCustomers().clear();
    	}
    }

    @FXML
    void deleteClient(ActionEvent event) {
    	User thisAdmin = tvAdmins.getSelectionModel().getSelectedItem();
    	
    	try {
			restaurant.deletedAdminUser(thisAdmin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void exportClientList(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
   	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Reporte-Clientes.csv");


        //Show save file dialog
        
        File file = fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());

        if (file != null) {
        	try {
				restaurant.exportCustomers(file.getAbsolutePath(), exportClientSeparatorTxt.getText());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @FXML
    void importClientList(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
      	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        
        if (file != null) {
        	try {
				restaurant.importCustomers(file.getAbsolutePath(), importClientSeparatorTxt.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    @FXML
    void showClientInfo(MouseEvent event) {
    	Customer thisCustomer = tvClients.getSelectionModel().getSelectedItem();
    	
    	clientNameTxt.setText(thisCustomer.getName());
    	clientSurnameTxt.setText(thisCustomer.getSurname());
    	clientIdTxt.setText(thisCustomer.getId());
    	clientAddressTxt.setText(thisCustomer.getAddress());
    	clientPhoneTxt.setText(thisCustomer.getPhoneNumber());
    	
    	
    }
   
     @FXML
    void updateClient(ActionEvent event) {
    	 Customer thisCustomer = tvClients.getSelectionModel().getSelectedItem();

 		
     	if(clientNameTxt.getText().isEmpty() || clientSurnameTxt.getText().isEmpty() || clientIdTxt.getText().isEmpty() || clientAddressTxt.getText().isEmpty() || clientPhoneTxt.getText().isEmpty()) {
     		Alert alert = new Alert(AlertType.ERROR);
 	    	alert.setTitle("Campos Vacíos");
 	    	alert.setHeaderText(null);
 	    	alert.setContentText("Por favor, rellene todos los campos para poder actualizar un cliente");
 	    	alert.showAndWait();
     	}else {
     		
 			thisCustomer.setName(clientNameTxt.getText());
 			thisCustomer.setSurname(clientSurnameTxt.getText());
 			thisCustomer.setId(clientIdTxt.getText());
 			thisCustomer.setAddress(clientAddressTxt.getText());
 			thisCustomer.setPhoneNumber(clientPhoneTxt.getText());
     			
     	}
    }
    
}
