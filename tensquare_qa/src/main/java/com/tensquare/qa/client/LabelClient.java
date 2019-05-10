package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/10 8:58
 */
@FeignClient(value = "tensquare-base", fallback = LabelClientImpl.class)
public interface LabelClient {

    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable("id") String id);
}
