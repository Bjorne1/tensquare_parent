package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/10 10:56
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR, "熔断器启动了");
    }
}
