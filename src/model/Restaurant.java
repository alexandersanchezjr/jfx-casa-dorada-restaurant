package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Restaurant implements Serializable{
	//Constants to Serialization
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public ArrayList<String> getIdProducts() {
		ArrayList<String> ids = new ArrayList<>();
		for(int i = 0; i<products.size(); i++) {
			if(products.get(i) != null) {
				ids.add(Long.toString(products.get(i).getId()));
			}
		}
		return ids;
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

	public boolean addProduct(String name, ArrayList<Ingredient> ingredients, Type t) throws IOException {
		boolean added = false;
		Product newProduct = new Product(name,(identifier++), ingredients, t, loggedUser);
		if(!products.contains(newProduct)) {	
			added = products.add(newProduct);

		}
		return added;
	}
	
	public void updateProduct(Product p, String name, boolean availability, String selectedType, ArrayList<Ingredient> ingredients) throws IOException {
		p.setName(name);
		p.setAvailability(availability);
		p.setType(selectedType);
		p.setModifier(loggedUser);
		p.setIngredients(ingredients);

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

		}
		return deleted;
	}
	
	public boolean disableProduct(Product p) throws IOException {
		boolean disabled = false;
		if(p.isAvailability()) {
			p.setAvailability(false);
			disabled = true;

		}
		return disabled;
	}
	
	public boolean enableProduct(Product p) throws IOException {
		boolean disabled = false;
		if(!p.isAvailability()) {
			p.setAvailability(true);
			disabled = true;

		}
		return disabled;
	}
	
	//Type of product MANAGEMENT
	
	public boolean addTypeProduct(String name, boolean availability) throws IOException {
		boolean added = false;
		Type thisType = new Type(name, availability, (identifier++), loggedUser);
		if(!types.contains(thisType)) {
			added = types.add(thisType);

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

		}
		return deleted;
	}
	
	public boolean disableType(Type t) throws IOException {
		boolean disabled = false;
		if(t.isAvailability()) {
			t.setAvailability(false);
			disabled = true;

		}
		return disabled;
	}
	
	public boolean enableType(Type t) throws IOException {
		boolean disabled = false;
		if(!t.isAvailability()) {
			t.setAvailability(true);
			disabled = true;

		}
		return disabled;
	}
	
	//Employee MANAGEMENT
	
	public boolean addEmployee(String name, String surname, String id, User creator) throws IOException {
		boolean added = false;
		Employee thisEmployee = new Employee(name, surname,id, loggedUser);
		if(!employees.contains(thisEmployee)) {
			added = employees.add(thisEmployee);

		}
		return added;
	}
	
	public void updateEmployee(Employee e, String name, String surname, String id) throws IOException {
		int index = employees.indexOf(e);
		employees.get(index).setName(name);
		employees.get(index).setSurname(surname);
		employees.get(index).setId(id);
		employees.get(index).setModifier(loggedUser);

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

		}
		return deleted;
	}
	
	public boolean enableEmployee(Employee e) throws IOException {
		boolean disabled = false;
		boolean changed = false;
		for (int i = 0; i < getEmployees().size() && !changed; i++) {
			if (getEmployees().get(i) == e) {
				getEmployees().get(i).setAvailability(true);
				changed = true;
				disabled = true;
			}
		}
		return disabled;
	}
	
	public boolean disableEmployee(Employee e) throws IOException {
		boolean disabled = false;
		boolean changed = false;
		for (int i = 0; i < getEmployees().size() && !changed; i++) {
			if (getEmployees().get(i) == e) {
				getEmployees().get(i).setAvailability(false);
				changed = true;
				disabled = true;
			}
		}
		return disabled;
	}
	
	//User MANAGEMENT
	
	public boolean addUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) throws IOException {
		boolean added = false;
		User thisUser = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!operatorsUsers.contains(thisUser)) { // If contains() generate error, change with a normal search with a for loop
			added = operatorsUsers.add(thisUser);

		}
		return added;
	}
	
	public void updateUser(User u, String username, String password) throws IOException {
		u.setUsername(username);
		u.setPassword(password);
		u.setModifier(loggedUser);

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

		return deleted;
	}
	
	public boolean enableUser(User u) throws IOException {
		boolean disabled = false;
		boolean changed = false;
		for (int i = 0; i < getOperatorsUsers().size() && !changed; i++) {
			if (getOperatorsUsers().get(i) == u) {
				getOperatorsUsers().get(i).setAvailability(true);
				changed = true;
				disabled = true;
			}
		}
		return disabled;
	}
	
	public boolean disableUser(User u) throws IOException {
		boolean disabled = false;
		boolean changed = false;
		for (int i = 0; i < getOperatorsUsers().size() && !changed; i++) {
			if (getOperatorsUsers().get(i) == u) {
				getOperatorsUsers().get(i).setAvailability(false);
				changed = true;
				disabled = true;
			}
		}
		return disabled;
	}
	
	// AdminUser MANAGEMENT
	
	public boolean addAdminUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) throws IOException {
		boolean added = false;
		User thisAdmin = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!admins.contains(thisAdmin)) { // If contains() generate error, change with a normal search with a for loop
			added = admins.add(thisAdmin);

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

		return deleted;
	}
	
	//Ingredients MANAGEMENT
	
	public boolean addIngredient(String name, boolean availability) throws IOException {
		boolean added = false;
		Ingredient thisIngredient = new Ingredient(name, availability, (identifier++), loggedUser);
		if(!ingredients.contains(thisIngredient)) {	
			added = ingredients.add(thisIngredient);

		}
		return added;
	}
	
	public boolean deleteIngredient(Ingredient ingredient) throws IOException {
		boolean deleted = false;
		for(int i = 0; i<products.size(); i++) {
			if(!products.get(i).getIngredients().contains(ingredient)) {
				deleted = ingredients.remove(ingredient);
			}
		}
		return deleted;
	}
	
	public void updateIngredient(Ingredient i, String name) throws IOException {
		i.setName(name);
		i.setModifier(loggedUser);

	}
	
	public boolean enableIngredient(Ingredient ingredient) throws IOException {
		boolean enabled = false;
		if(!ingredient.isAvailability()) {
			ingredient.setAvailability(true);
			enabled = true;

		}
		return enabled;
	}
	
	public boolean disableIngredient(Ingredient ingredient) throws IOException {
		boolean disabled = false;
		if(ingredient.isAvailability()) {
			ingredient.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
	
	//Order MANAGEMENT
	
	public boolean addOrder(String selectedStatus, ArrayList<DetailProduct> products, Employee e, String customerName, String customerSurname, String customerAddress, String customerPhoneNumber, String comments, User customerCreator, String customerComments) throws IOException {
		boolean added = false;
		Order newOrder = new Order((identifier++), selectedStatus, products,  e, customerName, customerSurname, customerAddress, customerPhoneNumber, comments, customerCreator, customerComments, loggedUser);
		if(!orders.contains(newOrder)) {
			added = orders.add(newOrder);
			orders.get(orders.indexOf(newOrder)).getEmployee().addOrder();
			
			int employeeValue = 0;			
			for (int i = 0; i < newOrder.getProducts().size(); i++) {
				employeeValue += newOrder.getProducts().get(i).getAmount() * newOrder.getProducts().get(i).getSelectedSize().getPrice();
			}
				
			orders.get(orders.indexOf(newOrder)).getEmployee().addOrderValue(employeeValue);
			

		}
		return added;
	}
	
	public boolean deleteOrder(Order o) throws IOException {
		boolean deleted = false;
		if(o.getStatus().equalsIgnoreCase("CANCELADO")) {
			deleted = orders.remove(o);

		}
		return deleted;
	}
	
	public void updateOrder(Order o, String status, ArrayList<DetailProduct> products, Employee e, String comments) throws IOException {
		o.setStatus(status);
		o.setProducts(products);
		o.setEmployee(e);
		o.setComments(comments);
		o.setModifier(loggedUser);

	}
	
	//Customer MANAGEMENT
	public boolean addCustomer(String name, String surname, String id, String address, String phoneNumber, String comments) throws IOException {
		boolean added = false;
		Customer thisCustomer = new Customer(name, surname, address, phoneNumber, comments, loggedUser);
		thisCustomer.setId(id);
		if (customers.isEmpty())
			added = customers.add(thisCustomer);
		else if(!customers.contains(thisCustomer)) {
				int i = 0;
				while (i < customers.size() && (thisCustomer.getName().compareToIgnoreCase(customers.get(i).getName()) < 0)) {
					i++;
				}
				customers.add(i, thisCustomer);
				added = true;
			
		}

		return added;
	}
	
	public boolean deleteCustomer(Customer c) throws IOException {
		boolean deleted = false;
		for(int i = 0; i<orders.size(); i++) {
			if((!orders.get(i).getStatus().equalsIgnoreCase("CANCELADO")) && (!orders.get(i).getCustomer().equals(c))) {
				deleted = customers.remove(c);

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
	}
	
	public boolean enableCustomer(Customer c) throws IOException {
		boolean enabled = false;
		if(!c.isAvailability()) {
			c.setAvailability(true);
			enabled = true;

		}
		return enabled;
	}
	
	public boolean disableCustomer(Customer c) throws IOException {
		boolean disabled = false;
		if(c.isAvailability()) {
			c.setAvailability(false);
			disabled = true;

		}
		return disabled;
	}

	
	//IMPORT CUSTOMERS
	
	public void importCustomers(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(separator);
			String name = parts[0];
			String surname = parts[1];
			String id = parts[2];
			String address = parts[3];
			String phoneNumber = parts[4];
			String comments = parts[5];
			addCustomer(name, surname, id, address, phoneNumber, comments);
			line = br.readLine();
		}
		br.close();

	}
	
	public void exportCustomers (String fileName, String separator) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		
		for(int j = 0; j < customers.size(); j++){
    		pw.write(customers.get(j).getName() + separator + customers.get(j).getSurname() + separator + customers.get(j).getId() + separator + customers.get(j).getAddress() + separator + customers.get(j).getPhoneNumber() + separator + customers.get(j).getComments()); 
    	}
		pw.close();
		
	}
	
	
	//IMPORT EMPLOYEES
	
	public void importEmployees(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null ) {
			String[] parts = line.split(separator);
			if (parts.length > 2) {
				String name = parts[0];
				String surname = parts[1];
				String id = parts[2];
				int ordersCont = Integer.parseInt(parts[3]);
				int totalSum = Integer.parseInt(parts[4]);
				employees.add(new Employee(name, surname, id, loggedUser));
				for(int i = 0; i<employees.size(); i++) {
					if(employees.get(i).getId().equals(id)) {
						employees.get(i).setOrdersCont(ordersCont);
						employees.get(i).addOrderValue(totalSum);
					}
				}
			}
			
			line = br.readLine();
		}
		br.close();
	}
	
	//IMPORT PRODUCTS
	
	public void importProducts(String fileName, String separator) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String firstLine = br.readLine();
		while(firstLine!=null) {
			String line = br.readLine();
			String[] parts = line.split(separator);
			String name = parts[0];
			long id = Long.parseLong(parts[1]);
			boolean availability = Boolean.parseBoolean(parts[2]);
			String selectedType = parts[3];
			Type t = null;
			ArrayList<Ingredient> ingredientsProduct = new ArrayList<Ingredient>();
			//Search the Object Type with the name (selectedType)
			boolean found = false; 
			for(int i = 0; i<types.size() && !found; i++) {
				if(types.get(i).getName().equals(selectedType)) {
					t = types.get(i);
					found = true;
				}
			}
			
			int k = 4;
			while(k < parts.length) {
				long idIngredient = Long.parseLong(parts[k]);
				for(int i = 0; i<ingredients.size(); i++) {
					if(ingredients.get(i).getId() == idIngredient) {
						ingredientsProduct.add(ingredients.get(i));
					}
				}
				k++;
			}
			
			if(t == null) {
				addTypeProduct(selectedType, true);
			}
			
			Product p = new Product(name, id, ingredients, t, loggedUser);
			p.setAvailability(availability);
			products.add(p);
			line = br.readLine();
		}
		br.close();

	}
	
	//IMPORT ORDERS
	
	public void importOrders(String fileName, String separator) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(separator);
			long id = Long.parseLong(parts[0]);
			String customerName = parts[1];
			String customerSurname = parts[2];
			String address = parts[3];
			String phoneNumber = parts[4];
			String employeeName = parts[5];
			
			int indexEmployee = 0;
			int indexCustomer = 0;
			for(int i = 0; i<employees.size(); i++) {
				if(employees.get(i).getName().equals(employeeName)) {
					indexEmployee = i;
				}
			}
			for(int i = 0; i<customers.size(); i++) {
				if(customers.get(i).getPhoneNumber().equals(phoneNumber)) {
					indexCustomer = i;
				}
			}
			String status = parts[6];
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMMM d HH:mm:ss z yyyy");
			Date date = sdf.parse(parts[7]);
			String comments = parts[8];
			ArrayList<DetailProduct> productsOrder = new ArrayList<>();
			for(int i = 9; i<parts.length; i+=4) {
				for(int j = 0; j<products.size(); j++) {
					if(parts[i].equals(products.get(j).getName())) {
						PriceBySize pbs = new PriceBySize(parts[i+2], Integer.parseInt(parts[i+3]));
						DetailProduct dp = new DetailProduct(products.get(j), Integer.parseInt(parts[i+1]), pbs);
						productsOrder.add(dp);
					}
				}
			}
			Order o = new Order(id, status, productsOrder, employees.get(indexEmployee), customerName, customerSurname, address, phoneNumber, comments, customers.get(indexCustomer).getCreator(), customers.get(indexCustomer).getComments(), loggedUser);
			o.setDate(date);
			orders.add(o);
			line = br.readLine();
		}
		br.close();

	}
	//EXPORT PRODUCTS
	public void exportProduct (String fileName, String separator) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter (fileName);
		pw.println("Nombre" + separator + "ID" + separator + "Disponibilidad" + separator + "Categoria");
		
		for(int i = 0; i<products.size(); i++) {
			String ingredientString = "";
			for(int j = 0; j<products.get(i).getIngredients().size(); j++) {
				String previous = separator;
				ingredientString += previous + products.get(i).getIngredients().get(j).getName();
			}
			pw.println(products.get(i).getName() + separator + products.get(i).getId() + separator + products.get(i).isAvailability() + separator + ingredientString);
		}
		
		pw.close();
	}
	
	//EXPORT EMPLOYEES
	
	public void exportEmployees(String fileName, String separator) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);
	    int total = 0;
	    int totalOrderNumber = 0;
	    for(int i = 0; i<employees.size(); i++) {
	      Employee thisEmployee = employees.get(i);
	      int ordersTotal = 0;
	      
	      for(int j = 0; j<orders.size(); j++) {
	    	  if(orders.get(i).getEmployee().equals(thisEmployee)) {
	    		  ordersTotal += orders.get(i).getTotal();
	    		  total += ordersTotal;
	    		  totalOrderNumber++;
	    	  }
	      }
	      pw.println(thisEmployee.getName()+separator+thisEmployee.getSurname()+separator+thisEmployee.getId()+separator+thisEmployee.getOrdersCont()+separator+ordersTotal + thisEmployee.getTotalSum());
	    }
	    pw.println(total + separator + totalOrderNumber);
	    pw.close();
	}

	//EXPORT ORDERS
	public void exportOrders(String fileName, String separator, ArrayList<String> listViewId) throws FileNotFoundException {
		
	    PrintWriter pw = new PrintWriter(fileName);
	    pw.println("Pedido ID" + separator + "Nombre del Cliente" + separator + "Direcci�n del Cliente" + separator + "Tel�fono del Cliente" + separator + "Nombre del Empleado" + separator + "Estado del Pedido" + separator + "Fecha y Hora" + separator + "Observaciones del Pedido");
	   
	    for(int i = 0; i < listViewId.size(); i++) {
	    	boolean found = false;
	    	for (int c = 0; c < orders.size() && !found; c++) {
	    		Order thisOrder = orders.get(c);
	    		if(listViewId.get(i).equals(thisOrder.getId())) {
			    	String productString = "";
			    	String previous = separator;
			    	for(int j = 0; j < thisOrder.getProducts().size(); j++){
			    		productString += previous + thisOrder.getProducts().get(j).getProduct().getName() + separator + thisOrder.getProducts().get(j).getAmount() + separator + thisOrder.getProducts().get(j).getSelectedSize().getPrice();
			    	}
			    	pw.println(thisOrder.getId() + separator + thisOrder.getCustomer().getName() + separator + thisOrder.getCustomer().getAddress() + separator + thisOrder.getCustomer().getPhoneNumber()+ separator + thisOrder.getEmployee().getName() + separator + thisOrder.getStatus() + separator + thisOrder.getDate() + separator + thisOrder.getComments() + productString);
			    	found = true;
		    	}
	    	}		    	
	    }
	    pw.close();
	}
	
	public Customer binarySearchCustomer(String firstName, String lastName) {

        Comparator<Customer> lastNameAndFirstName = new Comparator<Customer>() {
            @Override
            public int compare(Customer obj1, Customer obj2) {
                String f1 = obj1.getName().toLowerCase();
                String l1 = obj1.getSurname().toLowerCase();
                String f2 = obj2.getName().toLowerCase();
                String l2 = obj2.getSurname().toLowerCase();

                if (l1.compareTo(l2) == 0) {
                    return f2.compareTo(f1);
                } else {
                    return l2.compareTo(l1);
                }
            }
        };

        Customer key = new Customer(firstName,lastName, "", "", "", null);
        int index = Collections.binarySearch(customers, key,lastNameAndFirstName);
        if (index <0){
            key = null;
        }else{
            key = customers.get(index);
        }
        return key;
    }
}
