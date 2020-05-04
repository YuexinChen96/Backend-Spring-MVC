package com.kevin.e_mall.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kevin.e_mall.dto.ProductCategoryExe;
import com.kevin.e_mall.dto.Result;
import com.kevin.e_mall.entity.ProductCategory;
import com.kevin.e_mall.entity.Shop;
import com.kevin.e_mall.enums.ProductCategoryStateEnum;
import com.kevin.e_mall.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/addproductcategories", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> pcList, HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for(ProductCategory pc:pcList) {
			pc.setShopId(currentShop.getShopId());
		}
		if(pcList != null && pcList.size() > 0) {
			try {
				ProductCategoryExe pe = productCategoryService.addProductCategory(pcList);
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getState());
				}
			}catch(RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "At least enter one category");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(productCategoryId != null && productCategoryId > 0) {
			try {
				Shop curr_shop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExe pe = productCategoryService.deleteProductCategory(productCategoryId, curr_shop.getShopId());
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			}catch(RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "Enter at least one category.");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if(currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		}else {
//			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
//			return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
			return null;
		}
		
	}
	
}
