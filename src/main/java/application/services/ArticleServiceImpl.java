package application.services;

import application.converters.DailyMailRssItemToArticleDtoConverter;
import application.domain.DailyMailRssFeedRepository;
import application.domain.DailyMailRssItem;
import application.exceptions.ArticleNotFoundException;
import application.web.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Harry on 13/03/2016.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private DailyMailRssFeedRepository dailyMailRssFeedRepository;
    @Autowired
    private DailyMailRssItemToArticleDtoConverter converter;

    //region Public Methods

    @Override
    public ArticleDto getArticleById(String id) {
        Optional<DailyMailRssItem> result = this.dailyMailRssFeedRepository.findById(id);

        if (result.isPresent()){
            ArticleDto articleDto = this.converter.convert(result.get());

            return articleDto;
        }

        throw new ArticleNotFoundException("Could not find article with id: " + id);
    }

    @Override
    public Page<ArticleDto> searchForArticleByText(String searchTerm, Pageable pageable) {
        Page<DailyMailRssItem> searchResult = this.dailyMailRssFeedRepository.findByTitleContainingIgnoringCaseOrDescriptionContainingIgnoringCase(searchTerm, searchTerm, pageable);

        List<DailyMailRssItem> content = searchResult.getContent();

        List<ArticleDto> articleDtos = new ArrayList<>();
        for (DailyMailRssItem dailyMailRssItem : content) {
            ArticleDto articleDto = this.converter.convert(dailyMailRssItem);
            articleDtos.add(articleDto);
        }

        return new PageImpl<ArticleDto>(articleDtos, pageable, searchResult.getTotalElements());
    }

    @Override
    public Page<ArticleDto> getAllArticles(Pageable pageable) {

        Page<DailyMailRssItem> all = this.dailyMailRssFeedRepository.findAll(pageable);

        List<DailyMailRssItem> content = all.getContent();

        List<ArticleDto> articleDtos = new ArrayList<>();
        for (DailyMailRssItem dailyMailRssItem : content) {
            ArticleDto articleDto = this.converter.convert(dailyMailRssItem);
            articleDtos.add(articleDto);
        }

        return new PageImpl<ArticleDto>(articleDtos, pageable, all.getTotalElements());
    }


    //endregion

    //region Private Methods

    //endregion

}
