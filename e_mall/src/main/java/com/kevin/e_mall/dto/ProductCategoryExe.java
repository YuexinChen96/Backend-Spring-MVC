package com.kevin.e_mall.dto;

import java.util.List;

import com.kevin.e_mall.entity.ProductCategory;
import com.kevin.e_mall.enums.ProductCategoryStateEnum;

public class ProductCategoryExe {
	//result state
	private int state;
	//state info
	private String stateInfo;
	
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExe() {
		
	}	
	//fail constructor
	public ProductCategoryExe(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	public ProductCategoryExe(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}
	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	
	
}
