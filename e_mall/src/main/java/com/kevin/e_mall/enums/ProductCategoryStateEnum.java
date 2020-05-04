package com.kevin.e_mall.enums;

public enum ProductCategoryStateEnum {
	SUCCESS(1, "Create Success"), INNER_ERROR(-1001,"Operation Fail"), EMPTY_LIST(-1002,"Less than 1 added");

	private int state;
	
	private String stateInfo;
	
	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public int getState() {
		return state;
	}
	
	public String getStateInfo() {
		return stateInfo;
	}
	
	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state:values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}	
}
