package com.gcnbl.dao;

import com.gcnbl.bean.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Long> {
}
