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

import net.codejava.springmvc.dao.WaresDAO;
import net.codejava.springmvc.model.Wares;


public class JdbcWaresDAO implements WaresDAO
{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/*public void insert(Customer customer){
		
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
	}*/
	
public void update(Wares ware){
		//UPDATE MyGuests SET lastname='Doe' WHERE id=2
		/*String sql = "INSERT INTO CUSTOMER " +
				"(NAME, AGE) VALUES (?, ?)";*/
		
		String sql = "UPDATE WARE SET " +
				"NAME = ? WHERE ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ware.getName());
			ps.setInt(2, ware.getWareId());
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
	
public void delete(int id){
	
		String sql = "DELETE FROM WARE WHERE ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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

public Wares findByWareId(int wareId){
		
		String sql = "SELECT * FROM WARE WHERE ID = ?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, wareId);
			Wares ware = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ware = new Wares(
						rs.getInt("ID"),
						rs.getString("NAME") 
				);
			}
			rs.close();
			ps.close();
			return ware;
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


public Map<String,String> findCWareById(int wareId){
	
	String sql = "SELECT * FROM WARE WHERE ID = ?";
	
	Connection conn = null;
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, wareId);
		
		Map<String,String> ware = new HashMap<String,String>();
		Wares wares = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ware.put("id",rs.getString("id"));
			ware.put("name",rs.getString("name"));
		}
		rs.close();
		ps.close();
		return ware;
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
/*
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
*/
	
public List<Wares> findWares(int limit, int offset){
	
	//String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	String sql = "SELECT * FROM WARE LIMIT ? OFFSET ?";
	Connection conn = null;
	List<Wares> goods = new ArrayList<Wares>();
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, limit);
		ps.setInt(2, offset);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) { 
			Wares g = new Wares();
	        g.setName(rs.getString("name") );
	        g.setWareId(rs.getInt("id"));
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