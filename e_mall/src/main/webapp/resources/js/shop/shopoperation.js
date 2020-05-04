/**
 * 
 */
$(function(){
	var shopId = getQueryString('shopId');
	//check url-shopId is null
	var isEdit = shopId?true:false;
	//urls
	var initUrl = '/e_mall/shopadmin/getshopinitinfo';
	var registerShopUrl = "/e_mall/shopadmin/registershop";
	var shopInfoUrl = "/e_mall/shopadmin/getshopbyid?shopId=" + shopId;
	var editShopUrl = "/e_mall/shopadmin/modifyshop";
	
	if(!isEdit){
		getShopInitInfo();
	}else{
		getShopInfo(shopId);
	}
	
	//pass shopId to get shop info
	function getShopInfo(shopId){
		$.getJSON(shopInfoUrl,function(data){
			if(data.success){
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'+shop.shopCategory.shopCategoryId+'" selected>'
					+shop.shopCategory.shopCategoryName+'</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item,index){
					tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disabled','disable');
				$('#area').html(tempAreaHtml);
				$('#area option[data-id="'+shop.area.areaId+'"]').attr('selected','selected');
			}
		});
	}
	
	
	//补充店铺信息
	function getShopInitInfo(){
		$.getJSON(initUrl, function(data){
			if(data.success){
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item,index){
					tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName +
					'</option>';
				});
				data.areaList.map(function(item,index){
					tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
		
	//提交表单信息
	$('#submit').click(function(){
		var shop = {};
		if(isEdit){
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
				shopCategoryId:$('#shop-category').find('option').not(function(){
					return !this.selected;
				}).data('id')
		};
		shop.area = {
				areaId:$('#area').find('option').not(function(){
					return !this.selected;
				}).data('id')
		};
		var shopImg = $('#shop-img')[0].files[0];
		var formData = new FormData();
		formData.append('shopImg',shopImg);
		formData.append('shopStr',JSON.stringify(shop));
		
		var verifyCodeActual = $('#j_captcha').val();
		if(!verifyCodeActual){
			$.toast('请输入验证码！');
			return;
		}
		formData.append('verifyCodeActual',verifyCodeActual);
		
		console.log(formData.getAll('shopStr'));
		
		$.ajax({
			url: (isEdit ? editShopUrl : registerShopUrl),
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					$.toast('提交成功');
				}else{
					$.toast('提交失败'+data.errMsg);
				}
				$('#captcha_img').click();
			}
		});
	});
})