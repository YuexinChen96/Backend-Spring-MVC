package com.kevin.e_mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kevin.e_mall.entity.ProductCategory;

public interface ProductCategoryDao {
	
	List<ProductCategory> queryProductCategoryList(long shopId);
	//int -> numbers of product categories added
	int addProductCategories(List<ProductCategory> productCategoryList);
	
	int deleteProductCategory(@Param("productCategoryId") long productCategoryList, @Param("shopId") long shopId);
}
