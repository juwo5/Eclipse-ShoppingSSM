package com.ju.service;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ju.common.JSONData;
import com.ju.dao.OrderDao;
import com.ju.dao.OrderItemDao;
import com.ju.entity.Order;
import com.ju.entity.OrderItem;
import com.ju.utils.AliOssUtils;
import com.ju.utils.FileItem;
@Service
public class PayServiceImpl implements PayService{
	//配置日志
	Logger log= LoggerFactory.getLogger(PayServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private AliOssUtils aliOssUtils;
	
	
	@Override
	public JSONData pay(Integer userId, Long orderNo, String path) {
		//3.返回：订单和二维码
		Map<String,String> map = new HashMap<String,String>();
		//1.判断订单是否存在
		Order order = orderDao.selOrderByUserIdAndOrderNo(userId, orderNo);
		if(null==order)
			return JSONData.buildError("没有此订单");
		//2.订单的相关信息发送到沙箱环境（链接，二维码）
		String er_path = this.test_trade_precreate(order, userId, path);//显示在web页面
		
		//3.返回
		map.put("orderNo", order.getOrderNo()+"");
		map.put("erpath", er_path);
		
		return JSONData.buildSuccess(map);
		
	}
	//订单信息二维
	 // 测试当面付2.0生成支付二维码
    public String test_trade_precreate(Order order,Integer userId,String path) {
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = order.getOrderNo()+"";

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "xxx品牌xxx门店当面付扫码消费,订单号为："+order.getOrderNo();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPayment();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "总金额为："+order.getPayment().toString();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        	/**
        	 * 查询订单详情OrderItem
        	 */
        String upload="";
        List<OrderItem> orderItemList = orderItemDao.queryOrderItemsByUserIdAndOrder(userId, order.getOrderNo());
        for (OrderItem orderItem : orderItemList) {
        	GoodsDetail goods=GoodsDetail.newInstance(
        			orderItem.getProductId()+"",
        			orderItem.getProductName(),
        			orderItem.getCurrentUnitPrice(),
        			orderItem.getQuantity()
        			);
        	orderItemList.add(goods);
		}
        
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
            .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
            .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
            .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
            .setTimeoutExpress(timeoutExpress)
            //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
            	.setNotifyUrl("http://www.test-notify-url.com/callback")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
            .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);
                //判断路径是否存在
                File pathFile = new File(path);
                if(!pathFile.exists()){
                	pathFile.setWritable(true);
                	pathFile.mkdirs();
                }

                // 需要修改为运行机器上的路径(**二维码的生成)
                //String filePath = String.format("/Users/sudo/Desktop/qr-%s.png",response.getOutTradeNo());
                String filePath = String.format(path+"/qr-%s.png",response.getOutTradeNo());
                String qrFileName=String.format("/qr-%s.png",response.getOutTradeNo())
                log.info("filePath:" + filePath);
                //                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                //图片存储
                File targetFile=new File(path,qrFileName);
                //上传到阿里云
                //file-fileitem-multipartfile
                FileItem fileItem=FileUtils.creatFileItem(targetFile,targetFile.getName());
                MultipartFile multipartFile=new CommonsMultipartFile(fileItem);
                //二维码路径
                upload=aliOssUtils.upload(multipartFile);
                
                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return upload;
    }
    public JSONData callback(Map<String, String> params,Integer userId){
    	String orderNo = params.get("out_trade_no");//商户的订单号
    	Order order = orderDao.selOrderByUserIdAndOrderNo(userId, Long.parseLong(orderNo));
    	if(null==order)
    		return JSONData.buildError("没有订单信息，处理失败");
    	String tradeStatus = params.get("trade_status");
    	if("TRADE_SUCCESS".equals(tradeStatus)){
    		order.setStatus(20);//-20已付款
    		int num = orderDao.updateOrderStatusById(order);
    		if(num<0)
    			return JSONData.buildSuccess("修改失败");
    		//支付信息存储payInfo
    		//PayInfo p=new PayInfo();去set,payInfoDao.insert(p) ;return 插入成功
    		
    	}
		return null;
    }

}
