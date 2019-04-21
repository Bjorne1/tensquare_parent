package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    @Query(value = "SELECT * FROM tb_pl a,tb_problem b WHERE a.problemid=b.id AND a.labelid=?1 ORDER BY updatetime", nativeQuery = true)
    List<Problem> newList(String labelId, Pageable pageable);

    @Query(value = "SELECT * FROM tb_pl a,tb_problem b WHERE a.problemid=b.id AND a.labelid=?1", nativeQuery = true)
    List<Problem> hotList(String labelId, Pageable pageable);

    @Query("")
    List<Problem> waitList(String labelId, Pageable pageable);
}
