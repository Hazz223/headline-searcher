package application.services.dailymail;

import application.domain.RssUri;
import application.domain.RssUriRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 13/03/2016.
 */
@Component
public class DailyMailCrawlerImpl implements DailyMailCrawler {

    private RssUriRepository rssUriRepository;
    private String dailyMailUrl = "http://www.dailymail.co.uk/home/article-2684527/RSS-Feeds.html";

    //region Public Methods

    @Autowired
    public DailyMailCrawlerImpl(RssUriRepository rssUriRepository) {
        this.rssUriRepository = rssUriRepository;
    }

    @Override
    public void StartCrawl() {

        if (hasDownloadedRssFeeds()) {
            System.out.println("Has already gotten the RSS uris today. Will get them out of DB instead.");
        } else {

            try {
                this.rssUriCrawl();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Now i need to crawl the RSS feeds themselves. Woo!

        }
    }

    //endregion

    //region Private Methods

    private void rssUriCrawl() throws IOException {

        Document rssDocument = Jsoup.connect(dailyMailUrl).get();
        Elements rssButtons = rssDocument.getElementsByClass("rss");

        List<RssUri> rssUris = new ArrayList<>();

        for (Element rssButton : rssButtons) {

            String uri = rssButton.children().first().attr("href");
            String rssTitle = rssButton.parent().getElementsByClass("rss-title").first().text();

            rssUris.add(new RssUri(rssTitle, uri, new Date()));
        }

        this.rssUriRepository.save(rssUris);
    }

    private Boolean hasDownloadedRssFeeds() {

        PageRequest request = new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "insertDate"));
        Page<RssUri> page = this.rssUriRepository.findAll(request);

        return page.getTotalElements() > 0;
    }

    //endregion

}
