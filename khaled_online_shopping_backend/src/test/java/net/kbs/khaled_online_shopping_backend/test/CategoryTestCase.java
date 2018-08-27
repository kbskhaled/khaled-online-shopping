package net.kbs.khaled_online_shopping_backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kbs.khaled_online_shopping_backend.dao.CategoryDAO;
import net.kbs.khaled_online_shopping_backend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static CategoryDAO categoryDAO;
	
	
	private Category category;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kbs.khaled_online_shopping_backend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	
	/*@Test
	public void testAddCategory() {
		
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageUrl("CAT_105.png");
		
		assertEquals("Error in addition a category inside the table!",true,categoryDAO.add(category));
		
		
	}*/
	
	
	/*@Test
	public void testGetCategory() {
		
		category = categoryDAO.get(1);
		
		
		assertEquals("Error in fetching a single category from the table!","Laptop",category.getName());
		
		
	}*/
	
	
//	@Test
//	public void testUpdateCategory() {
//		
//		category = categoryDAO.get(1);
//		
//		category.setName("TV");
//		
//		assertEquals("Error on update a single category in the table!",true,categoryDAO.update(category));
//		
//		
//	}
	

	/*@Test
	public void testDeleteCategory() {
		
		category = categoryDAO.get(1);		
		assertEquals("Error in deleting a single category in the table!",true,categoryDAO.delete(category));
		
		
	}
	*/

	/*@Test
	public void testListCategory() {
					
		assertEquals("Error in fetching the list of categories from the table!",1,categoryDAO.list().size());
		
		
	}
	*/



	@Test
	public void testCRUDCategory() {
		
		// add operation
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageUrl("CAT_1.png");
		
		assertEquals("Error in addition a category inside the table!",true,categoryDAO.add(category));
		
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is some description for television!");
		category.setImageUrl("CAT_2.png");
		
		assertEquals("Error in addition a category inside the table!",true,categoryDAO.add(category));

		
		// fetching and updating the category
		category = categoryDAO.get(2);
		
		category.setName("TV");
		
		assertEquals("Error in updating a single category in the table!",true,categoryDAO.update(category));
		
		
		// delete the category
		assertEquals("Error in deleting a single category in the table!",true,categoryDAO.delete(category));
		
		
		//fetching the list
		assertEquals("Error in fetching the list of categories from the table!",1,categoryDAO.list().size());		
				
		
	}
	
	
}