package ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;
import model.Restaurant;

public class EmployeeGUI {
	@FXML
    private AnchorPane menuAndOrderPane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Spinner<Integer> amountChooser;

    @FXML
    private TextArea orderCommentTxt;

    @FXML
    private ComboBox<String> typeChooser;

    @FXML
    private ComboBox<Product> productChooser;

    @FXML
    private ComboBox<String> sizeChooser;

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
    private AnchorPane orderPane;

    @FXML
    private ListView<String> lvOrders;

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
    
    @FXML
    private Button bttEnProceso;

    @FXML
    private Button bttEnviado;

    @FXML
    private Button bttEntregado;
    
    private WelcomeGUI welcomeGUI;
    private Restaurant restaurant;
    
    public EmployeeGUI() {
    	injectWelcomeGUI(welcomeGUI, restaurant);
    }
    
    public void injectWelcomeGUI(WelcomeGUI welcomeGUI, Restaurant restaurant) {
    	this.welcomeGUI = welcomeGUI;
    }
    
    public void timer() {
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
    	  @Override
    	  public void run() {
    	   Date date = new Date ();
    	   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	   Platform.runLater(() -> labTime.setText(sdf.format(date)));
    	  }
    	}, 0, 1000);
    }

    @FXML
    public void addProductToList(ActionEvent event) {

    }

    @FXML
    public void changeStatusToDelivered(ActionEvent event) {
    	labOrderStatus.setText("ENTREGADO");
    }

    @FXML
    public void changeStatusToInProcess(ActionEvent event) {
    	labOrderStatus.setText("EN PROCESO");
    	bttEnviado.setDisable(false);
    	bttEnProceso.setDisable(true);
    }

    @FXML
    public void changeStatusToSent(ActionEvent event) {
    	labOrderStatus.setText("ENVIADO");
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

    @FXML
    public void goBackFromEmployee(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		
		fxmlLoader.setController(welcomeGUI);
		Parent WelcomeWindow = fxmlLoader.load();
		menuAndOrderPane.getChildren().setAll(WelcomeWindow);
		Stage st = (Stage)WelcomeWindow.getScene().getWindow();
		st.setHeight(600);
		st.setWidth(850);
		welcomeGUI.firstAdmin();
    }

    @FXML
    public void showMenuPane(ActionEvent event) {

    }

    @FXML
    public void showOrdersPane(ActionEvent event) {
    	menuPane.setVisible(false);
    	orderPane.setVisible(true);
    	ObservableList<String> orders = FXCollections.observableArrayList(restaurant.getIdOrders());
    	lvOrders.setItems(orders);
    	
    	int indexOrder = lvOrders.getSelectionModel().getSelectedIndex();
    	if(restaurant.getOrders().get(indexOrder).getStatus().equalsIgnoreCase("SOLICITADO")) {
    		bttEnviado.setDisable(true);
    		bttEntregado.setDisable(true);
    	}
    	else if(restaurant.getOrders().get(indexOrder).getStatus().equalsIgnoreCase("EN_PROCESO")) {
    		bttEnProceso.setDisable(true);
    		bttEntregado.setDisable(true);
    	}
    	else if(restaurant.getOrders().get(indexOrder).getStatus().equalsIgnoreCase("ENVIADO")) {
    		bttEnviado.setDisable(true);
    		bttEnProceso.setDisable(true);
    	}
    	else if(restaurant.getOrders().get(indexOrder).getStatus().equalsIgnoreCase("ENTREGADO")) {
    		bttEnProceso.setDisable(true);
    		bttEnviado.setDisable(true);
    		bttEntregado.setDisable(true);
    	}
    }
    
    @FXML 
    public void handleMouseClick(MouseEvent arg0) {
    	
    }
}
