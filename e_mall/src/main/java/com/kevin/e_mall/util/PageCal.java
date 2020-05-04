package com.kevin.e_mall.util;

public class PageCal {
	public static int rowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1)*pageSize : 0;
	}
}
