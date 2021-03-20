package model;

import java.util.ArrayList;

public class Restaurant {
	private ArrayList<Employee> employees;
	private ArrayList<Product> products;
	private ArrayList<Customer> customers;
	
	public Restaurant() {
		employees = new ArrayList<Employee>();
		products = new ArrayList<Product>();
		customers = new ArrayList<Customer>();
	}
}
