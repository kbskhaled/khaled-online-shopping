package net.kbs.khaled_online_shopping_backend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kbs.khaled_online_shopping_backend.dao.CategoryDAO;
import net.kbs.khaled_online_shopping_backend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CateoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static List<Category> categories = new ArrayList<>();

	/*
	 * static {
	 * 
	 * Category category=new Category(); category.setId(1);
	 * category.setName("Television");
	 * category.setDescription("description for Television");
	 * category.setImageUrl("Cat_1.png"); category.setActive(true);// on peut le
	 * supprimer car il est par defaut à true
	 * 
	 * categories.add(category);
	 * 
	 * category=new Category(); category.setId(2); category.setName("Mobile");
	 * category.setDescription("description for Mobile");
	 * category.setImageUrl("Cat_2.png"); category.setActive(true);// on peut le
	 * supprimer car il est par defaut à true
	 * 
	 * categories.add(category);
	 * 
	 * 
	 * category=new Category(); category.setId(3); category.setName("Laptop");
	 * category.setDescription("description for Laptop");
	 * category.setImageUrl("Cat_3.png"); category.setActive(true);// on peut le
	 * supprimer car il est par defaut à true
	 * 
	 * categories.add(category); }
	 */

	@Override
	public List<Category> list() {
		String selectActiveCategory = "From Category WHERE active= :active";// hsql +nom de la classe pas nom de la
																			// table
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	// getting single category based on id

	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));

		/*
		 * for(Category category:categories) { if(category.getId()==id) return category;
		 * }
		 * 
		 * return null;
		 */
	}

	@Override

	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);

			return true;
		} catch (Exception ex) {
			ex.getStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception ex) {
			ex.getStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {

		category.setActive(false);
		try {

			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception ex) {
			ex.getStackTrace();
			return false;
		}
	}

}
