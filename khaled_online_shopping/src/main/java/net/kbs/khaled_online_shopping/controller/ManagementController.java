package net.kbs.khaled_online_shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kbs.khaled_online_shopping.util.FileUploadUtility;
import net.kbs.khaled_online_shopping.validator.ProductValidator;
import net.kbs.khaled_online_shopping_backend.dao.CategoryDAO;
import net.kbs.khaled_online_shopping_backend.dao.ProductDAO;
import net.kbs.khaled_online_shopping_backend.dto.Category;
import net.kbs.khaled_online_shopping_backend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	//private static final Logger logger = LoggerFactory.getLogger(ManagementController.class); slf4j

	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation)
	{
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title","Manage Product");
		
        Product nProduct = new Product();
		
		// assuming that the user is ADMIN
		// later we will fixed it based on user is SUPPLIER or ADMIN
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if(operation!=null)
		{
			if(operation.equals("product"))
			{
				mv.addObject("message", "Product submitted Successfully");
			}
			else if (operation.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}
		return mv;
	}
	
	//handling product submission button
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request)
	{
		
		// file validator
		//new ProductValidator().validate(mProduct, results);
		// mandatory file upload check
				if(mProduct.getId() == 0) {
					new ProductValidator().validate(mProduct, results);
				}
				else {
					// edit check only when the file has been selected
					if(!mProduct.getFile().getOriginalFilename().equals("")) {
						new ProductValidator().validate(mProduct, results);
					}			
				}
		
		
		
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Validation fails for adding the product!");
			model.addAttribute("title","ManageProduct");
			model.addAttribute("userClickManageProduct",true);
			return "page";
		}			

		//logger.info(mProduct.toString());
		//add a new record or update
		if(mProduct.getId() == 0 ) {
			productDAO.add(mProduct);
		}
		else {
			productDAO.update(mProduct);
		}
		
		 //upload the file
		 if(!mProduct.getFile().getOriginalFilename().equals("") ){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode()); 
		 }
		
		return "redirect:/manage/product?operation=product";
	}
	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDAO.update(product);		
		return (isActive)? "Product Dectivated Successfully!": "Product Activated Successfully";
	}
	
	@RequestMapping("/{id}/product")
	public ModelAndView manageProductEdit(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		// Product nProduct = new Product();		
		mv.addObject("product", productDAO.get(id));

			
		return mv;
		
	}
	
	
	@ModelAttribute("categories") 
	public List<Category> modelCategories() {
		return categoryDAO.list();
	}
	
	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {					
		categoryDAO.add(mCategory);		
		//return "redirect:" + request.getHeader("Referer") + "?success=category";
		return "redirect:/manage/product?operation=category";
	}
			
	
	
	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}
	

}
