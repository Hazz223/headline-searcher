package application.web;

import application.services.ArticleService;
import application.web.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Harry on 13/03/2016.
 */
@RestController
public class ArticlesController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/article/{id}")
    public ArticleDto findById(@PathVariable("id") String id) {

        return this.articleService.getArticleById(id);
    }

    @RequestMapping("/article")
    public Page<ArticleDto> searchForAricles(@RequestParam(value = "searchTerm", required = false) String searchTerm, Pageable pageable) {

        Page<ArticleDto> result;

        if(searchTerm == null) {
            result = articleService.getAllArticles(pageable);
        } else {

            result = this.articleService.searchForArticleByText(searchTerm, pageable);
        }

        return result;
    }
}
