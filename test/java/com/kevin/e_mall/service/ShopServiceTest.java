package com.kevin.e_mall.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kevin.e_mall.BaseTest;
import com.kevin.e_mall.dto.ShopExecution;
import com.kevin.e_mall.entity.Area;
import com.kevin.e_mall.entity.PersonInfo;
import com.kevin.e_mall.entity.Shop;
import com.kevin.e_mall.entity.ShopCategory;
import com.kevin.e_mall.enums.ShopStateEnum;
import com.kevin.e_mall.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 3, 2);
		System.out.println("店铺列表数为： "+se.getShopList().size());
		System.out.println("店铺总数为： "+se.getCount());
	}
	
	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException, FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的店铺名称");
		File shopImg = new File("/Users/kevin/Desktop/you.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution shopEx = shopService.modifyShop(shop, is, "you.jpg");
		System.out.println("新图片地址： "+shopEx.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
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
		shop.setShopName("测试店铺3");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("110");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("/Users/kevin/Desktop/sky-new.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
}
