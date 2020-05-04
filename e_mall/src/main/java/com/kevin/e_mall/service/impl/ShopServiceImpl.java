package com.kevin.e_mall.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.e_mall.dao.ShopDao;
import com.kevin.e_mall.dto.ShopExecution;
import com.kevin.e_mall.entity.Shop;
import com.kevin.e_mall.enums.ShopStateEnum;
import com.kevin.e_mall.exceptions.ShopOperationException;
import com.kevin.e_mall.service.ShopService;
import com.kevin.e_mall.util.ImageUtil;
import com.kevin.e_mall.util.PageCal;
import com.kevin.e_mall.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCal.rowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if(shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (shopImgInputStream != null) {
					try {
						addShopImg(shop, shopImgInputStream, fileName);
					} catch (Exception e) {
						throw new ShopOperationException("Add shop img fail: " + e.getMessage());
					}
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("Update Img address fails");
					}
				}
			}

		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, dest, fileName);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException{
		 if(shop == null || shop.getShopId() == null) {
			 return new ShopExecution(ShopStateEnum.NULL_SHOP);
		 }else {
			 try {
				 if(shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
					 Shop t_shop = shopDao.queryByShopId(shop.getShopId());
					 if(t_shop.getShopImg() != null) {
						 ImageUtil.deleteFileOrPath(t_shop.getShopImg());
					 }
					 addShopImg(shop, shopImgInputStream, fileName);
				 }
				 shop.setLastEditTime(new Date());
				 int e_num = shopDao.updateShop(shop);
				 if(e_num <= 0) {
					 return new ShopExecution(ShopStateEnum.INNER_ERROR);
				 }else {
					 shop = shopDao.queryByShopId(shop.getShopId());
					 return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				 }
			 }catch(Exception e) {
				 throw new ShopOperationException("modifyShop error:" + e.getMessage());
			 }
		 }
	}
}
