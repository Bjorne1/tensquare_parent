package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: wenchangsheng
 * @date: created in 2019/4/25
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
