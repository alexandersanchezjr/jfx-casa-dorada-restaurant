package ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DetailProduct;
import model.Product;
import model.Restaurant;
import model.Type;

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
    private ComboBox<Type> typeChooser;

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
    private TableView<DetailProduct> tvProducts;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductName;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductType;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductSize;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductPrice;
    
    @FXML
    private TableColumn<DetailProduct, String> tcAmount;

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
    
    @FXML
    private TextArea txtAreaCommentsOrder;

    @FXML
    private TextArea txtAreaCommentsCustomer;
    
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
    	ArrayList<DetailProduct> products = new ArrayList<> ();
    	int i = typeChooser.getSelectionModel().getSelectedIndex();
    	products.add(new DetailProduct(restaurant.getProducts().get(i), amountChooser.getValue(), restaurant.getProducts().get(i).getPricesBySizes().get(i)));
    	
    	ObservableList<DetailProduct> observableList = FXCollections.observableArrayList(products);
    	tcOrderProductName.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("productName"));
    	tcOrderProductType.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("category"));
    	tcOrderProductSize.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("size"));
    	tcOrderProductPrice.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("totalPrice"));
    	tcAmount.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("amountToString"));
    
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
    	int index = lvOrders.getSelectionModel().getSelectedIndex();
    	txtAreaCommentsOrder.setText(restaurant.getOrders().get(index).getComments());
    	txtAreaCommentsCustomer.setText(restaurant.getOrders().get(index).getCustomer().getComments());
    }
    
    @FXML
    public void selectProductItem(ActionEvent event) {
    	if(productChooser.getValue() != null)
    		sizeChooser.setDisable(false);
    }

    @FXML
    public void selectSizeItem(ActionEvent event) {
    	if(sizeChooser.getValue() != null)
    		amountChooser.setDisable(false);
    }

    @FXML
    public void selectTypeItem(ActionEvent event) {
    	if(typeChooser.getValue() != null)
    		productChooser.setDisable(false);
    }

}
