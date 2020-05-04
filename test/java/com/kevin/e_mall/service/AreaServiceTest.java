package com.kevin.e_mall.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kevin.e_mall.BaseTest;
import com.kevin.e_mall.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList() {
		List<Area> areaList=areaService.getAreaList();
		assertEquals("西苑",areaList.get(0).getAreaName());
	}
}
