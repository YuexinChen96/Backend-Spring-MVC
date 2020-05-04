 package com.kevin.e_mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kevin.e_mall.entity.Shop;

public interface ShopDao {
	
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	//list shop
	Shop queryByShopId(long shopId);
	
	/* new shop */
	int insertShop(Shop shop);

	/* update shop */
	int updateShop(Shop shop);
}
