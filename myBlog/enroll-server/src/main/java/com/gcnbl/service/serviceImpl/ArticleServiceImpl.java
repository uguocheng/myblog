package com.gcnbl.service.serviceImpl;

import com.gcnbl.bean.Article;
import com.gcnbl.dao.ArticleDao;
import com.gcnbl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public int addArticle(String content) {
        Article article = new Article();
        article.setContent(content);

        Article article1 = articleDao.save(article);

        if (article1 != null) {
            return 1;
        }
        return 0;
    }
}
