package net.codejava.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import net.codejava.springmvc.dao.CustomerDAO;
import net.codejava.springmvc.model.Customer;

public class JdbcCustomerDAO implements CustomerDAO
{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Customer customer){
		
		String sql = "INSERT INTO CUSTOMER " +
				"(NAME, AGE) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setInt(2, customer.getAge());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public void update(Customer user){
		//UPDATE MyGuests SET lastname='Doe' WHERE id=2
		/*String sql = "INSERT INTO CUSTOMER " +
				"(NAME, AGE) VALUES (?, ?)";*/
		
		String sql = "UPDATE CUSTOMER SET " +
				"NAME = ?, Age = ? WHERE CUST_ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setInt(3, user.getCustId());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
public void delete(int ID){
	
		String sql = "DELETE FROM CUSTOMER WHERE CUST_ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
public Customer findByCustomerId(int custId){
		
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
						rs.getInt("CUST_ID"),
						rs.getString("NAME"), 
						rs.getInt("Age")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

public Map<String,String> findCurrentCustomerById(int custId){
	
	String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	
	Connection conn = null;
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, custId);
		
		Map<String,String> user = new HashMap<String,String>();
		Customer customer = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user.put("ID",rs.getString("CUST_ID"));
			user.put("Name",rs.getString("NAME"));
			user.put("Age",rs.getString("Age"));
		}
		rs.close();
		ps.close();
		return user;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} finally {
		if (conn != null) {
			try {
			conn.close();
			} catch (SQLException e) {}
		}
	}
}

public int countCustomers(){
	
	String sql = "SELECT COUNT(*) AS total FROM customer";
	Connection conn = null;
	List<Customer> goods = new ArrayList<Customer>();
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("total");
		}
		rs.close();
		ps.close();
		return count;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} finally {
		if (conn != null) {
			try {
			conn.close();
			} catch (SQLException e) {}
		}
	}
}

public List<Customer> findCustomers(int limit, int offset){
	
	//String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	String sql = "SELECT * FROM CUSTOMER LIMIT ? OFFSET ?";
	Connection conn = null;
	List<Customer> goods = new ArrayList<Customer>();
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, limit);
		ps.setInt(2, offset);
		Customer customer = null;
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) { 
			customer = new Customer(
					rs.getInt("CUST_ID"),
					rs.getString("NAME"), 
					rs.getInt("Age")
			);
			Customer g = new Customer();
	        g.setName(customer.getName());
	        g.setCustId(customer.getCustId());
	        g.setAge(customer.getAge());
	        goods.add(g);
		}
		rs.close();
		ps.close();
		return goods;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} finally {
		if (conn != null) {
			try {
			conn.close();
			} catch (SQLException e) {}
		}
	}
}


}