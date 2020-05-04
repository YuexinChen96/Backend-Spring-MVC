package com.kevin.e_mall.service;

import java.util.List;

import com.kevin.e_mall.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
