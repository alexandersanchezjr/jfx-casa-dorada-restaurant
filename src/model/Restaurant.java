package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Restaurant {
	//Constants to Serialization
	
	public final static String EMPLOYEE_FILE_NAME = "data/employees.cdr";
	public final static String OPERATORS_USERS_FILE_NAME = "data/operators.cdr";
	public final static String ADMINS_USERS_FILE_NAME = "data/admins.cdr";
	public final static String CUSTOMERS_FILE_NAME = "data/customers.cdr";
	public final static String ORDERS_FILE_NAME = "data/orders.cdr";
	public final static String PRODUCTS_FILE_NAME = "data/products.cdr";
	public final static String TYPES_PRODUCTS_FILE_NAME = "data/typesOfProducts.cdr";
	public final static String INGREDIENTS_FILE_NAME = "data/ingredients.cdr";
	public final static String[] ed = new String[5];
	
	//Attributes (Lists)
	
	private ArrayList<Employee> employees;
	private ArrayList<User> operatorsUsers;
	private ArrayList<User> admins;
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> types;
	private ArrayList<Customer> customers;
	private ArrayList<Order> orders;
	private User loggedUser;
	private long identifier;
	
	public Restaurant() {
		employees = new ArrayList<Employee>();
		setOperatorsUsers(new ArrayList<User>());
		admins = new ArrayList<User>();
		products = new ArrayList<Product>();
		ingredients = new ArrayList<Ingredient>();
		types = new ArrayList<Type>();
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
		setLoggedUser(null);
		identifier = 0;
	}

	/**
	 * @return the employees
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	/**
	 * @return the customers
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public ArrayList<String> getIdOrders() {
		ArrayList<String> ids = new ArrayList<>();
		for(int i = 0; i<orders.size(); i++) {
			ids.add(orders.get(i).toString());
		}
		return ids;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the admins
	 */
	public ArrayList<User> getAdmins() {
		return admins;
	}

	/**
	 * @return the operatorsUsers
	 */
	public ArrayList<User> getOperatorsUsers() {
		return operatorsUsers;
	}

	/**
	 * @param operatorsUsers the operatorsUsers to set
	 */
	public void setOperatorsUsers(ArrayList<User> operatorsUsers) {
		this.operatorsUsers = operatorsUsers;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the types
	 */
	public ArrayList<Type> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}
	
	/**
	 * @return the loggedUser
	 */
	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param loggedUser the loggedUser to set
	 */
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	//Product MANAGEMENT

	public boolean addProduct(String name, ArrayList<Ingredient> ingredients, ArrayList<PriceBySize> pricesBySizes, boolean availability, String selectedType, boolean typeAvailability, User typeCreator, String size, String price) throws IOException {
		boolean added = false;
		Product newProduct = new Product(name,(identifier++), ingredients, pricesBySizes, availability, selectedType, typeAvailability, typeCreator, size, price, loggedUser);
		if(!products.contains(newProduct)) {	
			added = products.add(newProduct);
			saveRestaurantData();

		}
		return added;
	}
	
	public void updateProduct(Product p, String name, boolean availability, String selectedType, ArrayList<Ingredient> ingredients) throws IOException {
		p.setName(name);
		p.setAvailability(availability);
		p.setType(selectedType);
		p.setModifier(loggedUser);
		p.setIngredients(ingredients);
		saveRestaurantData();

	}
	
	public boolean deleteProduct(Product p) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i = 0; i<orders.size() && !found; i++) {
			for(int j = 0; j<orders.get(i).getProducts().size() && !found; j++) {
				if(orders.get(i).getProducts().get(j).getProduct().equals(p)) {
					found = true;
				}
			}
		}
		if(found == false) {
			deleted = products.remove(p);
			saveRestaurantData();

		}
		return deleted;
	}
	
	public boolean disableProduct(Product p) throws IOException {
		boolean disabled = false;
		if(p.isAvailability()) {
			p.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	public boolean enableProduct(Product p) throws IOException {
		boolean disabled = false;
		if(!p.isAvailability()) {
			p.setAvailability(true);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	//Type of product MANAGEMENT
	
	public boolean addTypeProduct(String name, boolean availability) throws IOException {
		boolean added = false;
		Type thisType = new Type(name, availability, loggedUser);
		if(!types.contains(thisType)) {
			added = types.add(thisType);
			saveRestaurantData();

		}
		return added;
	}
	
	public boolean deleteTypeProduct(Type t) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i = 0; i<products.size() && !found; i++ ) {
			if(products.get(i).getType().equals(t)) {
				found = true;
			}
		}
		if(found == false) {
			deleted = types.remove(t);
			saveRestaurantData();

		}
		return deleted;
	}
	
	public boolean disableType(Type t) throws IOException {
		boolean disabled = false;
		if(t.isAvailability()) {
			t.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	public boolean enableType(Type t) throws IOException {
		boolean disabled = false;
		if(!t.isAvailability()) {
			t.setAvailability(true);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	//Employee MANAGEMENT
	
	public boolean addEmployee(String name, String surname, String id, User creator) throws IOException {
		boolean added = false;
		Employee thisEmployee = new Employee(name, surname,id, loggedUser);
		if(!employees.contains(thisEmployee)) {
			added = employees.add(thisEmployee);
			saveRestaurantData();

		}
		return added;
	}
	
	public void updateEmployee(Employee e, String name, String surname, String id) throws IOException {
		e.setName(name);
		e.setSurname(surname);
		e.setId(id);
		e.setModifier(loggedUser);
		saveRestaurantData();

	}
	
	public boolean deleteEmployee(Employee e) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i = 0; i<orders.size() && !found; i++) {
			if(orders.get(i).getEmployee().equals(e)) {
				found = true;
			}
		}
		if(found == false) {
			deleted = employees.remove(e);
			saveRestaurantData();

		}
		return deleted;
	}
	
	public boolean enableEmployee(Employee e) throws IOException {
		boolean disabled = false;
		if(!e.isAvailability()) {
			e.setAvailability(true);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	public boolean disableEmployee(Employee e) throws IOException {
		boolean disabled = false;
		if(e.isAvailability()) {
			e.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	//User MANAGEMENT
	
	public boolean addUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) throws IOException {
		boolean added = false;
		User thisUser = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!operatorsUsers.contains(thisUser)) { // If contains() generate error, change with a normal search with a for loop
			added = operatorsUsers.add(thisUser);
			saveRestaurantData();

		}
		return added;
	}
	
	public void updateUser(User u, String username, String password) throws IOException {
		u.setUsername(username);
		u.setPassword(password);
		u.setModifier(loggedUser);
		saveRestaurantData();

	}
	
	public boolean deleteUser(User u) throws IOException {
		boolean deleted = false;
		boolean found = false;
		for(int i = 0; i<orders.size() && !found; i++) { //If the user to delete is CREATOR or MODIFIER of an ORDER
			if(orders.get(i).getCreator().equals(u) || orders.get(i).getModifier().equals(u)) {
				found = true;
			}
		}
		for(int i = 0; i<customers.size() && !found; i++) { //If the user to delete is CREATOR or MODIFIER of a CUSTOMER
			if(customers.get(i).getCreator().equals(u) || customers.get(i).getModifier().equals(u)) {
				found = true;
			}
		}
		if(found == false) {
			deleted = operatorsUsers.remove(u);
		}
		saveRestaurantData();

		return deleted;
	}
	
	public boolean enableUser(User u) throws IOException {
		boolean disabled = false;
		if(!u.isAvailability()) {
			u.setAvailability(true);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	public boolean disableUser(User u) throws IOException {
		boolean disabled = false;
		if(u.isAvailability()) {
			u.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	// AdminUser MANAGEMENT
	
	public boolean addAdminUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) throws IOException {
		boolean added = false;
		User thisAdmin = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!admins.contains(thisAdmin)) { // If contains() generate error, change with a normal search with a for loop
			added = admins.add(thisAdmin);
			saveRestaurantData();

		}
		return added;
	}
	
	public boolean deletedAdminUser(User u) throws IOException {	//TO BE CHECKED
		boolean deleted = false;
		boolean found = false;
		for(int i = 0; i<products.size() && !found; i++) { //If the user to delete is CREATOR or MODIFIER of a PRODUCT, INGREDIENT OR TYPE OF PRODUCT
			if((products.get(i).getCreator().equals(u) || products.get(i).getModifier().equals(u))) {
				found = true;
			}
			
		}
		for(int i = 0; i<ingredients.size() && !found; i++) {
			if(ingredients.get(i).getCreator().equals(u) || ingredients.get(i).getModifier().equals(u)) {
				found = true;
			}
		}
		
		for(int i = 0; i<types.size() && !found; i++) {
			if(types.get(i).getCreator().equals(u) || types.get(i).getModifier().equals(u)) {
				found = true;
			}
		}
		
		for( int i = 0; i<employees.size() && !found; i++) { //If the user to delete is CREATOR or MODIFIER of an EMPLOYEE, OPERATOR USER OR ADMIN
			if((employees.get(i).getCreator().equals(u) || employees.get(i).getModifier().equals(u)) || (operatorsUsers.get(i).getCreator().equals(u) || operatorsUsers.get(i).getModifier().equals(u)) || (admins.get(i).getCreator().equals(u) || admins.get(i).getModifier().equals(u))) {
				found = true;
			}
		}
		
		if(found == false) {
			deleted = admins.remove(u);
		}
		saveRestaurantData();

		return deleted;
	}
	
	//Ingredients MANAGEMENT
	
	public boolean addIngredient(String name, boolean availability) throws IOException {
		boolean added = false;
		Ingredient thisIngredient = new Ingredient(name, availability, (identifier++), loggedUser);
		if(!ingredients.contains(thisIngredient)) {	
			added = ingredients.add(thisIngredient);
			saveRestaurantData();

		}
		return added;
	}
	
	public boolean deleteIngredient(Ingredient ingredient) throws IOException {
		boolean deleted = false;
		for(int i = 0; i<products.size(); i++) {
			if(!products.get(i).getIngredients().contains(ingredient)) {
				deleted = ingredients.remove(ingredient);
				saveRestaurantData();

			}
		}
		return deleted;
	}
	
	public void updateIngredient(Ingredient i, String name) throws IOException {
		i.setName(name);
		i.setModifier(loggedUser);
		saveRestaurantData();

	}
	
	public boolean enableIngredient(Ingredient ingredient) throws IOException {
		boolean enabled = false;
		if(!ingredient.isAvailability()) {
			ingredient.setAvailability(true);
			enabled = true;
			saveRestaurantData();

		}
		return enabled;
	}
	
	public boolean disableIngredient(Ingredient ingredient) throws IOException {
		boolean disabled = false;
		if(ingredient.isAvailability()) {
			ingredient.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	//Order MANAGEMENT
	
	public boolean addOrder(String selectedStatus, ArrayList<DetailProduct> products, Employee e, String customerName, String customerSurname, String customerId, String customerAddress, String customerPhoneNumber, String comments, User customerCreator, String customerComments) throws IOException {
		boolean added = false;
		Order newOrder = new Order((identifier++), selectedStatus, products,  e, customerName, customerSurname, customerId, customerAddress, customerPhoneNumber, comments, customerCreator, customerComments, loggedUser);
		if(!orders.contains(newOrder)) {
			added = orders.add(newOrder);
			orders.get(orders.indexOf(newOrder)).getEmployee().addOrder();
			saveRestaurantData();

		}
		return added;
	}
	
	public boolean deleteOrder(Order o) throws IOException {
		boolean deleted = false;
		if(o.getStatus().equalsIgnoreCase("CANCELADO")) {
			deleted = orders.remove(o);
			saveRestaurantData();

		}
		return deleted;
	}
	
	public void updateOrder(Order o, String status, ArrayList<DetailProduct> products, Employee e, String comments) throws IOException {
		o.setStatus(status);
		o.setProducts(products);
		o.setEmployee(e);
		o.setComments(comments);
		o.setModifier(loggedUser);
		saveRestaurantData();

	}
	
	//Customer MANAGEMENT
	public boolean addCustomer(String name, String surname, String id, String address, String phoneNumber, String comments) throws IOException {
		boolean added = false;
		Customer thisCustomer = new Customer(name, surname, id, address, phoneNumber, comments, loggedUser);
		if(!customers.contains(thisCustomer)) {
			for(int i = 0; i<customers.size(); i++) {
				if((thisCustomer.getName().compareToIgnoreCase(customers.get(i).getName()) > 0) || thisCustomer.getName().compareToIgnoreCase(customers.get(i).getName()) == 0) {
					customers.add(i, thisCustomer);
					added = true;
				}
				else {
					customers.add(i+1, thisCustomer);
					added = true;
				}
			}
			added = customers.add(thisCustomer);
		}
		saveRestaurantData();

		return added;
	}
	
	public boolean deleteCustomer(Customer c) throws IOException {
		boolean deleted = false;
		for(int i = 0; i<orders.size(); i++) {
			if((!orders.get(i).getStatus().equalsIgnoreCase("CANCELADO")) && (!orders.get(i).getCustomer().equals(c))) {
				deleted = customers.remove(c);
				saveRestaurantData();

			}
		}
		return deleted;
	}
	
	public void updateCustomer(Customer c, String name, String surname, String id, String address, String phoneNumber, String comments) throws IOException {
		c.setName(name);
		c.setSurname(surname);
		c.setId(id);
		c.setAddress(address);
		c.setPhoneNumber(phoneNumber);
		c.setComments(comments);
		c.setModifier(loggedUser);
		saveRestaurantData();
	}
	
	public boolean enableCustomer(Customer c) throws IOException {
		boolean enabled = false;
		if(!c.isAvailability()) {
			c.setAvailability(true);
			enabled = true;
			saveRestaurantData();

		}
		return enabled;
	}
	
	public boolean disableCustomer(Customer c) throws IOException {
		boolean disabled = false;
		if(c.isAvailability()) {
			c.setAvailability(false);
			disabled = true;
			saveRestaurantData();

		}
		return disabled;
	}
	
	//Serialization 
	
	public void saveRestaurantData() throws IOException {
		ObjectOutputStream oosE = new ObjectOutputStream(new FileOutputStream(EMPLOYEE_FILE_NAME));
		oosE.writeObject(employees);
		oosE.close();
		
		ObjectOutputStream oosO = new ObjectOutputStream(new FileOutputStream(OPERATORS_USERS_FILE_NAME));
		oosO.writeObject(operatorsUsers);
		oosO.close();
		
		ObjectOutputStream oosA = new ObjectOutputStream(new FileOutputStream(ADMINS_USERS_FILE_NAME));
		oosA.writeObject(admins);
		oosA.close();
		
		ObjectOutputStream oosC = new ObjectOutputStream(new FileOutputStream(CUSTOMERS_FILE_NAME));
		oosC.writeObject(customers);
		oosC.close();
		
		ObjectOutputStream oosOR = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE_NAME));
		oosOR.writeObject(orders);
		oosOR.close();
		
		ObjectOutputStream oosP = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE_NAME));
		oosP.writeObject(products);
		oosP.close();
		
		ObjectOutputStream oosT = new ObjectOutputStream(new FileOutputStream(TYPES_PRODUCTS_FILE_NAME));
		oosT.writeObject(types);
		oosT.close();
		
		ObjectOutputStream oosI = new ObjectOutputStream(new FileOutputStream(INGREDIENTS_FILE_NAME));
		oosI.writeObject(ingredients);
		oosI.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadRestaurantData() throws IOException, ClassNotFoundException {
		ObjectInputStream oisE = new ObjectInputStream(new FileInputStream(EMPLOYEE_FILE_NAME));
		employees = (ArrayList<Employee>)oisE.readObject();
		oisE.close();
		
		ObjectInputStream oisO = new ObjectInputStream(new FileInputStream(OPERATORS_USERS_FILE_NAME));
		operatorsUsers = (ArrayList<User>)oisO.readObject();
		oisO.close();
		
		ObjectInputStream oisA = new ObjectInputStream(new FileInputStream(ADMINS_USERS_FILE_NAME));
		admins = (ArrayList<User>)oisA.readObject();
		oisA.close();
		
		ObjectInputStream oisC = new ObjectInputStream(new FileInputStream(CUSTOMERS_FILE_NAME));
		customers = (ArrayList<Customer>)oisC.readObject();
		oisC.close();
		
		ObjectInputStream oisOR = new ObjectInputStream(new FileInputStream(ORDERS_FILE_NAME));
		orders = (ArrayList<Order>)oisOR.readObject();
		oisOR.close();
		
		ObjectInputStream oisP = new ObjectInputStream(new FileInputStream(PRODUCTS_FILE_NAME));
		products = (ArrayList<Product>)oisP.readObject();
		oisP.close();
		
		ObjectInputStream oisT = new ObjectInputStream(new FileInputStream(TYPES_PRODUCTS_FILE_NAME));
		types = (ArrayList<Type>)oisT.readObject();
		oisT.close();
		
		ObjectInputStream oisI = new ObjectInputStream(new FileInputStream(INGREDIENTS_FILE_NAME));
		ingredients = (ArrayList<Ingredient>)oisI.readObject();
		oisI.close();
	}
	
	//EXPORT EMPLOYEES
	
	public void exportEmployees(String fileName, String separator) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);

	    for(int i = 0; i<employees.size(); i++) {
	      Employee thisEmployee = employees.get(i);
	      pw.println(thisEmployee.getName()+ "separator" +thisEmployee.getSurname()+"separator"+thisEmployee.getId()+"separator"+thisEmployee.getOrdersCont());
	    }

	    pw.close();
	}
	public void exportOrders(String fileName, String separator, String listViewId) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);
	    pw.println("Pedido ID" + separator + "Nombre del Cliente" + separator + "Dirección del Cliente" + separator + "Teléfono del Cliente" + separator + "Nombre del Empleado" + separator + "Estado del Pedido" + separator + "Fecha y Hora" + separator + "Observaciones del Pedido");
	   
	    for(int i = 0; i < orders.size(); i++) {
	    	
	    	Order thisOrder = orders.get(i);
	    	if(thisOrder.getId().equals(listViewId)) {
		    	String productString = "";
		    	String previous = separator;
		    	for(int j = 0; j < thisOrder.getProducts().size(); j++){
		    		productString += previous + thisOrder.getProducts().get(j).getProduct().getName() + separator + thisOrder.getProducts().get(j).getAmount() + separator + thisOrder.getProducts().get(j).getSelectedSize().getPrice();
		    	}
		    	pw.println(thisOrder.getId() + separator + thisOrder.getCustomer().getName() + separator + thisOrder.getCustomer().getAddress() + separator + thisOrder.getCustomer().getPhoneNumber()+ separator + thisOrder.getEmployee().getName() + separator + thisOrder.getStatus() + separator + thisOrder.getDate() + separator + thisOrder.getComments() + productString);

	    	}
	    }
	    pw.close();
	}
}
