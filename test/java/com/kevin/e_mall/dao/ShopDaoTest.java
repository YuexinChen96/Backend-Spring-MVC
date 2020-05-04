package com.kevin.e_mall.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kevin.e_mall.BaseTest;
import com.kevin.e_mall.entity.Area;
import com.kevin.e_mall.entity.PersonInfo;
import com.kevin.e_mall.entity.Shop;
import com.kevin.e_mall.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		System.out.println("列表大小： "+shopList.size());
		System.out.println("店铺总数： "	+count);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 2);
		count = shopDao.queryShopCount(shopCondition);
		System.out.println("列表大小： "+shopList.size());
		System.out.println("店铺总数： "	+count);
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId: "+shop.getArea().getAreaId());
		System.out.println("areaName: "+shop.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory t_sc = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		t_sc.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(t_sc);
		shop.setShopName("测试店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("110");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("更新");
		shop.setShopAddr("更新");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}
}
