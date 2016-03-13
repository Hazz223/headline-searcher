package application.domain;

import java.time.LocalDateTime;

/**
 * Created by Harry on 13/03/2016.
 */
public class DailyMailRssItemBuilder {
    private String title;
    private String link;
    private String description;
    private LocalDateTime publicationDate;
    private String image;
    private String credit;

    private DailyMailRssItemBuilder() {
    }

    public static DailyMailRssItemBuilder aDailyMailRssItem() {
        return new DailyMailRssItemBuilder();
    }

    public DailyMailRssItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public DailyMailRssItemBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public DailyMailRssItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public DailyMailRssItemBuilder withPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public DailyMailRssItemBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public DailyMailRssItemBuilder withCredit(String credit) {
        this.credit = credit;
        return this;
    }

    public DailyMailRssItemBuilder but() {
        return aDailyMailRssItem().withTitle(title).withLink(link).withDescription(description).withPublicationDate(publicationDate).withImage(image).withCredit(credit);
    }

    public DailyMailRssItem build() {
        DailyMailRssItem dailyMailRssItem = new DailyMailRssItem();
        dailyMailRssItem.setTitle(title);
        dailyMailRssItem.setLink(link);
        dailyMailRssItem.setDescription(description);
        dailyMailRssItem.setPublicationDate(publicationDate);
        dailyMailRssItem.setImage(image);
        dailyMailRssItem.setCredit(credit);
        return dailyMailRssItem;
    }

    //region Public Methods

    //endregion

    //region Private Methods

    //endregion

}
