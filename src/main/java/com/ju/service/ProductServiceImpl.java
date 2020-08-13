package com.ju.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ju.common.HigherResponse;
import com.ju.dao.ProductDao;
import com.ju.entity.Product;
import com.ju.utils.UUIDUtils;
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;

	@Override
	public HigherResponse<Object> getPageBeanProduct(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> pageQueryPro = productDao.pageQueryPro();
		if(null==pageQueryPro){
			return HigherResponse.getResponseFailed("没有查到任何数据！");
		}
		PageInfo<Product> pageInfo = new PageInfo<>(pageQueryPro);
		return HigherResponse.getResponseSuccess(pageInfo);
	}
	//模糊查询
	@Override
	public HigherResponse<List<Product>> searchProByPage(Product product, Integer pageNum, Integer pageSize) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<Product> searchProByPage = productDao.searchProByPage(product);
		if(null==searchProByPage){
			return HigherResponse.getResponseFailed("没有任何数据，换个词试试");
		}
		PageInfo<Product> pageInfo = new PageInfo<>(searchProByPage);
		return HigherResponse.getResponseSuccess(pageInfo);
	}
	//修改
	@Override
	public HigherResponse<Boolean> updateProStatus(Product product) {
		if(null==product.getId()){
			return HigherResponse.getResponseFailed("服务异常");
		}
		if(null==product.getStatus()){
			return HigherResponse.getResponseFailed("服务异常");
		}
		Boolean updateProStatus = productDao.updateProStatus(product);
		if(false==updateProStatus){
			return HigherResponse.getResponseFailed("修改失败");
		}
		return HigherResponse.getResponseSuccess(updateProStatus);
	}
	//添加图片等文件
	@Override
	public HigherResponse<Boolean> addOneProduct(Product product,MultipartFile file)throws IllegalStateException, IOException {
		if (null == product) {
			return HigherResponse.getResponseFailed("服务器异常！！！");
		}
		if(null==file){
			return HigherResponse.getResponseFailed("文件为空！！！");
		}
		//生成唯一的目录名/图片名
		String imgName = UUIDUtils.getUUID();
		String originalFilename = file.getOriginalFilename();
		int lastIndexOf = originalFilename.lastIndexOf(".");
		String suffixName = originalFilename.substring(lastIndexOf);
		//后缀名判断
		if(!".jpg".equalsIgnoreCase(suffixName)&&!".png".equalsIgnoreCase(suffixName)){
			return HigherResponse.getResponseFailed("文件格式不对");
		}
		imgName+=suffixName;//图片名
		product.setMain_image(imgName);
		System.out.println(imgName);
		//添加到数据库
		Boolean addOneProduct = productDao.addOneProduct(product);
		if (false == addOneProduct) {
			return HigherResponse.getResponseFailed("添加失败，请重新尝试！！！");
		}
		
		//上传图片到指定目录
		file.transferTo(new File("d:\\imgs\\"+imgName));
		
		return HigherResponse.getResponseSuccess(addOneProduct);
	}
	@Override
	public HigherResponse<Object> queryProByCIdAndName(Integer pageNum, Integer pageSize, Product product) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> queryProByCIdAndKeyWord = productDao.queryProByCIdAndKeyWord(product);
		if(null==queryProByCIdAndKeyWord){
			return HigherResponse.getResponseFailed("无此商品");
		}
		PageInfo<Product> pageInfo = new PageInfo<>(queryProByCIdAndKeyWord);
		
		return HigherResponse.getResponseSuccess(pageInfo);
	}
	@Override
	public HigherResponse<Product> queryProInfoByPId(Integer pId) {
		if(null==pId){
			return HigherResponse.getResponseFailed("异常");
		}
		Product queryProInfoByPId = productDao.queryProInfoByPId(pId);
		if(null==queryProInfoByPId){
			return HigherResponse.getResponseFailed("异常");
		}
		return HigherResponse.getResponseSuccess(queryProInfoByPId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
