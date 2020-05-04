package com.kevin.e_mall.service;

import java.util.List;

import com.kevin.e_mall.dto.ProductCategoryExe;
import com.kevin.e_mall.entity.ProductCategory;
import com.kevin.e_mall.exceptions.pcOperationException;

public interface ProductCategoryService {
	List<ProductCategory> getProductCategoryList(long shopId);
	
	ProductCategoryExe addProductCategory(List<ProductCategory> pcList) throws pcOperationException;
	
	ProductCategoryExe deleteProductCategory(long productCategoryId, long shopId) throws pcOperationException;
}
