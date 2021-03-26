package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Product;
import model.Restaurant;

public class EmployeeGUI {
	@FXML
    private AnchorPane menuAndOrderPane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Spinner<?> amountChooser;

    @FXML
    private TextArea orderCommentTxt;

    @FXML
    private ComboBox<?> typeChooser;

    @FXML
    private ComboBox<?> productChooser;

    @FXML
    private ComboBox<?> sizeChooser;

    @FXML
    private TableView<Product> tvMenuProductsList;

    @FXML
    private TableColumn<?, ?> tcMenuProductName;

    @FXML
    private TableColumn<?, ?> tcMenuProductType;

    @FXML
    private TableColumn<?, ?> tcMenuProductSize;

    @FXML
    private TableColumn<?, ?> tcMenuProductPrice;

    @FXML
    private TextField clientNameTxt;

    @FXML
    private TextField clientSurnameTxt;

    @FXML
    private TextField labClientId;

    @FXML
    private TextField clientPhoneTxt;

    @FXML
    private TextField clientAddressTxt;

    @FXML
    private TextArea clientCommentTxt;

    @FXML
    private AnchorPane orderPaner;

    @FXML
    private ListView<?> lvOrders;

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
    private Label labOrderId;

    @FXML
    private Label labOrderTotalPrice;

    @FXML
    private Label labOrderStatus;

    @FXML
    private Label labOrderClientName;

    @FXML
    private Label labOrderEmployeeName;

    @FXML
    private Label labOrderComments;

    @FXML
    private Label labClientComments;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<?> timePicker;

    @FXML
    private Label labDate;

    @FXML
    private Label labTime;
    
    //private WelcomeGUI welcomeGUI;
    private Restaurant restaurant;
    
    public EmployeeGUI() {
    	//welcomeGUI = new WelcomeGUI(restaurant);
    	restaurant = new Restaurant();
    }

    @FXML
    public void addProductToList(ActionEvent event) {

    }

    @FXML
    public void changeStatusToDelivered(ActionEvent event) {

    }

    @FXML
    public void changeStatusToInProcess(ActionEvent event) {

    }

    @FXML
    public void changeStatusToSent(ActionEvent event) {

    }

    @FXML
    public void cleanProductsLists(ActionEvent event) {

    }

    @FXML
    public void createOrder(ActionEvent event) {

    }

    @FXML
    public void deleteProduct(ActionEvent event) {

    }

    @FXML
    public void filterOrders(ActionEvent event) {

    }

    /*@FXML
    public void goBack(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		
		fxmlLoader.setController(this);
		Parent WelcomeWindow = fxmlLoader.load();
    	
		menuAndOrderPane.getChildren().clear();
		menuAndOrderPane.setStyle("-fx-background-color:black; -fx-opacity:1;");
		menuAndOrderPane.getChildren().addAll(WelcomeWindow);
    }*/

    @FXML
    public void showMenuPane(ActionEvent event) {

    }

    @FXML
    public void showOrdersPane(ActionEvent event) {

    }
}
