package com.ju.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.util.StringUtil;
import com.ju.common.HigherResponse;
import com.ju.dao.CartDao;
import com.ju.dao.ProductDao;
import com.ju.entity.CartProductVo;
import com.ju.entity.CartVO;
import com.ju.entity.User;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;

	/*@Override
	public HigherResponse<CartVO> queryCartByUserId(Integer userId) {
		if(null==userId)
			return HigherResponse.getResponseFailed("您没有登录！");
		List<CartProductVo> cartProductByUserId = cartDao.getCartProductByUserId(userId);
		System.out.println(cartProductByUserId);
		if(cartProductByUserId.size()==0)
			return HigherResponse.getResponseFailed("购物车为空"); 
		CartVO cartVO = new CartVO();
		cartVO.setCartProductVolist(cartProductByUserId);//得到所有
		for (CartProductVo cartProductVo : cartProductByUserId) {
			
		}
		boolean flag=true;
		BigDecimal bigDecimal = new BigDecimal(0.00);
		for (int i = 0; i < cartProductByUserId.size(); i++) {
			if(cartProductByUserId.get(i).getProductChecked()==1){
				bigDecimal=bigDecimal.add(cartProductByUserId.get(i).getProductTotalPrice());//选中的把总价添加
			}else{
				flag=false;
			}
		}
		cartVO.setCartTotalPrice(bigDecimal);
		cartVO.setAllChecked(flag);
		System.out.println(cartVO);
		return HigherResponse.getResponseSuccess(cartVO);
	}*/
	//第二次修改，不带参数
	@Override
	public HigherResponse<CartVO> queryCartByUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User attribute = (User)session.getAttribute("user");
		Integer userId = attribute.getId();
		if(null==attribute)
			return HigherResponse.getResponseFailed("您没有登录！");
		List<CartProductVo> cartProductByUserId = cartDao.getCartProductByUserId(userId);
		System.out.println(cartProductByUserId);
		if(cartProductByUserId.size()==0)
			return HigherResponse.getResponseFailed("购物车为空"); 
		CartVO cartVO = new CartVO();
		cartVO.setCartProductVolist(cartProductByUserId);//得到所有
		/*for (CartProductVo cartProductVo : cartProductByUserId) {
		}*/
		boolean flag=true;
		BigDecimal bigDecimal = new BigDecimal(0.00);
		for (int i = 0; i < cartProductByUserId.size(); i++) {
			if(cartProductByUserId.get(i).getProductChecked()==1){
				bigDecimal=bigDecimal.add(cartProductByUserId.get(i).getProductTotalPrice());//选中的把总价添加
			}else{
				flag=false;
			}
		}
		cartVO.setCartTotalPrice(bigDecimal);
		cartVO.setAllChecked(flag);
		System.out.println(cartVO);
		return HigherResponse.getResponseSuccess(cartVO);
	}
	@Autowired
	private ProductDao productDao;
	
	@Override
	public HigherResponse<CartVO> addOneProductToCart(Integer proId, Integer count,HttpServletRequest request) {
		//保证登录得到userid;
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		if(StringUtils.isEmpty(user))
			return HigherResponse.getResponseFailed("用户没有登录");
		Integer userId = user.getId();
		CartProductVo cartProductVo = new CartProductVo();
		cartProductVo.setUserId(userId);
		cartProductVo.setProductId(proId);
		cartProductVo.setQuantity(count);
		Boolean addOneCartProduct = cartDao.addOneCartProduct(cartProductVo);
		if(false==addOneCartProduct)
			return HigherResponse.getResponseFailed("添加失败");
		//添加成功：减去库存。1.查到当前库存-count 2.直接修改库存
		Boolean updateStockFromProduct = productDao.updateStockFromProduct(proId, count);
		if(false == updateStockFromProduct) {
			return HigherResponse.getResponseFailed("修改库存失败，请重试！！！");
		}
		//重新查询
		//return queryCartByUserId(userId);
		return queryCartByUserId(request);
	}

	@Override
	public HigherResponse<CartVO> removeOneProduct(Integer proId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(StringUtils.isEmpty(user))
			return HigherResponse.getResponseFailed("用户没有登录");
		if(null==proId)
			return HigherResponse.getResponseFailed("服务器异常");
		Boolean removeOneProduct = cartDao.removeOneProduct(user.getId(), proId);
		if(false==removeOneProduct){
			return HigherResponse.getResponseFailed("删除失败");
		}
		return queryCartByUserId(request);
	}

	@Override
	public HigherResponse<CartVO> updateOneProductFromCart(Integer proId, Integer count, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(StringUtils.isEmpty(user))
			return HigherResponse.getResponseFailed("用户没有登录");
		if(null==proId||null==count)
			return HigherResponse.getResponseFailed("服务器异常");
		Boolean updateProFromCart = cartDao.updateProFromCart(user.getId(), proId, count);
		if(false==updateProFromCart)
			return HigherResponse.getResponseFailed("修改数量失败");
		return queryCartByUserId(request);
	}

	
	
	
	
	
	
	
	
	
	
	
}
