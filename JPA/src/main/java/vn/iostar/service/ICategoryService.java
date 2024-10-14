package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryService {
	
	 void insert(Category category);

	 int count();


	 List<Category> findAll(int page, int pagesize);


	 List<Category> searchByName(String catname);


	 List<Category> findAll();


	 Category findById(int cateid) throws Exception;


	 void delete(int cateid) throws Exception;


	 void update(Category category) throws Exception;


	 Category findByCategoryname(String name);


}
