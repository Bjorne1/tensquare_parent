package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wenchangsheng
 * @date: created in 2019/4/20
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(createSpecification(label));
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Specification<Label> specification = createSpecification(label);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(specification, pageRequest);
    }

    /**
     * 构建查询条件
     */
    private Specification<Label> createSpecification(Label label) {
        return (Specification<Label>) (root, criteriaQuery, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(label.getLabelname())) {
                predicateList.add(cb.like(
                        root.get("labelname").as(String.class), "%" +
                                label.getLabelname() + "%"));
            }
            if (!StringUtils.isEmpty(label.getState())) {
                predicateList.add(cb.equal(
                        root.get("state").as(String.class), label.getState()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }


}
