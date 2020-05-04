package com.kevin.e_mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kevin.e_mall.entity.ShopCategory;

public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
