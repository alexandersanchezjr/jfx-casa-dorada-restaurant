package ui;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.Restaurant;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminGUI {
	
	@FXML
    private AnchorPane mainAdminPane;
	
	@FXML
    private Label labTime;

    @FXML
    private AnchorPane mainPane;
    
    private Restaurant restaurant;
    private WelcomeGUI welcomeGUI;
    private InventoryGUI inventoryGUI;
    private UsersGUI usersGUI;
    
    public AdminGUI() {
    	inventoryGUI = new InventoryGUI();
    	usersGUI = new UsersGUI();
    	initialize();
    }
    
    public void initialize() {
    	usersGUI.injectAdminGUI(restaurant);
    	inventoryGUI.injectAdminGUI(restaurant);
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
    	   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	   Platform.runLater(() -> labTime.setText(sdf.format(date)));
    	  }
    	}, 0, 1000);
    }
    
    @FXML
    public void logOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		
		fxmlLoader.setController(welcomeGUI);
		Parent WelcomeWindow = fxmlLoader.load();
		mainAdminPane.getChildren().setAll(WelcomeWindow);
		Stage st = (Stage)WelcomeWindow.getScene().getWindow();
		st.setHeight(600);
		st.setWidth(850);
		welcomeGUI.firstAdmin();
    }

    @FXML
    public void showAdminPane(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admins_pane.fxml"));
		
		fxmlLoader.setController(usersGUI);
		Parent adminPane = fxmlLoader.load();
		mainPane.getChildren().setAll(adminPane);
		AnchorPane.setTopAnchor(adminPane, 0.0);
		AnchorPane.setBottomAnchor(adminPane, 0.0);
		AnchorPane.setLeftAnchor(adminPane, 0.0);
		AnchorPane.setRightAnchor(adminPane, 0.0);
    }
    
    @FXML
    public void showOperatorPane(ActionEvent event) {

    }

    @FXML
    public void showClientsPane(ActionEvent event) {

    }

    @FXML
    public void showEmployeePane(ActionEvent event) {

    }

    @FXML
    public void showIngredientsPane(ActionEvent event) {

    }

    @FXML
    public void showOrdersPane(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_orders_pane.fxml"));
		
		fxmlLoader.setController(inventoryGUI);
		Parent OrdersPane = fxmlLoader.load();
		mainPane.getChildren().setAll(OrdersPane);
		AnchorPane.setTopAnchor(OrdersPane, 0.0);
		AnchorPane.setBottomAnchor(OrdersPane, 0.0);
		AnchorPane.setLeftAnchor(OrdersPane, 0.0);
		AnchorPane.setRightAnchor(OrdersPane, 0.0);
    }

    @FXML
    public void showProductsPane(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("products_pane.fxml"));
		
		fxmlLoader.setController(inventoryGUI);
		Parent productPane = fxmlLoader.load();
		mainPane.getChildren().setAll(productPane);
		AnchorPane.setTopAnchor(productPane, 0.0);
		AnchorPane.setBottomAnchor(productPane, 0.0);
		AnchorPane.setLeftAnchor(productPane, 0.0);
		AnchorPane.setRightAnchor(productPane, 0.0);
    }

    @FXML
    public void showTypesPane(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("type_of_products_pane.fxml"));
		
    	fxmlLoader.setController(inventoryGUI);
		Parent typesPane = fxmlLoader.load();
		mainPane.getChildren().setAll(typesPane);
		AnchorPane.setTopAnchor(typesPane, 0.0);
		AnchorPane.setBottomAnchor(typesPane, 0.0);
		AnchorPane.setLeftAnchor(typesPane, 0.0);
		AnchorPane.setRightAnchor(typesPane, 0.0);
    }
}
