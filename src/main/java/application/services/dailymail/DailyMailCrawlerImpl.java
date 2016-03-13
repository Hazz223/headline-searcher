package application.services.dailymail;

import application.domain.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 13/03/2016.
 */
@Component
public class DailyMailCrawlerImpl implements DailyMailCrawler {

    private RssUriRepository rssUriRepository;
    private DailyMailRssFeedRepository dailyMailRssFeedRepository;
    private String dailyMailUrl = "http://www.dailymail.co.uk/home/article-2684527/RSS-Feeds.html";

    //region Public Methods


    @Autowired
    public DailyMailCrawlerImpl(DailyMailRssFeedRepository dailyMailRssFeedRepository, RssUriRepository rssUriRepository) {
        this.dailyMailRssFeedRepository = dailyMailRssFeedRepository;
        this.rssUriRepository = rssUriRepository;
    }

    @Override
    public void StartCrawl() {

        List<RssUri> rssUris = new ArrayList<>();

        if (hasDownloadedRssFeeds()) {
            System.out.println("Has already gotten the RSS uris today. Will get them out of DB instead.");
            rssUris = this.rssUriRepository.findAll();
        } else {

            try {
                rssUris = this.rssUriCrawl();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            this.downloadRssData(rssUris);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //endregion

    //region Private Methods

    private void downloadRssData(List<RssUri> restUris) throws IOException {

        RssUri articles = restUris.get(0); // there is a lot of data. Currently only grabbing the first reference.

        Document document = Jsoup.connect(articles.getUri()).get();

        List<DailyMailRssItem> dailyMailRssItems = new ArrayList<>();

       for (Element item : document.getElementsByTag("item")) {

            String title = item.getElementsByTag("title").text();
            String link = item.getElementsByTag("link").text();
            String description = item.getElementsByTag("description").text();
            String image = item.getElementsByTag("enclosure").attr("url");
            String pubDate = item.getElementsByTag("pubDate").text();
            String credit = item.getElementsByTag("media:credit").text();

            LocalDateTime parsed = LocalDateTime.parse(pubDate, DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss +0000"));

            DailyMailRssItem dailyMailRssItem = DailyMailRssItemBuilder.aDailyMailRssItem()
                    .withTitle(title)
                    .withDescription(description)
                    .withLink(link)
                    .withImage(image)
                    .withCredit(credit)
                    .withPublicationDate(parsed)
                    .build();

            dailyMailRssItems.add(dailyMailRssItem);
        }

        this.dailyMailRssFeedRepository.save(dailyMailRssItems);
    }

    private List<RssUri> rssUriCrawl() throws IOException {

        this.rssUriRepository.deleteAll();

        Document rssDocument = Jsoup.connect(dailyMailUrl).get();
        Elements rssButtons = rssDocument.getElementsByClass("rss");

        List<RssUri> rssUris = new ArrayList<>();

        for (Element rssButton : rssButtons) {

            String uri = rssButton.children().first().attr("href");
            String rssTitle = rssButton.parent().getElementsByClass("rss-title").first().text();

            rssUris.add(new RssUri(rssTitle, uri, new Date()));
        }

        this.rssUriRepository.save(rssUris);

        return rssUris;
    }

    private Boolean hasDownloadedRssFeeds() {

        PageRequest request = new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "insertDate"));
        Page<RssUri> page = this.rssUriRepository.findAll(request);

        return page.getTotalElements() > 0;
    }

    //endregion

}
