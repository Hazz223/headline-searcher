package application.crawlers;

import application.crawlers.dailymail.DailyMailCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Harry on 13/03/2016.
 */
@Service
public class CrawlerStartService {

    private DailyMailCrawler dailyMailCrawler;

    @Autowired
    public CrawlerStartService(DailyMailCrawler dailyMailCrawler) {
        this.dailyMailCrawler = dailyMailCrawler;
    }

    public void startCrawler() throws IOException {

        this.dailyMailCrawler.StartCrawl();
    }
}
