package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
/*import javafx.fxml.FXMLLoader;
import model.Restaurant;
import javafx.scene.Parent;*/
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DetailProduct;
import model.Ingredient;
import model.Order;
import model.PriceBySize;
import model.Restaurant;
import model.Type;

public class InventoryGUI {

	//admin_orders_pane.fxml attributes
	
	@FXML
    private AnchorPane orderPane;

    @FXML
    private ListView<String> lvOrders;

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
    private TableView<DetailProduct> tvProducts;

    @FXML
    private TableColumn<?, ?> tcOrderProductName1;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductName;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductType;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductSize;

    @FXML
    private TableColumn<DetailProduct, String> tcOrderProductPrice;

    @FXML
    private Label labOrderComments;

    @FXML
    private Label labClientComments;

    @FXML
    private TextField ordersImportSeparatorTxt;

    @FXML
    private TextField ordersExportSeparatorTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> timePicker;
    
    @FXML
    private DatePicker datePicker1;

    @FXML
    private ChoiceBox<String> timePicker1;
    
    @FXML
    private Button bttFilter;
    
    private String[] timesStart = {"00:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "23:59"};
	private String[] timesEnd = {"00:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "23:59"};

    
    //products_pane.fxml attributes
    
    @FXML
    private AnchorPane productsPane;

    @FXML
    private ListView<String> lvProducts;

    @FXML
    private ComboBox<String> cbIngredients;

    @FXML
    private TextField sizeNameTxt;

    @FXML
    private TableView<Ingredient> tvIngredients;

    @FXML
    private TableColumn<Ingredient, String> tcProductIngredientName;

    @FXML
    private TableColumn<Ingredient, String> tcProductIngredientCode;

    @FXML
    private TableView<PriceBySize> tvSizes;

    @FXML
    private TableColumn<PriceBySize, String> tcProductSizeName;

    @FXML
    private TableColumn<PriceBySize, Integer> tcProductSizePrice;

    @FXML
    private TextField sizePriceTxt;

    @FXML
    private TextField newProductNameTxt;

    @FXML
    private ComboBox<String> cbTypeForNewProduct;

    @FXML
    private ComboBox<String> cbIngredientForNewProduct;

    @FXML
    private TableView<Ingredient> tvIngredientsForNewProduct;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientsNameNewProduct;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientsCodeNewProduct;

    @FXML
    private TableView<PriceBySize> tvSizesForNewProduct;

    @FXML
    private TableColumn<PriceBySize, String> tcSizeNameNewProduct;

    @FXML
    private TableColumn<PriceBySize, Integer> tcSizePriceNewProduct;

    @FXML
    private TextField newSizePriceTxt;

    @FXML
    private TextField newSizeTxt;

    @FXML
    private Label labProductId;

    @FXML
    private TextField productNameTxt;

    @FXML
    private ToggleButton tbAvailability;

    @FXML
    private ComboBox<String> cbCategoryInProducts;

    @FXML
    private TextField productsImportSeparatorTxt;

    @FXML
    private TextField productsExportSeparatorTxt;
    
    //type_of_products_pane.fxml attributes
    
    @FXML
    private AnchorPane typePane;

    @FXML
    private ListView<String> lvTypes;

    @FXML
    private Label labTypeId;

    @FXML
    private TextField typeNameTxt;

    @FXML
    private ToggleButton tbTypeAvailability;

    @FXML
    private TextField newTypeNameTxt;

    @FXML
    private ToggleButton tbNewTypeAvailability;

    @FXML
    private TextField typeImportSeparatorTxt;

    @FXML
    private TextField typeExportSeparator;
    
    //ingredients_pane.fxml attributes
    
    @FXML
    private AnchorPane ingredientsPane;

    @FXML
    private Label labIngredientId;

    @FXML
    private TextField ingredientNameTxt;

    @FXML
    private ToggleButton tbIngredientAvailability;

    @FXML
    private ToggleGroup availability;

    @FXML
    private TextField newIngredientName;

    @FXML
    private ToggleButton tbNewIngredientAvailability;

    @FXML
    private ToggleGroup availability1;

    @FXML
    private TextField ingredientsImportSeparatorTxt;

    @FXML
    private TextField ingredientsExportSeparatorTxt;

    @FXML
    private TableView<Ingredient> tvIngredientsPane;
    
    @FXML
    private TableColumn<Ingredient, String> tcIngredientName;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientId;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientAvailability;

    @FXML
    private TableColumn<Ingredient, String> tcIngredientCreator;

	private Restaurant restaurant;
	private WelcomeGUI welcomeGUI;
    
    public InventoryGUI() {
    	
    }
    
    public void injectAdminGUI(Restaurant restaurant, WelcomeGUI welcomeGUI) {
    	this.restaurant = restaurant;
    	this.welcomeGUI = welcomeGUI;
    }
    
    //Orders Pane ActionEvent methods
    
    public void loadOrders() {
    	ObservableList<String> orders = FXCollections.observableArrayList(restaurant.getIdOrders());
    	lvOrders.setItems(orders);
    }
    
    public void loadOrderProducts(int index) {
    	ObservableList<DetailProduct> observableList = FXCollections.observableArrayList(restaurant.getOrders().get(index).getProducts());
    	tvProducts.setItems(observableList);
    	tcOrderProductName.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("productName"));
    	tcOrderProductType.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("category"));
    	tcOrderProductSize.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("size"));
    	tcOrderProductPrice.setCellValueFactory(new PropertyValueFactory<DetailProduct, String>("price"));
    }
    
    public ArrayList<String> getOrdersIds() {
    	List<String> ids = new ArrayList<String>();
    	ids = lvOrders.getItems().subList(0, lvOrders.getItems().size());
    	return (ArrayList<String>) ids;
    }
    
    @FXML
    public void selectedTime(MouseEvent event) {
    	int index = timePicker.getSelectionModel().getSelectedIndex();
    	ArrayList<String> tempList = new ArrayList<>();
    	for(int i = index; i<timesEnd.length; i++) {
    		tempList.add(timesEnd[i]);
    	}
    	ObservableList<String> listTimesEnd = FXCollections.observableArrayList(tempList);
    	timePicker1.setItems(listTimesEnd);
    }
    
    @FXML
    public void handleMouseClickOrders(MouseEvent event) {
    	if(!lvOrders.getSelectionModel().isEmpty()) {
    		int index = lvOrders.getSelectionModel().getSelectedIndex();
	    	labOrderClientName.setText(restaurant.getOrders().get(index).getCustomer().getName());
	    	labOrderEmployeeName.setText(restaurant.getOrders().get(index).getEmployee().getName());
	    	labOrderId.setText(Integer.toString(index));
	    	labOrderStatus.setText(restaurant.getOrders().get(index).getStatus());
	    	labOrderTotalPrice.setText(Integer.toString(restaurant.getOrders().get(index).getTotal()));
	    	labOrderComments.setText(restaurant.getOrders().get(index).getComments());
	    	labClientComments.setText(restaurant.getOrders().get(index).getCustomer().getComments());
	    	
	    	//Load Products
	    	loadOrderProducts(index);
	    	
	    	//Enable and fill ChoiceBoxs Time and DatePickers
	    	datePicker.setDisable(false);
	    	timePicker.setDisable(false);
	    	datePicker1.setDisable(false);
	    	timePicker1.setDisable(false);
	    	bttFilter.setDisable(false);
	    	
	    	timePicker.setValue("00:00");
	    	timePicker.getItems().addAll(timesStart);
	    	timePicker1.setValue("23:59");
	    	timePicker1.getItems().addAll(timesEnd);
	    	
    	}
    }
    
    @FXML
    public void exportOrdersList(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
      	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Reporte-Pedidos.csv");


        //Show save file dialog
        
        File file = fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());

        if (file != null) {
        	try {
				restaurant.exportOrders(file.getAbsolutePath(), ordersExportSeparatorTxt.getText(), getOrdersIds());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @FXML
    public void filterOrders(ActionEvent event) throws ParseException {
    	String start = datePicker.getValue().toString()+" "+timePicker.getValue();
    	String end = datePicker1.getValue().toString()+" "+timePicker1.getValue();
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	Date startDate = df.parse(start);
    	Date endDate = df.parse(end);
    	
    	ArrayList<String> filteredIds = new ArrayList<String>();
    	for(Order orders:restaurant.getOrders()) {
    		if(orders.getDate().after(startDate) && orders.getDate().before(endDate)) {
    			filteredIds.add(orders.getId());
    		}
    	}
    	ObservableList<String> filteredOrders = FXCollections.observableArrayList(filteredIds);
    	lvOrders.setItems(filteredOrders);
    	lvOrders.refresh();
    }

    @FXML
    public void importOrdersList(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Abrir archivo");
    	File file = fileChooser.showOpenDialog(orderPane.getScene().getWindow());
    	if(file != null) {
    		try {
    			try {
					restaurant.importOrders(file.getAbsolutePath(), ordersImportSeparatorTxt.getText());
				} catch (ParseException e) {
					System.out.println("Parse Exception");
					e.printStackTrace();
				}
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Importar pedidos");
    		    alert.setHeaderText(null);
    		    alert.setContentText("Los pedidos han sido importados");
    		    alert.showAndWait();
    		    loadOrders();
    		} catch(IOException io) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Importar pedidos");
    		    alert.setHeaderText(null);
    		    alert.setContentText("Los pedidos no han podido ser importados");
    		    alert.showAndWait();
    		}
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    //Products Pane ActionEvent methods
    
    public void loadProducts() {
    	ArrayList<String> productsNames = new ArrayList<String>();
    	for(int i = 0; i<restaurant.getProducts().size(); i++) {
    		if(restaurant.getProducts().get(i) != null) {
    			productsNames.add(restaurant.getProducts().get(i).getName());
    		}
    	}
		ObservableList<String> products = FXCollections.observableArrayList(productsNames);
		lvProducts.setItems(products);
		lvProducts.refresh();
    }
    
    public void loadIngredientsAndTypes() {
    	//Separating types enable
		ArrayList<String> types = new ArrayList<>();
		for(int i = 0; i<restaurant.getTypes().size(); i++) {
			if(restaurant.getTypes().get(i).isAvailability()) {
				types.add(restaurant.getTypes().get(i).getName());
			}
		}
		ObservableList<String> typesEnable = FXCollections.observableArrayList(types);
		cbTypeForNewProduct.setItems(typesEnable);
		//cbCategoryInProducts.setItems(typesEnable);
		//Separating ingredients enable
		ArrayList<String> ingredients = new ArrayList<>();
		for(int i = 0; i<restaurant.getIngredients().size(); i++) {
			if(restaurant.getIngredients().get(i).isAvailability()) {
				ingredients.add(restaurant.getIngredients().get(i).getName());
			}
		}
		ObservableList<String> ingredientsEnable = FXCollections.observableArrayList(ingredients);
		cbIngredientForNewProduct.setItems(ingredientsEnable);
    }
    
    private void loadIngredientsAndTypesToUpdate() {
    	ArrayList<String> types = new ArrayList<>();
		for(int i = 0; i<restaurant.getTypes().size(); i++) {
			if(restaurant.getTypes().get(i).isAvailability()) {
				types.add(restaurant.getTypes().get(i).getName());
			}
		}
		ObservableList<String> typesEnable = FXCollections.observableArrayList(types);
		cbCategoryInProducts.setItems(typesEnable);
		
		//Separating ingredients enable
		ArrayList<String> ingredients = new ArrayList<>();
		for(int i = 0; i<restaurant.getIngredients().size(); i++) {
			if(restaurant.getIngredients().get(i).isAvailability()) {
				ingredients.add(restaurant.getIngredients().get(i).getName());
			}
		}
		ObservableList<String> ingredientsEnable = FXCollections.observableArrayList(ingredients);
		cbIngredients.setItems(ingredientsEnable);
    }
    
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    ArrayList<PriceBySize> pricesBySize = new ArrayList<PriceBySize>();
    
    @FXML
    public void addIIngredientToNewProduct(ActionEvent event) throws IOException {
    	String ingredientSelected = cbIngredientForNewProduct.getValue();
    	int indexIngredient = 0;
    	for(int i = 0; i<restaurant.getIngredients().size(); i++) {
    		if(restaurant.getIngredients().get(i).getName().equals(ingredientSelected)) {
    			indexIngredient = i;
    		}
    	}
    	ingredients.add(restaurant.getIngredients().get(indexIngredient));
    	//cbIngredientForNewProduct.setPromptText("Ingredientes");
    	cbIngredientForNewProduct.setValue("Ingredientes");
    	loadIngredientsNewProduct();
    	tvIngredientsForNewProduct.refresh();
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void addSizeToNewProduct(ActionEvent event) {
    	if(newSizeTxt.getText().isEmpty() && newSizePriceTxt.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Agregar tamaño");
		    alert.setHeaderText(null);
		    alert.setContentText("¡Ups! no has ingresado el tamaño o el precio");
		    alert.showAndWait();
    	} else {
    		pricesBySize.add(new PriceBySize(newSizeTxt.getText(), Integer.parseInt(newSizePriceTxt.getText())));
    	}
    	
    	newSizeTxt.setText(""); 
    	newSizeTxt.setPromptText("Tamaño");
    	newSizePriceTxt.setText(""); 
    	newSizePriceTxt.setPromptText("Precio $");
    	loadPricesBySizeNewProduct();
    	tvSizesForNewProduct.refresh();
    }
    
    public void loadIngredientsNewProduct() {
    	ObservableList<Ingredient> ingredientsNewProduct = FXCollections.observableArrayList(ingredients);
    	tvIngredientsForNewProduct.setItems(ingredientsNewProduct);
    	tcIngredientsNameNewProduct.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));
    	tcIngredientsCodeNewProduct.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("id"));
    }
    
    public void loadPricesBySizeNewProduct() {
    	ObservableList<PriceBySize> pricesBySizeList = FXCollections.observableArrayList(pricesBySize);
    	tvSizesForNewProduct.setItems(pricesBySizeList);
    	tcSizeNameNewProduct.setCellValueFactory(new PropertyValueFactory<PriceBySize,String>("size"));
    	tcSizePriceNewProduct.setCellValueFactory(new PropertyValueFactory<PriceBySize,Integer>("price"));
    }
    
    @FXML
    public void createNewProduct(ActionEvent event) throws IOException {
    	boolean added = false;
    	if(newProductNameTxt.getText().isEmpty() || cbTypeForNewProduct.getValue().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Crear producto");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No has ingresado un nombre para el producto o no has seleccionado la categoria");
	    	alert.showAndWait();
    	}
    	else if(tvIngredientsForNewProduct.getItems().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Crear producto");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No has agregado ningun ingrediente");
	    	alert.showAndWait();
    	}
    	else {
			List<Ingredient> ingredientsList = (List<Ingredient>) tvIngredientsForNewProduct.getItems();
    	    ArrayList<Ingredient> ingredients;
    	    if (ingredientsList instanceof ArrayList<?>) {
    	        ingredients = (ArrayList<Ingredient>) ingredientsList;
    	    } else {
    	        ingredients = new ArrayList<Ingredient>(ingredientsList);
    	    }
    	    String type = cbTypeForNewProduct.getSelectionModel().getSelectedItem();
    	    System.out.println(type);
    	    Type t = null;
    	    boolean found = false;
    	    for(int i = 0; i<restaurant.getTypes().size() && !found; i++) {
    	    	if(restaurant.getTypes().get(i).getName().equals(type)) {
    	    		t = restaurant.getTypes().get(i);
    	    		found = true;
    	    	}
    	    }
    		try {
				added = restaurant.addProduct(newProductNameTxt.getText(), ingredients, t);
				System.out.println(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Crear producto");
		    	alert.setHeaderText(null);
		    	alert.setContentText("No ha sido posible crear el producto");
		    	alert.showAndWait();
			}
    		if(added) {
    			Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Crear producto");
		    	alert.setHeaderText(null);
		    	alert.setContentText("El producto ha sido creado y agregado a la lista de productos");
		    	alert.showAndWait();
		    	restaurant.getProducts().subList(restaurant.getProducts().size()-1, restaurant.getProducts().size()).get(0).setPricesBySizes(pricesBySize);
		    	
		    	newProductNameTxt.clear();
		    	cbTypeForNewProduct.getSelectionModel().clearSelection();
		    	cbIngredientForNewProduct.getSelectionModel().clearSelection();
		    	tvIngredientsForNewProduct.getItems().clear();
		    	tvSizesForNewProduct.getItems().clear();
		    	
		    	loadProducts();
		    	lvProducts.refresh();
    		}
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    @FXML
    public void deleteProduct(ActionEvent event) {
    	String thisProduct = lvProducts.getSelectionModel().getSelectedItem();
    	int indexProduct = 0;
    	boolean found = false;
    	boolean deleted = false;
    	for(int i = 0; i<restaurant.getProducts().size() && !found; i++) {
    		if(restaurant.getProducts().get(i).getName().equals(thisProduct)) {
    			indexProduct = i;
    			found = true;
    		}
    	}
    	
    	try {
			deleted = restaurant.deleteProduct(restaurant.getProducts().get(indexProduct));
    	    welcomeGUI.saveRestaurantData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(deleted) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar producto");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El producto ha sido borrado");
	    	alert.showAndWait();
	    	
	    	productNameTxt.clear();
	    	labProductId.setText("#0000");
	    	cbCategoryInProducts.getSelectionModel().clearSelection();
	    	tvIngredients.getItems().clear();
	    	tvSizes.getItems().clear();
	    	
	    	cbIngredients.getSelectionModel().clearSelection();
	    	sizeNameTxt.clear();
	    	sizePriceTxt.clear();
	    	
	    	loadProducts();
	    	lvProducts.refresh();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar producto");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No es posible eliminar este producto, esta referenciado por un pedido");
	    	alert.showAndWait();
	    	
	    	loadProducts();
	    	lvProducts.refresh();
    	}
	}

    @FXML
    public void addIngredient(ActionEvent event) throws IOException {
    	int index = lvProducts.getSelectionModel().getSelectedIndex();
    	for(int i = 0; i<restaurant.getIngredients().size(); i++) {
    		if(restaurant.getIngredients().get(i).getName().equals(cbIngredients.getSelectionModel().getSelectedItem())) {
    			restaurant.getProducts().get(index).addIngredient(restaurant.getIngredients().get(i));
    		}
    	}
    	loadIngredientsProduct(index);
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void addSize(ActionEvent event) throws IOException {
    	int index = lvProducts.getSelectionModel().getSelectedIndex();
    	if(sizeNameTxt.getText().isEmpty() || sizePriceTxt.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¡Ups! No has llenado todos los campos para añadir un tamaño y su precio");
	    	alert.showAndWait();
    	}
    	else {
    		PriceBySize newPriceBySize = new PriceBySize(sizeNameTxt.getText(), Integer.parseInt(sizePriceTxt.getText()));
    		restaurant.getProducts().get(index).addPriceBySize(newPriceBySize);
    		loadPricesBySizeProduct(index);
    	}
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void changeAvailability(ActionEvent event) throws IOException {
    	int index = lvProducts.getSelectionModel().getSelectedIndex();
    	if(tbAvailability.getText().equals("Habilitado")) {
    		restaurant.getProducts().get(index).setAvailability(false);
    		tbAvailability.setText("Deshabilitado");
    	}
    	else {
    		restaurant.getProducts().get(index).setAvailability(true);
    		tbAvailability.setText("Habilitado");
    	}
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void updateProductName(ActionEvent event) throws IOException {
    	int index = lvProducts.getSelectionModel().getSelectedIndex();
    	restaurant.getProducts().get(index).setName(productNameTxt.getText());
    	welcomeGUI.saveRestaurantData();
    }
    
    @FXML
    public void updateComboBox(ActionEvent event) throws IOException {
    	String name = cbCategoryInProducts.getSelectionModel().getSelectedItem();
    	for(int i = 0; i<restaurant.getTypes().size(); i++) {
    		if(restaurant.getTypes().get(i).getName().equals(name)) {
    			restaurant.getProducts().get(lvProducts.getSelectionModel().getSelectedIndex()).setType(name);
    			cbCategoryInProducts.setPromptText(name);
    		}
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    public void loadIngredientsProduct(int indexProduct) {
    	ObservableList<Ingredient> ingredientsProduct = FXCollections.observableArrayList(restaurant.getProducts().get(indexProduct).getIngredients());
		tvIngredients.setItems(ingredientsProduct);
		tcProductIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));
		tcProductIngredientCode.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("id"));
    }
    
    public void loadPricesBySizeProduct(int indexProduct) {
    	ObservableList<PriceBySize> pricesBySize = FXCollections.observableArrayList(restaurant.getProducts().get(indexProduct).getPricesBySizes());
		tvSizes.setItems(pricesBySize);
		tcProductSizeName.setCellValueFactory(new PropertyValueFactory<PriceBySize,String>("size"));
		tcProductSizePrice.setCellValueFactory(new PropertyValueFactory<PriceBySize,Integer>("price"));
    }
    
	@FXML 
	public void handleMouseClick(MouseEvent arg0) {
		if(lvProducts.getSelectionModel().getSelectedItem() != null) {
			int index = 0;
			productNameTxt.setText(restaurant.getProducts().get(index).getName());
			productNameTxt.setEditable(true);
			if(restaurant.getProducts().get(index).isAvailability()) {
				tbAvailability.setText("Habilitado");
			}
			else {
				tbAvailability.setText("Deshabilitado");
			}
			
			for(int i = 0; i<restaurant.getProducts().size(); i++) {
				if(restaurant.getProducts().get(i).getName().equals(productNameTxt.getText())) {
					index = i;
				}
			}
			loadIngredientsAndTypesToUpdate(); //Ingredients and Types available are added in cbCategoryInProducts and cbIngredients comboBoxs
			if(restaurant.getProducts().get(index).getType() == null) {
				System.out.println("El producto tiene la categoria null");
			}
			cbCategoryInProducts.setPromptText(restaurant.getProducts().get(index).getType().getName());
			
			loadIngredientsProduct(index);
			tvIngredients.refresh();
			loadPricesBySizeProduct(index);
			tvSizes.refresh();
			labProductId.setText(Long.toString(restaurant.getProducts().get(index).getId()));
		}
	}
	
	@FXML
    public void exportProductsList(ActionEvent event) {

    }

    @FXML
    public void importProductsList(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
     	 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        
        if (file != null) {
        	try {
				restaurant.importProducts(file.getAbsolutePath(), productsImportSeparatorTxt.getText());
				loadProducts();
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
    
	//Type of Product ActionEvent methods
    
    public void loadCategories() {
    	ArrayList<String> typesNames = new ArrayList<String>();
    	for(int i = 0; i<restaurant.getTypes().size(); i++) {
    		if(restaurant.getTypes().get(i) != null) {
    			typesNames.add(restaurant.getTypes().get(i).getName());
    		}
    	}
		ObservableList<String> types = FXCollections.observableArrayList(typesNames);
		lvTypes.setItems(types);
    }
    
    @FXML
    public void categorySelected(MouseEvent event) {
    	String typeSelected = lvTypes.getSelectionModel().getSelectedItem();
    	if(typeSelected != null) {
    		typeNameTxt.setEditable(true);
			int indexType = 0;
			for(int i = 0; i<restaurant.getTypes().size(); i++) {
				if(restaurant.getTypes().get(i) != null && restaurant.getTypes().get(i).getName().equals(typeSelected)) {
					indexType = i;
				}
			}
    		typeNameTxt.setText(restaurant.getTypes().get(indexType).getName());
			labTypeId.setText("# " + Long.toString(restaurant.getTypes().get(indexType).getId()));
			if(restaurant.getTypes().get(indexType).isAvailability()) {
				tbTypeAvailability.setText("Habilitado");
			}
			else {
				tbTypeAvailability.setText("Deshabilitado");
			}
    	}
    }
    
    @FXML
    public void updateType(ActionEvent event) throws IOException { //Update Type Name
    	int index = lvTypes.getSelectionModel().getSelectedIndex();
    	restaurant.getTypes().get(index).setName(typeNameTxt.getText());
    	loadCategories();
    	lvTypes.refresh();
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void changeTypeAvailability(ActionEvent event) throws IOException {
    	int index = lvTypes.getSelectionModel().getSelectedIndex();
    	if(tbTypeAvailability.getText().equals("Habilitado")) {
    		restaurant.disableType(restaurant.getTypes().get(index));
    		tbTypeAvailability.setText("Deshabilitado");
    	}
    	else {
    		restaurant.enableType(restaurant.getTypes().get(index));
    		tbTypeAvailability.setText("Habilitado");
    	}
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void createNewType(ActionEvent event) throws IOException {
    	boolean added = false;
    	if(newTypeNameTxt.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Crear nueva categoria");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No has ingresado un nombre para la nueva categoria");
	    	alert.showAndWait();
    	}
    	else {
    		boolean availability = tbNewTypeAvailability.getText().equals("Habilitado")? true : false;
    		added = restaurant.addTypeProduct(newTypeNameTxt.getText(), availability);
    	}
    	
    	if(added) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Categoria creada");
	    	alert.setHeaderText(null);
	    	alert.setContentText("La categoria ha sido creada y agregada a la lista de categorias");
	    	alert.showAndWait();
	    	
	    	newTypeNameTxt.clear();
	    	tbNewTypeAvailability.setText("Habilitado");
	    	
	    	loadCategories();
	    	lvTypes.refresh();
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    @FXML
    public void changeNewTypeAvailability(ActionEvent event) {
    	if(tbNewTypeAvailability.getText().equals("Habilitado")) {
    		tbNewTypeAvailability.setText("Deshabilitado");
    	}
    	else {
    		tbNewTypeAvailability.setText("Habilitado");
    	}
    }
    
    @FXML
    public void deleteType(ActionEvent event) throws IOException {
    	boolean deleted = false;
    	String t = lvTypes.getSelectionModel().getSelectedItem();
    	boolean found = false;
    	for(int i = 0; i<restaurant.getTypes().size() && !found; i++) {
    		if(restaurant.getTypes().get(i).getName().equals(t)) {
    			deleted = restaurant.deleteTypeProduct(restaurant.getTypes().get(i));
    			found = true;
    		}
    	}
    	if(deleted) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar Categoría");
	    	alert.setHeaderText(null);
	    	alert.setContentText("La categoría " + t + " ha sido borrada del sistema");
	    	alert.showAndWait();
	    	
	    	loadCategories();
	    	lvTypes.refresh();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar Categoría");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No es posible borrar la categoría " + t + ", existen productos que pertenecen a esta");
	    	alert.showAndWait();
	    	
	    	loadCategories();
	    	lvTypes.refresh();
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    //Ingredients ActionEvent methods
    
    public void loadIngredientsList() {
    	ObservableList<Ingredient> ingredientsList = FXCollections.observableArrayList(restaurant.getIngredients());
		tvIngredientsPane.setItems(ingredientsList);
		tcIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));
		tcIngredientId.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("id"));
		tcIngredientAvailability.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("availabilityText"));
		tcIngredientCreator.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("creatorUsername"));
    }
    
    @FXML
    public void ingredientSelected(MouseEvent event) {
    	if(!tvIngredientsPane.getSelectionModel().isEmpty()) {
	    	int indexIngredient = tvIngredientsPane.getSelectionModel().getSelectedIndex();
	    	ingredientNameTxt.setText(restaurant.getIngredients().get(indexIngredient).getName());
	    	labIngredientId.setText("# " + Long.toString(restaurant.getIngredients().get(indexIngredient).getId()));
	    	tbIngredientAvailability.setText(restaurant.getIngredients().get(indexIngredient).getAvailabilityText());
    	}
    }
    
    @FXML
    public void createNewIngredient(ActionEvent event) throws IOException {
    	boolean added = false;
    	if(newIngredientName.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Crear nuevo ingrediente");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No has ingresado un nombre para el nuevo ingrediente");
	    	alert.showAndWait();
    	}
    	else {
    		boolean availability = true;
    		if(tbNewIngredientAvailability.getText().equals("Deshabilitado")) {
    			availability = false;
        	}
    		added = restaurant.addIngredient(newIngredientName.getText(), availability);
    	}
    	if(added) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Ingrediente creado");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El ingrediente ha sido creado y será agregado a la lista de ingredientes");
	    	alert.showAndWait();
	    	
	    	newIngredientName.setText("");
	    	tbNewIngredientAvailability.setText("Habilitado");
	    	
	    	loadIngredientsList();
	    	tvIngredientsPane.refresh();
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
    @FXML
    public void changeNewIngredientAvailability(ActionEvent event) {
    	if(tbNewIngredientAvailability.getText().equals("Habilitado")) {
    		tbNewIngredientAvailability.setText("Deshabilitado");
    	}
    	else {
    		tbNewIngredientAvailability.setText("Habilitado");
    	}
    }
    
    @FXML
    public void changeIngredientAvailability(ActionEvent event) throws IOException {
    	int index = tvIngredientsPane.getSelectionModel().getSelectedIndex();
    	if(tbIngredientAvailability.getText().equals("Habilitado")) {
    		restaurant.disableIngredient(restaurant.getIngredients().get(index));
    		tbIngredientAvailability.setText("Deshabilitado");
    	}
    	else {
    		restaurant.enableIngredient(restaurant.getIngredients().get(index));
    		tbIngredientAvailability.setText("Habilitado");
    	}
    	loadIngredientsList();
    	tvIngredientsPane.refresh();
    	welcomeGUI.saveRestaurantData();
    }

    @FXML
    public void updateIngredient(ActionEvent event) throws IOException {
    	int index = tvIngredientsPane.getSelectionModel().getSelectedIndex();
    	restaurant.updateIngredient(restaurant.getIngredients().get(index), ingredientNameTxt.getText());
    	ingredientNameTxt.setText("");
    	tbIngredientAvailability.setText("Disponibilidad");
    	loadIngredientsList();
    	tvIngredientsPane.refresh();
    	welcomeGUI.saveRestaurantData();
    }
    
    @FXML
    public void deleteIngredient(ActionEvent event) throws IOException {
    	boolean deleted = false;
    	Ingredient ingredient = tvIngredientsPane.getSelectionModel().getSelectedItem();
    	deleted = restaurant.deleteIngredient(ingredient);
    	if(deleted) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar ingrediente");
	    	alert.setHeaderText(null);
	    	alert.setContentText("El ingrediente " + ingredient.getName() + " ha sido borrado del sistema");
	    	alert.showAndWait();
	    	
	    	loadIngredientsList();
	    	tvIngredientsPane.refresh();
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Eliminar ingrediente");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No es posible borrar el ingrediente " + ingredient.getName() + ", existe(n) producto(s) que contienen el ingrediente");
	    	alert.showAndWait();
	    	boolean found = false;
	    	for(int i = 0; i<restaurant.getProducts().size() && !found; i++) {
	    		if(restaurant.getProducts().get(i).getIngredients().contains(ingredient)) {
	    			found = true;
	    			System.out.println("Este es un ingrediente del producto: " + restaurant.getProducts().get(i).getName());
	    		}
	    	}
	    	
	    	loadIngredientsList();
	    	tvIngredientsPane.refresh();
    	}
    	welcomeGUI.saveRestaurantData();
    }
    
}
