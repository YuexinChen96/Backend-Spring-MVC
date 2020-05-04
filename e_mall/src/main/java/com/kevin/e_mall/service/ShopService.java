package com.kevin.e_mall.service;

import java.io.InputStream;

import com.kevin.e_mall.dto.ShopExecution;
import com.kevin.e_mall.entity.Shop;
import com.kevin.e_mall.exceptions.ShopOperationException;

public interface ShopService {
	//Base shop condition use page return shop list
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	Shop getByShopId(long shopId);
	
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
	
	//改shopImg格式为inputStream,增加fileName属性
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	
}
