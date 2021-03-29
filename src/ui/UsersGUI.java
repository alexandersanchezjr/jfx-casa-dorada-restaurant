package ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    private Label labAdminId;

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
    private PasswordField newPasswordTxt;

    @FXML
    private CheckBox chkbNewAdminShowPassword;

    @FXML
    private DatePicker adminDatePicker;

    @FXML
    private ChoiceBox<?> adminTimePicker;

    @FXML
    private TextField adminImportSeparatorTxt;

    @FXML
    private TextField adminExportSeparatorTxt;
    
    //
    
    private Restaurant restaurant;
    
    public UsersGUI() {
    	initializeAdminTableView ();
    }
    
    public void initializeAdminTableView () {
    	
    	ObservableList<User> observableList = FXCollections.observableArrayList(restaurant.getAdmins());
    	tvAdmins.setItems(observableList);
    	tcAdminName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    	tcAdminSurname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
    	tcAdminId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
    	tcAdminUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    }
    
    public void injectAdminGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
}
