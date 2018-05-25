package net.kbs.khaled_online_shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.kbs.khaled_online_shopping_backend.dao.CategoryDAO;
import net.kbs.khaled_online_shopping_backend.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value = {"/","/index","/home"})
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Home");
		
		// passer la liste des categories au vue
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;				
	}	
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;				
	}
	
	
	/**
	 * *
	 * *   show products based on category
	 *
	 */
	
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","ALL Products");
		
		// passer la liste des categories au vue
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id")int id)
	{
		ModelAndView mv=new ModelAndView("page");
		// fetch single category
		Category category=null;
		category=categoryDAO.get(id);		
		
		mv.addObject("title",category.getName());
		
		// passer la liste des categories au vue
		mv.addObject("categories", categoryDAO.list());
		
		// passer l'objet category au vue
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	
	
	
	
	
//	@RequestMapping(value = "/test")
//	public ModelAndView test(@RequestParam(value="greeting",required=false)String greeting)
//	{
//		if(greeting==null)
//		{
//			greeting="hello default";
//		}
//		ModelAndView mv=new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		return mv;
//	}
	
//	@RequestMapping(value = "/test/{greeting}")
//	public ModelAndView test(@PathVariable("greeting")String greeting)
//	{
//		if(greeting==null)
//		{
//			greeting="hello default";
//		}
//		ModelAndView mv=new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		return mv;
//	}


}
