package com.kevin.e_mall.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kevin.e_mall.BaseTest;
import com.kevin.e_mall.entity.ProductCategory;


public class ProductCategoryTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testQueryByShopId() throws Exception{
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("产品共有： "+productCategoryList.size());
		System.out.println(productCategoryList.get(1).getProductCategoryName());
		System.out.println(productCategoryList.get(1).getPriority());
		System.out.println(productCategoryList.get(1).getCreateTime());
	}
	
	@Test
	@Ignore
	public void testAddProductCategories() {
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryName("Cola");
		pc.setPriority(5);
		pc.setCreateTime(new Date());
		pc.setShopId(1L);
		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryName("Soda");
		pc2.setPriority(6);
		pc2.setCreateTime(new Date());
		pc2.setShopId(2L);
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();
		pcList.add(pc);
		pcList.add(pc2);
		int effectNum = productCategoryDao.addProductCategories(pcList);
		assertEquals(2, effectNum);
	}
	
	@Test
	public void testDeleteProductCategory() throws Exception{
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc : productCategoryList) {
			if("Soda".equals(pc.getProductCategoryName()) || "Cola".equals(pc.getProductCategoryName())) {
				int effectNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1, effectNum);
			}
		}
	}
}
