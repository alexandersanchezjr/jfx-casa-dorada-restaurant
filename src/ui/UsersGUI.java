package ui;

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
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class UsersGUI {
	
	//admins_pane.fxml attributes
	
	@FXML
    private AnchorPane adminPane;

    @FXML
    private TableView<?> tvAdmins;

    @FXML
    private TableColumn<?, ?> tcAdminName;

    @FXML
    private TableColumn<?, ?> tcAdminSurname;

    @FXML
    private TableColumn<?, ?> tcAdminId;

    @FXML
    private TableColumn<?, ?> tcAdminUsername;

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

    }
    
    public void injectAdminGUI(Restaurant restaurant) {
    	this.restaurant = restaurant;
    }
}
