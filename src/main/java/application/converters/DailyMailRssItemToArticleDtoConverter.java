package application.converters;

import application.domain.DailyMailRssItem;
import application.web.dto.ArticleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Harry on 13/03/2016.
 */
@Component
public class DailyMailRssItemToArticleDtoConverter implements Converter<DailyMailRssItem, ArticleDto> {

    //region Public Methods

    @Override
    public ArticleDto convert(DailyMailRssItem source) {

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(source.getId());
        articleDto.setDescription(source.getDescription());
        articleDto.setCredit(source.getCredit());
        articleDto.setImage(source.getImage());
        articleDto.setLink(source.getLink());
        articleDto.setPublicationDate(source.getPublicationDate());
        articleDto.setTitle(source.getTitle());

        return articleDto;
    }

    //endregion

    //region Private Methods

    //endregion

}
