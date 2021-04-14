package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Customer;
import model.Employee;
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
    private TextField newPasswordTxt;

    @FXML
    private CheckBox chkbNewAdminShowPassword;

    @FXML
    private TextField adminImportSeparatorTxt;

    @FXML
    private TextField adminExportSeparatorTxt;
    
    //
    
    //clients_pane.fxml attributes
    

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
    private Button updateClientButton;

    @FXML
    private TextField importClientSeparatorTxt;

    @FXML
    private TextField exportClientSeparatorTxt;
    
    //
    
    //operators_pane.fxml ATTRIBUTES
    

    @FXML
    private TableView<User> tvOperatorsList;

    @FXML
    private TableColumn<User, String> tcOperatorsName;

    @FXML
    private TableColumn<User, String> tcOperatorsSurname;

    @FXML
    private TableColumn<User, String> tcOperatorsId;

    @FXML
    private TableColumn<User, String> tcOperatorsUsername;

    @FXML
    private TextField operatorNameTxt;

    @FXML
    private TextField operatorSurnameTxt;

    @FXML
    private TextField operatorIdTxt;

    @FXML
    private ToggleButton operatorAvailability;

    @FXML
    private TextField operatorUsernameTxt;

    @FXML
    private PasswordField operatorHiddenPasswordTxt;

    @FXML
    private TextField operatorPasswordTxt;

    @FXML
    private CheckBox chkbShowOperatorPassword;

    @FXML
    private TextField newOperatorNameTxt;

    @FXML
    private TextField newOperatorSurnameTxt;

    @FXML
    private TextField newOperatorIdTxt;

    @FXML
    private TextField newOperatorUsernameTxt;

    @FXML
    private PasswordField newHiddenPasswordTxct;

    @FXML
    private TextField newOperatorPasswordTxt;

    @FXML
    private CheckBox chkbShowNewOperatorPassword;
    
    //admin_employee_pane.fxml attributes
    
    @FXML
    private AnchorPane orderPaner;

    @FXML
    private TableView<Employee> tvAdminEmployees;

    @FXML
    private TableColumn<Employee, String> tcAdminEmployeesName;

    @FXML
    private TableColumn<Employee, String> tcAdminEmployeesSurname;

    @FXML
    private TableColumn<Employee, String> tcAdminEmployeesId;

    @FXML
    private TableColumn<Employee, String> tcAdminsNumberOfEmployeeOrders;

    @FXML
    private TableColumn<Employee, String> tcAdminEmployeesTotalValue;

    @FXML
    private TextField employeeNameTxt;

    @FXML
    private TextField employeeSurnameTxt;

    @FXML
    private TextField employeeIdTxt;

    @FXML
    private Button updateEmployeeButton;

    @FXML
    private ToggleButton tbEmployeeAvailability;

    @FXML
    private Label labEmployeeId1;

    @FXML
    private TextField newEmployeeNameTxt;

    @FXML
    private TextField newEmployeeSurnameTxt;

    @FXML
    private TextField newEmployeeIdTxt;

    @FXML
    private TextField employeeImportSeparatorTxt;

    @FXML
    private TextField employeeExportSeparatorTxt;
    
    //////////////////////////////////////////////
    
    private Restaurant restaurant;
    
    private WelcomeGUI welcomeGUI;
    
    public UsersGUI() {
    	
    }
    
     public void injectAdminGUI(Restaurant restaurant, WelcomeGUI welcomeGUI) {
    	this.restaurant = restaurant;
    	this.welcomeGUI = welcomeGUI;
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
    			initializeAdminTableView();
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
    public void cleanList(ActionEvent event) {
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
    		try {
				welcomeGUI.saveRestaurantData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		initializeClientTableView();
  	    	clientNameTxt.setDisable(true);
    		clientNameTxt.setText(null);
   		
    		clientSurnameTxt.setDisable(true);
    		clientSurnameTxt.setText(null);

    		clientIdTxt.setDisable(true);
    		clientIdTxt.setText(null);

    		clientAddressTxt.setDisable(true);
    		clientAddressTxt.setText(null);

    		clientPhoneTxt.setDisable(true);
    		clientPhoneTxt.setText(null);
    	}
    }

    @FXML
    void deleteClient(ActionEvent event) {
    	Customer thisCustomer = tvClients.getSelectionModel().getSelectedItem();
    	
    	try {
			restaurant.deleteCustomer(thisCustomer);
			Alert alert = new Alert(AlertType.INFORMATION);
  	    	alert.setTitle("Cliente Eliminado");
  	    	alert.setHeaderText(null);
  	    	alert.setContentText("El cliente ha sido eliminado exitosamente.");
  	    	alert.showAndWait();
  	    	initializeClientTableView();
  	    	clientNameTxt.setDisable(true);
    		clientNameTxt.setText(null);
   		
    		clientSurnameTxt.setDisable(true);
    		clientSurnameTxt.setText(null);

    		clientIdTxt.setDisable(true);
    		clientIdTxt.setText(null);

    		clientAddressTxt.setDisable(true);
    		clientAddressTxt.setText(null);

    		clientPhoneTxt.setDisable(true);
    		clientPhoneTxt.setText(null);
    		
    		labClientComments.setText(null);
    	    updateClientButton.setDisable(true);;
    	    
    	    welcomeGUI.saveRestaurantData();
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
    void importClientList(ActionEvent event) {	// TODO check the separator exception
    	FileChooser fileChooser = new FileChooser();
      	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        
        if (file != null) {
        	try {
				restaurant.importCustomers(file.getAbsolutePath(), importClientSeparatorTxt.getText());
				initializeClientTableView();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
        }
        try {
			welcomeGUI.saveRestaurantData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void showClientInfo(MouseEvent event) {
    	Customer thisCustomer = tvClients.getSelectionModel().getSelectedItem();
    	
    	if (thisCustomer != null) {
    		clientNameTxt.setDisable(false);
    		clientNameTxt.setText(thisCustomer.getName());
   		
    		clientSurnameTxt.setDisable(false);
    		clientSurnameTxt.setText(thisCustomer.getSurname());

    		clientIdTxt.setDisable(false);
    		clientIdTxt.setText(thisCustomer.getId());

    		clientAddressTxt.setDisable(false);
    		clientAddressTxt.setText(thisCustomer.getAddress());

    		clientPhoneTxt.setDisable(false);
    		clientPhoneTxt.setText(thisCustomer.getPhoneNumber());
    		
    		labClientComments.setText(thisCustomer.getComments());
    	}
    	
    	
    }
   
     @FXML
    void updateClient(ActionEvent event) {	//TODO implement java sort
    	 Customer thisCustomer = tvClients.getSelectionModel().getSelectedItem();

 		
     	if(clientNameTxt.getText().isEmpty() || clientSurnameTxt.getText().isEmpty() || clientIdTxt.getText().isEmpty() || clientAddressTxt.getText().isEmpty() || clientPhoneTxt.getText().isEmpty()) {
     		Alert alert = new Alert(AlertType.ERROR);
 	    	alert.setTitle("Campos Vacíos");
 	    	alert.setHeaderText(null);
 	    	alert.setContentText("Por favor, rellene todos los campos para poder actualizar un cliente");
 	    	alert.showAndWait();
     	}else if (thisCustomer == null) {
  			Alert alert = new Alert(AlertType.ERROR);
  	    	alert.setTitle("Error al actualizar");
  	    	alert.setHeaderText(null);
  	    	alert.setContentText("Por favor, seleccione un cliente");
  	    	alert.showAndWait();
  		}else {
     		
 			try {
				restaurant.updateCustomer(thisCustomer, clientNameTxt.getText(), clientSurnameTxt.getText(), clientIdTxt.getText(), clientAddressTxt.getText(), clientPhoneTxt.getText(), labClientComments.getText());
				welcomeGUI.saveRestaurantData();
 			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			tvClients.refresh();
     			
     	}
    }
     
     //OPERATOR METHODS
     
     public void initializeOperatorsTableView () {
    	 
    	 ObservableList<User> observableList = FXCollections.observableArrayList(restaurant.getOperatorsUsers());
    	 tvOperatorsList.setItems(observableList);
    	 tcOperatorsName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    	 tcOperatorsSurname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
    	 tcOperatorsId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
    	 tcOperatorsUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
     }
     
     @FXML
     void changeOperatorAvailability(ActionEvent event) {
    	 User thisOperator = (User) tvOperatorsList.getSelectionModel().getSelectedItem();
     	
     	if (operatorAvailability.isSelected()) {
     		operatorAvailability.setText("HABILITADO");
     		thisOperator.setAvailability(true);
     	}else {
     		operatorAvailability.setText("DESHABILITADO");
     		thisOperator.setAvailability(false);
     	}
     		
     }

     @FXML
     void cleanOperatorsList(ActionEvent event) {
    	 TextInputDialog dialog = new TextInputDialog();
    	 dialog.setTitle("Confirme borrado de datos");
    	 dialog.setHeaderText("Por favor, escriba 'SI' si desea limpiar la lista de operadores ");
		
    	 // Traditional way to get the response value.
    	 String input = "";
    	 Optional<String> result = dialog.showAndWait();
    	 if (result.isPresent()){
		   input = result.get();
    	 }
    	 if (input.equals("SI")) {
    		 restaurant.getOperatorsUsers().clear();
    	 }
     }

     @FXML
     void confirmOperatorPassword(ActionEvent event) {
    	 if(!chkbShowOperatorPassword.isSelected()) {
     		User thisOperator = tvOperatorsList.getSelectionModel().getSelectedItem();
 	    	
 	    	TextInputDialog dialog = new TextInputDialog();
 	    	dialog.setTitle("Contraseña");
 	    	dialog.setHeaderText("Por favor, confirme su contraseña");
 	
 	    	// Traditional way to get the response value.
 	    	String inputPassword = "";
 	    	Optional<String> result = dialog.showAndWait();
 	    	if (result.isPresent()){
 	    	   inputPassword = result.get();
 	    	}
 	    	if (inputPassword.equals(thisOperator.getPassword())) {
 	    		operatorPasswordTxt.setText(inputPassword);
 	    		operatorPasswordTxt.setVisible(true);
 	    		operatorHiddenPasswordTxt.setVisible(false);
 	    	}
     	}else {
     		operatorHiddenPasswordTxt.setText(operatorPasswordTxt.getText());
     		operatorHiddenPasswordTxt.setVisible(true);
     		operatorPasswordTxt.setVisible(false);
     	}
     }

     @FXML
     void createOperator(ActionEvent event) {
    	 if(newOperatorNameTxt.getText().isEmpty() || newOperatorSurnameTxt.getText().isEmpty() || newOperatorIdTxt.getText().isEmpty() || newOperatorUsernameTxt.getText().isEmpty() || (newOperatorPasswordTxt.getText().isEmpty() && newHiddenPasswordTxct.getText().isEmpty())) {
     		Alert alert = new Alert(AlertType.ERROR);
 	    	alert.setTitle("Campos Vacíos");
 	    	alert.setHeaderText(null);
 	    	alert.setContentText("Por favor, rellene todos los campos para poder crear un operario");
 	    	alert.showAndWait();
     	}else {
     		if (newOperatorPasswordTxt.getText().equals(newHiddenPasswordTxct.getText())) {
     			try {
     				restaurant.addUser(newOperatorNameTxt.getText(), newOperatorSurnameTxt.getText(), newOperatorIdTxt.getText(), null, newOperatorUsernameTxt.getText(), newOperatorPasswordTxt.getText(), null);
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     		}
     		
     	}
    	 initializeOperatorsTableView ();
     }

     @FXML
     void deleteOperator(ActionEvent event) {
    	 User thisOperator = tvOperatorsList.getSelectionModel().getSelectedItem();
     	
     	try {
 			restaurant.deleteUser(thisOperator);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     }

     @FXML
     void showNewOperatorPassword(ActionEvent event) {
    	 if(!chkbShowNewOperatorPassword.isSelected()) {
 	    	
    		    
    		 newOperatorPasswordTxt.setText(newHiddenPasswordTxct.getText());
     		newOperatorPasswordTxt.setVisible(true);
     		newHiddenPasswordTxct.setVisible(false);
 	    
     	}else {
     		newHiddenPasswordTxct.setText(newOperatorPasswordTxt.getText());
     		newHiddenPasswordTxct.setVisible(true);
     		newOperatorPasswordTxt.setVisible(false);
     	}
     }

     @FXML
     void updateOperator(ActionEvent event) {
     	User thisOperator = tvOperatorsList.getSelectionModel().getSelectedItem();

		
     	if(operatorNameTxt.getText().isEmpty() || operatorSurnameTxt.getText().isEmpty() || operatorIdTxt.getText().isEmpty() || operatorUsernameTxt.getText().isEmpty() || (operatorHiddenPasswordTxt.getText().isEmpty() && operatorPasswordTxt.getText().isEmpty())) {
     		Alert alert = new Alert(AlertType.ERROR);
 	    	alert.setTitle("Campos Vacíos");
 	    	alert.setHeaderText(null);
 	    	alert.setContentText("Por favor, rellene todos los campos para poder actualizar un administrador");
 	    	alert.showAndWait();
     	}else {
     		if (adminHiddenPasswordTxt.getText().equals(adminPasswordTxt.getText())) {
     			
     			thisOperator.setName(operatorNameTxt.getText());
     			thisOperator.setSurname(operatorSurnameTxt.getText());
     			thisOperator.setId(operatorIdTxt.getText());
     			thisOperator.setUsername(operatorUsernameTxt.getText());
     			thisOperator.setPassword(operatorHiddenPasswordTxt.getText());
     			initializeOperatorsTableView();
     		}
     			
     	}    	 
     }
     
     //EMPLOYEE METHODS
     
     public void initializeEmployeeTableView () {
    	 
    	 ObservableList<Employee> observableList = FXCollections.observableArrayList(restaurant.getEmployees());
    	 tvAdminEmployees.setItems(observableList);
    	 tcAdminEmployeesName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    	 tcAdminEmployeesSurname.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
    	 tcAdminEmployeesId.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
    	 tcAdminsNumberOfEmployeeOrders.setCellValueFactory(new PropertyValueFactory<Employee, String>("ordersCont"));
    	 tcAdminEmployeesTotalValue.setCellValueFactory(new PropertyValueFactory<Employee, String>("totalSum"));
     }
     
     @FXML
     void changeEmployeeAvailability(ActionEvent event) {
    	 Employee thisEmployee = tvAdminEmployees.getSelectionModel().getSelectedItem();
     	
     	if (tbEmployeeAvailability.isSelected()) {
     		tbEmployeeAvailability.setText("HABILITADO");
     		thisEmployee.setAvailability(true);
     	}else {
     		tbEmployeeAvailability.setText("DESHABILITADO");
     		thisEmployee.setAvailability(false);
     	}
     	try{
     		welcomeGUI.saveRestaurantData();
     	}catch (IOException ioe) {
     		ioe.printStackTrace();
     	}
     }
     
     @FXML
     void cleanEmployeeList(ActionEvent event) {
		 TextInputDialog dialog = new TextInputDialog();
		 dialog.setTitle("Confirmar borrado de datos");
		 dialog.setHeaderText("Por favor, escriba 'SI' si desea limpiar la lista de empleados ");
		
		 // Traditional way to get the response value.
		 String input = "";
		 Optional<String> result = dialog.showAndWait();
		 if (result.isPresent()){
			 input = result.get();
		 }
		 if (input.equals("SI")) {
			restaurant.getEmployees().clear();
			try {
				welcomeGUI.saveRestaurantData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initializeEmployeeTableView();
			tbEmployeeAvailability.setDisable(true);
			employeeNameTxt.setDisable(true);
	    	employeeSurnameTxt.setDisable(true);
	    	employeeIdTxt.setDisable(true);
	    	updateEmployeeButton.setDisable(true);
	    	tbEmployeeAvailability.setText("Disponibilidad");
  	    	employeeNameTxt.setText(null);
  	    	employeeSurnameTxt.setText(null);
  	    	employeeIdTxt.setText(null);
		 }
     }

     @FXML
     void createEmployee(ActionEvent event) {
    	 if(newEmployeeNameTxt.getText().isEmpty() || newEmployeeSurnameTxt.getText().isEmpty() || newEmployeeIdTxt.getText().isEmpty() ) {
      		Alert alert = new Alert(AlertType.ERROR);
  	    	alert.setTitle("Campos Vacíos");
  	    	alert.setHeaderText(null);
  	    	alert.setContentText("Por favor, rellene todos los campos para poder crear un empleado");
  	    	alert.showAndWait();
      	}else {
  			try {
  				if(restaurant.addEmployee(newEmployeeNameTxt.getText(), newEmployeeSurnameTxt.getText(), newEmployeeIdTxt.getText(), null)) {
  					Alert alert = new Alert(AlertType.INFORMATION);
	  	  	    	alert.setTitle("Empleado Creado");
	  	  	    	alert.setHeaderText(null);
	  	  	    	alert.setContentText("El empleado ha sido creado exitosamente.");
	  	  	    	alert.showAndWait();
	  	     		welcomeGUI.saveRestaurantData();
	  	     		initializeEmployeeTableView ();

  				}else {
  					Alert alert = new Alert(AlertType.ERROR);
  		  	    	alert.setTitle("Error al crear");
  		  	    	alert.setHeaderText(null);
  		  	    	alert.setContentText("El usuario que intenta crear ya existe");
  		  	    	alert.showAndWait();
  				}
	  				
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
      	}
  		
     }
     @FXML
     void deleteEmployeeItem(ActionEvent event) {	//TODO 
    	 Employee thisEmployee = tvAdminEmployees.getSelectionModel().getSelectedItem();
    	 
    	 try {
			restaurant.deleteEmployee(thisEmployee);
			Alert alert = new Alert(AlertType.INFORMATION);
  	    	alert.setTitle("Empleado Eliminado");
  	    	alert.setHeaderText(null);
  	    	alert.setContentText("El empleado ha sido eliminado exitosamente.");
  	    	alert.showAndWait();
	     	welcomeGUI.saveRestaurantData();

  	    	initializeEmployeeTableView();
  	    	tbEmployeeAvailability.setDisable(true);
  	    	employeeNameTxt.setDisable(true);
  	    	employeeSurnameTxt.setDisable(true);
  	    	employeeIdTxt.setDisable(true);
  	    	updateEmployeeButton.setDisable(true);
  	    	tbEmployeeAvailability.setText("Disponibilidad");
  	    	employeeNameTxt.setText(null);
  	    	employeeSurnameTxt.setText(null);
  	    	employeeIdTxt.setText(null);
  	    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

     @FXML
     void exportEmployeesList(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
       	 
         //Set extension filter for text files
         FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
         fileChooser.getExtensionFilters().add(extFilter);
         fileChooser.setInitialFileName("Reporte-Empleados.csv");


         //Show save file dialog
         
         File file = fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());

         if (file != null) {
         	try {
 				restaurant.exportEmployees(file.getAbsolutePath(), employeeExportSeparatorTxt.getText());
 			} catch (FileNotFoundException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         }
     }

     @FXML
     void importEmployeesList(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
      	 
         //Set extension filter for text files
         FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
         fileChooser.getExtensionFilters().add(extFilter);
         
         File file = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
         
         if (file != null) {
         	try {
 				restaurant.importEmployees(file.getAbsolutePath(), employeeImportSeparatorTxt.getText());
  	     		welcomeGUI.saveRestaurantData();

 				initializeEmployeeTableView();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         }
     }
     
     @FXML
     void selectEmployeeItem(MouseEvent event) {
    	Employee thisEmployee = tvAdminEmployees.getSelectionModel().getSelectedItem();
    	
    	if (thisEmployee != null) {
    		tbEmployeeAvailability.setDisable(false);
        	employeeNameTxt.setDisable(false);
        	employeeSurnameTxt.setDisable(false);
        	employeeIdTxt.setDisable(false);
        	updateEmployeeButton.setDisable(false);
        	
        	if(thisEmployee.isAvailability()) {
        		tbEmployeeAvailability.setText("HABILITADO");
        		tbEmployeeAvailability.setSelected(true);
        	}else {
        		tbEmployeeAvailability.setText("DESHABILITADO");
        		tbEmployeeAvailability.setSelected(false);
        	}
        		
         	
        	employeeNameTxt.setText(thisEmployee.getName());
        	employeeSurnameTxt.setText(thisEmployee.getSurname());
         	employeeIdTxt.setText(thisEmployee.getId());
    	}
    	
     	  	 
     }

     @FXML
     void updateEmployeeBasicInfo(ActionEvent event) {
    	Employee thisEmployee = tvAdminEmployees.getSelectionModel().getSelectedItem();
 		
      	if(employeeNameTxt.getText().isEmpty() || employeeSurnameTxt.getText().isEmpty() || employeeIdTxt.getText().isEmpty()) {
      		Alert alert = new Alert(AlertType.ERROR);
  	    	alert.setTitle("Campos Vacíos");
  	    	alert.setHeaderText(null);
  	    	alert.setContentText("Por favor, rellene todos los campos para poder actualizar un empleado");
  	    	alert.showAndWait();
      	}else {
      		if (thisEmployee == null) {
      			Alert alert = new Alert(AlertType.ERROR);
	  	    	alert.setTitle("Error al actualizar");
	  	    	alert.setHeaderText(null);
	  	    	alert.setContentText("Por favor, seleccione un empleado");
	  	    	alert.showAndWait();
      		}else {
      			try {
    				restaurant.updateEmployee(thisEmployee, employeeNameTxt.getText(), employeeSurnameTxt.getText(), employeeIdTxt.getText());
    				Alert alert = new Alert(AlertType.INFORMATION);
	  	  	    	alert.setTitle("Empleado Actualizado");
	  	  	    	alert.setHeaderText(null);
	  	  	    	alert.setContentText("El empleado ha sido actualizado exitosamente.");
	  	  	    	alert.showAndWait();
	  	     		welcomeGUI.saveRestaurantData();

	  	  	    	tvAdminEmployees.refresh();
		  	  	    tbEmployeeAvailability.setDisable(true);
		  	    	employeeNameTxt.setDisable(true);
		  	    	employeeSurnameTxt.setDisable(true);
		  	    	employeeIdTxt.setDisable(true);
		  	    	updateEmployeeButton.setDisable(true);
      			
      			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
      		}
      	}	
     }

    
}
