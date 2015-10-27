package net.codejava.springmvc.dao;

import java.util.List;
import java.util.Map;

import net.codejava.springmvc.model.Wares;

public interface WaresDAO {

	//public Wares findByWaresId(int wareId);
	public List<Wares> findWares(int limit, int offset);
	public Wares findByWareId(int wareId);
	public void update(Wares ware);
	public Map<String,String> findCWareById(int wareId);
	public void delete(int id);

}
