package com.kevin.e_mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.e_mall.dao.ProductCategoryDao;
import com.kevin.e_mall.dto.ProductCategoryExe;
import com.kevin.e_mall.entity.ProductCategory;
import com.kevin.e_mall.enums.ProductCategoryStateEnum;
import com.kevin.e_mall.exceptions.pcOperationException;
import com.kevin.e_mall.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId){
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	
	@Override
	@Transactional
	public ProductCategoryExe addProductCategory(List<ProductCategory> pcList) throws pcOperationException{
		if(pcList != null && pcList.size() > 0) {
			try {
				int effectNum = productCategoryDao.addProductCategories(pcList);
				if(effectNum <= 0) {
					throw new pcOperationException("Create product category fails.");
				}else {
					return new ProductCategoryExe(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e){
				throw new pcOperationException("Add product category error: " + e.getMessage());
			}
		}else {
			return new ProductCategoryExe(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExe deleteProductCategory(long productCategoryId, long shopId) throws pcOperationException {
		try {
			int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectNum <= 0) {
				throw new pcOperationException("Delete product category fails.");
			}else {
				return new ProductCategoryExe(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e) {
			throw new pcOperationException("Delete product category error: " + e.getMessage());
		}
	}
}
