package ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import model.Customer;
import model.DetailProduct;
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
    private ComboBox<String> productChooser;

    @FXML
    private ComboBox<String> sizeChooser;

    @FXML
    private TableView<DetailProduct> tvMenuProductsList;

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
    
    @FXML
    private Button bttDeleteProduct;
    
    @FXML
    private Label searchingTime;

    @FXML
    private TextField searchCustomerName;

    @FXML
    private TextField searchCustomerLastName;
    
    private Customer orderCustomer;
    
    private WelcomeGUI welcomeGUI;
    private Restaurant restaurant;
    
    private ArrayList<DetailProduct> productsList = new ArrayList<DetailProduct>();
    
    public EmployeeGUI() {

    }
    
    public void injectWelcomeGUI(WelcomeGUI welcomeGUI, Restaurant restaurant) {
    	this.welcomeGUI = welcomeGUI;
    	this.restaurant = restaurant;
    }
    
    public void timer() {
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
    	  @Override
    	  public void run() {
    	   Date date = new Date ();
    	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    	   Platform.runLater(() -> labDate.setText(sdf.format(date)));
    	  }
    	}, 0, 1000);
    }
    
    public void loadOptions() {
    	//Separating types enable
		ArrayList<String> types = new ArrayList<>();
		for(int i = 0; i<restaurant.getTypes().size(); i++) {
			if(restaurant.getTypes().get(i).isAvailability()) {
				types.add(restaurant.getTypes().get(i).getName());
			}
		}
		ObservableList<String> typesEnable = FXCollections.observableArrayList(types);
		typeChooser.setItems(typesEnable);
    }
    
    @FXML
    public void showMenuPane(ActionEvent event) {
    	menuPane.setVisible(true);
    	orderPane.setVisible(false);
    	ArrayList<String> typesStringList = new ArrayList<String>();
    	for(int i = 0; i<restaurant.getTypes().size(); i++) {
    		if(restaurant.getTypes().get(i).isAvailability()) {
    			typesStringList.add(restaurant.getTypes().get(i).getName());
    		}
    	}
    	ObservableList<String> categoryList = FXCollections.observableArrayList(typesStringList);
    	typeChooser.getItems().addAll(categoryList);
    }

    @FXML
    public void showOrdersPane(ActionEvent event) {
    	menuPane.setVisible(false);
    	orderPane.setVisible(true);
    	ObservableList<String> orders = FXCollections.observableArrayList(restaurant.getIdOrders());
    	lvOrders.setItems(orders);
    }
    
    //ActionEvent methods of Menu AnchorPane
    
    public void loadProductsList() {
    	ObservableList<DetailProduct> observableList = FXCollections.observableArrayList(productsList);
    	tvMenuProductsList.setItems(observableList);
    	tcOrderProductName.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("productName"));
    	tcOrderProductType.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("category"));
    	tcOrderProductSize.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("size"));
    	tcOrderProductPrice.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("totalPrice"));
    	tcAmount.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("amountToString"));
    	
    	tvMenuProductsList.refresh();
    }
    
    @FXML
    public void categoryChosen(ActionEvent event) { //Method to fill the comboBox of products according to the chosen category
    	String selectedType = typeChooser.getSelectionModel().getSelectedItem();
    	ArrayList<String> productsEnabled = new ArrayList<String>();
    	for(int i = 0; i<restaurant.getProducts().size(); i++) {
    		if(restaurant.getProducts().get(i).isAvailability() && restaurant.getProducts().get(i).getType().getName().equals(selectedType)) {
    			productsEnabled.add(restaurant.getProducts().get(i).getName());
    		}
    	}
    	ObservableList<String> productsList = FXCollections.observableArrayList(productsEnabled);
    	productChooser.setItems(productsList);
    	productChooser.setDisable(false);
    }
    
    @FXML
    public void productChosen(ActionEvent event) { //Method to fill the comboBox of sizes according to the chosen product
    	String p = productChooser.getSelectionModel().getSelectedItem();
      	boolean exit = false;
		for(int i = 0; i<restaurant.getProducts().size() && !exit; i++) {
    		if(restaurant.getProducts().get(i).isAvailability() && restaurant.getProducts().get(i).getName().equals(p)) {
    	    	ObservableList<String> productsList = FXCollections.observableArrayList(restaurant.getProducts().get(i).getSizes());
    	    	sizeChooser.setItems(productsList);
    	    	exit = true;
    		}
    	}
		
    }
    
    @FXML
    public void addProductToList(ActionEvent event) {
    	//Searching the selected product in the List of Products of the Restaurant
    	
    	int indexProduct = 0;
    	boolean found = false;
    	for(int i = 0; i<restaurant.getProducts().size() & !found; i++) {
    		if(restaurant.getProducts().get(i).getName().equals(productChooser.getValue())) {
    			indexProduct = i;
    			found = true;
    		}
    	}
    	
    	//Searching the selected size in the list of product selected's sizes
    	int indexSize = restaurant.getProducts().get(indexProduct).getSizes().indexOf(sizeChooser.getValue());
    	DetailProduct detailProduct = new DetailProduct(restaurant.getProducts().get(indexProduct), amountChooser.getValue(), restaurant.getProducts().get(indexProduct).getPricesBySizes().get(indexSize));
    	productsList.add(detailProduct);
    	
    	detailProduct.getProduct().setCont(amountChooser.getValue());
    	detailProduct.getProduct().setTotal(Integer.parseInt(detailProduct.getTotalPrice()));
    	loadProductsList();
    }
    
    @FXML
    public void productSelected(MouseEvent event) {
    	bttDeleteProduct.setDisable(false);
    }
    
    @FXML
    public void cleanProductsLists(ActionEvent event) {
		if(!tvMenuProductsList.getItems().isEmpty())	{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmación limpiar lista");
			alert.setHeaderText("Limpiar Lista de Productos");
			alert.setContentText("¿Estás seguro de limpiar toda la lista de productos?");
		
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				tvMenuProductsList.getItems().clear();
				tvMenuProductsList.refresh();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Limpiar lista");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No hay productos en la lista por limpiar");
	    	alert.showAndWait();
		}
    }
    
  //BinarySearch Customer

    @FXML
    public void searchCustomer(ActionEvent event) throws InterruptedException {

        long init = System.currentTimeMillis();
         
        Thread.sleep(2000);
         
        orderCustomer = restaurant.binarySearchCustomer(searchCustomerName.getText(), searchCustomerLastName.getText());
        long end = System.currentTimeMillis();
         
        double tiempo = (double) (end - init);

        searchingTime.setText(tiempo +" Milisegundos");

        if (orderCustomer != null){
        	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Busqueda de cliente");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El cliente " + orderCustomer.getName() + " " + orderCustomer.getSurname() + " ha sido encontrado");
	    	alert.showAndWait();
	    	
        	clientNameTxt.setText(orderCustomer.getName());
        	clientSurnameTxt.setText(orderCustomer.getSurname());
        	labClientId.setText(orderCustomer.getId());
        	clientPhoneTxt.setText(orderCustomer.getPhoneNumber());
        	clientAddressTxt.setText(orderCustomer.getAddress());
        	clientCommentTxt.setText(orderCustomer.getComments());
        	
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Busqueda de cliente");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El cliente " + searchCustomerName.getText() + " " + searchCustomerLastName.getText() + " no esta en el Sistema");
	    	alert.showAndWait();
        }

    }

    @FXML
    public void createOrder(ActionEvent event) throws IOException {
    	
    	restaurant.addOrder("SOLICITADO", productsList, restaurant.getLoggedUser(), clientNameTxt.getText(), clientSurnameTxt.getText(), clientAddressTxt.getText(), clientPhoneTxt.getText(), orderCommentTxt.getText(), restaurant.getLoggedUser(), clientCommentTxt.getText());
    }

    @FXML
    public void deleteProduct(ActionEvent event) {
    	boolean deleted = false;
    	DetailProduct dp = tvMenuProductsList.getSelectionModel().getSelectedItem();
    	deleted = productsList.remove(dp);
    	if(deleted) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar producto");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El producto ha sido borrado");
	    	alert.showAndWait();
	    	
	    	loadProductsList();
    	}
    }
    
    //ActionEvent methods of Orders AnchorPane
    
    @FXML
    public void changeStatusToInProcess(ActionEvent event) {
    	labOrderStatus.setText("EN PROCESO");
    	bttEnviado.setDisable(false);
    	bttEnProceso.setDisable(true);
    }

    @FXML
    public void changeStatusToSent(ActionEvent event) {
    	labOrderStatus.setText("ENVIADO");
    	bttEnviado.setDisable(true);
    	bttEntregado.setDisable(false);
    }

    @FXML
    public void changeStatusToDelivered(ActionEvent event) {
    	labOrderStatus.setText("ENTREGADO");
    	bttEntregado.setDisable(true);
    }

    @FXML
    public void filterOrders(ActionEvent event) {

    }

    @FXML
    public void logOutFromEmployee(ActionEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmación Cerrar Sesión");
    	alert.setHeaderText("Cerrando Sesión");
    	alert.setContentText("¿Estás seguro de cerrar sesión?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
			
			fxmlLoader.setController(welcomeGUI);
			Parent WelcomeWindow = fxmlLoader.load();
			menuAndOrderPane.getChildren().setAll(WelcomeWindow);
			Stage st = (Stage)WelcomeWindow.getScene().getWindow();
			st.setHeight(600);
			st.setWidth(850);
			welcomeGUI.firstAdmin();
    	}
    }

    
    @FXML 
    public void handleMouseClick(MouseEvent arg0) {
    	int indexOrder = lvOrders.getSelectionModel().getSelectedIndex();
    	if(restaurant.getOrders().get(indexOrder) != null) {
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
	    	
	    	labOrderId.setText(restaurant.getOrders().get(indexOrder).getId());
	    	labOrderStatus.setText(restaurant.getOrders().get(indexOrder).getStatus());
	    	labOrderTotalPrice.setText(Integer.toString(restaurant.getOrders().get(indexOrder).getTotal()));
	    	
	    	txtAreaCommentsOrder.setText(restaurant.getOrders().get(indexOrder).getComments());
	    	txtAreaCommentsCustomer.setText(restaurant.getOrders().get(indexOrder).getCustomer().getComments());
	    	txtAreaCommentsOrder.setEditable(true);
	    	txtAreaCommentsCustomer.setEditable(true);
    	}
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
