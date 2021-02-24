package com.gcnbl.dao;

import com.gcnbl.beans.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Long> {
}
