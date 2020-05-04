package com.kevin.e_mall.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin", method= {RequestMethod.GET})
public class ShopAdminController {
	//转到shopOperation.html
	//因为spring里面设置过prefix,suffix前后缀，所以直接转到WEB-INF/shop下
	@RequestMapping(value="/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}
	
	@RequestMapping(value="/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value="/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value="/productcategorymanagement", method= {RequestMethod.GET})
	private String productCategoryManage() {
		return "shop/productcategorymanagement";
	}
}
