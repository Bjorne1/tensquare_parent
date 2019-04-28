package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: wenchangsheng
 * @date: created in 2019/4/25
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findById(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentid(parentId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result thumbUp(@PathVariable String id) {
        String userId = "123";
        if (redisTemplate.opsForValue().get("thumbup_" + userId) != null) {
            return new Result(false, StatusCode.OK, "点赞失败");
        }

        spitService.thumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + userId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
