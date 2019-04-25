package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: wenchangsheng
 * @date: created in 2019/4/25
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
