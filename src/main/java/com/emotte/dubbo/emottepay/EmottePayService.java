package com.emotte.dubbo.emottepay;

public interface EmottePayService {

	/**
     * 联动主扫生成支付二维码Dubbo接口
     *
     * @param json
     *          {"payFeeId":"123456789","payMode":"WX"}
     *          WX： 微信扫码支付
     *          AL： 支付宝扫码支付
     *
     * @return json
     *          {"code":"1","msg":"失败原因"} {"code":"0","msg":"成功","url":"[图片]http://www.baidu.com"}
     */
    String lianDongZhuSaoPay(String json);
}
