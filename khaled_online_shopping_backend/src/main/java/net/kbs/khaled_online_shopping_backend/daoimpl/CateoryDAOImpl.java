package net.kbs.khaled_online_shopping_backend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.kbs.khaled_online_shopping_backend.dao.CategoryDAO;
import net.kbs.khaled_online_shopping_backend.dto.Category;

@Repository("categoryDAO")
public class CateoryDAOImpl implements CategoryDAO {

	private static List<Category> categories= new ArrayList<>();
	
	static {
		
		Category category=new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("description for Television");
		category.setImageUrl("Cat_1.png");
		category.setActive(true);// on peut le supprimer car il est par defaut à true
		
		categories.add(category);
		
	    category=new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("description for Mobile");
		category.setImageUrl("Cat_2.png");
		category.setActive(true);// on peut le supprimer car il est par defaut à true
		
		categories.add(category);
		
		
		category=new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("description for Laptop");
		category.setImageUrl("Cat_3.png");
		category.setActive(true);// on peut le supprimer car il est par defaut à true
		
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
	
		 return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category:categories)
		{
			if(category.getId()==id) return category;
		}
		
		return null;
	}


}
