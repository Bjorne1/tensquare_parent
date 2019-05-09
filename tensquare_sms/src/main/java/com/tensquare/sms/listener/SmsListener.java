package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 短信监听类
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/9 11:46
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    //模板编号
    @Value("${aliyun.sms.template_code}")
    private String template_code;

    //签名
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    /**
     * 发送短信
     *
     * @param map
     */
    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        System.out.println("手机号：" + map.get("mobile"));
        System.out.println("验证码：" + map.get("code"));
        try {
            smsUtil.sendSms(map.get("mobile"), template_code, sign_name, "number:" + map.get("code") + "");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
