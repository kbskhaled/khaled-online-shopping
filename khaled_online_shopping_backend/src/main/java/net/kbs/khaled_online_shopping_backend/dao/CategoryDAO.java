package net.kbs.khaled_online_shopping_backend.dao;

import java.util.List;

import net.kbs.khaled_online_shopping_backend.dto.Category;

public interface CategoryDAO {
	
   List<Category> list();
   Category get(int id);

}
