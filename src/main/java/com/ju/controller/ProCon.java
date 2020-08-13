package com.ju.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ju.common.HigherResponse;
import com.ju.entity.Product;
import com.ju.service.ProductService;

@RestController
@RequestMapping("/manage/product")
public class ProCon {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list.do")
	public HigherResponse<Object> queryProByPage(
			@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "8") Integer pageSize
			){
		return productService.getPageBeanProduct(pageNum, pageSize);
	}
//	public Map<String,Object> queryProByPage(
//			@RequestParam(required = true, defaultValue = "1") Integer pageNum,
//			@RequestParam(required = true, defaultValue = "4") Integer pageSize
//			) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("pageNum", pageNum);
//		map.put("pageSize", pageSize);
//		return map;
//	}
	//模糊查询通过id和name 查到
	@RequestMapping("/search.do")
	public HigherResponse<List<Product>> searchQueryProByPage(
			Integer proId, 
			String proName,
			@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "4") Integer pageSize
			){
		Product product = new Product();
		product.setId(proId);
		product.setName(proName);
		HigherResponse<List<Product>> searchProByPage = 
				productService.searchProByPage(product, pageNum, pageSize);
		return searchProByPage;
		
	}
	
	@RequestMapping("/set_sale_status.do")
	public HigherResponse<Boolean> updateProStatus(Integer proId,int status){
		Product product = new Product();
		product.setId(proId);
		product.setStatus((byte)status);
		System.out.println(product);
		return productService.updateProStatus(product);
	}
	
	//上传文件
	@RequestMapping("/upload.do")
	public HigherResponse<Object> uploadPhoto(MultipartFile file) throws IllegalStateException, IOException{
		System.out.println(file.getOriginalFilename());
		file.transferTo(new File("D:\\imgs\\"+file.getOriginalFilename()));
		return null;
	}
	/*@ResponseBody
	@RequestMapping("/save.do")
	public HigherResponse<Boolean> uploadPhoto(Product product,MultipartFile file) throws IllegalStateException, IOException {
		//文件名
		//添加商品到数据库表
		//上传图片，传到指定目录
		return productService.addOneProduct(product, file);
		
	}*/
	
	@RequestMapping("/save.do")
	public HigherResponse<Boolean> uploadPhoto(Product product,MultipartFile file) throws IllegalStateException, IOException{
		//添加商品到数据库/添加图片
		HigherResponse<Boolean> addOneProduct = productService.addOneProduct(product, file);
		System.out.println(file.getOriginalFilename());
		System.out.println(product);
		return addOneProduct;
	}
	
	//@ResponseBody
	@RequestMapping("/user/list.do")
	public HigherResponse<Object> queryProByCIdAndName(
			@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "3") Integer pageSize,
			Integer cId,
			String pName) {
		Product product = new Product();
		product.setCategory_id(cId);
		product.setName(pName);
		return productService.queryProByCIdAndName(pageNum, pageSize,product);
	}
	@RequestMapping("/detail.do")
	public HigherResponse<Product> queryProByPId(Integer pId){
		return productService.queryProInfoByPId(pId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
