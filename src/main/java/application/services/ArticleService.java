package application.services;

import application.web.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Harry on 13/03/2016.
 */
public interface ArticleService {

    ArticleDto getArticleById(String id);

    Page<ArticleDto> searchForArticleByText(String searchTerm, Pageable pageable);

    Page<ArticleDto> getAllArticles(Pageable pageable);

}
