package net.codejava.springmvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.codejava.springmvc.model.Warehouses;

public interface WarehousesDAO {

	//public Wares findByWaresId(int wareId);
	public List<Warehouses> findWarehouses(int limit, int offset);
//	public Warehouses findByWarehousesId(int whouseId);
	public void update(String updateSQL);
	public void insert(String insertSQL);
	public Map<String,String> findCWarehousesById(int whouseId);
	public void delete(String sql);
	public Map<Integer, HashMap<String,String>> SQLquery(String sqlQuery);

}
