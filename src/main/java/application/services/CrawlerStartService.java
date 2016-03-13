package application.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by Harry on 13/03/2016.
 */
@Component
public class CrawlerStartService {

    private String dailyMailUrl = "http://www.dailymail.co.uk/home/article-2684527/RSS-Feeds.html";

    @PostConstruct
    public void startCrawler() throws IOException {


        Document rssDocument = Jsoup.connect(dailyMailUrl).get();


        Elements rssButtons = rssDocument.getElementsByClass("rss");

        for (Element rssButton : rssButtons) {
            String uri = rssButton.children().first().attr("href");
            String rssTitle = rssButton.parent().getElementsByClass("rss-title").first().text();

            break;
        }

        // I need to load that into a database!

    }

}
