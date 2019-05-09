package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/9 10:09
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {

    /**
     * 检索
     *
     * @param title,content
     * @return Article
     */
    Page<Article> findByTitleOrContentLike(String title, String
            content, Pageable pageable);
}
