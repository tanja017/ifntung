package net.codejava.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import com.mysql.jdbc.ResultSetMetaData;

//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;

import net.codejava.springmvc.dao.WarehousesDAO;
import net.codejava.springmvc.model.Warehouses;


public class JdbcWarehousesDAO implements WarehousesDAO
{
	//private JdbcTemplate jdbcTemplate;
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
	
/*public void update(Warehouses whouses){
		//UPDATE MyGuests SET lastname='Doe' WHERE id=2
		/*String sql = "INSERT INTO CUSTOMER " +
				"(NAME, AGE) VALUES (?, ?)";
		
		String sql = "UPDATE WAREHOUSE SET " +
				"NAME = ? WHERE ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, whouses.getName());
			ps.setInt(2, whouses.getWhouseId());
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
	
public void delete(String sql){
	
		//String sql = "DELETE FROM WAREHOUSE WHERE ID = ? ";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
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

/*
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
*/

public Map<String,String> findCWarehousesById(int whouseId){
	
	String sql = "SELECT * FROM WAREHOUSE WHERE ID = ?";
	
	Connection conn = null;
	
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, whouseId);
		
		Map<String,String> whouse = new HashMap<String,String>();
		//Warehouses whouses = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			whouse.put("id",rs.getString("id"));
			whouse.put("name",rs.getString("name"));
		}
		rs.close();
		ps.close();
		return whouse;
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
	
public List<Warehouses> findWarehouses(int limit, int offset){
	
	//String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	String sql = "SELECT * FROM warehouse LIMIT ? OFFSET ?";
	Connection conn = null;
	List<Warehouses> goods = new ArrayList<Warehouses>();
	try {
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, limit);
		ps.setInt(2, offset);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) { 
			Warehouses g = new Warehouses();
	        g.setName(rs.getString("name") );
	        g.setWhouseId(rs.getInt("id"));
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

public Map<Integer, HashMap<String,String>> SQLquery(String sqlQuery){
	
	Map<Integer,HashMap<String,String>> ResultMap = new HashMap<Integer, HashMap<String,String>>();
	
	Connection conn = null;
	
	PreparedStatement ps;
	try {
		
		Vector<String> columnNames = new Vector<String>();
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(sqlQuery);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData columns = (ResultSetMetaData) rs.getMetaData();
        int i = 0;
        
        while (i < columns.getColumnCount()) {
        	
          i++;
          columnNames.add(columns.getColumnName(i));
          
        }
        
        Integer j = 0;
        while (rs.next()) {
        	HashMap<String,String> Entry = new HashMap<String,String>();
	        for (i = 0; i < columnNames.size(); i++) {
	        	
	            Entry.put(columns.getColumnName(i+1), rs.getString(columnNames.get(i)));
	            
	        }
          
	        ResultMap.put(j, Entry);
	        //Map<String, String> map = ResultMap.get(j);
	        System.out.print("\n");
	        j++;
        }
        
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	}
	
	return ResultMap;
	
}

public void update(String updateSQL){
	  
	  Connection conn = null;
	  
	  try {
	   conn = dataSource.getConnection();
	   PreparedStatement ps = conn.prepareStatement(updateSQL);
	   ps.executeUpdate();
	   System.out.println(ps.executeUpdate());

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


public void insert(String insertSQL){
	  
	  Connection conn = null;
	  
	  try {
	   conn = dataSource.getConnection();
	   PreparedStatement ps = conn.prepareStatement(insertSQL);
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
}