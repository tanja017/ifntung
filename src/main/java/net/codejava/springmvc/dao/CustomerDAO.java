package net.codejava.springmvc.dao;

import java.util.List;
import java.util.Map;

import net.codejava.springmvc.model.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public void delete(int ID);
	public void update(Customer user);
	public Customer findByCustomerId(int custId);
	public List<Customer> findCustomers(int limit, int offset);
	public int countCustomers();
	public Map<String,String> findCurrentCustomerById(int ID);
}
