package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryDao {

	void update(Category category);

	void insert(Category category);

	List<Category> findAll();

	Category findByCategoryname(String name) throws Exception;

	Category findById(int cateid) throws Exception;

	void delete(int cateid) throws Exception;

	List<Category> searchByName(String catname);

	int count();

	List<Category> findAll(int page, int pagesize);


}
